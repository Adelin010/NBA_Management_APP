package nba.service;

import nba.model.NBATeam;
import nba.model.Manager;
import nba.repo.Repository;

import java.util.List;
import nba.exceptions.*;

public class TeamService {
    private final Repository<NBATeam> teams;
    private final Repository<Manager> managers;

    public TeamService(Repository<NBATeam> teams, Repository<Manager> managers){
        this.teams = teams;
        this.managers = managers;
    }

    public void addTeam(NBATeam team, int managerId) throws AlreadyExistingException{
        // Check for the existance of the Team
        if(teams.get(team.getId()) != null){
            throw new AlreadyExistingException("Try Insert an already existing team");
        }

        //check if there is a manager 
        Manager teamManager = managers.get(managerId);
        if(teamManager == null){
            team.setManager(null);
        }else{
            //check if the manager is already taken
            boolean alreadyTaken = false;
            for(NBATeam other: teams.getAll()){
                if(other.getManager().getId() == teamManager.getId())
                    alreadyTaken = true;
            }
            //If the Team has a Manager update any manager state
            if(!alreadyTaken){
                teamManager.setTeam(team);
                team.setManager(teamManager);
            }else{
                team.setManager(null);
            }
        }

        teams.add(team);
    }

    public NBATeam getTeamById(int id){
        return teams.get(id);
    }

    public void updateTeam(NBATeam team){
        if(teams.get(team.getId()) == null){
            //Add the team because it does not exists
            int managerId = team.getManager() == null ? -1 : team.getManager().getId();
            addTeam(team, managerId);
            return;
        }
        teams.update(team);
    }

    public List<NBATeam> getAllTeams(){
        return teams.getAll();
    }

    public NBATeam getTeamByName(String name){
        List<NBATeam> available = teams.getAll();
        if(available.size() == 0){
            System.out.println("No Teams inside which to search...");
            return null;
        }
        for(NBATeam team: available){
            if(name.equals(team.getName())){
                return team;
            }
        }
        return null;
    }

}
