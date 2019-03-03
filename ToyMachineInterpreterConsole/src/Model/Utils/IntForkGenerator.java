package Model.Utils;

public class IntForkGenerator {
    private static int number = 1;

    public static Integer generate() {
        return number++;
    }
}
