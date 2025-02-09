//UMD 2015

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class MinimumFlips {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numCases = Integer.parseInt(scan.nextLine());
        for(int i = 0; i<numCases; i++) {
            BitSet bits;
            int length = 0;
            { //limit scope of coins string so we can clean it up (it might be massive)
                String coins = scan.nextLine();
                length = coins.length();
                bits = new BitSet(length);
                for(int j = 0; j<length; j++) {
                    bits.set(j, coins.charAt(j) == 'H');
                }
            }
            System.gc(); //now we only have the bitset in memory; memory saved
            ArrayList<String> moves = new ArrayList<>();
            while(bits.cardinality() != length) {
                performBestFlip(bits, length);
                moves.add(bitsToString(bits, length));
            }
            for(String s : moves) {
                System.out.println(s);
            }
        }
    }

    private static void performBestFlip(BitSet bits, int length) {
        int maxYield = 0;
        int maxYieldFrom = -1;
        int maxYieldTo = -1;
        for(int l = length; l>0; l--) {
            //search through length set
            for(int j = 0; (j + l) <= length; j++) {
                int before = countHeads(bits, j, j + l);
                bits.flip(j, j + l);
                int after = countHeads(bits, j, j + l);
                int yield = after - before;
                if(yield > maxYield) {
                    maxYield = yield;
                    maxYieldFrom = j;
                    maxYieldTo = j + l;
                    if(yield == l) {
                        //we won't find a better match than this in this length set (and yes we have to undo it first)
                        bits.flip(maxYieldFrom, maxYieldTo);
                        break;
                    }
                }
                bits.flip(j, j + l); //undo it
            }
            if(maxYield >= (l-1)) { //we gain nothing by moving to a lower length where the maximum yield of that length is ≤ what we already have
                break;
            }
        }
        assert(maxYield > 0);
        bits.flip(maxYieldFrom, maxYieldTo);
    }

    static int countHeads(BitSet bits, int from, int to) {
        int total = 0;
        for(int i = from; i<to; i++) {
            if(bits.get(i)) {
                total++;
            }
        }
        return total;
    }

    static String bitsToString(BitSet bits, int length) {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i<length; i++) {
            if(bits.get(i)) b.append('H');
            else b.append('T');
        }
        return b.toString();
    }


}
