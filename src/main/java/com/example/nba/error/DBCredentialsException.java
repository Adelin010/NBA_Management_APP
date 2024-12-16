package com.example.nba.error;

import com.example.nba.interfaces.CustomException;

public class DBCredentialsException extends RuntimeException implements CustomException {
    
    public DBCredentialsException(String message){
        super(message);
    }

    @Override
    public void printInfo() {
        this.generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
}
