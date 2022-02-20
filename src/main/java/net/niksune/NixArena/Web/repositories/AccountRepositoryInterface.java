package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryInterface extends JpaRepository<Account,Integer> {

    @EntityGraph(value="graph.Account.characsWithEquippedWeapon",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllCharacsWithEquippedWeaponBy();

    @EntityGraph(value="graph.Account.characters",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllWithCharacsBy();

    @EntityGraph(value="graph.Account.onlyInfos",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllInfosBy();

    //Implemented in AccountRepositoryService
    //Optional<Account> findCompleteByID(int ID);

    @EntityGraph(value="graph.Account.onlyInfos",type= EntityGraph.EntityGraphType.FETCH)
    Optional<Account> findInfosByID(int ID);

    @EntityGraph(value="graph.Account.weaponsStored",type= EntityGraph.EntityGraphType.FETCH)
    Optional<Account> findWithWeaponsStoredByID(int ID);

    boolean existsByName(String s);

    @EntityGraph(value="graph.Account.characters",type= EntityGraph.EntityGraphType.FETCH)
    Account getWithCharacsByName(String name);
}
