//package Java;
import java.util.concurrent.atomic.AtomicInteger;

public class Swap {

    public static void swap(AtomicInteger x, AtomicInteger y) {
        int temp = x.get();
        x.set(y.get());
        y.set(temp);
    }
    public static void main(String[] args) {
        AtomicInteger x = new AtomicInteger(7);
        AtomicInteger y = new AtomicInteger(12);
        System.out.println("Before: "+x +" "+y);
        swap(x,y);
        System.out.println("After: "+x +" "+y);
        return;
    }
}