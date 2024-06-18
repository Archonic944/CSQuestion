import utils.Pair;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//UNFINISHED
public class StringCompression {
    static final int MIN = 1;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String comp = compress(input);
        while(!(comp.equals(compress(comp)))){}
        System.out.println("\n\n\n" + comp);
    }

    //hellohellohello ==> 3(hello)
    public static String compress(String in){
        StringBuilder input = new StringBuilder(in);
        for(int len = input.length(); len >= 1; len--){
            for(int pos = 0; pos<=input.length() - len*2; pos++){
                int count = 0;
                String seg = input.substring(pos, pos + len);
                int segsLeft = (input.length() - pos) / len;
                for(int segsAhead = 1; segsAhead<segsLeft; segsAhead++){
                    int start = pos + segsAhead * len;
                    String next = input.substring(start, start + len);
                    if(next.equals(seg)) count++;
                }
                String compressed = (count + 1) + "(" + seg + ")";
                int cost = compressed.length();
                int saved = seg.length() * (count + 1); //it's better than nate's
                System.out.println("count = " + count + ", " + compressed);
                if(saved > cost){
                    //replace segment
                    input.replace(pos, pos + len*(count + 1), compressed);
                    //update pos
                    pos += compressed.length();
                }
            }
        }
        System.out.println(input + "\n\n");
        return input.toString();
    }

    public static int digitCount(int num){
        int count = 0;
        while(num > 0){
            num /= 10;
            count++;
        }
        return count;
    }
}
