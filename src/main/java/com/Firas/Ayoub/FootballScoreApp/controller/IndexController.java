package com.Firas.Ayoub.FootballScoreApp.controller;

import com.Firas.Ayoub.FootballScoreApp.repositories.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class IndexController {

    @Autowired
    LeagueRepository leagueRepository;

    @GetMapping( value = "/" )
    public String infosList( Model model ) {

        model.addAttribute( "leagueList", leagueRepository.findAll() );
        return "index";

    }

}
