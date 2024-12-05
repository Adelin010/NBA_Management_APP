package com.example.nba.controller;

import java.util.List;

import com.example.nba.model.NBAPlayer;
import com.example.nba.service.PlayerService;

public class PlayerController {
    
    public PlayerService ps;

    public PlayerController(PlayerService ps){
        this.ps = ps;
    }
    /**
     * Adds a new NBA player
     * @param name  the name of the player
     * @param age   the age of the player
     * @param salary  the salary of the player
     * @param position the position of the player
     * @param points   the total points scored by the player
     * @param rebounds  the total rebounds made by the player
     * @param assists  the total assists made by the player
     * @param teamId   the ID of the team that the player is associated with
     */
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
