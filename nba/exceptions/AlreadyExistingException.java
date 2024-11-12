package nba.exceptions;

public class AlreadyExistingException extends RuntimeException implements CustomException{
    public AlreadyExistingException(String message){
        super(message);
    }

    public void printInfo(){
        System.out.println(this.getMessage());
        System.out.println("############## Stack Trace #############");
        this.printStackTrace();
        System.out.println("################################################");
    }
    
}
