package nba.model;

public class Fonds implements IdBounded{

    protected Integer id;
    private String sponsorName;
    private String sponsorshipDeal;
    private NBATeam team;
    public Fonds(String sponsorName, String sponsorshipDeal, NBATeam team) {}
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
}
