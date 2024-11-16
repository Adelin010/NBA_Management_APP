package nba.controller;
import nba.model.Manager;
import nba.model.NBATeam;
import nba.service.ManagerService;
import nba.exceptions.*;
import java.util.*;
public class ManagerController {
    private final ManagerService managerService;
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    public void addManager(int id, String name, int teamID){
        if (name.length()<=2) {
            System.out.println("Name is too short");
            return;
        }
        try{
            Manager manager = new Manager(id,name,teamID);
            managerService.addManager(manager);
            System.out.println("Manager added");
        }
        catch(AlreadyExistingException e){
            System.out.println("Manager already exists");
        }
        catch (NullPointerException e) {
            System.out.println("Null reference encountered. Please check your input and try again.");
            e.printStackTrace();
        }
    }
    public Manager getManagerById(int id){return managerService.getManagerById(id);}
    public void changeManager(int newManagerId, int teamId){
        int isGood = managerService.changeManager(teamId,newManagerId);
        if(isGood==0){
            System.out.println("Manager has been changed");
        }
    }

    public List<Manager> getManagersByName(String name) {
        return managerService.getManagersByName(name);
    }
}
