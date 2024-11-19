package nba.ui;

import java.util.Scanner;

public class ManagerMenu {
     private final Scanner in;

    public ManagerMenu(Scanner in){
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
            1)Add Manager
            2)Get Manager By Id
            3)Get Manager By Name
            4)Delete Manager
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
            case -1:
                return false;
        }
        return true;
    }
}
