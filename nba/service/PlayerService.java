package nba.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import nba.error.IdOutOfRangeException;
import nba.error.InexistenteInstance;
import nba.model.NBAPlayer;
import nba.model.NBATeam;
import nba.repo.Repo;

public class PlayerService {
    
    Repo<NBAPlayer> repP;
    Repo<NBATeam> repT;
 
    public PlayerService(Repo<NBAPlayer> repP, Repo<NBATeam> repT){
        this.repP = repP;
        this.repT = repT;
        //init the MAX_ID
        repP.getAll();
    }

    public void add(NBAPlayer player){
        /*
         * Check Foreign_keys constraints
         */
        Integer teamId = player.getTeamId();
        if(repT.get(teamId) == null){
            throw new InexistenteInstance("\033[31mException raised in Add_Player transaction. Team_Foreign_Key violation, Team inexistante.\033[0m");
        }
        repP.add(player);
    }

    public NBAPlayer getById(Integer id){
        return repP.get(id);
    }

    public List<NBAPlayer> getAll(){
        return repP.getAll();
    }

    public List<NBAPlayer> getByName(String name){
        List<NBAPlayer> players = repP.getAll();
        List<NBAPlayer> res = new ArrayList<>();
        for(NBAPlayer player: players){
            if(name.equals(player.getName()))
                res.add(player);
        }
        return res.size() == 0 ? null : res;
    }

    public void delete(Integer id)throws IdOutOfRangeException{
        if(id >= NBAPlayer.getMaxId())
            throw new IdOutOfRangeException("\033[31mId out of bound exception for the Delete_Player transaction...\033[0m");
        repP.delete(id);
    }

    public List<NBAPlayer> sortByAge(){
        return repP.getAll().stream().sorted(Comparator.comparing(NBAPlayer::getAge)).toList();
    }
}
