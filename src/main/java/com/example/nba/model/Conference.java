package com.example.nba.model;

import com.example.nba.interfaces.*;

public class Conference implements IdBounded, FileBounded, StreamedValues {
    //Fields
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String name;
    //Constructors
    public Conference(String conferenceName) {
        this.id = Conference.MAX_ID;
        Conference.MAX_ID++;
        this.name = conferenceName;
    }

    public Conference(Integer id, String conferenceName) {
        this.id = id;
        this.name = conferenceName;
    }
    public Conference(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id+1;
        this.name = args[1];
    }
    //Getters
    public static int getMaxId(){
        return MAX_ID;
    }
    public String getName() {
        return name;
    }
    //Setters
    public void setName(String conferenceName) {
        this.name = conferenceName;
    }  
    //To String methods
    public String toString(){
        String res = """
            {
                id: %d,
                conferenceName: %s
            }
                """.formatted(id, name);
        return res;
    }
    //Override for the interfaces
    @Override
    public Integer getId(){
        return id;
    }
    @Override
    public String fileFormat(){
        return "%d,%s".formatted(id, name);
    }
    @Override 
    public String valuesof(){
        return "('%s')".formatted(name);
    }
}
