package nba.service;

import java.util.List;

import nba.error.IdOutOfRangeException;
import nba.error.InexistenteInstance;
import nba.model.Manager;
import nba.model.NBATeam;
import nba.repo.Repo;

public class ManagerService {
    Repo<Manager> repM;
    Repo<NBATeam> repT;

    public ManagerService(Repo<Manager> repM, Repo<NBATeam> repT){
        this.repM = repM;
        this.repT = repT;
        //init MAX_ID
        repM.getAll();
    }

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

    public Manager getById(Integer id){
        return repM.get(id);
    }

    public List<Manager> getAll(){
        return repM.getAll();
    }

    public Manager getByName(String name){
        List<Manager> managers = repM.getAll();
        for(Manager manager: managers){
            if(name.equals(manager.getName()))
                return manager;
        }
        return null;
    }

    public Manager getByTeamId(Integer teamId){
        List<Manager> managers = repM.getAll();
        for(Manager m: managers){
            if(m.getTeamId() == teamId)
                return m;
        }
        return null;
    }

    public void delete(Integer id)throws IdOutOfRangeException{
        if(id >= Manager.getMaxId()){
            throw new IdOutOfRangeException("\033[31mId out of bound for the Delete_Manager transaction...\033[0m");
        }
        repM.delete(id);
    }
}
