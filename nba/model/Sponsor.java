package nba.model;

public class Sponsor implements IdBounded {
    protected Integer id;
    private String sponsorName;
    private String sponsorshipDeal;
    public Sponsor(String sponsorName, String sponsorshipDeal) {}
    public String getSponsorName() {
        return sponsorName;
    }
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
    public String getSponsorshipDeal() {
        return sponsorshipDeal;
    }
    public void setSponsorshipDeal(String sponsorshipDeal) {
        this.sponsorshipDeal = sponsorshipDeal;
    }
    @Override
    public Integer getId(){return id;}
}
