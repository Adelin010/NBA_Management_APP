package nba.interfaces;

@FunctionalInterface
public interface CustomException {
    
    void printInfo();

    default void generateMessage(String mes, PrintStackFunctionalInterface pr){
        System.out.println(mes);
        System.out.println("############## Stack Trace #############");
        pr.printStack();
        System.out.println("################################################");
    
    }
    
}
