package com.example.nba.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.nba.error.AlreadyExistingException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.NBAPlayer;
import com.example.nba.model.NBATeam;
import com.example.nba.repos.RepoDB;

public class PlayerS {
    private final Repo<NBAPlayer> rp;
    private final Repo<NBATeam> rt;
    private final boolean isQ;
    
    public PlayerS(Repo<NBAPlayer> rp, Repo<NBATeam> rt){
        this.rp = rp;
        this.rt = rt;
        if(rp instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(NBAPlayer player)throws InexistenteInstance{
        // Check for the integrity of the foreign key
        NBATeam t = rt.get(player.getTeamId());
        if(t == null)
            throw new InexistenteInstance("The Team assigned to the player is null. Foreign key violation...");
        
        NBAPlayer p = rp.get(player.getId());
        if(p != null)
            throw new AlreadyExistingException("The Player id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rp.add(player);
    }

    public void delete(Integer id){
        rp.delete(id);
    }

    public NBAPlayer getById(Integer id ){
        return rp.get(id);
    }
    public List<NBAPlayer> getAll(){
        return rp.getAll();
    }

    public List<NBAPlayer> sortByAge(){
        if(isQ){
            //use the function in the repoDB
            return null;
        }else{
            List<NBAPlayer> players = rp.getAll();
            return players.stream().sorted((NBAPlayer p1, NBAPlayer p2) -> Integer.compare(p1.getAge(), p2.getAge())).collect(Collectors.toList());
        }
    }


}
