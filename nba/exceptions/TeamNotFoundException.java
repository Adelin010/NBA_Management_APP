package nba.exceptions;

public class TeamNotFoundException extends RuntimeException implements CustomException {

    public TeamNotFoundException(String message) {
        super(message);
    }
    public void printInfo(){
        generateMessage(this.getMessage(),() -> this.printStackTrace());
    }

}
