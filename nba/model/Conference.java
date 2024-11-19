package nba.model;

import nba.interfaces.*;

import java.util.List;

public class Conference implements IdBounded, FileBounded {
    //Fields
    protected static int MAX_ID = 1;
    protected Integer id;
    private String name;
    private List<Integer> teamIds;

    //Constructors
    public Conference(int id, String conferenceName) {
        this.id = Conference.MAX_ID;
        Conference.MAX_ID++;
        this.name = conferenceName;
        this.teamIds = null;
    }
    //Getters
    public String getName() {
        return name;
    }
    public List<Integer> getTeams(){return teamIds;}

    //Setters
    public void setName(String conferenceName) {
        this.name = conferenceName;
    }

    public void setTeams(List<Integer> teamIds){
        this.teamIds = teamIds;
    }

    //To String methods
    public String toString(){
        String res = """
            {
                id: %d,
                conferenceName: %s,
                teamIds: %s
            }
                """.formatted(id, name, teamIds.toString());
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
