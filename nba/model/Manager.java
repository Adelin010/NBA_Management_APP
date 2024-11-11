package nba.model;

public class Manager implements IdBounded{
    protected Integer id;
    private String name;
    private NBATeam team = null;
    public Manager(int id, String name, NBATeam team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }
    public Manager(int id, String name){
        this.id = id;
        this.name = name;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public NBATeam getTeam() {return team;}
    public void setTeam(NBATeam team) {this.team = team;}

    @Override 
    public Integer getId(){return id;}

    public String toString(){
        String res = "Manager " + name + " with id " + id;
        if(team != null)
            res += " of team " + team.getName();
        
        return res;
    }
}
