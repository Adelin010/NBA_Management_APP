package nba;

import nba.test.*;

public class Driver{
    public static void main(String[] args) {
        
        TestIOFile testFR = new TestIOFile();
        try{
            testFR.test();
        }catch(Exception exp){
            exp.printStackTrace();
        }
        
    }

}