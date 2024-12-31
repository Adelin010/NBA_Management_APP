package com.example.nba.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.nba.error.AlreadyExistingException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.Manager;
import com.example.nba.model.NBATeam;
import com.example.nba.repos.RepoDB;

public class ManagerS {
    private final Repo<Manager> rm;
    private final Repo<NBATeam> rt;
    private final boolean isQ;

    public ManagerS(Repo<Manager> rm, Repo<NBATeam> rt){
        this.rm = rm;
        this.rt = rt;
        if(rt instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(Manager manager)throws InexistenteInstance{
        // Check for the integrity of the foreign key
        NBATeam t = rt.get(manager.getTeamId());
        if(t == null)
            throw new InexistenteInstance("The team assigned to the manager is null. Foreign key violation...");
        
        Manager m = rm.get(manager.getId());
        if(m != null)
            throw new AlreadyExistingException("The manager id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rm.add(manager);
    }

    public void delete(Integer id){
        rm.delete(id);
    }

    public Manager getById(Integer id ){
        return rm.get(id);
    }
    public List<Manager> getAll(){
        return rm.getAll();
    }


    /*
     * Get by Name
     */
    /**
     * The function filters out the managers after a given name
     * @param name
     * @return list of managers
     */
    public List<Manager> getByName(String name){
        if(isQ){
            return ((RepoDB<Manager>)rm).getByColumn("name", name);
        }else{
            var list = rm.getAll();
            return list.stream().filter((var p) -> p.getName().equals(name)).collect(Collectors.toList());
        }
    }

}
