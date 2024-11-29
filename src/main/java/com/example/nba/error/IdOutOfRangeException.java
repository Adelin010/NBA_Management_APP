package com.example.nba.error;

import com.example.nba.interfaces.CustomException;

public class IdOutOfRangeException extends RuntimeException implements CustomException{

    public IdOutOfRangeException(String mes){
        super(mes);
    }

    @Override
    public void printInfo(){
        generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
}
