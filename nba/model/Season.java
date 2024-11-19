package nba.model;

import java.util.List;
import nba.interfaces.*;

public class Season implements IdBounded, FileBounded{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    private int year;
    private List<Integer> gameIds;

    //CONSTRUCTORS
    public Season(int id ,int year) {
        this.id = Season.MAX_ID;
        Season.MAX_ID++;
        this.year = year;
        gameIds = null;
    }

    //GETTERS
    public int getYear(){
        return year;
    }
    public List<Integer> getGames(){
        return gameIds;
    }
    //SETTERS
    public void setYear(int year){
        this.year = year;
    }
    public void setGames(List<Integer> gameIds){
        this.gameIds = gameIds;
    }

    //TO STRING METHOD
    @Override
    public String toString() {
        String res ="""
            {
                id: %d,
                year: %d,
                gameIds: %s
            }
                """.formatted(id, year, gameIds.toString());
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    @Override
    public String fileFormat(){
        return "";
    }
    @Override
    public Integer getId(){
        return id;
    }
}
