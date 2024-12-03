package com.example.nba.model;


import com.example.nba.interfaces.*;

public class Sponsor implements IdBounded, FileBounded, StreamedValues{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String name;

    //CONSTRUCTORS
    public Sponsor(int id,String sponsorName) {
        this.id = Sponsor.MAX_ID;
        Sponsor.MAX_ID++;
        this.name = sponsorName;
    }

    public Sponsor(String[] args){
        id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : 1+id;
        name = args[1];
    }

    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }

    public String getSponsorName() {
        return name;
    }

    //SETTERS
    public void setSponsorName(String sponsorName) {
        this.name = sponsorName;
    }
   
    //TO STRING METHOD  
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s
            }
                """.formatted(id, name);
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    @Override
    public Integer getId(){return id;}

    @Override 
    public String fileFormat(){
        return "%d,%s".formatted(id, name);
    }
    @Override 
    public String valuesof(){
        return "('%s')".formatted(name);
    }
}
