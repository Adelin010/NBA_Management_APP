// package nba;

// import java.util.Scanner;

// import nba.controller.GeneralController;
// import nba.model.Game;
// import nba.model.Manager;
// import nba.model.NBAPlayer;
// import nba.model.NBATeam;
// import nba.model.Player;
// import nba.model.Sponsor;
// import nba.repo.InMemRepository;
// import nba.repo.Repository;
// import nba.service.GeneralService;

// public class Driver {


//     private static String mainMenu(Scanner in){
//         System.out.println("Wellcome to the main page...\nTo select a option enter\nthe number of the option and \nthe letter of each menu\n");
//         String menu = """
//                 Menu Player(p):
//                 \t1)add player
//                 \t2)get player by id
//                 \t3)get all players in a team

//                 Menu Manager(m):
//                 \t1)add manager
//                 \t2)get team manager
//                 \t3)get manager of player

//                 Menu Sponsor(s):
//                 \t1)add sponsor
//                 \t2)get sponsor by id

//                 Menu Team(t):
//                 \t1)add team
//                 \t2)get all teams
//                 \t3)get team by id
//                 \t4)get team by name

//                 Menu Game(g):
//                 \t1)add game
//                 \t2)get game by id

//                 Special Menu(sp):
//                 \t1)Change the manager of a team
//                 """;

//         System.out.println(menu);
//         String op = in.nextLine().trim();
//         System.out.println("\033[H\033[2J");
//         System.out.flush();
//         return op;
//     }

//     private static void addPlayer(GeneralController ctr, Scanner in){
//         System.out.print("Enter id: ");
//         int id  = in.nextInt();
//         System.out.print("Enter age: ");
//         int age = in.nextInt();
//         System.out.print("Enter name: ");
//         String name = in.next();
//         System.out.print("Enter salary: ");
//         double salary = in.nextDouble();
//         System.out.print("Enter position: ");
//         String pos = in.next();
//         try{
//             ctr.addPlayer(id, name, age, salary, pos);

//         }catch(Exception exp){
//             exp.printStackTrace();
//         }
//     }  
    

//     private static void getPlayer(GeneralController ctr, Scanner in ){
//         System.out.print("id: ");
//         int id = in.nextInt();
//         try{
//             NBAPlayer player = ctr.getNbaPlayerById(id);
//             System.out.print(player.toString());
//         }catch(Exception ex){
//             ex.printStackTrace();
//         }
//     }

//     private static void getAllPlayersInTeam(GeneralController ctr, Scanner in){
//         System.out.print("Enter team id: ");
//         int id = in.nextInt();
//         try{
//             List<NBAPlayer> players = ctr.getAllPlayersInTeam(id);
//             for(NBAPlayer p: players){
//                 System.out.println(p.toString());
//             }
//         }catch(Exception ex){
//             ex.printStackTrace();
//         }
//     }

//     private static void addManager(GeneralController ctr, Scanner in){
//         System.out.print("Enter id: ");
//         int id = in.nextInt();
//         System.out.print("Enter name: ");
//         String name = in.next();
//         ctr.addManager(id, name, null);
//     }
    

//     public static void addTeam(GeneralController ctr, Scanner in){
//         System.out.print("Enter id: ");
//         int id = in.nextInt();
//         System.out.print("Enter name: ");
//         String name = in.next();
//         System.out.println("Enter manager id: ");
//         int manId = in.nextInt();
//         ctr.addTeam(name, id, manId);
//     }

//     public static void getManagerOfTeam(GeneralController ctr, Scanner in){
//         System.out.print("Enter team name: ");
//         String name = in.next();
//         System.out.println(ctr.getTeamManager(name).toString()); 
//     }

//     public static void getTeamById(GeneralController ctr, Scanner in){
//         System.out.print("Enter team id: ");
//         int id = in.nextInt();
//         System.out.println(ctr.getTeamByID(id).toString());
//     }


//     public static void getTeamByName(GeneralController ctr, Scanner in){
//         System.out.print("Enter team name: ");
//         String name = in.next();
//         System.out.println(ctr.getTeamByName(name).toString());
//     }

//     public static void getAllTeams(GeneralController ctr, Scanner in){
//         for(NBATeam team: ctr.getAllTeams())
//             System.out.println(team.toString());
//     }


//     public static void hireManager(GeneralController ctr, Scanner in){
//         System.out.print("Enter team id: ");
//         int teamId = in.nextInt();
//         System.out.print("Enter manager id: ");
//         int namagerId = in.nextInt();
//         int res = ctr.hireManager(teamId, namagerId);
//         if(res == -1)
//             System.out.println("\033[33m Manager already taken\033[0m");
//         else 
//             System.out.println("Manager hired");
        
//     }

//     private static void execOption(String option, GeneralController ctr, Scanner in){
//         switch (option) {

//             // Player menu cases
//             case "1p":{
//                 addPlayer(ctr, in);
//                 break;
//             }
//             case "2p":{
//                 getPlayer(ctr, in);
//                 break;
//             }
//             case "3p":{
//                 getAllPlayersInTeam(ctr, in);
//                 break;
//             }
//             // Manager menu cases
//             case "1m":{
//                 addManager(ctr, in);
//                 break;
//             }
//             case "2m":{
//                 getManagerOfTeam(ctr, in);
//                 break;
//             }
//             case "3m":{
//                 break;
//             }
//             // Sponsor menu cases
//             case "1s":{
//                 break;
//             }
//             case "2s":{
//                 break;
//             }
//             // Team menu cases
//             case "1t":{
//                 addTeam(ctr, in);
//                 break;
//             }
//             case "2t":{
//                 getAllTeams(ctr, in);
//                 break;
//             }
//             case "3t":{
//                 getTeamById(ctr, in);
//                 break;
//             }
//             case "4t":{
//                 getTeamByName(ctr, in);
//                 break;
//             }
//             // Game menu cases
//             case "1g":{
//                 break;
//             }
//             case "2g":{
//                 break;
//             }      
            
//             case "1sp":{
//                 hireManager(ctr, in);
//                 break;
//             }
//         }
//     }
    
//     public static void main(String[] args) {
//         final Repository<Player> players = new InMemRepository<>();
//         final Repository<Sponsor> sponsors = new InMemRepository<>();
//         final Repository<Game> games = new InMemRepository<>();
//         final Repository<NBATeam> teams = new InMemRepository<>();
//         final Repository<Manager> managers = new InMemRepository<>();
//         final GeneralService service = new GeneralService(players, teams,sponsors, games, managers);
//         final GeneralController ctr = new GeneralController(service);
        
//         Scanner in = new Scanner(System.in);

//         while (true){
//             String op = mainMenu(in);
//             if(op == "/quit"){
//                 System.out.println("Menu left...\n");
//                 break;
//             }
//             execOption(op, ctr, in);
//             System.out.println("\033[32mWant to exit the menu pres write '/quit'... \033[0m");
//             // Wait for an input
            
//         }

//         in.close();
//     }
    
// }

package nba;

import nba.repo.InMemRepository;
import nba.repo.Repository;
import nba.service.PlayerService;
import nba.service.TeamService;
import nba.ui.*;

import java.util.Scanner;

import nba.controller.PlayerController;
import nba.controller.TeamController;
import nba.model.*;

public class Driver{
    public static void main(String[] args) {

        //Input Stream
        Scanner in = new Scanner(System.in);

        Repository<NBATeam> tmR = new InMemRepository<>();
        Repository<Manager> manR = new InMemRepository<>();
        Repository<NBAPlayer> plR = new InMemRepository<>();
        //Services
        TeamService tmS = new TeamService(tmR, manR);
        PlayerService plS = new PlayerService(plR, tmR);
        
        //Controllers
        TeamController tmC = new TeamController(tmS);
        PlayerController plC = new PlayerController(plS);

        //Menus
        TeamMenu teamMenu = new TeamMenu(tmC, in);
        PlayerMenu playerMenu = new PlayerMenu(plC, in);

        //Switch between the menus 
        int option;
        boolean loop = true;
        while(loop){
            System.out.print("1)Player Menu\n2)Team Menu\nChoose menu: ");
            option = in.nextInt();
            switch(option){
                case 1:{
                    playerMenu.run();
                    break;
                }case 2:{
                    teamMenu.run();
                    break;
                }default:{
                    System.out.println("Session ended...");
                    loop = false;
                }
            }
        }
    }
}


