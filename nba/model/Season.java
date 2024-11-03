package nba.model;

import java.util.List;

public class Season implements IdBounded{
    protected Integer id;
    private int year;
    private List<Game> games;

    public Season(int year) {}
    public Integer getId(){
        return id;
    }
}
