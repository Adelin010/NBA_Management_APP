package nba.model;

public class Manager implements IdBounded{
    protected Integer id;
    protected String name;
    protected NBATeam team;
    public Manager(int id, String name, NBATeam team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }
    public Manager(int id, String name){
        this.id = id;
        this.name = name;
        this.team = null;
    }
    /*
     * Getter/Setters Name
     */
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    /*
     * Getters/Setters Team
     */
    public NBATeam getTeam() {return team;}
    public void setTeam(NBATeam team) {this.team = team;}

    @Override 
    public Integer getId(){return id;}

    public String toString(){
        return "";
    }
}
