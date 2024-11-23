package nba.service;

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

    public NBAPlayer getByName(String name){
        List<NBAPlayer> players = repP.getAll();
        for(NBAPlayer player: players){
            if(name.equals(player.getName()))
                return player;
        }
        return null;
    }

    public void delete(Integer id)throws IdOutOfRangeException{
        if(id >= NBAPlayer.getMaxId())
            throw new IdOutOfRangeException("\033[31mId out of bound exception for the Delete_Player transaction...\033[0m");
        repP.delete(id);
    }
}
