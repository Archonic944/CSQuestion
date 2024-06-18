import utils.Coordinates;
import utils.MiscUtils;

import java.util.Scanner;

public class DrawingDonut {
    static final double STEP = 0.01;
    public static void main(String[] args) {
        while(true) {
            int radius = new Scanner(System.in).nextInt();
            boolean[][] canvas = new boolean[radius + 10][radius + 10];
            Coordinates origin = new Coordinates(canvas.length / 2, canvas.length / 2); //we don't even need a pair
            for (int r = radius; r > radius - 4; r--) {
                for (double d = 0; d < Math.PI * 2; d += STEP) {
                    int x = (int) (Math.cos(d) * r) + origin.x - 1;
                    int y = (int) (Math.sin(d) * r / 2) + origin.y - 1;
                    canvas[y][x] = true;
                }
            }
            for (boolean[] bs : canvas) {
                for (boolean b : bs) {
                    if (b) {
                        System.out.print("0");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
