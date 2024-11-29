package com.example.nba.ui;

import java.util.Scanner;

import com.example.nba.controller.AdvancedController;

public class AdvancedMenu {
    private final Scanner in;
    private final AdvancedController advC;

    public AdvancedMenu(Scanner in, AdvancedController advC){
        this.in = in;
        this.advC = advC;
    }

    public void run(){
        while (true){
            boolean response = display();
            if(!response)
                break;
        }
    }

    public void getManagerWinning(){
        System.out.print("Enter the game id: ");
        Integer id = in.nextInt();
        try{
            System.out.print(advC.managerWinningTeam(id));

        }catch(Exception exp){
            exp.printStackTrace();
            System.out.print(exp.getMessage());
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
                getManagerWinning();
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
