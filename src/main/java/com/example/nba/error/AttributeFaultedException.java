package com.example.nba.error;

import com.example.nba.interfaces.CustomException;

public class AttributeFaultedException extends RuntimeException implements CustomException{
    public AttributeFaultedException(String message){
        super(message);
    }

    public void printInfo(){
        generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
    
}