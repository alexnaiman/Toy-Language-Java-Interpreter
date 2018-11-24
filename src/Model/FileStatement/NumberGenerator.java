package Model.FileStatement;

public class NumberGenerator {
    private static int counter = 0;
    public static int getId(){
        return counter++;
    }
}
