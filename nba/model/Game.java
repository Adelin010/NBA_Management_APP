package nba.model;

// 
public abstract class Game  implements IdBounded {
    protected Integer id;
    protected String date;
    protected NBATeam team1;
    protected NBATeam team2;
    protected int scoreTeam1;
    protected int scoreTeam2;
    //No Constructors in an abstract class

    @Override
    public Integer getId(){return id;}

    public NBATeam[] getTeams(){
        NBATeam[] teams = {team1, team2};
        return teams;
    }
    public String getDate(){
        return date;
    }
}
