package nba.controller;

import nba.model.*;

import java.util.List;

import nba.service.GeneralService;

public class GeneralController{

    private GeneralService service;

    public GeneralController(GeneralService service) {
    }

    public void addPlayer(String name, int age, double salary, String position){
        Player player = new NBAPlayer(name, age, salary, position);
        service.addPlayer(player);
        System.out.println("Player " + name + " added to the list");
    }
    public void addTeam(String name, int id, Manager manager){
        NBATeam team = new NBATeam(id, name,manager);
        service.addTeam(team);
        System.out.println("Team " + name + " added to the list");

    }
    public NBATeam getTeamByID(int id){
        NBATeam team = service.getTeamById(id);
        if(team != null){
            System.out.println("Team " + id + " found in the list");
        }
        else {
            System.out.println("Team " + id + " not found in the list");
        }
        return team;
    }
    public List<NBATeam> getAllTeams(){
        return service.getAllTeams();
    }
    public void addManager(String name, int id, NBATeam team){
        Manager manager = new Manager(name,team);
        service.addManager(manager);
        System.out.println("Manager " + name + " added to the list");
    }

    public Manager getTeamManager(String teamName){
        Manager manager = service.getTeamManager(teamName);
        if(manager != null){
            System.out.println("Manager " + teamName + " found in the list");
        }
        else {
            System.out.println("Manager " + teamName + " not found in the list");
        }
        return manager;
    }
    public Manager getManagerOfPlayer(int playerId) {
        return service.getManagerOfPlayer(playerId);
    }
    public List<NBAPlayer> getAllPlayersPerTeam(NBATeam team){
        return service.getAllPlayersPerTeam(team);
    }
    public void addGame(Game game){
        service.addGame(game);
        System.out.println("Game added to the list");
    }
    public void addSponsor(Sponsor sponsor){
        service.addSponsor(sponsor);
        System.out.println("Sponsor added to the list");
    }
}