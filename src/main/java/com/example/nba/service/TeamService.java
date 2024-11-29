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

    public TeamService(Repo<NBATeam> repT, Repo<Conference> repC){
        this.repT = repT;
        this.repC = repC;
        //init the MAX_ID
        repT.getAll();
    }

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

    public NBATeam getById(Integer id){
        return repT.get(id);
    }

    public List<NBATeam> getAll(){
        return repT.getAll();
    }

    public NBATeam getByName(String name){
        List<NBATeam> teams = repT.getAll();
        for(NBATeam team: teams){
            if(name.equals(team.getName()))
                return team;
        }
        return null;
    }

    public void delete(Integer id)throws IdOutOfRangeException{

        if(id >= NBATeam.getMaxId())
            throw new IdOutOfRangeException("\033[31mId out of bound exception for Delete_Team tranzaction...\033[0m");
        repT.delete(id);
    }
}
