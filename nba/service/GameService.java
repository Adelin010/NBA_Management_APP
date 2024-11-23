package nba.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.text.ParseException;

import nba.error.IdOutOfRangeException;
import nba.error.InexistenteInstance;
import nba.model.NBATeam;
import nba.model.Game;
import nba.repo.Repo;



public class GameService {
    Repo<Game> repG;
    Repo<NBATeam> repT;


    private class ComparatorByDate implements Comparator<Game>{
        @Override 
        public int compare(Game g1, Game g2){
            //get both the dates 
            String d1 = g1.getDate(), d2 = g2.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try{
                Date date1 = sdf.parse(d1);
                Date date2 = sdf.parse(d2);
                return date1.compareTo(date2);

            }catch(ParseException exp){
                exp.printStackTrace();
            }
            return 0;
        }
    }

    public GameService(Repo<Game> repG, Repo<NBATeam> repT){
        this.repG = repG;
        this.repT = repT;
        //init MAX_ID
        repG.getAll();
    }

    public void add(Game game)throws InexistenteInstance{
        /*
         * Check Foreign_keys constraints
         */
        Integer[] teamIds = game.getTeamsId();
        if(repT.get(teamIds[0]) == null || repT.get(teamIds[1]) == null){
            throw new InexistenteInstance("\033[31mException raised in Add_Team transaction. Conference_Foreign_Key violation, NBATeam inexistante.\033[0m");
        }
        repG.add(game);
    }

    public Game getById(Integer id){
        return repG.get(id);
    }

    public List<Game> getAll(){
        return repG.getAll();
    }

    public void delete(Integer id)throws IdOutOfRangeException{
        if(id >= Game.getMaxId())
            throw new IdOutOfRangeException("\033[31mId out of bound exception for Delete_Game transaction...\033[0m");
        repG.delete(id);
    }

    public List<Game> sortByDate(){
        List<Game> games = repG.getAll();
        Collections.sort(games, new ComparatorByDate());
        return games;
    }
}
