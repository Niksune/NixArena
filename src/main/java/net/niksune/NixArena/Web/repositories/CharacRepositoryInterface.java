package net.niksune.NixArena.Web.repositories;

import net.niksune.NixArena.Web.beans.Charac;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacRepositoryInterface extends JpaRepository<Charac,Integer> {

    @EntityGraph(value="graph.Character.weapon",type= EntityGraph.EntityGraphType.FETCH)
    List<Charac> findAllWithWeaponBy();

    @EntityGraph(value="graph.Character.owner",type= EntityGraph.EntityGraphType.FETCH)
    List<Charac> findAllWithOwnerBy();
/*
    @EntityGraph(value="graph.Character.fightingReports",type= EntityGraph.EntityGraphType.FETCH)
    List<Charac> findAllWithFightingreportsBy();
*/
    List<Charac> findAllByOrderByLevelAsc();

    @EntityGraph(value="graph.Character.complete",type= EntityGraph.EntityGraphType.FETCH)
    Charac findCompleteByID(int ID);

}
