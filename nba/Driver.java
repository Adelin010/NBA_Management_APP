package nba;

import nba.test.*;
import nba.ui.MainMenu;
import java.util.Scanner;

public class Driver{
    public static void main(String[] args) {
        /*
         * Run in test mode
         */
        if(args.length > 0 && args[0].equals("-t")){
            TestIOFile testFR = new TestIOFile();
            try{
                testFR.test();
            }catch(Exception exp){
                exp.printStackTrace();
            }
            return;
        }
        /*
         * Run in normal mode
         */
        Scanner in = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(in);
        mainMenu.run();
    }

}