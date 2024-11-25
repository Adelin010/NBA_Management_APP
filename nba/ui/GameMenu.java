package nba.ui;

import java.util.List;
import java.util.Scanner;
import nba.model.Game;

import nba.controller.GameController;

public class GameMenu {
    private final Scanner in;
    private final GameController gc;

    public GameMenu(Scanner in, GameController gc){
        this.in = in;
        this.gc = gc;
    }

    public void run(){
        while (true){
            boolean response = display();
            if(!response)
                break;
        }
    }

    public void sortByDate(){
        List<Game> games = gc.sortByDate();
        try{
            for(Game g: games)
                System.out.println(g);
        }catch(Exception exp){
            exp.printStackTrace();
            System.out.println(exp.getMessage());
        }
       
    }

    // public void add(){
    //     System.out.print("Enter the date: ");

    //     try{
    //         gc.add(null, 0, 0, null, null, null);
    //     }
    // }

    private boolean display(){
        String menu ="""
        \033[32m
            1)Add Game
            2)Get Game By Id
            3)Delete Game
            4)Sort By Date
            5)Quit(-1)
            * Your choice: \033[0m
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
                sortByDate();
            }
            case -1:
                return false;
        }
        return true;
    }
}
