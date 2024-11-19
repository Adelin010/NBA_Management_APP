package nba.ui;

import java.util.Scanner;

public class AdvancedMenu {
    private final Scanner in;

    public AdvancedMenu(Scanner in){
        this.in = in;
    }

    public void run(){
        while (true){
            boolean response = display();
            if(!response)
                break;
        }
    }

    private boolean display(){
        String menu ="""
        \033[32m
            1)Get best(points based) 'n' players from both team of a game
            2)Get manager of the winning team
            3)Get ranking(win based) of the team per conference
            4)Get sponsors ordered by deal for winning team
            5)Quit(-1)
            * Your choice:  \033[0m
                """;
        System.out.print(menu);
        int option = in.nextInt();
        switch(option){
            case 1:{
                break;
            }
            case 2:{
                break;
            }
            case 3:{
                break;
            }
            case 4:{
                break;
            }
            case -1:{
                return false;
            }
        }
        return true;
    }
}
