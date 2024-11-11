package nba.controller;

import nba.model.*;

import java.util.List;

import nba.service.GeneralService;

public class GeneralController{

    private GeneralService service;

    public GeneralController(GeneralService service) {
        this.service = service;
    }

    public void addPlayer(int id, String name, int age, double salary, String position){
        Player player = new NBAPlayer(id, name, age, salary, position);
        service.addPlayer(player);
        System.out.println("Player added !");
    }

    public int hireManager(int teamId, int managerId){
        return service.changeTeamManager(teamId, managerId);
    }

    public NBAPlayer getNbaPlayerById(int id){
        return service.getPlayerById(id);
    }

    public List<NBAPlayer> getAllPlayersInTeam(int teamId){
        NBATeam team = service.getTeamById(teamId);
        return service.getAllPlayersPerTeam(team); 
    }

    public void addTeam(String name, int id, int  manId){
        Manager manager = service.getManagerById(manId);
        System.out.println(manager.toString());
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
    public void addManager(int id, String name, NBATeam team){
        Manager manager = new Manager(id,name,team);
        service.addManager(manager);
        System.out.println("Manager " + name + " added to the list");
    }

    public Manager getTeamManager(String teamName){
        Manager manager = service.getTeamManager(teamName);
        return manager;
    }

    public NBATeam getTeamByName(String name){
        return service.getTeamByTeamName(name);
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