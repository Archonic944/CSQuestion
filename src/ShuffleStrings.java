import utils.ParseUtility;

import java.util.Scanner;

public class ShuffleStrings {
    //ask the user for an arbitrary amount of strings (with explanation) using a scanner and call the function
    public static void main(String[] args) {
        ParseUtility p = new ParseUtility();
        System.out.println("Enter strings to shuffle, separated by spaces:");
        p.readTable(1);
        String[] strings = p.strArrayAt(0);
        System.out.println(shuffle(strings));
    }

    public static String shuffle(String[] strings){
        StringBuilder sb = new StringBuilder();
        boolean cont = true;
        for(int i = 0; cont; i++){
            cont = false;
            for(String s : strings){
                if(i < s.length()){
                    cont = true;
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }
}
