package Model.Utils;

public class IntHeapKeyGenerator implements KeyGenerator<Integer> {
    private int next = 1;

    @Override
    public Integer generate() {
        return next++;
    }
}
