package nba.model;

import java.util.ArrayList;
import java.util.List;

public class NBATeam implements IdBounded{
    protected Integer id;
    protected String name;
    protected Manager manager;
    protected Conference conference;
    protected List<NBAPlayer> players; 
    //Constructor not implemeted - WHY?
    public NBATeam(int id, String name, Manager manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        conference = null;
        players = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Manager getManager(){
        return manager;
    }
    public void setManager(Manager manager){
        this.manager = manager;
    }

    public Conference getConference() {
        return conference;
    }
    public void setConference(Conference conference) {
        this.conference = conference;
    }
    public void setPlayers(List<NBAPlayer> players){
        this.players = players;
    }
    public List<NBAPlayer> getPlayers(){
        return players;
    }
    public void addPlayer(NBAPlayer player){
        players.add(player);
    }
    public NBAPlayer getPlayer(int id){
        for(NBAPlayer player: players){
            if(player.getId() == id)
                return player;
        }
        return null;
    }

    @Override 
    public Integer getId(){return id;}

    public String toString(){
        String res = "{ \n\tteamId: " + id + ",\n\tteamName: " + name + ",\n";
        if(manager != null){
            res += "\tmanager: {\n\t\tid: " + manager.id + ",\n\t\tname: " + manager.name + "\n\t}\n\t";
        }
        if(players.size() != 0){
            res += "\tplayers : [\n\t\t";
            for(NBAPlayer player: players){
                res += "{\n\t\t\tid: " + player.id + ",\n\t\t\tname: " + player.name + ",\n\t\t\tposition: " + player.position + ",\n\t\t\tsalary: " + player.salary + ",\n\t\t\tage: " + player.age + "\n\t\t},\n\t\t";
            }
        }
        res += "}";
        return res;
    }

}
