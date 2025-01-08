package com.example.nba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.nba.interfaces.Repo;
import com.example.nba.menus.MenuGUI;
import com.example.nba.model.*;
import com.example.nba.repos.*;
import com.example.nba.services.*;

import javafx.application.Application;

import com.example.nba.controller.AppCtrl;

public class Driver {

    
    public static void main(String[] args) throws Exception {
        boolean test = false;

        if(!test){
                //create the connection
            String url = System.getenv("DB_URL");
            System.out.println(url);
            Connection conn = null;
            try{
                conn = DriverManager.getConnection(url);
            }catch(SQLException e){
                e.printStackTrace();
            }

            //init the Repos
            Repo<Conference> rc = new RepoDB<>(conn, Conference.class, "Conference");
            Repo<NBATeam> rt = new RepoDB<>(conn, NBATeam.class, "Team");
            Repo<Manager> rm = new RepoDB<>(conn, Manager.class, "Manager");
            Repo<Sponsor> rsp = new RepoDB<>(conn, Sponsor.class, "Sponsor");
            Repo<Season> rs = new RepoDB<>(conn, Season.class, "Season");
            Repo<Game> rg = new RepoDB<>(conn, Game.class, "Game");
            Repo<NBAPlayer> rp = new RepoDB<>(conn, NBAPlayer.class, "Player");
            Repo<Found> rf = new RepoDB<>(conn, Found.class, "Found");

            //init services
            PlayerS ps = new PlayerS(rp, rt);
            TeamS ts = new TeamS(rc, rt);
            Security sec = new Security(rm);
            ManagerS ms = new ManagerS(rm, rt);
            GameS gs = new GameS(rt, rg);
            SeasonS ss = new SeasonS(rs);
            FoundsS fs = new FoundsS(rt, rsp, rf);
            SponsorS sps = new SponsorS(rsp);

            //init controller
            AppCtrl controller = new AppCtrl(fs, gs, ms, ps, ss, sps, ts);

            //start the application
            MenuGUI.setCrtl(controller);
            Application.launch(MenuGUI.class, args);
        }
        else{
            System.out.println(Driver.class.getClassLoader().getResourceAsStream("images/PlayerBack.jpg"));
            System.out.println();
            System.out.println("END____________-");

        }
        
    }
}
