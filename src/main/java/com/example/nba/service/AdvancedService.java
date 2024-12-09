package com.example.nba.service;

import java.util.List;

import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.*;
import com.example.nba.repo.Repo;

public class AdvancedService {

    Repo<Game> rg;
    Repo<NBAPlayer> rp;
    Repo<Manager> rm;
    Repo<NBATeam> rt;


    public AdvancedService(Repo<Game> rg, Repo<NBAPlayer> rp, Repo<NBATeam> rt, Repo<Manager> rm){
        this.rg = rg;
        this.rp = rp;
        this.rm = rm;
        this.rt = rt;
    }

    
    public Manager managerWinningTeam(Integer gameId)throws InexistenteInstance{
        //Get the game
        Game game = rg.get(gameId);
        if(game == null)
            throw new InexistenteInstance("No Game existing for the managerWinningTeam to fetch...");
        
        System.out.println(game);
        //Get the winning team
        Integer teamId;
        {
            int[] scores = game.getScores();
            Integer[] teamIds = game.getTeamsId();
            teamId = scores[0] > scores[1] ? teamIds[0] : teamIds[1];
        }
        System.out.println("TeamId == " + teamId);
        //Search for the manager if the team has one
        List<Manager> managers = rm.getAll();
        for(Manager m: managers){
            if(m.getTeamId() == teamId)
                return m;
        }
        return null;
    }

    public List<NBAPlayer> nthBestPerTeam(){
        return null;
    }
}
