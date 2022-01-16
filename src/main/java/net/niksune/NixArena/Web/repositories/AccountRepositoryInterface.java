package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryInterface extends JpaRepository<Account,Integer> {

    @EntityGraph(value="graph.Account.characAndWeapons",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllCompleteBy();

    @EntityGraph(value="graph.Account.characters",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllWithCharacsBy();

    @EntityGraph(value="graph.Account.onlyInfos",type= EntityGraph.EntityGraphType.FETCH)
    List<Account> findAllInfosBy();

    @EntityGraph(value="graph.Account.characAndWeapons",type= EntityGraph.EntityGraphType.FETCH)
    Optional<Account> findCompleteByID(int ID);

    @EntityGraph(value="graph.Account.onlyInfos",type= EntityGraph.EntityGraphType.FETCH)
    Optional<Account> findInfosByID(int ID);

}
