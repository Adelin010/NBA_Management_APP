package com.example.nba.services;

import com.example.nba.repos.RepoDB;

import java.util.List;
import java.util.stream.Collectors;

import com.example.nba.error.*;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.Sponsor;
import com.example.nba.model.NBATeam;
import com.example.nba.model.Found;

public class FoundsS {
    private final Repo<NBATeam> rt;
    private final Repo<Sponsor> rs;
    private final Repo<Found> rf;
    private final boolean isQ;

    public FoundsS(Repo<NBATeam> rt, Repo<Sponsor> rs, Repo<Found> rf){
        this.rt = rt;
        this.rs = rs;
        this.rf = rf;
        if(rf instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(Found found)throws InexistenteInstance{
        // Check for the integrity of the foreign key
        NBATeam t = rt.get(found.getTeamId());
        if(t == null)
            throw new InexistenteInstance("The team assigned to the found entry is null. Foreign key violation...");
        
        Sponsor s = rs.get(found.getSponsorId());
        if(s == null)
            throw new InexistenteInstance("The sponsor assigned to the found entry is null. Foreign key violation...");

        Found f = rf.get(found.getId());
        if(f != null)
            throw new AlreadyExistingException("The manager id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rf.add(found);
    }

    public void delete(Integer id){
        rf.delete(id);
    }

    public Found getById(Integer id ){
        return rf.get(id);
    }
    public List<Found> getAll(){
        return rf.getAll();
    }

    
    /**
     * Get a list of founds of a team where the query has been made based on the name of the Team
     * if there are nno founds we get back an empty list.
     * @param teamName
     * @return
     * @throws AttributeFaultedException
     */
    public List<Found> getTeamFounds(String teamName)throws AttributeFaultedException{
        if(rf instanceof RepoDB){
            return ((RepoDB<Found>)rf).getTeamFounds(teamName);
        }else{
            //check if the team is real
            NBATeam t = rt.getAll().stream().filter((var team) -> team.getName().equals(teamName)).findFirst().orElse(null);
            if(t == null)
                throw new AttributeFaultedException("Team name %s faulted exception. The name is wrong...".formatted(teamName));

            return rf.getAll().stream().filter((var found) -> found.getTeamId() == t.getId()).collect(Collectors.toList());
        }   

    }

    public List<Found> getSponsorFounds(String sponsorName){
        if(isQ){
            return ((RepoDB<Found>)rf).getSponsorFound(sponsorName);
        }else{
            //check if the team is real
            NBATeam t = rt.getAll().stream().filter((var team) -> team.getName().equals(sponsorName)).findFirst().orElse(null);
            if(t == null)
                throw new AttributeFaultedException("Sponsor name %s faulted exception. The name is wrong...".formatted(sponsorName));
    
            return rf.getAll().stream().filter((var found) -> found.getTeamId() == t.getId()).collect(Collectors.toList());
        }   
    }
    
}
