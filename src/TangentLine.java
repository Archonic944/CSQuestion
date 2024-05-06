import utils.ParseUtility;

import java.util.Scanner;

public class TangentLine {
    //find tangent line at an x value on a trinomial
    //a, b, and c are given
    public static void main(String[] args) {
        ParseUtility parse = new ParseUtility(new Scanner(System.in));
        int a = parse.integerAt(0,0);
        int b = parse.integerAt(1, 0);
        int c = parse.integerAt(2, 0); //we don't need c, since we only need slope
        int x = parse.integerAt(3, 0);
        //power rule
        int slope = 2 * a * x + b;
        System.out.println(slope);
    }
}
