package nba.model;

public class Manager implements IdBounded{
    protected Integer id;
    private String name;
    private NBATeam team;
    public Manager(String id, NBATeam name) {
        this.id = Integer.valueOf(id);
        this.name = String.valueOf(name);
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public NBATeam getTeam() {return team;}
    public void setTeam(NBATeam team) {this.team = team;}

    @Override 
    public Integer getId(){return id;}
}
