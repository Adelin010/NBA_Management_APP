package nba.model;

import nba.interfaces.*;


public class Manager implements IdBounded, FileBounded{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String name;
    protected Integer teamId;
    public Manager(String name, Integer teamId){
        this.id = Manager.MAX_ID;
        Manager.MAX_ID++;
        this.name = name;
        this.teamId = teamId;
    }
    public Manager(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id+1;
        this.name = args[1];
        this.teamId = "null".equals(args[2]) ? null : Integer.parseInt(args[2]);
    }

    //Getters
    public static int getMaxId(){
        return MAX_ID;
    }
    public String getName() {return name;}
    public Integer getTeamId() {return teamId;}
    
    //Setters
    public void setName(String name) {this.name = name;}
    public void setTeamId(Integer teamId) {this.teamId = teamId;}

   


    public String toString() {
        String res = """
            {
                id: %d,
                name: %s,
                teamId: %d
            }
                """.formatted(id, name, teamId);
        return res;
    }

    @Override 
    public Integer getId(){return id;}


    @Override
    public String fileFormat(){
        return "%d,%s,%d".formatted(id, name, teamId);
     }
 
}
