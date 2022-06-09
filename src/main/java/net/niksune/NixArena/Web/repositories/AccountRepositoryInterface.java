package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepositoryInterface extends JpaRepository<Account, UUID> {

    @EntityGraph(value="graph.Account.characsWithEquippedWeapon",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllCharacsWithEquippedWeaponBy();

    @EntityGraph(value="graph.Account.characters",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllWithCharacsBy();

    @EntityGraph(value="graph.Account.onlyInfos",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllInfosBy();

    //Implemented in AccountRepositoryService
    //Optional<Account> findCompleteByID(int ID);

    @EntityGraph(value="graph.Account.onlyInfos",type= EntityGraph.EntityGraphType.FETCH)
    Optional<Account> findInfosByID(UUID ID);

    @EntityGraph(value="graph.Account.weaponsStored",type= EntityGraph.EntityGraphType.FETCH)
    Optional<Account> findWithWeaponsStoredByID(UUID ID);

    boolean existsByName(String s);

    @EntityGraph(value="graph.Account.characters",type= EntityGraph.EntityGraphType.FETCH)
    Account getWithCharacsByName(String name);

    @Query("SELECT a FROM Account a WHERE a.name = :name AND a.password = :password")
    Optional<Account> findByNameAndPassword(String name, String password);
}
