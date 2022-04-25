package com.Firas.Ayoub.FootballScoreApp.controller;

import com.Firas.Ayoub.FootballScoreApp.Services.FootService;
import com.Firas.Ayoub.FootballScoreApp.entities.Club;
import com.Firas.Ayoub.FootballScoreApp.entities.League;
import com.Firas.Ayoub.FootballScoreApp.entities.Matche;
import com.Firas.Ayoub.FootballScoreApp.repositories.ClubRepository;
import com.Firas.Ayoub.FootballScoreApp.repositories.LeagueRepository;
import com.Firas.Ayoub.FootballScoreApp.repositories.MatcheRepository;
import com.Firas.Ayoub.FootballScoreApp.repositories.StadiumRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping( value = "/matche" )
public class MatcheController {

    @Autowired
    MatcheRepository matcheRepository;
    @Autowired
    LeagueRepository leagueRepository;
    @Autowired
    StadiumRepository stadiumRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    FootService footService;

    @GetMapping( value = "/addMatche" )
    public String matcheForm( Model model ) {

        model.addAttribute( "stadiumList", stadiumRepository.findAll() );
        model.addAttribute( "leagueList", leagueRepository.findAll() );
        model.addAttribute( "clubList", clubRepository.findAll() );

        return "addMatche";
    }

    @PostMapping( value = "/addMatche" )
    public String addMatche(@ModelAttribute("matche") Matche matche) {

        footService.goalsClub( matche );

        matche.getHome_team().getMatchs().add( matche );
        matche.getAway_team().getMatchs().add( matche );
        matche.setStadium( matche.getHome_team().getStadium() );

        matcheRepository.save( matche );

        return "redirect:/matche/listMatche";
    }

    @GetMapping( value = "/listMatche" )
    public String displayMatche( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<Matche> pageMatches = matcheRepository.findAll( PageRequest.of( p, s ) );
        model.addAttribute( "listMatche", pageMatches.getContent() );
        int[] pages = new int[pageMatches.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listMatche";

    }

    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        matcheRepository.deleteById( id );

        return "redirect:/matche/listMatche";

    }

    @GetMapping( value = "/results" )

    public String results( Long id, Model model ) {

        League targetLeague = leagueRepository.findById( id ).orElse( null );
        List<Matche> targetMatche = matcheRepository.findByLeagueOrderByRoundDesc( targetLeague );
        model.addAttribute( "matcheList", targetMatche );
        model.addAttribute( "league", targetLeague );

        return "results";
    }

    @GetMapping( value = "/standings" )

    public String standings( Long id, Model model ) {
        League targetLeague = leagueRepository.findById( id ).orElse( null );
        List<Club> targetClub = clubRepository.findAllOrderByPoints( targetLeague );
        List<Club> targetClubHome = clubRepository.findHomeOrderByPoints( targetLeague );
        List<Club> targetClubAway = clubRepository.findAwayOrderByPoints( targetLeague );
        List<Club> targetGoalsFor = clubRepository.findAllOrderByGoalsFor( targetLeague );
        List<Club> targetGoalsAgainst = clubRepository.findAllOrderByGoalsAgainst( targetLeague );

        model.addAttribute( "clubList", targetClub );
        model.addAttribute( "clubListHome", targetClubHome );
        model.addAttribute( "clubListAway", targetClubAway );
        model.addAttribute( "clubListGoalsFor", targetGoalsFor );
        model.addAttribute( "clubListGoalsAgainst", targetGoalsAgainst );
        model.addAttribute( "league", targetLeague );

        return "standings";
    }
}
