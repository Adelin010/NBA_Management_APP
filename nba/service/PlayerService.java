package nba.service;

import nba.model.NBAPlayer;
import nba.repo.Repository;

public class PlayerService {
    
    private final Repository<NBAPlayer> players;

    public PlayerService(Repository<NBAPlayer> players){
        this.players = players;
    }

    public void addPlayer(NBAPlayer player){
        
    }
}
