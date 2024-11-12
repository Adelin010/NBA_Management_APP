package nba.controller;

import java.util.List;

import nba.exceptions.AlreadyExistingException;
import nba.model.NBATeam;
import nba.service.TeamService;

public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    public void addTeam(int id, String name, int managerId){
        //The length of the team name must be al least 4 letters
        if(name.length() < 4){
            System.out.println("Length of the name insuficient, minimum 4 letters !!!");
            return;
        }
        NBATeam team = new NBATeam(id, name, null);
        try{
            teamService.addTeam(team, managerId);
        }catch(AlreadyExistingException exp){
            exp.printInfo();
        }
    }

    public NBATeam getTeamById(int id){
        return teamService.getTeamById(id);
    }

    public List<NBATeam> getAllTeams(){
        return teamService.getAllTeams();
    }

    public NBATeam getTeamByName(String name){
        if(name.length() < 4){
            System.out.println("Length of the name insuficient, minimum 4 letters !!!");
            return null;
        }
        NBATeam team = teamService.getTeamByName(name);
        if(team != null)
            System.out.println("FOUND");
        return team;
    }
}
