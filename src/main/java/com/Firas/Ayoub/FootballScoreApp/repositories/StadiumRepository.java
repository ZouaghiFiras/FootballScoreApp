package com.Firas.Ayoub.FootballScoreApp.repositories;

import javax.transaction.Transactional;

import com.Firas.Ayoub.FootballScoreApp.entities.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;



@Transactional
public interface StadiumRepository extends JpaRepository<Stadium, Long> {

}
