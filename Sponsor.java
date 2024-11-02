public class Sponsor {
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
    public void changeSponsor(String sponsorName, String sponsorshipDeal) {}
    public void addSponsor(String sponsorName, String sponsorshipDeal) {}
    public void removeSponsor(String sponsorName) {}
    public void increaseSponsorDeal(String sponsorName, String sponsorshipDeal) {}
    public void decreaseSponsorDeal(String sponsorName, String sponsorshipDeal) {}

}
