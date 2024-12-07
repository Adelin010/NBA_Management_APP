package com.example.nba.controller;

import java.util.List;

import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.Game;
import com.example.nba.service.GameService;

public class GameController {
     public GameService gs;

    public GameController(GameService gs){
        this.gs = gs;
    }
    /**
     * Adds a new game
     * @param date      the date of the game
     * @param scoreTeam1 the score of team 1
     * @param scoreTeam2 the score of team 2
     * @param team1Id   the ID of team 1
     * @param team2Id   the ID of team 2
     * @param type      the type of game
     * @param seasonId  the ID of the season
     * @throws InexistenteInstance if any of the ID do not correspond
     */
    public void add(String date, int scoreTeam1, int scoreTeam2, Integer team1Id, Integer team2Id, String type, Integer seasonId)throws InexistenteInstance{
        Game player = new Game(date, scoreTeam1, scoreTeam2, team1Id, team2Id, type, seasonId);
        gs.add(player);
    }
    /**
     * Retrieves a game by its ID
     * @param id the ID of the game to retrieve
     * @return the Game object corresponding to the ID
     */

    public Game getById(Integer id){
        return gs.getById(id);
    }
    /**
     * Gets all gams
     * @return a list of all Game objects
     */
    public List<Game> getAll(){
        return gs.getAll();
    }
    /**
     * Deletes a game by ID
     * @param id the ID of the game to delete
     */
    public void delete(Integer id){
        gs.delete(id);
    }
    /**
     * Sorts all games by date
     * @return a list of all games sorted by date
     */
    public List<Game> sortByDate(){
        return gs.sortByDate();
    }
}
