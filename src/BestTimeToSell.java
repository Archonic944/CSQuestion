import utils.ParseUtility;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BestTimeToSell {
    public static void main(String[] args) {
        ParseUtility parse = new ParseUtility();
        parse.readTable(1, ",");
        int[] prices = parse.intArrayAtRow(0);
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        System.out.println(maxProfit);
    }
}
