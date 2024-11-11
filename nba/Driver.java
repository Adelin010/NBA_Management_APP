package nba;

import java.util.List;
import java.util.Scanner;

import nba.controller.GeneralController;
import nba.model.NBAPlayer;

public class Driver {

    private static String mainMenu(){
        System.out.println("Wellcome to the main page...\nTo select a option enter\nthe number of the option and \nthe letter of each menu");
        String menu = """
                Menu Player(m):
                \t1)add player
                \t1)get player by id
                \t1)get all players in a team

                Menu Manager(m):
                \t1)add manager
                \t1)get team manager
                \t1)get manager of player

                Menu Sponsor(s):
                \t1)add sponsor
                \t1)get sponsor by id

                Menu Team(t):
                \t1)add team
                \t1)get all teams
                \t1)get team by id
                \t1)get team by name

                Menu Game(g):
                \t1)add game
                \t1)get game by id
                """;

        System.out.println(menu);
        Scanner in  = new Scanner(System.in);
        String op = in.nextLine().trim();
        in.close();
        return op;
    }

    private static void addPlayer(GeneralController ctr){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id  = in.nextInt();
        System.out.println("Enter age: ");
        int age = in.nextInt();
        System.out.println("Enter name: ");
        String name = in.next();
        System.out.println("Enter salary: ");
        double salary = in.nextDouble();
        System.out.println("Enter position: ");
        String pos = in.next();
        try{
            ctr.addPlayer(id, name, age, salary, pos);

        }catch(Exception exp){
            exp.printStackTrace();
        }
        in.close();
    }  
    

    private static void getPlayer(GeneralController ctr){
        Scanner in = new Scanner(System.in);
        System.out.println("id: ");
        int id = in.nextInt();
        in.close();
        try{
            NBAPlayer player = ctr.getNbaPlayerById(id);
            System.out.println(player.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void getAllPlayersInTeam(GeneralController ctr){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter team id: ");
        int id = in.nextInt();
        try{
            List<NBAPlayer> players = ctr.getAllPlayersInTeam(id);
            for(NBAPlayer p: players){
                System.out.println(p.toString());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    

    private static void execOption(String option, GeneralController ctr){
        switch (option) {
            // Player menu cases
            case "1p":{
                addPlayer(ctr);
                break;
            }
            case "2p":{
                getPlayer(ctr);
                break;
            }
            case "3p":{
                getAllPlayersInTeam(ctr);
                break;
            }
            // Manager menu cases
            case "1m":{
                break;
            }
            case "2m":{
                break;
            }
            case "3m":{
                break;
            }
            // Sponsor menu cases
            case "1s":{
                break;
            }
            case "2s":{
                break;
            }
            // Team menu cases
            case "1t":{
                break;
            }
            case "2t":{
                break;
            }
            case "3t":{
                break;
            }
            case "4t":{
                break;
            }
            // Game menu cases
            case "1g":{
                break;
            }
            case "2g":{
                break;
            }      
            
        }
    }
    
    public static void main(String[] args) {
        String op = mainMenu();

    }
    
}
