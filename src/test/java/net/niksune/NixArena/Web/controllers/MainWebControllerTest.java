package net.niksune.NixArena.Web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.repositories.*;
import net.niksune.NixArena.Web.services.CharacService;
import net.niksune.NixArena.Web.services.FightOrganizerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MainWebController.class)
class MainWebControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    AccountRepositoryInterface accountRepositoryInterface;
    @MockBean
    CharacRepositoryInterface characRepositoryInterface;
    @MockBean
    private WeaponRepositoryInterface weaponRepositoryInterface;

    @MockBean
    private AccountRepositoryService accountRepositoryService;
    @MockBean
    private CharacRepositoryService characRepositoryService;

    @MockBean
    private CharacService characService;

    @MockBean
    private FightOrganizerService fightOrganizerService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    Account ACCOUNT_1 = new Account(UUID.randomUUID(),"Test");
    Account ACCOUNT_2 = new Account(UUID.randomUUID(),"New User");

    @Test
    //@GetMapping("/allAccountsInfos")
    public void getAllAccountsInfos_success() throws Exception {

        List<Account> records = new ArrayList<>(Arrays.asList(ACCOUNT_1,ACCOUNT_2));

        Mockito.when(accountRepositoryInterface.findAllInfosBy()).thenReturn(records);

        System.out.println(ACCOUNT_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/API/allAccountsInfos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("New User")));
    }


}