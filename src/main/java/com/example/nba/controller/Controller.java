package com.example.nba.controller;

import com.example.nba.error.AttributeFaultedException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.*;
import com.example.nba.services.*;

import java.util.List;

public class Controller {

    private final FoundsS foundsService;
    private final GameS gameService;
    private final PlayerS playerService;
    private final ManagerS managerService;
    private final TeamS teamService;
    private final SeasonS seasonService;
    private final Security securityService;

    public Controller(FoundsS foundsService, GameS gameService, PlayerS playerService, ManagerS managerService, TeamS teamService, SeasonS seasonService, Security security) {
        this.foundsService = foundsService;
        this.gameService = gameService;
        this.playerService = playerService;
        this.managerService = managerService;
        this.teamService = teamService;
        this.seasonService = seasonService;
        this.securityService = security;
    }
    public void addFound(Found found) throws InexistenteInstance {foundsService.add(found);}
    public Found getFoundById(Integer id) {
        return foundsService.getById(id);
    }
    public List<Found> getAllFounds() {
        return foundsService.getAll();
    }
    public List<Found> getTeamFounds(String teamName) throws AttributeFaultedException {return foundsService.getTeamFounds(teamName);}
    public List<Found> getSponsorFounds(String sponsorName) {
        return foundsService.getSponsorFounds(sponsorName);
    }
    public void deleteFound(Integer id) {
        foundsService.delete(id);
    }
    public void addGame(Game game) throws InexistenteInstance {gameService.add(game);}
    public Game getGameById(Integer id) {
        return gameService.getById(id);
    }
    public List<Game> getAllGames() {
        return gameService.getAll();
    }
    public List<Game> sortGamesByDate() {
        return gameService.sortByDate();
    }
    public List<Game> getGamesPerTeam(String teamName) {
        return gameService.getGamesPerTeam(teamName);
    }
    public void deleteGame(Integer id) {
        gameService.delete(id);
    }
    public void addPlayer(NBAPlayer player) throws InexistenteInstance {playerService.add(player);}
    public NBAPlayer getPlayerById(Integer id) {
        return playerService.getById(id);
    }
    public List<NBAPlayer> getAllPlayers() {
        return playerService.getAll();
    }
    public List<NBAPlayer> sortPlayersByAge() {
        return playerService.sortByAge();
    }
    public List<NBAPlayer> sortPlayersBySalary() {
        return playerService.sortBySalary();
    }
    public List<NBAPlayer> filterPlayersByAgeInterval(int start, int end) {return playerService.filterByAgeInterval(start, end);}
    public List<NBAPlayer> filterPlayersBySalaryInterval(double start, double end) {return playerService.filterBySalaryInterval(start, end);}
    public List<NBAPlayer> getPlayersByName(String name) {
        return playerService.getByName(name);
    }
    public void deletePlayer(Integer id) {
        playerService.delete(id);
    }
    public void addManager(Manager manager) throws InexistenteInstance {managerService.add(manager);}
    public Manager getManagerById(Integer id) {return managerService.getById(id);}
    public List<Manager> getAllManagers() {return managerService.getAll();}
    public List<Manager> getManagersByName(String name) {return managerService.getByName(name);}
    public void deleteManager(Integer id) {managerService.delete(id);}
    public void addSeason(Season season) throws InexistenteInstance {seasonService.add(season);}
    public void deleteSeason(Integer id) {seasonService.delete(id);}
    public Season getSeasonById(Integer id) {return seasonService.getById(id);}
    public List<Season> getAllSeasons() {return seasonService.getAll();}
    public Season getSeasonByYear(int year) throws AttributeFaultedException {return seasonService.getByYear(year);}
    public Season getSeasonByName(String name) {return seasonService.getByName(name);}
    public boolean authenticateManager(String managerName, String password) {return securityService.auth(managerName, password);}
    public void addTeam(NBATeam team) throws InexistenteInstance {teamService.add(team);}
    public void deleteTeam(Integer id) {teamService.delete(id);}
    public NBATeam getTeamById(Integer id) {return teamService.getById(id);}
    public List<NBATeam> getAllTeams() {return teamService.getAll();}
    public List<NBATeam> getTeamsByConference(Integer conferenceId) {return teamService.getTheConferenceList(conferenceId);}
    public NBATeam getTeamByName(String name) {return teamService.getByName(name);}
}
