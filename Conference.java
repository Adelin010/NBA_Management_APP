import java.util.List;

public class Conference {
    private String conferenceName;
    private List<NBATeam> teams;
    public Conference(String conferenceName, List<NBATeam> teams) {
    }
    public String getConferenceName() {
        return conferenceName;
    }
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
}
