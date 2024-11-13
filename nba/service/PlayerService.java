package nba.service;

import nba.model.NBAPlayer;
import nba.model.NBATeam;
import nba.repo.Repository;

import java.util.ArrayList;
import java.util.List;

import nba.exceptions.*;

public class PlayerService {
    
    private final Repository<NBAPlayer> players;
    private final Repository<NBATeam> teams;

    public PlayerService(Repository<NBAPlayer> players, Repository<NBATeam> teams){
        this.players = players;
        this.teams = teams;
    }
    /**
     * Function that adds the player to a team if the team exists
     * @param player
     * @throws InexistenteInstance
     */
    public void addToTeam(NBAPlayer player, int teamId)throws InexistenteInstance{
        //check the existance of the team 
        NBATeam team = teams.get(teamId);
        if(team == null)
            throw new InexistenteInstance("Attempt to add a player to a team that does not exists...");
        
        //Add the player to the team
        team.addPlayer(player);
    }

    /**
     * Add a player, check if the primary key constraint is met with the id field and 
     * if the player is in a team add it to the team -> forward the errors 
     * @param player
     * @throws AlreadyExistingException
     * @throws InexistenteInstance
     */
    public void addPlayer(NBAPlayer player, int teamId) throws AlreadyExistingException, InexistenteInstance{
        //check for id clashes
        if(players.get(player.getId()) != null){
            throw new AlreadyExistingException("Attempt to add a player that already exists...");
        }
        //If the player was assign already a team add the player into the team list of players
        if(teamId != -1)
            addToTeam(player, teamId);
        players.add(player);
    }

    public NBAPlayer getPlayerById(int id){
        return players.get(id);
    }

    /**
     * Returns a list of players after name in case we have many persons with the same name
     * if there is no player with this name returns an empty list
     * @param name
     * @return
     */
    public List<NBAPlayer> getPlayersByName(String name){
        List<NBAPlayer> matched = new ArrayList<>();
        List<NBAPlayer> available = players.getAll();
        if(available.size() == 0)
            return matched;
        
        for(NBAPlayer player: available){
            if(name.equals(player.getName()))
                matched.add(player);
        }
        return matched;
    }


    public List<NBAPlayer> getPlayersBySalary(double salary){
        List<NBAPlayer> matched = new ArrayList<>();
        List<NBAPlayer> available = players.getAll();
        if(available.size() == 0)
            return matched;
        
        for(NBAPlayer player: available){
            if(salary == player.getSalary())
                matched.add(player);
        }
        return matched;
    }

    public List<NBAPlayer> getPlayersByPosition(String position){
        List<NBAPlayer> available = players.getAll();
        return available.stream().filter(player -> position.equals(player.getPosition())).toList();

    }
    
}
