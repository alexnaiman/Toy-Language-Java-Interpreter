package Model.Threads.CyclicBarrier;

public class BarrierAdressGenerator {
    private static  int next = 1;
    public static Integer generate() {
        return next++;
    }
}
