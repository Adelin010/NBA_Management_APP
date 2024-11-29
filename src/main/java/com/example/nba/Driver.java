package com.example.nba;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import com.example.nba.test.*;
import com.example.nba.ui.MainMenu;


public class Driver{
    public static void main(String[] args)throws Exception {
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
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();


        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","petru", "Slugterra#10");
        Statement s = con.createStatement();
        var x = s.executeQuery("select * from Added");
        System.out.println(x);
        con.close();
    }


}