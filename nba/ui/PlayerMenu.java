package nba.ui;

import java.util.Scanner;

public class PlayerMenu {
    
    private final Scanner in;

    public PlayerMenu(Scanner in){
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
            1)Add PLayer
            2)Get PLayer By Id
            3)Get Player By Name
            4)Delete Player
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
