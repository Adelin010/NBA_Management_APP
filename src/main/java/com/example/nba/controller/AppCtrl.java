package com.example.nba.controller;


import com.example.nba.error.AttributeFaultedException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.*;
import com.example.nba.services.*;

import java.util.List;

public class AppCtrl {

    private final FoundsS foundsService;
    private final GameS gameService;
    private final ManagerS managerService;
    private final PlayerS playerService;
    private final SeasonS seasonService;
    private  final SponsorS sponsorService;
    private final TeamS teamService;

    public AppCtrl(FoundsS foundsService, GameS gameService, ManagerS managerService, PlayerS playerService, SeasonS seasonService,SponsorS sponsorService, TeamS teamService) {
        this.foundsService = foundsService;
        this.gameService = gameService;
        this.managerService = managerService;
        this.playerService = playerService;
        this.seasonService = seasonService;
        this.sponsorService = sponsorService;
        this.teamService = teamService;
    }
    //Found related methods
    public void addFound(Found found) throws InexistenteInstance {foundsService.add(found);}
    public Found getFoundById(Integer id) {return foundsService.getById(id);}
    public List<Found> getAllFounds() {return foundsService.getAll();}
    public List<Found> getTeamFounds(String teamName) throws AttributeFaultedException {return foundsService.getTeamFounds(teamName);}
    public List<Found> getSponsorFounds(String sponsorName) {return foundsService.getSponsorFounds(sponsorName);}
    public void deleteFound(Integer id) {foundsService.delete(id);}
    //Game related methods
    public void addGame(Game game) throws InexistenteInstance {gameService.add(game);}
    public Game getGameById(Integer id) {return gameService.getById(id);}
    public List<Game> getAllGames() {return gameService.getAll();}
    public List<Game> sortGamesByDate() {return gameService.sortByDate();}
    public List<Game> getGamesPerTeam(String teamName) {return gameService.getGamesPerTeam(teamName);}
    public void deleteGame(Integer id) {gameService.delete(id);}
    //Manager related methods
    public void addManager(Manager manager) throws InexistenteInstance {managerService.add(manager);}
    public Manager getManagerById(Integer id) {return managerService.getById(id);}
    public List<Manager> getAllManagers() {return managerService.getAll();}
    public List<Manager> getManagerByName(String name) {return managerService.getByName(name);}
    public void deleteManager(Integer id) {managerService.delete(id);}
    //Player related methods
    public void addPlayer(NBAPlayer player) throws InexistenteInstance {playerService.add(player);}
    public NBAPlayer getPlayerById(Integer id) {return playerService.getById(id);}
    public List<NBAPlayer> getAllPlayers() {return playerService.getAll();}
    public List<NBAPlayer> sortPlayersByAge() {return playerService.sortByAge();}
    public List<NBAPlayer> sortPlayersBySalary() {return playerService.sortBySalary();}
    public List<NBAPlayer> filterPlayersByAge(int start, int end) {return playerService.filterByAgeInterval(start, end);}
    public List<NBAPlayer> filterPlayersBySalary(double start, double end) {return playerService.filterBySalaryInterval(start, end);}
    public List<NBAPlayer> getPlayerByName(String name) {return playerService.getByName(name);}
    public void deletePlayer(Integer id) {playerService.delete(id);}
    //Season related methods
    public void addSeason(Season season) throws InexistenteInstance {seasonService.add(season);}
    public Season getSeasonById(Integer id) {return seasonService.getById(id);}
    public List<Season> getAllSeasons() {return seasonService.getAll();}
    public Season getSeasonByYear(int year) throws AttributeFaultedException {return seasonService.getByYear(year);}
    public Season getSeasonByName(String name) {return seasonService.getByName(name);}
    public void deleteSeason(Integer id) {seasonService.delete(id);}
    //Sponsor related methods
    public void addSponsor(Sponsor sponsor) throws InexistenteInstance {sponsorService.add(sponsor);}
    public void deleteSponsor(Integer id) {sponsorService.delete(id);}
    public Sponsor getSponsorById(Integer id) {return sponsorService.getById(id);}
    public List<Sponsor> getAllSponsors() {return sponsorService.getAll();}
    public Sponsor getSponsorByName(String name) {return sponsorService.getByName(name);}
    public List<Sponsor> filterSponsorsByAge(int start, int end) {return sponsorService.filterByAgeInterval(start, end);}
    public List<Sponsor> getCompanySponsors() {return sponsorService.getTheCompanies();}
    //Teamrelated methods
    public void addTeam(NBATeam team) throws InexistenteInstance {teamService.add(team);}
    public void deleteTeam(Integer id) {teamService.delete(id);}
    public NBATeam getTeamById(Integer id) {return teamService.getById(id);}
    public List<NBATeam> getAllTeams() {return teamService.getAll();}
    public NBATeam getTeamByName(String name) {return teamService.getByName(name);}
    public List<NBATeam> getTeamsByConference(Integer conferenceId) {return teamService.getTheConferenceList(conferenceId);}
    public void updateTeam(NBATeam team) {teamService.update(team);}
}

