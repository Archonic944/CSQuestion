//mostly just to calibrate my sense of polynomial time
public class SpeedTest {
    static int operations = 10;
    static int deg = 9;

    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        long totalOps = (long) Math.pow(operations, deg);
        double fulfilled = 1;
        for(long i = 0; i<totalOps; i++){
            fulfilled = Math.sin(fulfilled) * 2;
        }
        System.out.println("O(n^" + deg + ") where n is " + operations + ":\n" + (System.currentTimeMillis() - millis));
    }

    static int subarrays(int n){
        return n * (n + 1) / 2;
    }
}
