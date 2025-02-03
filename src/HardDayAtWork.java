import utils.Pair;
import utils.ParseUtility;

import java.util.Arrays;
import java.util.Scanner;

//UVA 2018
public class HardDayAtWork {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int times = Integer.parseInt(scan.nextLine());
        ParseUtility parse = new ParseUtility(scan);
        for(int i = 0; i<times; i++){
            int doors = Integer.parseInt(scan.nextLine());
            parse.readTable(doors);
            int[] difficulties = parse.intArrayAtColumn(0);
            String[] names = parse.stringArrayAtColumn(1);
            Pair[] pairs = new Pair[doors];
            for (int j = 0; j < doors; j++) {
                pairs[j] = new Pair<>(difficulties[j], names[j]);
            }
            Arrays.sort(pairs, (p1, p2) -> (int) p1.key >= (int) p2.key ? 1 : -1);
            System.out.println("Case " + (i + 1) + ": ");
            for (int j = 0; j < doors; j++) {
                System.out.println(pairs[j].value);
            }
        }
    }
}
