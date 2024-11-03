package nba.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nba.model.Game;
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

    /**
     * Dependicy injections
     * @param players
     * @param sponsors
     * @param games
     */
    public GeneralService(Repository<Player> players, Repository<Sponsor> sponsors, Repository<Game> games, Repository<NBATeam> teams){
        this.players = players;
        this.games = games;
        this.sponsors = sponsors;
        this.teams = teams;
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
            games.create(game);
        }catch(Exception exp){
            System.err.println(exp.getMessage());
            exp.printStackTrace();
        }
        

    }


    public void addPlayer(Player player){
        players.create(player);
    }

    public void addSponsor(Sponsor sponsor){
        sponsors.create(sponsor);
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
