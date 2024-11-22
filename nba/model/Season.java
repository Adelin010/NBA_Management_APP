package nba.model;

import nba.interfaces.*;

public class Season implements IdBounded, FileBounded{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    private int year;

    //CONSTRUCTORS
    public Season(int id ,int year) {
        this.id = Season.MAX_ID;
        Season.MAX_ID++;
        this.year = year;
    }

    public Season(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id;
        MAX_ID++;
        this.year = Integer.parseInt(args[1]);

    }

    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }

    public int getYear(){
        return year;
    }
    
    //SETTERS
    public void setYear(int year){
        this.year = year;
    }

    //TO STRING METHOD
    @Override
    public String toString() {
        String res ="""
            {
                id: %d,
                year: %d
            }
                """.formatted(id, year);
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    @Override
    public String fileFormat(){
        return "%d,%d".formatted(id, year);
    }
    @Override
    public Integer getId(){
        return id;
    }
}
