package com.example.nba.controller;

import java.util.List;
import com.example.nba.model.Manager;
import com.example.nba.service.ManagerService;

public class ManagerController {
     public ManagerService ms;

    public ManagerController(ManagerService ms){
        this.ms = ms;
    }
    /**
     * Adds a new manager with the specified name and team ID
     * @param name   the name of the manager
     * @param teamId the ID of the team of the manager
     */
    public void add(String name,Integer teamId){
        Manager manager = new Manager(name,teamId);
        ms.add(manager);
    }
    /**
     * Gets a manager by their ID
     * @param id the ID of the manager to get
     * @return the Manager by ID
     */

    public Manager getById(Integer id){
        return ms.getById(id);
    }
    /**
     * Gets all managers
     * @return a list of all manager
     */
    public List<Manager> getAll(){
        return ms.getAll();
    }
    /**
     * Gets a manager by their name
     * @param name the name of the manager to get
     * @return the manager with the specified name, or null if not found
     */
    public Manager getByName(String name){
        return ms.getByName(name);
    }
    /**
     * Deletes a manager by ID
     * @param id the ID of the manager to delete
     */
    public void delete(Integer id){
        ms.delete(id);
    }
}
