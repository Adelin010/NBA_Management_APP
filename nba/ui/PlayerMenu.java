package nba.ui;

import java.util.Scanner;

import nba.controller.PlayerController;
import nba.model.NBAPlayer;
import nba.repo.*;
import nba.service.PlayerService;

public class PlayerMenu {


    public static void addPlayer(PlayerController plc, Scanner in){

    }

    public static void getPlayerById(PlayerController plc, Scanner in){
        
    }
    public static void getPlayerByName(PlayerController plc, Scanner in){
        
    }
    public static void getPlayersBySalary(PlayerController plc, Scanner in){
        
    }
    public static void getPlayersByPosition(PlayerController plc, Scanner in){
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Repository<NBAPlayer> plr = new InMemRepository<>();
        PlayerService pls = new PlayerService();
        PlayerController plc = new PlayerController();

        while(true){
            System.out.print("1)Add Player\n2)Get Player by Id\n3)Get PLayer by name\n4)Get Players by salary\n5)Get Players by position\n Option:");
            int option = in.nextInt();
            switch (option) {
                case 1:{
                    addPlayer(plc, in);
                    break;
                }         
                case 2:{
                    getPlayerById(plc, in);
                    break;
                }  
                case 3:{
                    getPlayerByName(plc, in);
                    break;
                }
                case 4:{
                    getPlayersBySalary(plc, in);
                    break;
                }
                case 5:{
                    getPlayersByPosition(plc, in);
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
