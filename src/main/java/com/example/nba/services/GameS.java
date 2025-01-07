package com.example.nba.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import com.example.nba.error.AlreadyExistingException;
import com.example.nba.error.AttributeFaultedException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.Game;
import com.example.nba.model.NBATeam;
import com.example.nba.repos.RepoDB;

public class GameS {
    private final Repo<NBATeam> rt;
    private final Repo<Game> rg;
    private final boolean isQ;


    private class DateComparison implements Comparator<Game>{
        @Override 
        public int compare(Game g1, Game g2){
            SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
            try{
                Date d1 = form.parse(g1.getDate());
                Date d2 = form.parse(g2.getDate());
                System.out.println(d1);
                System.out.println(d2);
                return d1.compareTo(d2);
            }catch(ParseException p){
                System.out.println(p.getMessage());
            }
            return 0;
        }
    }

    public GameS(Repo<NBATeam> rt, Repo<Game> rg){
        this.rt = rt;
        this.rg = rg;
        if(rg instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(Game game)throws InexistenteInstance{
        // Check for the integrity of the foreign key
        NBATeam t1 = rt.get(game.getTeamsId()[0]);
        NBATeam t2 = rt.get(game.getTeamsId()[1]);
        if(t1 == null || t2 == null)
            throw new InexistenteInstance("The teams assigned to the game are null either of them. Foreign key violation...");
        
        Game g = rg.get(game.getId());
        if(g != null)
            throw new AlreadyExistingException("The manager id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rg.add(game);
    }

    public void delete(Integer id){
        rg.delete(id);
    }

    public Game getById(Integer id ){
        return rg.get(id);
    }
    public List<Game> getAll(){
        return rg.getAll();
    }

    /**
     * Ascending order sorting function for the Game
     * @return a list of the games sorted by the Game
     */
    public List<Game> sortByDate(){
        if(isQ){
            return null;
        }else{
            List<Game> games = new ArrayList<>(rg.getAll());
            Collections.sort(games, new DateComparison());
            return games;
        }
    }

    public List<Game> getGamesPerTeam(String teamName){
        if(isQ){
            return ((RepoDB<Game>)rg).getGamesPerTeam(teamName);
        }else{
            //check if the team is real
            NBATeam t = rt.getAll().stream().filter((var team) -> team.getName().equals(teamName)).findFirst().orElse(null);
            if(t == null)
                throw new AttributeFaultedException("Team name %s faulted exception. The name is wrong...".formatted(teamName));

            return rg.getAll().stream().filter((var game) -> {
                Integer[] teamIds = game.getTeamsId();
                if(rt.get(teamIds[0]).getName().equals(teamName) || rt.get(teamIds[1]).getName().equals(teamName))
                    return true;
                else 
                    return false;
            }).collect(Collectors.toList());
        }   
    }

}
