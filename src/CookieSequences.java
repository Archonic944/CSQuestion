import utils.ParseUtility;

import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

//TJIOI 2024 Problem M - Feeding Elmo
public class CookieSequences {
    public static void main(String[] args) {
        ParseUtility parse = new ParseUtility();
        parse.readTable(1);
        int n = parse.integerAt(0,0);
        int d = parse.integerAt(1, 0);
        int m = parse.integerAt(2, 0);
        parse.readTable(1);
        int[] cookies = parse.intArrayAtRow(0);
        //stress testing (commented)
//        int[] cookies = new int[100000];
//        for(int i = 0; i<cookies.length; i++){
//            cookies[i] = ThreadLocalRandom.current().nextInt(-1000000000, 1000000000);
//        }
        //int d = 1000;
        //int m = 99999;
        System.out.println(solve(d, m, cookies));
    }

    /**
     * Maximum unique arithmetic sequences given a series of integers
     * @param d sequence common difference
     * @param m size of each sequence
     * @param cookies the list of integers
     * @return A count
     */
    public static int solve(int d, int m, int[] cookies){
        //special case for when m is 1: count unique ints
        if(m == 1) {
            HashSet<Integer> ints = new HashSet<>();
            for (int i : cookies) {
                ints.add(i);
            }
            return ints.size();
        }else { //standard case begins here
            Vector<Integer>[] mods = new Vector[d];
            for(int i = 0; i<d; i++){
                mods[i] = new Vector<>();
            }
            for (int i : cookies) {
                mods[Math.abs(i % d)].add(i);
            }
            int result = 0;
            for (Vector<Integer> v : mods) {
                Collections.sort(v);
                for (int i = (m - 1); i < v.size(); i++) {
                    boolean sequence = true;
                    //iterate backwards m elements to see if we can make a sequence
                    for (int j = i - (m - 2); j <= i; j++) { //its m - 2 because we start 1 ahead, and look back
                        if(v.get(j) - v.get(j - 1) != d){
                            sequence = false;
                            break;
                        }
                    }
                    if(sequence) result++;
                }
            }
            return result;
        }
    }
}
