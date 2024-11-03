package nba.model;

public class NBAPlayer extends Player{
    private String position;
    private NBATeam team;
    private int points;
    private int rebounds;
    private int assists;
    public NBAPlayer(String name, int age, double salary, String position) {
        super(name, age, salary);
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public NBATeam getTeam() {
        return team;
    }
    public void setTeam(NBATeam team) {
        this.team = team;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getRebounds() {
        return rebounds;
    }
    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }
    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
}
