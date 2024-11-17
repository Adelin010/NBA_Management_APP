package nba.controller;
import nba.model.Manager;
import nba.model.NBATeam;
import nba.service.ManagerService;
import nba.exceptions.*;
import nba.service.TeamService;

import java.util.*;
import java.util.stream.Collectors;

public class ManagerController {
    private final ManagerService managerService;
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    public void addManager(int id, String name, int teamID){
        if (name.length() <= 2) {
            System.out.println("Name is too short");
            return;
        }

        try {
            managerService.addManager(id, name, teamID);
            System.out.println("Manager added successfully.");
        } catch (AlreadyExistingException e) {
            System.out.println("Manager already exists.");
        } catch (InexistenteInstance e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
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
