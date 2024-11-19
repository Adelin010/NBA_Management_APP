package nba.error;

public class AlreadyExistingException extends RuntimeException implements CustomException{
    public AlreadyExistingException(String message){
        super(message);
    }

    public void printInfo(){
        generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
    
}
