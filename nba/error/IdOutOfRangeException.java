package nba.error;

import nba.interfaces.CustomException;

public class IdOutOfRangeException extends RuntimeException implements CustomException{

    public IdOutOfRangeException(String mes){
        super(mes);
    }

    @Override
    public void printInfo(){
        generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
}
