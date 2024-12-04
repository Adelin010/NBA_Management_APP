package com.example.nba.service;

import java.util.List;

import com.example.nba.error.IdOutOfRangeException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.Conference;
import com.example.nba.model.NBATeam;
import com.example.nba.repo.Repo;

public class TeamService {
    Repo<NBATeam> repT;
    Repo<Conference> repC;
    /**
     * Constructor to initialize TeamService with the specified repos
     * @param repT the repository for NBATeam
     * @param repC the repository for Conference
     */

    public TeamService(Repo<NBATeam> repT, Repo<Conference> repC){
        this.repT = repT;
        this.repC = repC;
        //init the MAX_ID
        repT.getAll();
    }
    /**
     * Adds a new NBATeam to the repository
     * @param team the NBATeam to be added
     * @throws InexistenteInstance if the specified conference does not exist
     */

    public void add(NBATeam team)throws InexistenteInstance{
        /*
         * Check Foreign_keys constraints
         */
        Integer conferenceId = team.getConferenceId();
        if(repC.get(conferenceId) == null){
            throw new InexistenteInstance("\033[31mException raised in Add_Team transaction. Conference_Foreign_Key violation, Conference inexistante.\033[0m");
        }
        repT.add(team);
    }
    /**
     * Gets an NBATeam by it ID
     *
     * @param id the ID of the NBATeam to get
     * @return the NBATeam with the specified ID, or null if not found
     */
    public NBATeam getById(Integer id){
        return repT.get(id);
    }
    /**
     * Gets all NBATeams from the repository
     *
     * @return a list of all NBATeams
     */
    public List<NBATeam> getAll(){
        return repT.getAll();
    }
    /**
     * Getss an NBATeam by its name.
     *
     * @param name the name of the NBATeam to get
     * @return the NBATeam with the specified name, or null if not found
     */
    public NBATeam getByName(String name){
        List<NBATeam> teams = repT.getAll();
        for(NBATeam team: teams){
            if(name.equals(team.getName()))
                return team;
        }
        return null;
    }
    /**
     * Deletes an NBATeam by ID
     *
     * @param id the ID of the NBATeam to delete
     * @throws IdOutOfRangeException if the ID is out of range
     */
    public void delete(Integer id)throws IdOutOfRangeException{

        if(id >= NBATeam.getMaxId())
            throw new IdOutOfRangeException("\033[31mId out of bound exception for Delete_Team tranzaction...\033[0m");
        repT.delete(id);
    }
}
