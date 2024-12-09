package com.example;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.nba.model.Game;
import com.example.nba.model.Manager;
import com.example.nba.model.NBAPlayer;
import com.example.nba.model.NBATeam;
import com.example.nba.repo.Repo;
import com.example.nba.repo.RepoDB;
import com.example.nba.service.AdvancedService;


public class TestDB {
    
    /**
     * Test the new get methods made to comunicate with the database through jdbc
     * @throws Exception
     */
    @Test
    public void TestGetMethods()throws Exception{
        String url = System.getenv("DB_URL");
        Repo<NBATeam> rt = new RepoDB<>(url, NBATeam.class, "Team");
        NBATeam t1 = rt.get(2);
        assertEquals(t1.getId(), 2);
        assertEquals(t1.getName(), "Team2");
        assertEquals(t1.getConferenceId(), 1);
        t1 = rt.get(5);
        assertEquals(t1.getId(), 5);
        assertEquals(t1.getName(), "Team5");
        assertEquals(t1.getConferenceId(), 2);

        Repo<NBAPlayer> rp = new RepoDB<>(url, NBAPlayer.class, "Player");
        NBAPlayer p1 = rp.get(7);
        assertEquals(p1.getId(), 7);
        assertEquals(p1.getAge(), 25);
        assertEquals(p1.getName(), "Player7");
        assertEquals(p1.getPosition(), "C");

        if(rt instanceof RepoDB)
            ((RepoDB<NBATeam>)rt).closeConnection();
        
    }

    /**
     * Test for the advenced methods to check the logic before putting them into the code 
     * @throws Exception
     */
    @Test
    public void TestAdvMethods() throws Exception{
        String url = System.getenv("DB_URL");
        Repo<NBAPlayer> rp = new RepoDB<>(url, NBAPlayer.class, "Player");
        Repo<NBATeam> rt = new RepoDB<>(url, NBATeam.class, "Team");
        Repo<Game> rg = new RepoDB<>(url, Game.class, "Game");
        Repo<Manager> rm = new RepoDB<>(url, Manager.class, "Manager");
        AdvancedService advS = new AdvancedService(rg, rp, rt, rm);

        Manager m = advS.managerWinningTeam(1);
        assertNotEquals(m, null);
        assertEquals(m.getTeamId(), 1);
        assertEquals(m.getId(), 1);
    }
}
