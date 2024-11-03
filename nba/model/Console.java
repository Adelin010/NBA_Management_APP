package nba.model;
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
                   break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Exiting...");

            }

        }


    }
}
