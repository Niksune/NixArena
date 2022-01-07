package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepositoryInterface extends CrudRepository<Account,Integer> {
}
