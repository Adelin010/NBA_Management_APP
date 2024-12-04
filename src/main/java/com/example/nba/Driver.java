package com.example.nba;

import com.example.nba.ui.MainMenu;


public class Driver{
    public static void main(String[] args)throws Exception {
        /*
         * Run in test mode
         */
        // if(args.length > 0 && args[0].equals("-t")){
        //     TestIOFile testFR = new TestIOFile();
        //     try{
        //         testFR.test();
        //     }catch(Exception exp){
        //         exp.printStackTrace();
        //     }
        //     return;
        // }
        
        /*
         * Run in normal mode
         */
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();

        String connectionURL = "jdbc:sqlserver://localhost;databaseName=NBA;user=SA;password=Slugterra#10;encrypt=true;trustServerCertificate=true";
    }

}