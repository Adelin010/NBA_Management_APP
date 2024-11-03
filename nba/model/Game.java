package nba.model;


public abstract class Game  implements IdBounded {
    protected Integer id;
    protected String data;
    protected NBATeam team1;
    protected NBATeam team2;
    protected int scoreTeam1;
    protected int scoreTeam2;
    public Game(String data,NBATeam team1,NBATeam team2){}
    @Override 
    public Integer getId(){return id;}

    public NBATeam[] getTeams(){
        NBATeam[] teams = {team1, team2};
        return teams;
    }
    public String getDate(){
        return data;
    }
}
