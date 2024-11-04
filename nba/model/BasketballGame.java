package nba.model;

public class BasketballGame extends Game{
    public BasketballGame(String date, NBATeam team1, NBATeam team2) {
        super(date, team1, team2);
        this.data = date;
        this.team1 = team1;
        this.team2 = team2;
    }

    public BasketballGame() {
        super();
    }

    public void setScores(int scoreTeam1, int scoreTeam2) {
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }
}
