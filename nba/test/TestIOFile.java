package nba.test;

import java.util.List;
import nba.model.NBAPlayer;
import nba.model.NBATeam;
import nba.util.FReader;
import nba.util.FWriter;

public class TestIOFile {


    public void test()throws Exception{
        FReader<NBATeam> fr = new FReader<>("assets/db/team.txt", NBATeam.class);
        FReader<NBAPlayer> fr2 = new FReader<>("assets/db/player.txt", NBAPlayer.class);

        //test all players
        List<NBAPlayer> players = fr2.initListElements();
        int id = 1;
        for(NBAPlayer p: players){
            assert p.getId() == id : "Wrong Id at pbject player";
            id++;
        }
        //test random player
        long max_id = players.stream().count();
        for(int i = 1; i <= max_id; ++i){
            NBAPlayer p = fr2.getKthEntry(i);
            assert p.getId() == i : "Knth method wrong return object";
        }

        //Test all teams
        List<NBATeam> teams = fr.initListElements();
        id = 1;
        for(NBATeam t: teams){
            assert t.getId() == id : "Wrong Id at pbject player";
            id++;
        }
        //Test random team
        max_id = teams.stream().count();
        for(int i = 1; i <= max_id; ++i){
            NBATeam t = fr.getKthEntry(i);
            assert t.getId() == i : "Knth method wrong return object";
        }


        FWriter<NBAPlayer> fw = new FWriter<>("assets/db/player.txt", NBAPlayer.class);
        FWriter<NBATeam> fw2 = new FWriter<>("assets/db/team.txt", NBATeam.class);

        fw.append("9,Alehandro9,26,126.07,PG,3");
        fw.delete(5);
        fw.update(5, "5,Alehandro6-5,31,175.05,SF,2");

        fw2.append("4,Buan,2");
        fw2.delete(2);
        fw2.update(3, "3,Buan,1");
    }

    
    
}
