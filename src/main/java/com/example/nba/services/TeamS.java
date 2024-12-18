package com.example.nba.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.nba.error.AlreadyExistingException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.repos.RepoDB;
import com.example.nba.model.Conference;
import com.example.nba.model.NBATeam;

public class TeamS {
    private final Repo<Conference> rc;
    private final Repo<NBATeam> rt;
    private final boolean isQ;

    public TeamS(Repo<Conference> rc, Repo<NBATeam> rt){
        this.rc = rc;
        this.rt = rt;
        if(rt instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(NBATeam team)throws InexistenteInstance{
        // Check for the integrity of the foreign key
        Conference c = rc.get(team.getConferenceId());
        if(c == null)
            throw new InexistenteInstance("The conference assigned to the team is null. Foreign key violation...");
        
        NBATeam t = rt.get(team.getId());
        if(t != null)
            throw new AlreadyExistingException("The Team id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rt.add(team);
    }

    public void delete(Integer id){
        rt.delete(id);
    }

    public NBATeam getById(Integer id ){
        return rt.get(id);
    }
    public List<NBATeam> getAll(){
        return rt.getAll();
    }

    /*
     * Get All team from the conference
     */
    public List<NBATeam> getTheConferenceList(Integer conferenceId){
        if(isQ){
            return null;
        }else{
            var list = rt.getAll();
            return list.stream().filter((var t) -> t.getConferenceId() == conferenceId).collect(Collectors.toList());
        }
    }
    /*
     * Get By Name 
     */
    public NBATeam getByName(String name){
        if(isQ){
            return null;
        }else{
            var list = rt.getAll();
            return list.stream().filter((var t) -> t.getName().equals(name)).findFirst().orElse(null);
        }
    }
}
