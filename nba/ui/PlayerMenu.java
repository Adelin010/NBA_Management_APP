package nba.ui;

import java.util.List;
import java.util.Scanner;

import nba.controller.PlayerController;
import nba.exceptions.AlreadyExistingException;
import nba.exceptions.InexistenteInstance;
import nba.model.NBAPlayer;

public class PlayerMenu {

    private final Scanner in;
    private final PlayerController plC;

    public PlayerMenu(PlayerController plC, Scanner in){
        this.in = in;
        this.plC = plC;
    }

    private  void addPlayer(PlayerController plC, Scanner in){
        System.out.print("Player id: ");
        int id = in.nextInt();
        System.out.print("Player Name: ");
        String name = in.next();
        System.out.print("Player age: ");
        int age = in.nextInt();
        System.out.print("Player salary: ");
        double salary = in.nextDouble();
        System.out.print("Player position(posible choices: PG, SG, SF, PF, C): ");
        String position = in.next();
        System.out.print("Players team id(if not into a team enter -1): ");
        int teamId = in.nextInt();
        try{
            plC.addPlayer(id, name, age, salary, position, teamId);
        }catch(AlreadyExistingException exp){
            exp.printInfo();
        }catch(InexistenteInstance exp){
            exp.printInfo();
        }catch(Exception exp){
            System.err.println("Other kind of exception appeared when adding a Player");
            exp.printStackTrace();
        }
    }

    private  void getPlayerById(PlayerController plC, Scanner in){
        System.out.print("Player id: ");
        int id = in.nextInt();
        try{
            System.out.println(plC.getPlayerById(id).toString());
        }catch(NullPointerException exp){
            exp.printStackTrace();
        }
        
    }
    private  void getPlayerByName(PlayerController plc, Scanner in){
        System.out.print("Name for filtering: ");
        String name = in.next();
        List<NBAPlayer> res = plC.getPlayersByName(name);
        if(res.isEmpty()){
            System.out.println("No results matched...");
            return;
        }
        //Show all the players
        for(NBAPlayer pl: res)
            System.out.println(pl.toString());
    }
    private  void getPlayersBySalary(PlayerController plc, Scanner in){
        System.out.print("Salary for filtering: ");
        double salary = in.nextDouble();
        List<NBAPlayer> res = plC.getPlayersBySalary(salary);
        if(res.isEmpty()){
            System.out.println("No results matched...");
            return;
        }
        
        //Show all the players
        for(NBAPlayer pl: res)
            System.out.println(pl.toString());
    }
    private  void getPlayersByPosition(PlayerController plc, Scanner in){
        System.out.print("Position for filtering(available positions: PG, SG, SF, PF, C): ");
        String position = in.next();
        List<NBAPlayer> res = plC.getPlayersByPosition(position);
        if(res.isEmpty()){
            System.out.println("No results matched...");
            return;
        }
        //Show all the players
        for(NBAPlayer pl: res)
            System.out.println(pl.toString());
    }

    public void run() {

        boolean loop = true;

        while(loop){
            System.out.print("1)Add Player\n2)Get Player by Id\n3)Get PLayer by name\n4)Get Players by salary\n5)Get Players by position\n Option:");
            int option = in.nextInt();
            switch (option) {
                case 1:{
                    addPlayer(plC, in);
                    break;
                }         
                case 2:{
                    getPlayerById(plC, in);
                    break;
                }  
                case 3:{
                    getPlayerByName(plC, in);
                    break;
                }
                case 4:{
                    getPlayersBySalary(plC, in);
                    break;
                }
                case 5:{
                    getPlayersByPosition(plC, in);
                    break;
                }
                case -1:{
                    loop = false;
                    break;
                }
                default:
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
