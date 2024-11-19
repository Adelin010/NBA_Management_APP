package nba.ui;

import java.util.Scanner;

public class TeamMenu {
    private Scanner in;

    public TeamMenu(Scanner in){
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
            1)Add Team
            2)Get Team By Id
            3)Get Team By Name
            4)Delete Team
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
                break;
            }
            case -1:{
                return false;
            }
        }
        return true;
    }
}
