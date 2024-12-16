package com.example;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.example.nba.model.*;

public class TestModel{
    
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
        String[] args3 = {"1", "man", "22", "1"};
        Manager m = new Manager(args3);
        assertTrue("1,man,22,1".equals(m.seq(false)));
        assertTrue("man,22,1".equals(m.seq(true)));  
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
}
