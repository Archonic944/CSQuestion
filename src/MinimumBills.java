import utils.ParseUtility;

import java.util.Scanner;

public class MinimumBills {
    //Input: 1st line is the amount you need to pay, next lines are 'your wallet' (formatted [bill denomination, amount you have] for each line). Bills are sorted by amount.
    //Output: The minimum amount of bills you need to pay while still passing the amount. YOU DO NOT HAVE TO ACHIEVE THE AMOUNT EXACTLY.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amt = Integer.parseInt(scanner.nextLine());
        ParseUtility parse = new ParseUtility(scanner);
        parse.readTable(Integer.parseInt(scanner.nextLine())); //table length should be inputted before table
        int myTotal = 0;
        int totalBills = 0;
        int[] denominations = parse.intArrayAtColumn(0);
        int[] billAmounts = parse.intArrayAtColumn(1);
        for(int i = 0; i<denominations.length; i++){
            for(int j = 0; j<billAmounts[i]; j++){
                myTotal += denominations[i];
                totalBills++;
                if(myTotal >= amt) System.out.println(totalBills);
            }
        }
        System.out.println("-1");
    }
}
