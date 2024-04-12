import utils.ParseUtility;

import java.util.Arrays;
import java.util.Scanner;

public class EtTuBrute {
    //every minute, randomly chooses a room
    //each room: p% capture chance, 1 minute
    //after m minutes, senators give up
    //need: chance of escaping

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int count = Integer.parseInt(s.nextLine());
        ParseUtility parse = new ParseUtility(s);
        for(int i = 0; i<count; i++){
            parse.readTable(1);
            int rooms = parse.integerAt(0, 0);
            int minutesLeft = parse.integerAt(1, 0);
            parse.readTable(rooms);
            double[] chances = parse.doubleArrayAtColumn(0);
            int[][] doors = new int[rooms][4];
            for(int room = 0; room<rooms; room++) doors[room] = parse.intArrayAtRow(room, 1);
            BruteSituation situation = new BruteSituation(chances, doors, rooms);
            System.out.println(averageChance(situation, 1, minutesLeft));
        }
    }

    public static double averageChance(BruteSituation situation, int current, int minutesLeft){
        minutesLeft--;
        if(minutesLeft <= 0){
            System.out.println("Ended on " + current);
            return situation.chances[current - 1];
        }
        else{
            System.out.println("Calculating chance for " + current + ", minutesLeft:" + minutesLeft);
            double sum = 0;
            for(int i = 0; i<4; i++) sum += averageChance(situation, situation.doors[current - 1][i], minutesLeft);
            sum /= 4;
            return situation.chances[current - 1] * sum;
        }
    }

    public static class BruteSituation {
        double[] chances;
        int[][] doors;
        int rooms;

        public BruteSituation(double[] chances, int[][] doors, int rooms) {
            this.chances = chances;
            this.doors = doors;
            this.rooms = rooms;
        }

        @Override
        public String toString() {
            return "BruteSituation{" +
                    "chances=" + Arrays.toString(chances) +
                    ", doors=" + Arrays.toString(doors) +
                    ", rooms=" + rooms;
        }
    }
}
