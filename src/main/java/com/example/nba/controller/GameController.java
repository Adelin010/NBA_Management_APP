package com.example.nba.controller;

import java.util.List;

import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.Game;
import com.example.nba.service.GameService;

public class GameController {
     public GameService gs;

    public GameController(GameService gs){
        this.gs = gs;
    }

    public void add(String date, int scoreTeam1, int scoreTeam2, Integer team1Id, Integer team2Id, String type)throws InexistenteInstance{
        Game player = new Game(date, scoreTeam1, scoreTeam2, team1Id, team2Id, type);
        gs.add(player);
    }

    public Game getById(Integer id){
        return gs.getById(id);
    }
    public List<Game> getAll(){
        return gs.getAll();
    }
    
    public void delete(Integer id){
        gs.delete(id);
    }
    public List<Game> sortByDate(){
        return gs.sortByDate();
    }
}