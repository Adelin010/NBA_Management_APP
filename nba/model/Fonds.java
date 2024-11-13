package nba.model;

public class Fonds implements IdBounded{

    protected Integer id;
    private String sponsorName;
    private String sponsorshipDeal;
    private NBATeam team;
    public Fonds(int id, String sponsorName, String sponsorshipDeal, NBATeam team) {
        this.id = id;
        this.sponsorName = sponsorName;
        this.sponsorName = sponsorshipDeal;
        this.team = team;
    }
    @Override 
    public Integer getId(){return id;}

    public String getSponsorName(){
        return sponsorName;
    }

    public String getSponsorshipDeal(){return sponsorshipDeal;}
    public NBATeam getTeam(){return team;}
    public void setSponsorName(String sponsorName){
        this.sponsorName = sponsorName;
    }
    public void setSponsorshipDeal(String sponsorshipDeal){
        this.sponsorshipDeal = sponsorshipDeal;
    }
    public void setTeam(NBATeam team){
        this.team = team;
    }
    public String toString(){
        String res = "{\n\tFondsID:"+ id+",\n\tSponsor Name: "+ sponsorName+"\n\tSponsor Deal: "+ sponsorshipDeal+",\n";
        if(team != null){
            res+= "\tTeam: {\n\t\tTeamID:" + team.getId() +"\n\t\tTeam Name:"+ team.getName()+"\n\t}\n";
        }
        res+="}";
        return res;
    }
}
