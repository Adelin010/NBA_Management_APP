package nba.model;

import nba.interfaces.*;

public class Conference implements IdBounded, FileBounded {
    //Fields
    protected static int MAX_ID = 1;
    protected Integer id;
    private String name;
    //Constructors
    public Conference(int id, String conferenceName) {
        this.id = Conference.MAX_ID;
        Conference.MAX_ID++;
        this.name = conferenceName;
    }
    public Conference(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id;
        MAX_ID++;
        this.name = args[1];
    }
    //Getters
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
        return "";
    }
}
