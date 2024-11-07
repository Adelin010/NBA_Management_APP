package nba.controller;

import nba.model.Player;

import java.util.List;

import nba.model.NBAPlayer;
import nba.model.NBATeam;
import nba.service.GeneralService;

public class GeneralController{

    private GeneralService service;

    //Get the input from the Console-App and 
    public void addPlayer(String name, int age, double salary, String position){
        //Create a new Player and added it into the Repo
        Player player = new NBAPlayer(name, age, salary, position);
        service.addPlayer(player);
    }

    
    public List<NBAPlayer> getAllPlayersPerTeam(NBATeam team){
        return service.getAllPlayersPerTeam(team);
    }
}