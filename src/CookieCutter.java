import utils.ParseUtility;

import java.util.Arrays;
import java.util.Scanner;

//TJIOI 2024
public class CookieCutter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ParseUtility parse = new ParseUtility(scan);
        parse.readTable(1);
        int[] xyab = parse.intArrayAtRow(0);
        //sort to make things easier
        System.out.println(minCutTime(xyab));
    }

    static int minCutTime(int[] xyab){
        if(xyab[0] < xyab[1]) swap(xyab, 0, 1);
        if(xyab[2] < xyab[3]) swap(xyab, 2, 3);
        if(!checkPossible(xyab)) return -1;
        if(xyab[0] == xyab[2]){
            if(xyab[1] == xyab[3]) return 0;
            return xyab[1];
        }
        if(xyab[1] == xyab[3]) return xyab[1];
        return xyab[1] + xyab[3];
    }

    static boolean checkPossible(int[] xyab){
        return xyab[0] >= xyab[2] && xyab[1] >= xyab[3];
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
