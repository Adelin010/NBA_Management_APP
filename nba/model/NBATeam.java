package nba.model;

import java.util.List;
import nba.interfaces.*;


public class NBATeam implements IdBounded, FileBounded{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    protected String name;
    protected Integer managerId;
    protected Integer conferenceId;    
    protected List<Integer> playerIds; 
    //CONSTROCTORS
    public NBATeam(String name, Integer managerId) {
        this.id = NBATeam.MAX_ID;
        NBATeam.MAX_ID++;
        this.name = name;
        this.managerId = managerId;
        conferenceId = -1;
        playerIds = null;
    }
    public NBATeam(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = id > MAX_ID ? id : MAX_ID;
        MAX_ID++;
        this.name = args[1];
        this.managerId = args[2].equals("null") ? null : Integer.parseInt(args[2]);
        this.conferenceId = args[3].equals("null") ? null : Integer.parseInt(args[3]);
        //Set the players if any 
        if(args[4] == null)
            this.playerIds = null;
    }
    public NBATeam(String name){
        this.id = NBATeam.MAX_ID;
        NBATeam.MAX_ID++;
        this.name = name;
        this.managerId = -1;
        conferenceId = -1;
        playerIds = null;
    }
    //GETTERS
    public String getName(){
        return name;
    }
    public Integer getPlayerIfExists(int id){
        for(Integer playerId: playerIds){
            if(playerId == id)
                return playerId;
        }
        return -1;
    }
    public Integer getManagerId(){
        return managerId;
    }
    public Integer getConferenceId() {
        return conferenceId;
    }
    public List<Integer> getPlayerIds(){
        return playerIds;
    }

    //SETTERS
    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }
    public void setPlayerIds(List<Integer> playerIds){
        this.playerIds = playerIds;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void addPlayer(Integer player){
        playerIds.add(player);
    }
    public void setManagerId(Integer managerId){
        this.managerId = managerId;
    }
    
    //TO STRING METHOD
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s,
                managerId: %d,
                coferenceId: %d,
                playerIds: %s
            }
                """.formatted(id, name, managerId, conferenceId, playerIds.toString());
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    @Override 
    public Integer getId(){return id;}


    @Override 
    public String fileFormat(){
        return "";
    }

}
