package nba.model;

public class ConferenceGame extends Game{
    public ConferenceGame(int id,String data, NBATeam team1,NBATeam team2) {
        this.date = data;
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
    }

}
