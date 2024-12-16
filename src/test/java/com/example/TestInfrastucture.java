package com.example;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.nba.interfaces.Repo;
import com.example.nba.model.*;
import com.example.nba.repos.*;
import com.example.nba.services.PlayerS;
import com.example.nba.services.Security;

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

        String[] arg5 = {"1","Team","3"};
        NBATeam team = new NBATeam(arg5);
        rt.add(team);
        PlayerS ps = new PlayerS(rp, rt);
        String[] args4 = {"1", "man", "22", "P","2134.00","21","23","12","1"};
        NBAPlayer p = new NBAPlayer(args4);
        String[] args5 = {"2", "man", "25", "P","2134.00","21","23","12","1"};
        NBAPlayer p2 = new NBAPlayer(args5);
        String[] args6 = {"3", "man", "32", "P","2134.00","21","23","12","1"};
        NBAPlayer p3 = new NBAPlayer(args6);
        String[] args7 = {"4", "man", "22", "P","2134.00","21","23","12","1"};
        NBAPlayer p4 = new NBAPlayer(args7);
        String[] args8 = {"5", "man", "21", "P","2134.00","21","23","12","1"};
        NBAPlayer p5 = new NBAPlayer(args8);

        ps.add(p);
        ps.add(p5);
        ps.add(p4);
        ps.add(p2);
        ps.add(p3);

        int age = -1;
        for(NBAPlayer i :ps.sortByAge()) {
            assertTrue(age <= i.getAge());
            age = i.getAge();
        }  
        

    }
}
