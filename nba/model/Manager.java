package nba.model;

import nba.interfaces.*;


public class Manager implements IdBounded, FileBounded{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String name;
    protected Integer teamId;
    public Manager(int id, String name, Integer teamId) {
        this.id = Manager.MAX_ID;
        Manager.MAX_ID++;
        this.name = name;
        this.teamId = teamId;
    }
    public Manager(int id, String name){
        this.id = Manager.MAX_ID;
        Manager.MAX_ID++;
        this.name = name;
        this.teamId = -1;
    }

    //Getters
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
        return "";
     }
 
}
