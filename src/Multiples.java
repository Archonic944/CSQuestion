import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

//mostly just to calibrate my sense of polynomial time
public class Multiples {
    static int operations = 100000;
    static int deg = 2;

    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        long totalOps = (long) Math.pow(operations, deg);
        long fulfilled = 0;
        for(long i = 0; i<totalOps; i++){
            fulfilled++;
        }
        System.out.println("O(n^" + deg + ") where n is " + operations + ":\n" + (System.currentTimeMillis() - millis));
    }
}
