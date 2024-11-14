package nba.model;

public class Sponsor implements IdBounded {
    protected Integer id;
    private String sponsorName;
    private String sponsorshipDeal;
    public Sponsor(int id,String sponsorName, String sponsorshipDeal) {
        this.id  = id;
        this.sponsorName = sponsorName;
        this.sponsorshipDeal = sponsorshipDeal;
    }

    public Sponsor(int id) {
        this.id = id;
    }

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
    public String toString(){
        String res = "{\n\tsponsorId: " + id + ",\n";
        if (sponsorName != null) {
            res+= "\tname: " + sponsorName + ",\n";
        } else {
            res+= "\tsponsorName: null,\n";
        }
        if (sponsorshipDeal != null) {
            res+= "\tdeal: " + sponsorshipDeal + ",\n";
        } else {
            res+= "\tdeal: null,\n";
        }
        res += "}";
        return res;
    }
}
