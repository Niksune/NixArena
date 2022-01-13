package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepositoryInterface extends CrudRepository<Account,Integer> {

    @Override
    @EntityGraph(value="graph.Account.characters",type= EntityGraph.EntityGraphType.FETCH)
    Iterable<Account> findAll();
}
