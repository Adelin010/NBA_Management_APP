package nba.model;


public class NBAPlayer extends Player{
    //FIELDS
    protected String position;
    protected Integer teamId;
    protected int points = 0;
    protected int rebounds = 0;
    protected int assists = 0;
    protected static int MAX_ID = 1;
    
    //CONSTRUCTORS
    public NBAPlayer(String name, int age, double salary, String position) {
        this.id = MAX_ID;
        NBAPlayer.MAX_ID++;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.teamId = -1;

    }

    public NBAPlayer(String[] args){
        id = Integer.parseInt(args[0]);
        MAX_ID = id >= MAX_ID ? id+1 : MAX_ID;
        name = args[1];
        age = Integer.parseInt(args[2]);
        salary = Double.parseDouble(args[3]);
        position = args[4];
        points = Integer.parseInt(args[5]);
        rebounds = Integer.parseInt(args[6]);
        assists = Integer.parseInt(args[7]);
        teamId = args[8].equals("null") ? null : Integer.parseInt(args[8]); 
    }

    public NBAPlayer(String name, int age, double salary, String position,int points, int rebounds, int assists ,int teamId) {
        this.id = MAX_ID;
        NBAPlayer.MAX_ID++;

        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.teamId = teamId;
    }

    public NBAPlayer() {
        this.id = MAX_ID;
        NBAPlayer.MAX_ID++;
        this.name = "default";
        this.age = 0;
        this.salary = 0.00;
        this.position = "unknown";
        this.teamId = -1;
    }
    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }

    public String getPosition() {
        return position;
    }
    
    public int getTeamId() {
        return teamId;
    }
    
    public int getPoints() {
        return points;
    }
    
    public int getRebounds() {
        return rebounds;
    }
    
    public int getAssists() {
        return assists;
    }

    //SETTERS
    public void setPoints(int points) {
        this.points = points;
    }
    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }
    public void setTeam(int teamId) {
        this.teamId= teamId;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    

    //TO STRING METHOD
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s,
                age: %d,
                position: %s,
                salary: %.2f,
                points: %d,
                rebounds: %d,
                assists: %d,
                teamId: %d
            }
                """.formatted(id, name, age,position, salary, points, rebounds, assists, teamId);
        return res;
    }
    
    //OVERRIDE FOR THE INTERFACE
    public String fileFormat(){
        return "%d,%s,%d,%.2f,%s,%d,%d,%d,%d".formatted(id, name, age, salary, position,points,rebounds, assists ,teamId);
    }
}








