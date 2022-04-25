package com.Firas.Ayoub.FootballScoreApp.repositories;

import javax.transaction.Transactional;

import com.Firas.Ayoub.FootballScoreApp.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;



@Transactional
public interface LeagueRepository extends JpaRepository<League, Long> {

}
