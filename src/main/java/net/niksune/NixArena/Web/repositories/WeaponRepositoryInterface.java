package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.beans.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepositoryInterface extends JpaRepository<Weapon,Integer> {

}
