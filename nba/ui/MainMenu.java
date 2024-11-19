package nba.ui;

import java.util.Scanner;


public class MainMenu {
    private final Scanner in;
    private final PlayerMenu pm;
    private final TeamMenu tm;
    private final ManagerMenu mm;
    private final GameMenu gm;
    private final AdvancedMenu adv;

    public MainMenu(Scanner in){
        this.in = in;
        mm = new ManagerMenu(in);
        pm = new PlayerMenu(in);
        tm = new TeamMenu(in);
        gm = new GameMenu(in);
        adv = new AdvancedMenu(in);
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
