package com.example.nba.model;

import com.example.nba.interfaces.*;

public class Found implements Entity{
    //Fields
    protected static int MAX_ID = 1;
    protected Integer id;
    protected Integer sponsorId;
    protected long dealAmount;
    protected Integer teamId;
    //Constructors
    public Found(Integer sponsorId, Integer team, long dealAmount) {
        this.id = Found.MAX_ID;
        Found.MAX_ID++;
        this.sponsorId = sponsorId;
        this.dealAmount = dealAmount;
        this.teamId = team;
    }

    public Found(Integer id, Integer sponsorId, Integer team, long dealAmount) {
        this.id = id;
        this.sponsorId = sponsorId;
        this.dealAmount = dealAmount;
        this.teamId = team;
    }
    public Found(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id+1;
        this.teamId = "null".equals(args[1]) ? null : Integer.parseInt(args[2]);
        this.sponsorId = "null".equals(args[2]) ? null : Integer.parseInt(args[1]);
        this.dealAmount = Long.parseLong(args[3]);
    } 
    //Getters
    public static int getMaxId(){
        return MAX_ID;
    }

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
                teamId: %d,
                sponsorId: %d,
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
    public String seq(boolean dbFlag){
        String res = "%d,%d,%d,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(teamId, sponsorId, dealAmount);
        }
        
        return res.formatted(id, teamId, sponsorId, dealAmount);
    }
}

