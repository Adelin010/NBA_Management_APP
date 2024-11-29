package com.example.nba.ui;

import java.util.List;
import java.util.Scanner;

import com.example.nba.controller.PlayerController;
import com.example.nba.error.IdOutOfRangeException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.NBAPlayer;

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
    public void add() {
        System.out.print("Enter player name: ");
        String name = in.next();
        System.out.print("Enter player age: ");
        int age = in.nextInt();
        System.out.print("Enter player salary: ");
        double salary = in.nextDouble();
        System.out.print("Enter player position: ");
        String position = in.next();
        System.out.print("Enter player points: ");
        int points = in.nextInt();
        System.out.print("Enter player rebounds: ");
        int rebounds = in.nextInt();
        System.out.print("Enter player assists: ");
        int assists = in.nextInt();
        System.out.print("Enter team ID: ");
        int teamId = in.nextInt();

        NBAPlayer player = new NBAPlayer(name, age, salary, position, points, rebounds, assists, teamId);

        try {
            pc.add(player.getName(), player.getAge(), player.getSalary(), player.getPosition(),player.getPoints(), player.getRebounds(), player.getAssists(), teamId);
            System.out.println("Player added successfully.");
        } catch (InexistenteInstance exp) {
            exp.printStackTrace();
            System.out.println(exp.getMessage());
        }
    }
    public void getById() {
        System.out.print("Enter Player ID: ");
        Integer id = in.nextInt();
        NBAPlayer player = pc.getById(id);
        if (player != null) {
            System.out.println("Player: " + player);
        } else {
            System.out.println("No player found");
        }
    }
    public void getByName() {
        System.out.print("Enter Player name: ");
        String name = in.next();
        List<NBAPlayer> players = pc.getByName(name);
        if (players != null && !players.isEmpty()) {
            System.out.println("Player:");
            for (NBAPlayer player : players) {
                System.out.println(player);
            }
        } else {
            System.out.println("No players found");
        }
    }
    public void delete() {
        System.out.print("Enter Player to delete: ");
        Integer id = in.nextInt();

        try {
            pc.delete(id);
            System.out.println("Player deleted successfully");
        } catch (IdOutOfRangeException exp) {
            exp.printStackTrace();
            System.out.println(exp.getMessage());
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
                sortByAge();
                break;
            }
            case 5:{
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
