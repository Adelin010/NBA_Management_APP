package nba.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nba.model.Game;
import nba.model.Manager;
import nba.model.NBATeam;
import nba.model.Player;
import nba.model.Sponsor;
import nba.repo.Repository;
import nba.model.NBAPlayer;

public class GeneralService {
    
    private final Repository<Player> players;
    private final Repository<Sponsor> sponsors;
    private final Repository<Game> games;
    private final Repository<NBATeam> teams;
    private final Repository<Manager> managers;

    public GeneralService(Repository<Player> players, Repository<NBATeam> teams, Repository<Sponsor> sponsors, Repository<Game> games, Repository<Manager> managers) {
        this.players = players;
        this.sponsors = sponsors;
        this.games =  games;
        this.teams = teams;
        this.managers =managers;
    }


   

    public void addPlayerToTeam(){

    }
    /**
     * Adding a game has the next constraints:
     * 1. The teams in the game must exist and be in the database
     * @param game
     */
    public void addGame(Game game){
        //Extract the teams to search for existance

        try{
            NBATeam t1 = game.getTeams()[0],
                t2 = game.getTeams()[1];

            if(teams.get(t1.getId()) == null || teams.get(t2.getId()) == null){
                throw new Exception("\nCan't add a Game between teams that are not inregistrated...\n");
            }
            /*  Verifing the format on the date and if the year, month and day are in a specific range
             * 
             */
            String regex = "[1-9]\\d{3}-[1-9]\\d?-[1-9]\\d?/g";
            Pattern patt = Pattern.compile(regex);
            Matcher match = patt.matcher(game.getDate());
            
            if(!match.matches()){
                throw new Exception("\nDate format invalid");
            }
            //Final functionality
            games.add(game);
        }catch(Exception exp){
            System.err.println(exp.getMessage());
            exp.printStackTrace();
        }
        

    }
    /**
     * Team related function - adds a new Team in the memory
     * @param team
     */
    public void addTeam(NBATeam team){
        Manager man = team.getManager();
        man.setTeam(team);
        System.out.println(man.getTeam().toString());
        teams.add(team);
    }
    /**
     * retunrs all the Teams from the memory
     * @return List<NBATeam>
     */
    public List<NBATeam> getAllTeams(){
        return teams.getAll();
    }

    public int changeTeamManager(int teamId, int managerId){
        Manager man = managers.get(managerId);
        System.out.println(man.getName());
        NBATeam team = teams.get(teamId);
        System.out.println(team.getManager().getName());

        //check if the new Manager is already assigned
        boolean isAssigned = false;
        for(NBATeam current: teams.getAll()){
            if(current.getManager().getId() == managerId)
                isAssigned = true;
        }
        if(isAssigned)
            return -1;
        // Fire the old manager
        Manager oldOne = team.getManager();
        oldOne.setTeam(null);
        //Hire the new one
        team.setManager(man);
        System.out.println(team.getManager().getName());
        return 0;
    }

    /**
     * Returns a Team by the name provided
     * @param teamName
     * @return
     */
    public NBATeam getTeamByTeamName(String teamName){
        System.out.println("\033[31m" + teamName);
        for(NBATeam team: teams.getAll()){
            System.out.println("\033[31m");
            System.out.println(team.toString());
            if(team.getName() == teamName)
                System.out.println("Found it................");
                return team;
        }
        return null;
    }
    /**
     * Returns a Team by the id
     * @param id
     * @return
     */
    public NBATeam getTeamById(int id){
        for(NBATeam team: teams.getAll()){
            if(team.getId() == id)
                return team;
        }
        return null;
    }
    /**
     * Manager related methods - adds a new Manager in memory
     * @param manager
     */
    public void addManager(Manager manager){
        managers.add(manager);
    }
    /**
     * Retrieves a Manager of a specific Team
     * @param teamName
     * @return
     */
    public Manager getTeamManager(String teamName){
        NBATeam team = getTeamByTeamName(teamName);
        System.out.println(team.toString());
        return team.getManager();
    }

    public Manager getManagerById(int id){
        return managers.get(id);
    }

    public Manager getManagerOfPlayer(int playerId){
        NBAPlayer player = getPlayerById(playerId);
        return player.getTeam().getManager();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public NBAPlayer getPlayerById(int id){
        
        return (NBAPlayer)players.get(id);
    }

    public void addSponsor(Sponsor sponsor){
        sponsors.add(sponsor);
    }

    public List<NBAPlayer> getAllPlayersPerTeam(NBATeam team){
        //check for All players if they belong to the team
        List<NBAPlayer> nbaPLayers = new ArrayList<>();
        for(Player player: this.players.getAll()){
            if(player instanceof NBAPlayer && ((NBAPlayer)player).getTeam().getId() == team.getId()){
                nbaPLayers.add((NBAPlayer)player);
            }
        }
        return nbaPLayers;
    }


}
