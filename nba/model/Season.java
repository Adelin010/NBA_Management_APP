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
        String res ="{\n\tSeasonID:"+id+",\n\tYear:"+year+",\n\tGames:[\n";
        if(games!=null && games.size()>0){
            for(Game g : games){
                res += "\t\t"+g.toString().replace("\n","\n\t\t")+",\n";
            }
        }
        else{res+="\t\tThere are no games\n";}
        res+="\t]\n}";
        return res;
    }
}
