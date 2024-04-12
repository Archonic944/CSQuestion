import utils.ParseUtility;

import java.util.ArrayList;
import java.util.Scanner;

public class MeetingRooms {
    public static void main(String[] args) {
        ParseUtility parser = new ParseUtility(new Scanner(System.in));
        while(true) {
            parser.readArrayNotation();
            if (parser.table.length < 2) {
                System.out.println(parser.table.length);
                continue;
            }
            ArrayList<Integer>[] overlaps = new ArrayList[parser.table.length]; //index corresponds to meeting, each contains an array of meetings that overlap with it
            for (int i = 0; i < parser.table.length; i++) {
                ArrayList<Integer> currentOverlaps = new ArrayList<>();
                for (int j = 0; j < parser.table.length; j++) {
                    if (i != j) {
                        int[] meeting1 = parser.intArrayAtRow(i);
                        int[] meeting2 = parser.intArrayAtRow(j);
                        if ((meeting1[0] >= meeting2[0] && meeting1[0] < meeting2[1]) || (meeting1[1] > meeting2[0] && meeting1[1] <= meeting2[1])) {
                            currentOverlaps.add(j);
                        }
                    }
                }
                overlaps[i] = currentOverlaps;
            }
            int max = 0;
            for (ArrayList<Integer> list : overlaps) {
                if (list.size() > max) {
                    max = list.size();
                }
            }
            System.out.println(max + 1);
        }
    }
}
