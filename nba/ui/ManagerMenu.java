package nba.ui;

import nba.controller.ManagerController;
import nba.error.IdOutOfRangeException;
import nba.error.InexistenteInstance;
import nba.model.Manager;

import java.util.Scanner;

public class ManagerMenu {
     private final Scanner in;
     private final ManagerController mc;

    public ManagerMenu(Scanner in, ManagerController mc) {
        this.in = in;
        this.mc = mc;
    }

    public void run(){
        while (true){
            boolean response = display();
            if(!response)
                break;
        }
    }
    public void add(){
        System.out.print("Enter Manager name: ");
        String name = in.next();
        System.out.print("Enter Team ID: ");
        Integer teamId = in.nextInt();
        try{
            mc.add(name,teamId);
            System.out.println("Manager added successfully");
        }catch (InexistenteInstance exp) {
            exp.printStackTrace();
            System.out.println(exp.getMessage());
        }
    }
    public void getById() {
        System.out.print("Enter Manager ID: ");
        Integer id = in.nextInt();

        Manager manager = mc.getById(id);
        if (manager != null) {
            System.out.println("Manager: " + manager);
        } else {
            System.out.println("No manager found");
        }
    }

    public void getByName() {
        System.out.print("Enter Manager name: ");
        String name = in.next();

        Manager manager = mc.getByName(name);
        if (manager != null) {
            System.out.println("Manager: " + manager);
        } else {
            System.out.println("No manager found");
        }
    }
    public void delete() {
        System.out.print("Enter Manager to delete: ");
        Integer id = in.nextInt();
        try{
            mc.delete(id);
            System.out.println("Manager deleted successfully");
        }catch (IdOutOfRangeException exp) {
            exp.printStackTrace();
            System.out.println(exp.getMessage());
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
            case -1:
                return false;
        }
        return true;
    }
}
