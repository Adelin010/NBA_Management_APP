package nba.enums;

public enum Positions{
    PG("Point Guard"),
    SG("shooting Guard"),
    SF("Small Forward"),
    PF("Power Forward"),
    C("Center");

    private final String desc;

    Positions(String desc){
        this.desc = desc;
    }

    public String description(){
        return desc;
    }
}