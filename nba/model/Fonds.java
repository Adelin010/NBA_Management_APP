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
        String res = "{\n\tfondsId: " + id + ",\n";
        if (sponsorName != null) {
            res += "\tsponsorName: " + sponsorName + ",\n";
        }
        if (sponsorshipDeal != null) {
            res += "\tsponsorshipDeal: " + sponsorshipDeal + ",\n";
        }
        if (team != null) {
            res += "\tteam: {\n";
            res += "\t\tteamId: " + team.getId() + ",\n";
            res += "\t\tteamName: " + team.getName() + "\n";
            res += "\t}\n";
        }
        res += "}";
        return res;
    }
}
