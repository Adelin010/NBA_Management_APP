package com.example.nba.error;

import com.example.nba.interfaces.*;

public class InexistenteInstance extends RuntimeException implements CustomException{
    public InexistenteInstance(String message){
        super(message);
    }

    public void printInfo(){
        generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
}
