package nba.service;
import java.util.ArrayList;
import java.util.List;
import nba.exceptions.*;
import nba.model.Manager;
import nba.model.NBATeam;
import nba.repo.Repository;

public class ManagerService {
    private final Repository<Manager> managers;
    private final Repository<NBATeam> teams;
    public ManagerService(Repository<Manager> managers, Repository<NBATeam> teams) {
        this.managers = managers;
        this.teams = teams;
    }

    /**
     * Adds a new manager to the repository.
     * @param manager The manager to be added.
     * @throws AlreadyExistingException
     */
    public void addManager(Manager manager)throws AlreadyExistingException{
        if(managers.get(manager.getId()) != null){
            throw new AlreadyExistingException("Manager with ID " + manager.getId() + " already exists.");
        }
        if(managers.get(manager.getTeam().getId()) != null){
            NBATeam team = teams.get(manager.getTeam().getId());
            if(team!=null){
                team.setManager(manager);
            }

        }
        managers.add(manager);

    }
    public Manager getManagerById(int id){return managers.get(id);}
    public List<Manager> getManagersByName(String name){
        List<Manager> matched = new ArrayList<>();
        for (Manager manager : managers.getAll()) {
            if (manager.getName().equalsIgnoreCase(name)) {
                matched.add(manager);
            }
        }
        return matched;
    }

    public int changeManager(int teamId, int newManagerId) throws TeamNotFoundException, ManagerNotFoundException, AlreadyExistingException{
        NBATeam team = teams.get(teamId);
        if (team == null) {
            throw new TeamNotFoundException("Team with ID " + teamId + " does not exist.");
        }
        Manager newManager=managers.get(newManagerId);
        if (newManager == null) {
            throw new ManagerNotFoundException("Manager with ID " + newManagerId + " does not exist.");
        }
        for (NBATeam currentTeam : teams.getAll()) {
            if (currentTeam.getManager()!= null && newManagerId ==currentTeam.getManager().getId()) {
                throw new AlreadyExistingException("Manager " +newManager.getName() + " is already hired by another team.");
            }
        }
        Manager oldManager = team.getManager();
        if (oldManager != null) {
            oldManager.setTeam(null);
            System.out.println("Fired old manager: " + oldManager.getName());
        }
        team.setManager(newManager);
        newManager.setTeam(team);
        System.out.println("Hired new manager: " + newManager.getName());
        return 0;
    }
    public List<Manager> getManagerByName(String name) {
        List<Manager> matched = new ArrayList<>();
        List<Manager> available = managers.getAll();
        if (available.isEmpty()) {
            return matched;
        }

        for (Manager manager : available) {
            if (name.equalsIgnoreCase(manager.getName())) {
                matched.add(manager);
            }
        }
        return matched;
    }
}
