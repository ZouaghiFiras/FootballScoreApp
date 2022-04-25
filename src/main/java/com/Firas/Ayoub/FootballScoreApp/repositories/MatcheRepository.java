package com.Firas.Ayoub.FootballScoreApp.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.Firas.Ayoub.FootballScoreApp.entities.League;
import com.Firas.Ayoub.FootballScoreApp.entities.Matche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Transactional
public interface MatcheRepository extends JpaRepository<Matche, Long> {

    @Query( "select m from Matche m where m.league=?1 order by m.round desc,m.date_match desc" )
    public List<Matche> findByLeagueOrderByRoundDesc( League league );

}
