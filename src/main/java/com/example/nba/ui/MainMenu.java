package com.example.nba.ui;

import java.util.Scanner;
import com.example.nba.model.*;
import com.example.nba.repo.*;
import com.example.nba.service.AdvancedService;
import com.example.nba.service.GameService;
import com.example.nba.service.ManagerService;
import com.example.nba.service.PlayerService;
import com.example.nba.service.TeamService;
import com.example.nba.controller.*;

public class MainMenu {
    private final Scanner in;

    private final PlayerMenu pm;
    private final TeamMenu tm;
    private final ManagerMenu mm;
    private final GameMenu gm;
    private final AdvancedMenu adv;

    private final Repo<Manager> rm;
    private final Repo<NBAPlayer> rp;
    private final Repo<NBATeam> rt;
    private final Repo<Game> rg;
    private final Repo<Conference> rc;

    private final ManagerController mc;
    private final GameController gc;
    private final AdvancedController advc;
    private final TeamController tc;
    private final PlayerController pc;

    private final PlayerService ps;
    private final TeamService ts;
    private final AdvancedService advs;
    private final ManagerService ms;
    private final GameService gs;

    public MainMenu(){
    
        //Intance the Repo
        rm = new RepoFile<>("src/resource/db/manager.txt", Manager.class);
        rp = new RepoFile<>("src/resource/db/player.txt", NBAPlayer.class);
        rt = new RepoFile<>("src/resource/db/team.txt", NBATeam.class);
        rg = new RepoFile<>("src/resource/db/game.txt", Game.class);
        rc = new RepoFile<>("src/resource/db/conference.txt", Conference.class);
        //Services 
        ms = new ManagerService(rm, rt);
        ps = new PlayerService(rp, rt);
        advs = new AdvancedService(rg, rp, rt, rm);
        ts = new TeamService(rt, rc);
        gs = new GameService(rg, rt);
        //Controllers
        mc = new ManagerController(ms);
        pc = new PlayerController(ps);
        advc = new AdvancedController(advs);
        tc = new TeamController(ts);
        gc = new GameController(gs);
        //Intance main
        this.in = new Scanner(System.in);
        mm =  new ManagerMenu(in,mc);
        tm = new TeamMenu(in, tc);
        pm = new PlayerMenu(in, pc);
        adv = new AdvancedMenu(in, advc);
        gm = new GameMenu(in, gc);
    }

    public void run(){
        while(true){
            boolean response = display();
            if(!response)
                break;
        }
    }

    public boolean display(){
        String menu = """
            \033[32m
                *)Player Menu(/player)
                *)Team Menu(/team)
                *)Manager Menu(/manager)
                *)Game Menu(/game)
                *)Advanced Menu(/adv)
                *)Quit(/quit)
                * Your option: \033[0m
                """;
        System.out.print(menu);
        String choice = in.next();
        switch(choice){
            case "/player":{
                pm.run();
                break;
            }
            case "/team":{
                tm.run();
                break;
            }
            case "/manager":{
                mm.run();
                break;
            }
            case "/game":{
                gm.run();
                break;
            }
            case "/adv":{
                adv.run();
                break;
            }
            case "/quit":
                return false;
        }
        return true;
    }
}
