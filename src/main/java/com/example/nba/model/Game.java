package com.example.nba.model;

import com.example.nba.interfaces.*;


public class Game  implements Entity {
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String date;
    protected Integer team1Id;
    protected Integer team2Id;
    protected int scoreTeam1;
    protected int scoreTeam2;
    protected String type;
    protected Integer seasonId;

    //CONSTRUCTOR
    public Game(String date, int scoreTeam1, int scoreTeam2, int team1Id, int team2Id, String type, int seasonId){
        this.id = Game.MAX_ID;
        Game.MAX_ID++;
        this.date = date;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.type = type;
        this.seasonId = seasonId;
    }

    public Game(Integer id, String date, int scoreTeam1, int scoreTeam2, int team1Id, int team2Id, String type, int seasonId){
        this.id = id;
        this.date = date;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.type = type;
        this.seasonId = seasonId;
    }

    public Game(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id+1;
        this.date = args[1];
        this.scoreTeam1 = Integer.parseInt(args[4]);
        this.scoreTeam2 = Integer.parseInt(args[5]);
        this.team1Id = Integer.parseInt(args[2]);
        this.team2Id = Integer.parseInt(args[3]);
        this.type = args[6];
        this.seasonId = Integer.parseInt(args[7]);
    }

    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }
    public int getSeasonId(){
        return seasonId;
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
    public String seq(boolean dbFlag){
        String res = "%d,%s,%d,%d,%d,%d,%s,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(date, team1Id, team2Id, scoreTeam1, scoreTeam2, type, seasonId);
        }
        
        return res.formatted(id, date, team1Id, team2Id, scoreTeam1, scoreTeam2, type, seasonId);
    }
}
