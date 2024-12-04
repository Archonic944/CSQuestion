import utils.ParseUtility;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BestTimeToSell {
    public static void main(String[] args) {
        ParseUtility parse = new ParseUtility();
        parse.readTable(1, ",");
        int[] prices = parse.intArrayAtRow(0);
        int maxProfit = 0;
        for(int i = 0; i<prices.length - 1; i++){
            for(int j = i + 1; j<prices.length; j++){
                if(prices[j] - prices[i] > maxProfit){
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        System.out.println(maxProfit);
    }
}
