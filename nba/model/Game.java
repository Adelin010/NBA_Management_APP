package nba.model;

import nba.interfaces.*;


public class Game  implements IdBounded, FileBounded {
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String date;
    protected Integer team1Id;
    protected Integer team2Id;
    protected int scoreTeam1;
    protected int scoreTeam2;
    protected String type;

    //CONSTRUCTOR
    public Game(int id, String date, int team1Id, int team2Id, int scoreTeam1, int scoreTeam2, String type){
        this.id = Game.MAX_ID;
        Game.MAX_ID++;
        this.date = date;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.type = type;
    }

    public Game(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id;
        MAX_ID++;
        this.date = args[1];
        this.team1Id = Integer.parseInt(args[2]);
        this.team2Id = Integer.parseInt(args[3]);
        this.scoreTeam1 = Integer.parseInt(args[4]);
        this.scoreTeam2 = Integer.parseInt(args[5]);
    }

    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }

    public Integer[] getTeamsId(){
        Integer[] teams = {team1Id, team2Id};
        return teams;
    }
    public String getType(){
        return type;
    }
    public String getDate(){
        return date;
    }
    public int[] getScores(){
        int[] scores =  {scoreTeam1, scoreTeam2};
        return scores;
    }
    //SETTERS
    
    
    //TO STRING METHOD
    public String toString(){
        String res = """
                {
                    id: %d,
                    date: %s,
                    team1Id: %d,
                    scoreTeam1: %d,
                    team2Id: %d,
                    scoreTeam2: %d
                }
                """.formatted(id, date, team1Id, scoreTeam1, team2Id, scoreTeam2);

        return res;
    }

    //Override for interfaces
    @Override
    public Integer getId(){return id;}
    @Override
    public String fileFormat(){
        return "%d,%d,%d,%d,%d,%s,%s".formatted(id, team1Id, team2Id, scoreTeam1, scoreTeam2, date, type);
    }
}
