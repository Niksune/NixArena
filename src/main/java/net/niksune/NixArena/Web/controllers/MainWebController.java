package net.niksune.NixArena.Web.controllers;

import net.niksune.NixArena.Core.beans.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainWebController {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Account> getAllAccounts() {
        return jdbcTemplate.query("SELECT * FROM account",
                (rs, rowNum) -> new Account(rs.getInt("ID"), rs.getString("Email"),rs.getString("Password")));
    }

}
