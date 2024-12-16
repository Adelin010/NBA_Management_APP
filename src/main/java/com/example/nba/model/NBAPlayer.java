package com.example.nba.model;


public class NBAPlayer extends Person{
    //FIELDS
    protected String position;
    protected Integer teamId;
    protected int points = 0;
    protected int rebounds = 0;
    protected int assists = 0;
    protected double salary;
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

    public NBAPlayer(Integer id,String name, int age, double salary, String position) {
        this.id = id;
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
        position = args[3];
        salary = Double.parseDouble(args[4]);
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
    public double getSalary(){
        return salary;
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
    public void setSalary(Double salary){
        this.salary = salary;
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
   
    @Override
    public String seq(boolean dbFlag){
        String res = "%d,%s,%d,%s,%.2f,%d,%d,%d,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(name, age, position, salary, points, rebounds, assists, teamId);
        }
        
        return res.formatted(id, name, age,position, salary,points,rebounds, assists ,teamId);
    }
}








