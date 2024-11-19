package nba.model;

import nba.interfaces.*;

public class Fonds implements IdBounded, FileBounded{

    //Fields
    protected static int MAX_ID = 1;
    protected Integer id;
    protected Integer sponsorId;
    protected long dealAmount;
    protected Integer teamId;

    //Constructors
    public Fonds(int id, Integer sponsorId, Integer team, long dealAmount) {
        this.id = Fonds.MAX_ID;
        Fonds.MAX_ID++;
        this.sponsorId = sponsorId;
        this.dealAmount = dealAmount;
        this.teamId = team;
    }
    
    //Getters
    public Integer getSponsorId(){
        return sponsorId;
    }

    public long getDealAmount(){return dealAmount;}

    public Integer getTeamId(){return teamId;}

    //Setters
    public void setSponsorId(Integer sponsorId){
        this.sponsorId = sponsorId;
    }

    public void setDealAmount(long dealAmount){
        this.dealAmount = dealAmount;
    }
    public void setTeamId(Integer team){
        this.teamId = team;
    }

    //To String method
    public String toString(){
        String res = """
            {
                id: %d,
                sponsorId: %d,
                teamId: %d,
                dealAmount: %d
            }
                """.formatted(id, sponsorId, teamId, dealAmount);
        return res;
    }
    //Override for the interfaces
    @Override 
    public Integer getId(){
        return id;
    }

    @Override 
    public String fileFormat(){
        return "";
    }
}
