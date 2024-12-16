package com.example.nba.model;

import com.example.nba.interfaces.*;

public class Season implements Entity{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected int year;
    protected String name;

    //CONSTRUCTORS
    public Season(String name,int year) {
        this.id = Season.MAX_ID;
        Season.MAX_ID++;
        this.year = year;
        this.name = name;
    }

    public Season(Integer id, String name,int year) {
        this.id = id;
        this.year = year;
        this.name = name;
    }

    public Season(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : 1+id;
        this.name = args[1];
        this.year = Integer.parseInt(args[2]);

    }

    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }

    public int getYear(){
        return year;
    }
    public String getName(){
        return name;
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
                name: %s,
                year: %d
            }
                """.formatted(id,name, year);
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    
    @Override
    public Integer getId(){
        return id;
    }
    
    @Override
    public String seq(boolean dbFlag){
        String res = "%d,%s,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(name, year);
        }
        
        return res.formatted(id, name, year);
    }
}
