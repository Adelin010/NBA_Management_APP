package nba.model;

import nba.interfaces.*;


public class NBATeam implements IdBounded, FileBounded{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String name;
    protected Integer conferenceId;    
    //CONSTROCTORS
    public NBATeam(String name, Integer confId) {
        this.id = NBATeam.MAX_ID;
        NBATeam.MAX_ID++;
        this.name = name;
        conferenceId = confId;
    }
    public NBATeam(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = id >= MAX_ID ? id+1 : MAX_ID;
        MAX_ID++;
        this.name = args[1];
        this.conferenceId = args[2].equals("null") ? null : Integer.parseInt(args[2]);
        
    }
    
    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }

    public String getName(){
        return name;
    }
    
   
    public Integer getConferenceId() {
        return conferenceId;
    }
  

    //SETTERS
    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }
   
    public void setName(String name) {
        this.name = name;
    }

    
    //TO STRING METHOD
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s,
                coferenceId: %d
            }
                """.formatted(id, name,conferenceId);
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    @Override 
    public Integer getId(){return id;}


    @Override 
    public String fileFormat(){
        return "%d,%s,%d".formatted(id, name, conferenceId);
    }

}
