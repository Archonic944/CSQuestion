import java.util.BitSet;
import java.util.Scanner;

public class BinaryModulus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter two numbers to mod:");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(modulus(a, b));
    }

    public static int modulus(int a, int b){
        while((a - b) >= 0){
            a -= b;
        }
        return a;
    }
}
