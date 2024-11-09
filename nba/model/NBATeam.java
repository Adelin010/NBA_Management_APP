package nba.model;

public class NBATeam implements IdBounded{
    protected Integer id;
    private String name;
    private Manager manager;
    private Conference conference;
    //Constructor not implemeted - WHY?
    public NBATeam(int id, String name, Manager manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {}
    public Manager getManager(){
        return manager;
    }
    public void setManager(Manager manager){}

    public Conference getConference() {
        return conference;
    }
    public void setConference(Conference conference) {
    }
    @Override 
    public Integer getId(){return id;}
}
