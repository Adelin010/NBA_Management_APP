package nba.ui;
import java.util.Scanner;

import nba.Driver;
import nba.controller.ManagerController;
import nba.exceptions.*;
import nba.model.Manager;
import java.util.*;
public class ManagerMenu {
    private final Scanner in;
    private final ManagerController mgrC;
    public ManagerMenu(ManagerController mgC, Scanner in) {
        this.in = in;
        this.mgrC = mgC;
    }
    private void addManager(ManagerController mgC,Scanner in) {
        System.out.println("Manager id:");
        int id = in.nextInt();
        System.out.println("Manager name:");
        String name = in.next();
        System.out.println("Team id:");
        int teamId = in.nextInt();
        try {
            mgrC.addManager(id, name, teamId);
        } catch (AlreadyExistingException exp) {
            exp.printInfo();
        } catch (InexistenteInstance exp) {
            exp.printInfo();
        } catch (Exception exp) {
            System.err.println("Other kind of exception occurred when adding a Manager");
            exp.printStackTrace();
        }
    }
    private void getManagerById(ManagerController mgC,Scanner in) {
        System.out.println("Manager id:");
        int id = in.nextInt();
        try {
            System.out.println(mgrC.getManagerById(id).toString());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }
    private void getManagersByName(ManagerController mgrC, Scanner in) {
        System.out.print("Name for filtering: ");
        String name = in.next();
        List<Manager> res = mgrC.getManagersByName(name);
        if (res.isEmpty()) {
            System.out.println("No results matched...");
            return;
        }
        for (Manager mgr : res)
            System.out.println(mgr.toString());
    }
    public void run() {
        boolean loop = true;
        while (loop) {
            System.out.print("1) Add Manager\n2) Get Manager by Id\n3) Get Manager by Name\nOption: ");
            int option = in.nextInt();
            switch (option) {
                case 1: {
                    addManager(mgrC, in);
                    break;
                }
                case 2: {
                    getManagerById(mgrC, in);
                    break;
                }
                case 3: {
                    getManagersByName(mgrC, in);
                    break;
                }
                case -1: {
                    loop = false;
                    break;
                }
                default:
                    System.out.println("Try again...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
    private static void handleSubMenu(Driver menu, Scanner in) {
        boolean subMenuLoop = true;
        while (subMenuLoop) {
            menu.main();
            System.out.print("\nEnter 'b' to go back to the main menu or any other key to continue in the current menu: ");
            String input = in.next().trim().toLowerCase();
            if (input.equals("b")) {
                subMenuLoop = false;
            }
        }
    }
}


