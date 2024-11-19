package nba.model;


import nba.interfaces.*;

public class Sponsor implements IdBounded, FileBounded{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer id;
    private String name;

    //CONSTRUCTORS
    public Sponsor(int id,String sponsorName) {
        this.id = Sponsor.MAX_ID;
        Sponsor.MAX_ID++;
        this.name = sponsorName;
    }

    //GETTERS
    public String getSponsorName() {
        return name;
    }

    //SETTERS
    public void setSponsorName(String sponsorName) {
        this.name = sponsorName;
    }
   
    //TO STRING METHOD  
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s
            }
                """.formatted(id, name);
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
