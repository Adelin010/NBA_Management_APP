package nba.model;

public class NBAPlayer extends Player{
    protected String position;
    protected NBATeam team;
    protected int points;
    protected int rebounds;
    protected int assists;
    //Refactor the constructors id must always be included
    public NBAPlayer(int id,String name, int age, double salary, String position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.team = null;
    }

    public NBAPlayer(int id) {
        this.id = id;
        this.name = "default";
        this.age = 0;
        this.salary = 0.00;
        this.position = "unknown";
        this.team = null;
    }
    /*
     * Getters/Setters Position
     */
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    /*
     * Getters/Setters Team
     */
    public NBATeam getTeam() {
        return team;
    }
    public void setTeam(NBATeam team) {
        this.team = team;
    }
    /*
     * Getters/Setters Points
     */
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
