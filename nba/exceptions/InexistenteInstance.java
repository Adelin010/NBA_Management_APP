package nba.exceptions;

public class InexistenteInstance extends RuntimeException implements CustomException{
    public InexistenteInstance(String message){
        super(message);
    }

    public void printInfo(){
        generateMessage(this.getMessage(), () -> this.printStackTrace());
    }
}
