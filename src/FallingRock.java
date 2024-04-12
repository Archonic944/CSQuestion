import java.util.Scanner;

public class FallingRock {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int height = scan.nextInt();
        for(int i = height; i>0; i--){
            String str = " ".repeat(i) + "R";
            System.out.println(str);
        }
        System.out.println("______");
    }
}
