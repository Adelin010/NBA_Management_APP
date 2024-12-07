package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.nba.model.NBAPlayer;
import com.example.nba.model.NBATeam;
import com.example.nba.repo.Repo;
import com.example.nba.repo.RepoDB;


public class TestDB {
    
    @Test
    public void TestGetMethods()throws Exception{
        String url = "jdbc:sqlserver://localhost;databaseName=NBA;user=SA;password=Slugterra#10;encrypt=true;trustServerCertificate=true";
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
}
