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
    public String toString(){
        String res ="{\n\tgameId: " + id + ",\n\tdate: " + date + ",\n";
        if(team1 != null){
            res += "\tteam1: {\n\t\tid: " + team1.getId() + ",\n\t\tname: " + team1.getName() + "\n\t},\n";
        }
        if(team2 != null){
            res += "\tteam2: {\n\t\tid: " + team2.getId() + ",\n\t\tname: " + team2.getName() + "\n\t},\n";
        }
        res += "\tscoreTeam1: " + scoreTeam1 + ",\n";
        res += "\tscoreTeam2: " + scoreTeam2 + "\n";
        res+="}";
        return res;

    }

}
