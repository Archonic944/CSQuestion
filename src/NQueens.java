import utils.ParseUtility;

import java.util.Scanner;

//UVA HSPC 2018
public class NQueens {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < cases; i++) {
            int n = Integer.parseInt(scan.nextLine());
            int currentColonies = n;
            int amt = n; //amount of ants in each colony
            int total = n;
            while(amt > 1){
                //debug info
                currentColonies = currentColonies * 5 * (amt == n ? 1 : amt);
                amt /= 2;
                total += currentColonies * amt;
            }
            System.out.println(total);
        }
    }
}