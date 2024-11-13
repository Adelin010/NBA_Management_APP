package nba.ui;

import nba.controller.TeamController;
import nba.model.*;
import java.util.Scanner;
import java.util.List;

public class TeamMenu {
    
    private final TeamController tmC;
    private final Scanner in;

    private void addTeam(TeamController tc, Scanner in){
        System.out.print("Id of the Team: ");
        int id = in.nextInt();
        System.out.print("Name of the team: ");
        String name = in.next();
        System.out.print("Manager id: ");
        int manId = in.nextInt();
        try{
            tc.addTeam(id, name, manId);
            System.out.println("Team Added!!!");
        }catch(Exception exp){
            System.err.println("Exception at adding the Team");
        }
    }

    private void getTeamById(TeamController tc, Scanner in){
        System.out.print("Id of the Team: ");
        int id = in.nextInt();
        try{
            NBATeam team = tc.getTeamById(id);
            System.out.println(team.toString());
        }catch(Exception exp){
            exp.printStackTrace();
            System.err.println("Exception at getting the team by id");
        }
    }

    private void getTeamByName(TeamController tc, Scanner in){
        System.out.print("Id of the Team: ");
        String name = in.next();
        System.out.println("Name entered: " + name);
        try{
            NBATeam team = tc.getTeamByName(name);
            System.out.println(team.toString());
        }catch(Exception exp){
            exp.printStackTrace();
            System.err.println("Exception at getting the team by name");
        }
    }

    private void getAllTeams(TeamController tc, Scanner in){
        try{
            List<NBATeam> teams = tc.getAllTeams();
            if(teams.size() == 0)
                System.out.println("There are no teams added!!!");
            else{
                for(NBATeam team: teams)
                    System.out.println(team.toString());
            }
        }catch(Exception exp){
            exp.printStackTrace();
            System.err.println("Exception at getting all teams");
        }
    }

    public TeamMenu(TeamController tmC, Scanner in){
        this.in = in;
        this.tmC = tmC;
    }

    public void run() {

        boolean loop = true;
        while(loop){
            System.out.println("1) Add Team\n2)Get Team by Id\n3)Get Team By Name\n4)Get all teams");
            System.out.println("Option: ");
            int option = in.nextInt();
            switch (option){
                case 1:{
                    addTeam(tmC, in);
                    break;
                }
                case 2:{
                    getTeamById(tmC, in);
                    break;
                }
                case 3:{
                    getTeamByName(tmC, in);
                    break;
                }
                case 4:{
                    getAllTeams(tmC, in);
                    break;
                }
                case -1:{
                    loop = false;
                    break;
                }
                default:{
                    System.out.println("Try again...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}