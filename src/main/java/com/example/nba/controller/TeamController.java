package com.example.nba.controller;

import com.example.nba.service.TeamService;

import java.util.List;

import com.example.nba.model.NBATeam;

public class TeamController {
    
    public TeamService ts;

    public TeamController(TeamService ts){
        this.ts = ts;
    }

    public void add(String name, Integer conferenceId){
        NBATeam team = new NBATeam(name, conferenceId);
        ts.add(team);
    }

    public NBATeam getById(Integer id){
        return ts.getById(id);
    }
    public List<NBATeam> getAll(){
        return ts.getAll();
    }
    public NBATeam getByName(String name){
        return ts.getByName(name);
    }
    public void delete(Integer id){
        ts.delete(id);
    }
}
