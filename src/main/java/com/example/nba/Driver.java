package com.example.nba;


import java.util.List;

import com.example.nba.model.Conference;
import com.example.nba.model.NBAPlayer;
import com.example.nba.model.NBATeam;
import com.example.nba.repo.Repo;
import com.example.nba.repo.RepoDB;
import com.example.nba.ui.MainMenu;


public class Driver{
    public static void main(String[] args)throws Exception {
        /*
         * Run in test mode
         */
        // if(args.length > 0 && args[0].equals("-t")){
        //     TestIOFile testFR = new TestIOFile();
        //     try{
        //         testFR.test();
        //     }catch(Exception exp){
        //         exp.printStackTrace();
        //     }
        //     return;
        // }
        
        /*
         * Run in normal mode
         */
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();

        String connectionURL = "jdbc:sqlserver://localhost;databaseName=NBA;user=SA;password=Slugterra#10;encrypt=true;trustServerCertificate=true";

        RepoDB<NBAPlayer> rep = new RepoDB<>(connectionURL, NBAPlayer.class, "Player");
        NBAPlayer p = rep.get(1);
        // System.out.println(p);
        
        RepoDB<NBATeam> rt = new RepoDB<>(connectionURL, NBATeam.class, "Team");
        NBATeam t = rt.get(4);
        // System.out.println(t);

        // var l1 = rt.getAll();
        // for(var tt: l1){
        //     System.out.println(tt);
        // }

        // var l2 = rep.getAll();
        // for(var tt: l2){
        //     System.out.println(tt);
        // }

        System.out.println(rep.getBySpecificValue("age", 25));

        RepoDB<Conference> rf = new RepoDB<>(connectionURL, Conference.class, "Conference");
        Conference c1 = new Conference("Added"), c2 = new Conference("Added2");
        

        // Conference c3 = rf.getBySpecificValue("name", "Ale%").get(0);
        // System.out.println(c3);
        // c3.setName("Rejected");
        // rf.update(c3);

        NBAPlayer p2 = rep.getBySpecificValue("name", "Player1").get(0);
        p2.setAge(26);
        p2.setPosition("C");
        rep.update(p2);

        if(rep instanceof RepoDB)
            ((RepoDB<NBAPlayer>)rep).closeConnection();

    }


}