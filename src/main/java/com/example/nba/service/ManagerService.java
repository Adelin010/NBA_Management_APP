package com.example.nba.service;

import java.util.List;

import com.example.nba.error.IdOutOfRangeException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.Manager;
import com.example.nba.model.NBATeam;
import com.example.nba.repo.Repo;

public class ManagerService {
    Repo<Manager> repM;
    Repo<NBATeam> repT;
    /**
     * Constructor to initialize ManagerService with repos
     * @param repM the repository for Manager
     * @param repT the repository for NBATeam
     */

    public ManagerService(Repo<Manager> repM, Repo<NBATeam> repT){
        this.repM = repM;
        this.repT = repT;
        //init MAX_ID
        repM.getAll();
    }
    /**
     * Adds a new Manager to the repository.
     *
     * @param manager the Manager to add
     * @throws InexistenteInstance if the team doesnt exist
     */
    public void add(Manager manager){
        /*
         * Check Foreign_keys constraints
         */
        Integer teamId = manager.getTeamId();
        if(repT.get(teamId) == null){
            throw new InexistenteInstance("\033[31mException raised in Add_Manager transaction. Team_Foreign_Key violation, Team inexistante.\033[0m");
        }
        repM.add(manager);
    }
    /**
     * Gets a Manager by their ID
     * @param id the ID of the Manager to get
     * @return the Manager with the specified ID, or null if not found
     */
    public Manager getById(Integer id){
        return repM.get(id);
    }
    /**
     * Gets all Managers from the repository
     * @return a list of all managers
     */
    public List<Manager> getAll(){
        return repM.getAll();
    }
    /**
     * Gets a Manager by their name
     * @param name the name of the Manager to get
     * @return the Manager with the specified name, or null if no manager is found
     */
    public Manager getByName(String name){
        List<Manager> managers = repM.getAll();
        for(Manager manager: managers){
            if(name.equals(manager.getName()))
                return manager;
        }
        return null;
    }
    /**
     * Retrieves a Manager by the taemID
     * @param teamId the ID of the team with the Manager
     * @return the Manager managing the specified team, or null if no manager is found
     */
    public Manager getByTeamId(Integer teamId){
        List<Manager> managers = repM.getAll();
        for(Manager m: managers){
            if(m.getTeamId() == teamId)
                return m;
        }
        return null;
    }
    /**
     * Deletes a Manager by their ID.
     * @param id the ID of the Manager to delete
     * @throws IdOutOfRangeException if the ID is out of range
     */
    public void delete(Integer id)throws IdOutOfRangeException{
        if(id >= Manager.getMaxId()){
            throw new IdOutOfRangeException("\033[31mId out of bound for the Delete_Manager transaction...\033[0m");
        }
        repM.delete(id);
    }
}
