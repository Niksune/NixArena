package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.FightingReport;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FightingReportRepositoryInterface extends JpaRepository<FightingReport,Integer> {

}
