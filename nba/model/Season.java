package nba.model;

import java.util.List;

public class Season implements IdBounded{
    protected Integer id;
    private int year;
    private List<Game> games;

    public Season(int id ,int year) {
        this.id = id;
        this.year = year;
    }
    public Integer getId(){
        return id;
    }
    public int getYear(){
        return year;
    }
    public List<Game> getGames(){
        return games;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setGames(List<Game> games){
        this.games = games;
    }
    @Override
    public String toString() {
        String res = "{\n";
        res+= "\tid: " + id + ",\n";
        res+= "\tyear: " + year + ",\n";

        if (games != null && !games.isEmpty()) {
            res+= "\tgames: [\n";
            for (Game game : games) {
                /*
                    res += "\t\t{\n\t\t\tid: game.getIn(),\n\t\t\t"
                 */
                res+= "\t\t" + game.toString().replace("\n", "\n\t\t") + ",\n";
            }
            res+= "\t]\n";
        }
        res+= "}";
        return res;
    }
}
