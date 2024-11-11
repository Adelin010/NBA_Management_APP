package nba.model;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nba.controller.GeneralController;
import nba.model.Game;
import nba.model.NBATeam;
import nba.model.Player;
import nba.model.Sponsor;
import nba.model.NBAPlayer;
import nba.repo.InMemRepository;
import nba.repo.Repository;
import nba.service.GeneralService;
public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private final GeneralController controller;

    public Console(GeneralController controller) {
        this.controller = controller;
    }
    public void start(){
        boolean is_on = true;
        while (is_on) {
            System.out.println("\n=== NBA Management System ===");
            System.out.println("1. Add a Player");
            System.out.println("2. Add a Team");
            System.out.println("3. Add a Manager");
            System.out.println("4. Add a Game");
            System.out.println("5. Add a Sponsor");
            System.out.println("6. Get All Players per Team");
            System.out.println("7. Get All Teams");
            System.out.println("8. Get Player by ID");
            System.out.println("9. Get Team by ID");
            System.out.println("10. Get Team by Name");
            System.out.println("11. Get Manager of a Player");
            System.out.println("12. Get Team Manager");
            System.out.println("13. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    addPlayer();
                    break;
                case 2:
                    addTeam();
                    break;
                case 3:
                    addManager();
                    break;
                case 4:
                    addGame();
                    break;
                case 5:
                    addSponsor();
                    break;
                case 6:
                    getAllPlayersPerTeam();
                    break;
                case 7:
                    getAllTeams();
                    break;
                case 8:
                    getPlayerById();
                    break;
                case 9:
                    getTeamById();
                    break;
                case 10:
                    getTeamByName();
                    break;
                case 11:
                    getManagerOfPlayer();
                    break;
                case 12:
                    getTeamManager();
                    break;
                case 13:
                    System.out.println("Exiting...");
                    is_on = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    private void addPlayer() {
        System.out.print("Enter player name: ");
        String name = scanner.nextLine();
        System.out.print("Enter player age: ");
        int age = scanner.nextInt();
        System.out.print("Enter player salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter player position: ");
        String position = scanner.nextLine();
        // controller.addPlayer(name, age, salary, position);
    }

    private void addTeam() {
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();
        System.out.print("Enter team ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Manager manager = new Manager("Default Manager", null);
        controller.addTeam(name, id, manager);
    }

    private void addManager() {
        System.out.print("Enter manager name: ");
        String name = scanner.nextLine();
        System.out.print("Enter manager ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the name of the team of this manager: ");
        String teamName = scanner.nextLine();
        NBATeam team = controller.getTeamByID(id);
        if (team != null) {
            controller.addManager(name, id, team);
        } else {
            System.out.println("Team not found.");
        }
    }

    private void addGame() {
    }

    private void addSponsor() {
        System.out.print("Enter sponsor name: ");
        String name = scanner.nextLine();
        Sponsor sponsor = new Sponsor(name);
        controller.addSponsor(sponsor);
    }

    private void getAllPlayersPerTeam() {
        System.out.print("Enter the team ID to get all players: ");
        int teamId = scanner.nextInt();
        NBATeam team = controller.getTeamByID(teamId);
        if (team != null) {
            controller.getAllPlayersPerTeam(team).forEach(player -> System.out.println(player.getName()));
        } else {
            System.out.println("Team not found.");
        }
    }

    private void getAllTeams() {
        controller.getAllTeams().forEach(team -> System.out.println(team.getName()));
    }

    private void getPlayerById() {
        System.out.print("Enter player ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Manager player = controller.getManagerOfPlayer(id);
        if (player != null) {
            System.out.println("Player found: " + player.getName());
        } else {
            System.out.println("Player not found.");
        }
    }

    private void getTeamById() {
        System.out.print("Enter team ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        NBATeam team = controller.getTeamByID(id);
        if (team != null) {
            System.out.println("Team found: " + team.getName());
        } else {
            System.out.println("Team not found.");
        }
    }

    private void getTeamByName() {
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();
        NBATeam team = controller.getTeamByID(name.hashCode());
        if (team != null) {
            System.out.println("Team found: " + team.getName());
        } else {
            System.out.println("Team not found.");
        }
    }

    private void getManagerOfPlayer() {
        System.out.print("Enter player ID: ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        Manager manager = controller.getManagerOfPlayer(playerId);
        if (manager != null) {
            System.out.println("Manager of player: " + manager.getName());
        } else {
            System.out.println("Manager not found.");
        }
    }

    private void getTeamManager() {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        Manager manager = controller.getTeamManager(teamName);
        if (manager != null) {
            System.out.println("Manager found: " + manager.getName());
        } else {
            System.out.println("Manager not found.");
        }
    }
    public static void main(String[] args) {
        InMemRepository<Player> playerRepo = new InMemRepository<>();
        InMemRepository<Sponsor> sponsorRepo = new InMemRepository<>();
        InMemRepository<Game> gameRepo = new InMemRepository<>();
        InMemRepository<NBATeam> teamRepo = new InMemRepository<>();
        InMemRepository<Manager> managerRepo = new InMemRepository<>();

        GeneralService service = new GeneralService(playerRepo, sponsorRepo, gameRepo, teamRepo, managerRepo);
        GeneralController controller = new GeneralController(service);

        Console console = new Console(controller);
        console.start();
    }
}
