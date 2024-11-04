package nba.model;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import nba.model.Game;
import nba.model.NBATeam;
import nba.model.Player;
import nba.model.Sponsor;
import nba.model.NBAPlayer;
import nba.repo.Repository;
import nba.service.GeneralService;
public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private final GeneralService generalService;

    public Console(GeneralService generalService) {
        this.generalService = generalService;
    }
    public void start(){
        boolean is_on=true;
        while(is_on){
            System.out.println("  NBA Management System  ");
            System.out.println("1.Add Player");
            System.out.println("2.Add Sponsor");
            System.out.println("3.Add Game");
            System.out.println("4.Get All Players per Team");
            System.out.println("5.Exit");
            System.out.print("Choose an option:");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    addPlayer();
                   break;
                case 2:
                    addSponsor();
                    break;
                case 3:
                    addGame();
                    break;
                case 4:
                    getAllPlayers();
                    break;
                case 5:
                    System.out.println("Exiting...");

            }

        }


    }
    private  void addPlayer(){
        System.out.println("Enter player name:");
        String name = scanner.nextLine();
        NBAPlayer player = new NBAPlayer();
        player.setName(name);
        generalService.addPlayer(player);
        System.out.println("Player added");
    }
    private  void addSponsor(){
        System.out.println("Enter sponsor name:");
        String name = scanner.nextLine();
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorName(name);
        generalService.addSponsor(sponsor);
        System.out.println("Sponsor added");
    }
    private  void addGame(){
        System.out.println("Enter team1ID:");
        String team1ID = scanner.nextLine();
        System.out.println("Enter team2ID:");
        String team2ID = scanner.nextLine();
        System.out.println("Enter a date for the game:");
        String date = scanner.nextLine();
        NBATeam team1 = new NBATeam(team1ID);
        NBATeam team2 = new NBATeam(team2ID);
        BasketballGame game = new BasketballGame();
        generalService.addGame(game);
        System.out.println("Game added");
    }
    private  void getAllPlayers(){
        System.out.println("Enter team1ID:");
        String team1ID = scanner.nextLine();
        NBATeam team = new NBATeam(team1ID);
        List<NBAPlayer> players = generalService.getAllPlayersPerTeam(team);
        System.out.println("Player for the team" + team1ID);
        for (NBAPlayer player : players) {
            System.out.println("- " + player.getName());
        }
    }
}
