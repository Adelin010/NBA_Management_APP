package com.example.nba.ui;

import java.util.Scanner;

import com.example.nba.controller.TeamController;

public class TeamMenu {
    private Scanner in;
    private final TeamController tc;

    public TeamMenu(Scanner in, TeamController tc){
        this.in = in;
        this.tc = tc;
    }

    public void run(){
        while (true){
            boolean response = display();
            if(!response)
                break;
        }
    }

    public void getById(){
        System.out.print("Id: ");
        Integer id = in.nextInt();
        try{
            System.out.println(tc.getById(id));
        }catch(Exception exp){
            exp.printStackTrace();
        }
    }

    public void getByName(){
        System.out.print("name: ");
        String name = in.next();
        try{
            System.out.println(tc.getByName(name));
        }catch(Exception exp){
            exp.printStackTrace();
        }
    }

    public void add(){
        System.out.print("name: ");
        String name = in.next();
        System.out.print("conference_id: ");
        Integer id = in.nextInt();
        try{
            tc.add(name, id);
        }catch(Exception exp){
            exp.printStackTrace();
        }
    }

    public void delete(){
        System.out.print("id: ");
        Integer id = in.nextInt();
        try{
            tc.delete(id);
        }catch(Exception exp){
            exp.printStackTrace();
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
                add();
                break;
            }
            case 2:{
                getById();
                break;
            }
            case 3:{
                getByName();
                break;
            }
            case 4:{
                delete();
                break;
            }
            case -1:{
                return false;
            }
        }
        return true;
    }
}
