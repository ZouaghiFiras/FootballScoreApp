package com.Firas.Ayoub.FootballScoreApp.repositories;

import javax.transaction.Transactional;

import com.Firas.Ayoub.FootballScoreApp.entities.Athletic;
import org.springframework.data.jpa.repository.JpaRepository;



@Transactional
public interface AthleticRepository extends JpaRepository<Athletic, Long> {

}
