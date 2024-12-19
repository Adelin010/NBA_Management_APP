package com.example;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.*;
import com.example.nba.repos.*;
import com.example.nba.services.*;
import com.example.nba.util.FileHandlerUtil;
import com.example.nba.util.LoaderInfo;

public class TestInfrastucture{
    
    @Test
    public void testModel(){
        //Conference
        String[] args = {"1", "Conf1"};
        Conference c = new Conference(args);
        assertTrue("Conf1".equals(c.seq(true)));
        assertTrue("1,Conf1".equals(c.seq(false)));
        //Found
        String[] args1 = {"1", "2", "2", "234500"};
        Found f = new Found(args1);
        assertTrue("1,2,2,234500".equals(f.seq(false)));
        assertTrue("2,2,234500".equals(f.seq(true)));  
        //Game 
        String[] args2 = {"1", "date", "2", "1", "123", "213", "type", "1"};
        Game g = new Game(args2);
        assertTrue("1,date,2,1,123,213,type,1".equals(g.seq(false)));
        assertTrue("date,2,1,123,213,type,1".equals(g.seq(true)));  
        //Manager
        String[] args3 = {"1", "man","man1234", "22", "1"};
        Manager m = new Manager(args3);
        assertTrue("1,man,man1234,22,1".equals(m.seq(false)));
        assertTrue("man,man1234,22,1".equals(m.seq(true)));  
        //Player
        String[] args4 = {"1", "man", "22", "P","2134.00","21","23","12","1"};
        NBAPlayer p = new NBAPlayer(args4);
        assertTrue("1,man,22,P,2134.00,21,23,12,1".equals(p.seq(false)));
        assertTrue("man,22,P,2134.00,21,23,12,1".equals(p.seq(true)));  
        //Team
        String[] arg5 = {"2","Team","3"};
        NBATeam team = new NBATeam(arg5);
        assertTrue("2,Team,3".equals(team.seq(false)));
        assertTrue("Team,3".equals(team.seq(true)));
        //Season
        String[] arg6 = {"1", "S2", "2024"};
        Season season = new Season(arg6);
        assertTrue("1,S2,2024".equals(season.seq(false))); 
        assertTrue("S2,2024".equals(season.seq(true)));
        //Sponsor
        String[] args7 = {"1", "Alex", "20", "email", "1"};
        Sponsor sp = new Sponsor(args7);
        assertTrue("1,Alex,20,email,1".equals(sp.seq(false)));
        assertTrue("Alex,20,email,1".equals(sp.seq(true)));

    }

    @Test 
    public void testRepoMemory(){
        Repo<Manager> mr = new RepoMemory<>();
        
        String[] args3 = {"1", "man","man1234", "22", "1"};
        Manager m = new Manager(args3);
        mr.add(m);
        /*
         * Check for add/get method
         */
        Manager temp = mr.get(m.getId());
        assertTrue(temp != null);
        assertTrue(temp.getAge() == m.getAge());
        assertTrue(temp.getName().equals(m.getName()));
        assertTrue(temp.getTeamId() == m.getTeamId());
        /*
         * Check for update method
         */
        m.setName("man2");
        mr.update(m);
        temp = mr.get(m.getId());
        assertTrue(temp.getName().equals(m.getName()));
        /*
         * Check for delete method
         */
        mr.delete(m.getId());
        temp = mr.get(m.getId());
        assertTrue(temp == null);
    }

    @Test
    public void testLogin(){
        Repo<Manager> mr = new RepoMemory<>();
        
        String[] args3 = {"1", "man","man1234", "22", "1"};
        Manager m = new Manager(args3);
        mr.add(m);

        String[] args4 = {"2", "man2","man12345", "22", "1"};
        Manager m2 = new Manager(args4);
        mr.add(m2);

        Security sec = new Security(mr);
        assertTrue(sec.auth("man", "man1234"));
        assertFalse(sec.auth("man", "man12345"));
        assertFalse(sec.auth("man3", "man123"));
        assertTrue(sec.auth("man2", "man12345"));
    }

    @Test
    public void testServiceMethods(){
        //Player Service in memory
        Repo<NBAPlayer> rp = new RepoMemory<>();
        Repo<NBATeam> rt = new RepoMemory<>();
        Repo<Manager> rm = new RepoMemory<>();
        Repo<Sponsor> rsp = new RepoMemory<>();
        Repo<Game> rg = new RepoMemory<>();

        PlayerS ps = new PlayerS(rp, rt);
        ManagerS ms = new ManagerS(rm, rt);
        SponsorS ss = new SponsorS(rsp);
        GameS gs = new GameS(rt, rg);

        String[] arg5 = {"1","Team","3"};
        NBATeam team = new NBATeam(arg5);
        String[] arg51 = {"2", "Team2", "4"};
        NBATeam team2 = new NBATeam(arg51);
        String[] arg52 = {"3", "Team3", "4"};
        NBATeam team3 = new NBATeam(arg52);
        rt.add(team);
        rt.add(team2);
        rt.add(team3);
        String[] args4 = {"1", "man1", "22", "P","2134.00","21","23","12","1"};
        NBAPlayer p = new NBAPlayer(args4);
        String[] args5 = {"2", "man3", "25", "P","21134.00","21","23","12","1"};
        NBAPlayer p2 = new NBAPlayer(args5);
        String[] args6 = {"3", "man2", "32", "P","11334.00","21","23","12","1"};
        NBAPlayer p3 = new NBAPlayer(args6);
        String[] args7 = {"4", "man1", "22", "P","32134.00","21","23","12","1"};
        NBAPlayer p4 = new NBAPlayer(args7);
        String[] args8 = {"5", "man", "21", "P","12134.00","21","23","12","1"};
        NBAPlayer p5 = new NBAPlayer(args8);

        ps.add(p);
        ps.add(p5);
        ps.add(p4);
        ps.add(p2);
        ps.add(p3);

        /*
         * Sorted by Age
         */
        int age = -1;
        for(NBAPlayer i :ps.sortByAge()) {
            assertTrue(age <= i.getAge());
            age = i.getAge();
        }  
        /*
         * Filter by interval age
         */
        int start = 25;
        int end = -1;
        for(var i : ps.filterByAgeInterval(start, end)){
            assertTrue(i.getAge() >= start);
        }

        start = 25;
        end = 30;
        for(var i : ps.filterByAgeInterval(start, end)){
            assertTrue(i.getAge() >= start && i.getAge() <= end);
        }

        start = -1;
        end = 30;
        for(var i : ps.filterByAgeInterval(start, end)){
            assertTrue(i.getAge() >= start && i.getAge() <= end);
        }
        /*
         * Filter by interval salary
         */

         double startS = 20000.00;
         double endS = -1;
         for(var i : ps.filterBySalaryInterval(startS, endS)){
             assertTrue(i.getSalary() >= startS);
         }
 
         startS = 10000.00;
         endS = 30000.00;
         for(var i : ps.filterBySalaryInterval(startS, endS)){
             assertTrue(i.getSalary() >= startS && i.getSalary() <= endS);
         }
 
         startS = -1;
         endS = 30000.00;
         for(var i : ps.filterBySalaryInterval(startS, endS)){
             assertTrue(i.getSalary() >= startS && i.getSalary() <= endS);
         }
        startS = -1;
        endS = 10.00;
        assertTrue(ps.filterBySalaryInterval(startS, endS).isEmpty());

        /*
         * Sort by Salary
         */
        double salary = 0.00;
        for(NBAPlayer i :ps.sortBySalary()) {
            assertTrue(salary <= i.getSalary());
            salary = i.getSalary();

        }  
        /*
         * Get by Name
         */
        String name = "man1";
        for(var i: ps.getByName(name)){
            assertTrue(i.getName().equals(name));
        }

        /*
         * Test Manager functions
         */
        String[] args3 = {"1", "man","man1234", "22", "10"};
        Manager m = new Manager(args3);
        try{
            ms.add(m);

        }catch(InexistenteInstance a){
            System.out.println("Team id is not good!!!");
            m.setTeamId(1);
            ms.add(m);

        }

        String[] args1 = {"2", "man","man12345", "22", "1"};
        Manager m2 = new Manager(args1);
        ms.add(m2);

        for(var man: ms.getByName("man")){
            assertTrue(man.getName().equals("man"));
        }


        /*
         * Sponsor service
         */
        String[] args11 = {"1", "Alex", "20", "email", "1"};
        Sponsor sp = new Sponsor(args11);
        String[] args12 = {"2", "Cola", "20", "email", "0"};
        Sponsor sp2 = new Sponsor(args12);
        ss.add(sp2);
        ss.add(sp);

        for(var s: ss.getTheCompanies()){
            assertFalse(s.isPF());
        }

        /*
         * Checking the operation for the game service
         */
        String[] args2 = {"1", "12-03-2024", "2", "1", "123", "213", "type", "1"};
        Game g = new Game(args2);
        String[] args21 = {"2", "10-03-2024", "2", "1", "123", "213", "type", "1"};
        Game g1 = new Game(args21);
        String[] args22 = {"3", "12-04-2024", "2", "1", "123", "213", "type", "1"};
        Game g2 = new Game(args22);
        gs.add(g);
        gs.add(g1);
        gs.add(g2);

        List<Game> games = gs.sortByDate();
        System.out.println(games);

        System.out.println(gs.getGamesPerTeam("Team3").size());
        assertTrue(gs.getGamesPerTeam("Team3").size() == 0);
        assertTrue(gs.getGamesPerTeam("Team").size() == 3);

    }


    @Test
    public void testLoaderInMemory()throws Exception{
        String url = System.getenv("DB_URL");
        System.out.println(url);
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Repo<NBATeam> rt = new RepoMemory<>();
        Repo<NBAPlayer> rp = new RepoMemory<>();
        Repo<Manager> rm = new RepoMemory<>();
        Repo<Found> rf = new RepoMemory<>();
        Repo<Game> rg = new RepoMemory<>();
        Repo<Sponsor> rsp = new RepoMemory<>();
        Repo<Season> rs = new RepoMemory<>();
        Repo<Conference> rc = new RepoMemory<>();

        LoaderInfo<Conference> loaderC = new LoaderInfo<>(conn, rc, 1, Conference.class, "Conference");
        loaderC.init();
        LoaderInfo<NBATeam> loaderT = new LoaderInfo<>(conn, rt, 1, NBATeam.class, "Team");
        loaderT.init();
        LoaderInfo<Sponsor> loaderSp = new LoaderInfo<>(conn, rsp, 1, Sponsor.class, "Sponsor");
        loaderSp.init();
        LoaderInfo<Manager> loaderM = new LoaderInfo<>(conn, rm, 1, Manager.class, "Manager");
        loaderM.init();
        LoaderInfo<NBAPlayer> loaderP = new LoaderInfo<>(conn, rp, 1, NBAPlayer.class, "Player");
        loaderP.init();
        LoaderInfo<Season> loaderS = new LoaderInfo<>(conn, rs, 1, Season.class, "Season");
        loaderS.init();
        LoaderInfo<Game> loaderG = new LoaderInfo<>(conn, rg, 1, Game.class, "Game");
        loaderG.init();
        LoaderInfo<Found> loaderF = new LoaderInfo<>(conn, rf, 1, Found.class, "Found");
        loaderF.init();


        //Test Teams
        String[] teamNames = {"Lakers", "Chicago Bulls", "Golden State Warrios", "Boston Celtics"};
        int i = 0;
        for(NBATeam t: rt.getAll()){
            System.out.println(t);
            assertTrue(t.getName().equals(teamNames[i]));
            i++;
        }

        //Test Conferences
        String[] Conf = {"East", "West"};
        i = 0;
        for(Conference c: rc.getAll()){
            assertTrue(c.getName().equals(Conf[i]));
            i++;
        }

        //Test the sponsors
        String[] spons = {"Convers", "Cola", "Nike", "Adidas", "Alex Bidonici"};
        i = 0;
        for(Sponsor s: rsp.getAll()){
            s.getName().equals(spons[i]);
            i++;
        }

        //Test the Managers
        String[] managers = {"Michael Gates", "Adelin Cojocaru", "Andrei Blaj", "Mario Lopez"};
        i = 0;
        for(Manager m: rm.getAll()){
            m.getName().equals(managers[i]);
            i++;
        }
    }


    @Test
    public void testFileRepo()throws Exception{
        // Repo<Conference> rf = new RepoMemory<>();
        FileHandlerUtil<Conference> fh = new FileHandlerUtil<>(Conference.class, "db/Conference.txt");
        // Conference c = new Conference(3, "AddedConf1");
        // fh.addObject(c);
        // Conference c3 = new Conference(4, "AddedConf2");
        // fh.addObject(c3);
        Conference c2 = fh.getObject(3);
        c2.setName("Refactored");
        System.out.println(c2);
        fh.updateObject(c2);
        // System.out.println(fh.getObject(3));
        
    }
}
