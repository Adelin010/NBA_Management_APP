package nba.model;

public class PlayOffGame extends Game{
    public PlayOffGame(int id, String date, NBATeam team1, NBATeam team2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
    }
    public String toString(){
        String res ="{\n\tgameId: " + id + ",\n\tdate: " + date + ",\n";
        if(team1 != null){
            res += "\tteam1: {\n\t\tteamId: " + team1.getId() + ",\n\t\tteamName: " + team1.getName() + "\n\t},\n";
        }
        if(team2 != null){
            res += "\tteam2: {\n\t\tteamId: " + team2.getId() + ",\n\t\tteamName: " + team2.getName() + "\n\t},\n";
        }
        res += "\tscoreTeam1: " + scoreTeam1 + ",\n";
        res += "\tscoreTeam2: " + scoreTeam2 + "\n";
        res+="}";
        return res;

    }
}
