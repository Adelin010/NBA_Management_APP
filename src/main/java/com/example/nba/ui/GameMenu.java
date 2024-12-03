package com.example.nba.ui;

import java.util.List;
import java.util.Scanner;

import com.example.nba.error.IdOutOfRangeException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.Game;


import com.example.nba.controller.GameController;

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




     public void add(){
         System.out.print("Enter the date (dd-MM-yyyy): ");
         String date = in.next();
         System.out.print("Enter score for Team 1: ");
         int scoreTeam1 = in.nextInt();
         System.out.print("Enter score for Team 2: ");
         int scoreTeam2 = in.nextInt();
         System.out.print("Enter hoem team ID: ");
         Integer team1Id = in.nextInt();
         System.out.print("Enter away team ID: ");
         Integer team2Id = in.nextInt();
         System.out.print("Enter game type: ");
         String type = in.next();
         System.out.print("Enter the season ID: ");
         Integer seasonId = in.nextInt();
         try{
             gc.add(date, scoreTeam1, scoreTeam2, team1Id, team2Id, type, seasonId);
             System.out.println("Game added successfully.");
         } catch (InexistenteInstance exp) {
             exp.printStackTrace();
             System.out.println(exp.getMessage());
         }
     }
    public void getById() {
        System.out.print("Enter game ID:");
        int id = in.nextInt();
        Game game = gc.getById(id);
        if (game != null) {
            System.out.println("Game: " + game);
        } else {
            System.out.println("No game with ID: " + id);
        }
    }
    public void delete() {
        System.out.print("Enter the game to delete: ");
        int id = in.nextInt();
        try {
            gc.delete(id);
            System.out.println("Game deleted successfully");
        } catch (IdOutOfRangeException exp) {
            exp.printStackTrace();
            System.out.println(exp.getMessage());
        }
    }

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
                add();
                break;
            }
            case 2:{
                getById();
                break;
            }
            case 3:{
                delete();
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
