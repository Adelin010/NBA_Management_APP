package com.example.nba.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.nba.error.IdOutOfRangeException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.NBAPlayer;
import com.example.nba.model.NBATeam;
import com.example.nba.repo.Repo;

public class PlayerService {
    
    Repo<NBAPlayer> repP;
    Repo<NBATeam> repT;
    /**
     * Constructor to initialize PlayerService with the specified repos
     * @param repP the repository for NBAPlayer
     * @param repT the repository for NBATeam
     */
 
    public PlayerService(Repo<NBAPlayer> repP, Repo<NBATeam> repT){
        this.repP = repP;
        this.repT = repT;
        //init the MAX_ID
        repP.getAll();
    }
    /**
     * Adds a new NBAPlayer to the repository
     * @param player the NBAPlayer to add
     * @throws InexistenteInstance if the team does not exist
     */
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
    /**
     * Retrieves an NBAPlayer by ID
     *
     * @param id the ID of the NBAPlayer to get
     * @return the NBAPlayer with the ID, or null if not found
     */
    public NBAPlayer getById(Integer id){
        return repP.get(id);
    }
    /**
     * Gets all NBAPlayers from the repository
     * @return a list of all NBAPlayers
     */

    public List<NBAPlayer> getAll(){
        return repP.getAll();
    }
    /**
     * Gets all NBAPlayers with the specified name
     * @param name the name of the players to get
     * @return a list of NBAPlayers with the specified name, or null if not found
     */
    public List<NBAPlayer> getByName(String name){
        List<NBAPlayer> players = repP.getAll();
        List<NBAPlayer> res = new ArrayList<>();
        for(NBAPlayer player: players){
            if(name.equals(player.getName()))
                res.add(player);
        }
        return res.size() == 0 ? null : res;
    }
    /**
     * Deletes an NBAPlayer by their ID
     * @param id the ID of the NBAPlayer to delete
     * @throws IdOutOfRangeException if the ID is out of range
     */

    public void delete(Integer id)throws IdOutOfRangeException{
        if(id >= NBAPlayer.getMaxId())
            throw new IdOutOfRangeException("\033[31mId out of bound exception for the Delete_Player transaction...\033[0m");
        repP.delete(id);
    }
    /**
     * Gets a sorted list of NBAPlayers by age in ascending order
     * @return a list of NBAPlayers sorted by age
     */
    public List<NBAPlayer> sortByAge(){
        return repP.getAll().stream().sorted(Comparator.comparing(NBAPlayer::getAge)).toList();
    }
}
