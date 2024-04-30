import utils.Coordinates;
import utils.ParseUtility;

import java.util.Scanner;

//2018 UVA HSPC
public class BraveArchery {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int times = Integer.parseInt(scan.nextLine());
        ParseUtility parser = new ParseUtility(scan);
        parser.readTable(times * 2);
        for (int i = 0; i < times; i++) {
            Coordinates start = new Coordinates(parser.integerAt(0, i), parser.integerAt(1, i));
            Coordinates vel = new Coordinates(parser.integerAt(0, i + 1), parser.integerAt(1, i + 1));
            double t = t(0, start.x, vel.x);
            double y = y(t, start.y, vel.x, vel.y);
            if(Math.abs(y) <= 1) System.out.println("5");
            else if(Math.abs(y) <= 2) System.out.println("3");
            else System.out.println("0");
        }
    }

    static double y(double t, int y0, int v, int w){

        return w * t - 5 * t*t + y0;
    }

    static int x(int t, int x0, int v, int w){
        return v * t + x0;
    }

    static double t(int x, int x0, int v){
        return (double) (x - x0) / v;
    }
}
