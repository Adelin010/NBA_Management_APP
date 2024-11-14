package nba.model;

import java.util.List;

public class Conference implements IdBounded {
    protected Integer id;
    private String conferenceName;
    private List<NBATeam> teams;
    public Conference(int id, String conferenceName, List<NBATeam> teams) {
        this.id = id;
        this.conferenceName = conferenceName;
        this.teams = teams;
    }
    public String getConferenceName() {
        return conferenceName;
    }
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public void setTeams(List<NBATeam> teams){
        this.teams = teams;
    }
    public List<NBATeam> getTeams(){return teams;}

    @Override
    public Integer getId(){
        return id;
    }
    public String toString(){
        String res = "{ \n\tconferenceId: " + id + ",\n\tconferenceName: " + conferenceName + ",\n";
        if (teams != null){
            res+= "\tteams:[\n\t\t";
            for (NBATeam team : teams){
                res+= "{\n\t\t\tid: " + team.getId() + ",\n\t\t\tname: " + team.getName() + "\n\t\t},\n\t\t";
            }
            res+= "\n\t]\n";
        }
        res+= " }";
        return res;
    }

}
