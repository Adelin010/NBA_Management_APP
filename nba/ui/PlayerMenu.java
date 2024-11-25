package nba.ui;

import java.util.List;
import java.util.Scanner;

import nba.controller.PlayerController;
import nba.model.NBAPlayer;

public class PlayerMenu {
    
    private final Scanner in;
    private final PlayerController pc;

    public PlayerMenu(Scanner in, PlayerController pc){
        this.in = in;
        this.pc = pc;
    }

    public void run(){
        while (true){
            boolean response = display();
            if(!response)
                break;
        }
    }

    public void sortByAge(){
        List<NBAPlayer> players = pc.sortByAge();
        try{
            for(NBAPlayer p: players)
                System.out.println(p);
        }catch(Exception exp){
            exp.printStackTrace();

        }
    }

    private boolean display(){
        String menu ="""
        \033[32m
            1)Add PLayer
            2)Get PLayer By Id
            3)Get Player By Name
            4)Sort By Age
            5)Delete Player
            6)Quit(-1)
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
                sortByAge();
                break;
            }
            case 5:{
                break;
            }
            case -1:{
                return false;
            }
        }
        return true;
    }
}
