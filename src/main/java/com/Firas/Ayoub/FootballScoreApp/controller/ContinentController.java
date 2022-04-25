package com.Firas.Ayoub.FootballScoreApp.controller;



import com.Firas.Ayoub.FootballScoreApp.entities.Continent;
import com.Firas.Ayoub.FootballScoreApp.repositories.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping( value = "/continent" )
public class ContinentController {

    @Autowired
    private ContinentRepository continentRepository;

    @PostMapping( value = "/addContinent" )
    public String addContinent(@ModelAttribute("continent") Continent continent ) {

        continentRepository.save( continent );

        return "redirect:/continent/listContinent";

    }

    @GetMapping( value = "/addContinent" )
    public String continentForm() {

        return "addContinent";

    }

    @GetMapping( value = "/listContinent" )
    public String displayContinent( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<Continent> pageContinents = continentRepository.findAll( PageRequest.of( p, s ) );
        model.addAttribute( "listContinent", pageContinents.getContent() );
        int[] pages = new int[pageContinents.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listContinent";

    }

    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        continentRepository.deleteById( id );

        return "redirect:/continent/listContinent";

    }

    @GetMapping( value = "/edit" )
    public String edit( Model model, Long id ) {

        Continent updatedContinent = continentRepository.findById( id ).orElse( null );
        model.addAttribute( "continent", updatedContinent );
        return "editContinent";
    }

}
