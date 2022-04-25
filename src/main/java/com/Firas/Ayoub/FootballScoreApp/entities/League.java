package com.Firas.Ayoub.FootballScoreApp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class League implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long         id_league;

    @Column( unique = true )
    private String       name_league;

    private int          nbr_club;

    private String       country;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "continent_id" )
    private Continent    continent;

    /*
     * FetchType.LAZY is on demand (i.e. when we required the data).
     * 
     * FetchType.EAGER is immediate (i.e. before our requirement comes we are
     * unnecessarily fetching the record)
     */

    @OneToMany( mappedBy = "league", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Club>   clubs;

    @OneToMany( mappedBy = "league", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Matche> matchs;

    public League() {
        super();
    }

    public League(String name_league, int nbr_club,
            String country ) {
        super();
        this.name_league = name_league;
        this.nbr_club = nbr_club;
        this.country = country;
    }

    public League(String name_league, int nbr_club,
            String country, Continent continent ) {
        super();
        this.name_league = name_league;
        this.nbr_club = nbr_club;
        this.country = country;
        this.continent = continent;
    }

    public Long getId_league() {
        return id_league;
    }

    public void setId_league( Long id_league ) {
        this.id_league = id_league;
    }

    public String getName_league() {
        return name_league;
    }

    public void setName_league( String name_league ) {
        this.name_league = name_league;
    }

    public int getNbr_club() {
        return nbr_club;
    }

    public void setNbr_club( int nbr_club ) {
        this.nbr_club = nbr_club;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent( Continent continent ) {
        this.continent = continent;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs( List<Club> clubs ) {
        this.clubs = clubs;
    }

    public List<Matche> getMatchs() {
        return matchs;
    }

    public void setMatchs( List<Matche> matchs ) {
        this.matchs = matchs;
    }

}
