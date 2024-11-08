package nba.model;

public class PlayoffGame extends Game{
    public PlayoffGame(int id, String date,NBATeam team1,NBATeam team2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
    }
}
