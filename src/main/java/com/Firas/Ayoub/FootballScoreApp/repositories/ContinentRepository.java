package com.Firas.Ayoub.FootballScoreApp.repositories;

import javax.transaction.Transactional;

import com.Firas.Ayoub.FootballScoreApp.entities.Continent;
import org.springframework.data.jpa.repository.JpaRepository;



@Transactional
public interface ContinentRepository extends JpaRepository<Continent, Long> {

}
