package nba.controller;

import java.util.List;

import nba.model.NBAPlayer;
import nba.service.PlayerService;

public class PlayerController {
    
    public PlayerService ps;

    public PlayerController(PlayerService ps){
        this.ps = ps;
    }

    public void add(String name, int age, double salary, String position, int points, int rebounds, int assists, Integer teamId){
        NBAPlayer player = new NBAPlayer(name, age, salary, position, points, rebounds, assists, teamId);
        ps.add(player);
    }

    public NBAPlayer getById(Integer id){
        return ps.getById(id);
    }
    public List<NBAPlayer> getAll(){
        return ps.getAll();
    }
    public List<NBAPlayer> getByName(String name){
        return ps.getByName(name);
    }
    public void delete(Integer id){
        ps.delete(id);
    }
    public List<NBAPlayer> sortByAge(){
        return ps.sortByAge();
    }
}
