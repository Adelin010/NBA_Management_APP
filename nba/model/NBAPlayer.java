package nba.model;

public class NBAPlayer extends Player{
    private String position;
    private NBATeam team;
    private int points;
    private int rebounds;
    private int assists;
    //Refactor the constructors id must always be included
    public NBAPlayer(int id,String name, int age, double salary, String position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }

    public NBAPlayer(String name, int id, double salary, String position) {
        this.id = id;
        this.name = "default";
        this.age = 0;
        this.salary = 0.00;
        this.position = "unknown";
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
