package nba.test;

import java.util.List;

import nba.util.*;
import nba.model.*;
import nba.controller.*;
import nba.service.*;
import nba.repo.*;

public class TestIOFile {


    public void testFiles()throws Exception{
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


    public void testController(){
        Repo<NBATeam> rt = new RepoFile<>("assets/db/team.txt", NBATeam.class);
        Repo<NBAPlayer> rp = new RepoFile<>("assets/db/player.txt", NBAPlayer.class);
        Repo<Conference> rc = new RepoFile<>("assets/db/coference.txt", Conference.class);
        Repo<Game> rg = new RepoFile<>("assets/db/game.txt", Game.class);
        Repo<Manager> rm = new RepoFile<>("assets/db/manager.txt", Manager.class);

        ManagerService ms = new ManagerService(rm, rt);
        GameService gs  = new GameService(rg, rt);
        TeamService ts = new TeamService(rt, rc);
        PlayerService ps = new PlayerService(rp, rt);

        PlayerController pc = new PlayerController(ps);
        TeamController tc = new TeamController(ts);
        ManagerController mc = new ManagerController(ms);
        GameController gc = new GameController(gs);

        pc.add("Added", 32, 21.00, "C", 123, 21, 3, 1);
        assert pc.getAll().size() == 9 : "add failed for player";
        pc.delete(4);
        assert pc.getAll().size() == 8 : "delete failed for player";
        assert pc.getByName("Alehandro7").getId() == 6 : "getByName failed for player";
        assert pc.getById(3).getName().equals("Alehandro4") : "getById failed for player";


        mc.add("added1", 1);
        mc.add("added", 1);
        assert mc.getAll().size() == 4 : "add failed for manager";
        mc.delete(1);
        assert mc.getAll().size() == 3 : "delete failed for manager";
        assert mc.getById(1).getName().equals("Manager2"): "getById failed for manager";
        assert mc.getByName("added").getId() == 3 : "getByName failed for manager";


        tc.add("Added", 2);
        assert pc.getAll().size() == 4 : "add failed for team";
        pc.delete(2);
        assert pc.getAll().size() == 3 : "delete failed for team";
        assert pc.getByName("BlajTeam").getId() == 1 : "getByName failed for team";
        assert pc.getById(2).getName().equals("TrascauTeam") : "getById failed for team";

        gc.add("01-01-2000", 21, 12, 1, 2, "PT");
        assert gc.getAll().size() == 3 : "add failed for game";
        gc.delete(3);
        assert gc.getAll().size() == 2 : "dleete failed for game";
        assert gc.getById(2).getType().equals("RS") : "getById failed for game";

    }

    public void test()throws Exception{
        //testFiles();
        testController();
    }

    
    
}
