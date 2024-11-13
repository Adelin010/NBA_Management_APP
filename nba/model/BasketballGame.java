package nba.model;

public class BasketballGame extends Game{
    public BasketballGame(String date, NBATeam team1, NBATeam team2) {
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
    }

    public BasketballGame() {
    
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
    public String toString() {
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
