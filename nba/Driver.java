package nba;

import java.util.List;
import java.util.Scanner;

import nba.controller.GeneralController;
import nba.model.Game;
import nba.model.Manager;
import nba.model.NBAPlayer;
import nba.model.NBATeam;
import nba.model.Player;
import nba.model.Sponsor;
import nba.repo.InMemRepository;
import nba.repo.Repository;
import nba.service.GeneralService;

public class Driver {


    private static String mainMenu(Scanner in){
        System.out.println("Wellcome to the main page...\nTo select a option enter\nthe number of the option and \nthe letter of each menu\n");
        String menu = """
                Menu Player(p):
                \t1)add player
                \t2)get player by id
                \t3)get all players in a team

                Menu Manager(m):
                \t1)add manager
                \t2)get team manager
                \t3)get manager of player

                Menu Sponsor(s):
                \t1)add sponsor
                \t2)get sponsor by id

                Menu Team(t):
                \t1)add team
                \t2)get all teams
                \t3)get team by id
                \t4)get team by name

                Menu Game(g):
                \t1)add game
                \t2)get game by id
                """;

        System.out.println(menu);
        String op = in.nextLine().trim();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        return op;
    }

    private static void addPlayer(GeneralController ctr, Scanner in){
        System.out.print("Enter id: ");
        int id  = in.nextInt();
        System.out.print("Enter age: ");
        int age = in.nextInt();
        System.out.print("Enter name: ");
        String name = in.next();
        System.out.print("Enter salary: ");
        double salary = in.nextDouble();
        System.out.print("Enter position: ");
        String pos = in.next();
        try{
            ctr.addPlayer(id, name, age, salary, pos);

        }catch(Exception exp){
            exp.printStackTrace();
        }
    }  
    

    private static void getPlayer(GeneralController ctr, Scanner in ){
        System.out.print("id: ");
        int id = in.nextInt();
        try{
            NBAPlayer player = ctr.getNbaPlayerById(id);
            System.out.print(player.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void getAllPlayersInTeam(GeneralController ctr, Scanner in){
        System.out.print("Enter team id: ");
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
    

    private static void execOption(String option, GeneralController ctr, Scanner in){
        switch (option) {

            // Player menu cases
            case "1p":{
                addPlayer(ctr, in);
                break;
            }
            case "2p":{
                getPlayer(ctr, in);
                break;
            }
            case "3p":{
                getAllPlayersInTeam(ctr, in);
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
        final Repository<Player> players = new InMemRepository<>();
        final Repository<Sponsor> sponsors = new InMemRepository<>();
        final Repository<Game> games = new InMemRepository<>();
        final Repository<NBATeam> teams = new InMemRepository<>();
        final Repository<Manager> managers = new InMemRepository<>();
        final GeneralService service = new GeneralService(players, sponsors, games, teams, managers);
        final GeneralController ctr = new GeneralController(service);
        Scanner in = new Scanner(System.in);

        while (true){
            String op = mainMenu(in);
            if(op == "/quit"){
                System.out.println("Menu left...\n");
                break;
            }
            execOption(op, ctr, in);
        }

        in.close();
    }
    
}