package nba.exceptions;

public class ManagerNotFoundException extends RuntimeException implements CustomException{

  public ManagerNotFoundException(String message) {
    super(message);
  }
  public void printInfo(){
    generateMessage(this.getMessage(),() -> this.printStackTrace());
  }


}
