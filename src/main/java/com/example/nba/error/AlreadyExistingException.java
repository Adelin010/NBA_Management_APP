package com.example.nba.error;

import com.example.nba.interfaces.*;

public class AlreadyExistingException extends RuntimeException implements CustomException{
    public AlreadyExistingException(String message){
        super(message);
    }

    public void printInfo(){
        generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
    
}
