import utils.ParseUtility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ContainsAllKeywords {
    public static void main(String[] args) {
        ParseUtility parse = new ParseUtility();
        parse.readTable(2);
        String target = parse.strAt(0, 1);
        System.out.println(containsAllKeywordsSlow(parse.strArrayAt(0), target));
        System.out.println(containsAllKeywordsFast(parse.strArrayAt(0), target));
    }

    public static boolean containsAllKeywordsFast(String[] keywords, String target){
        char[] nextCharacter = new char[keywords.length];
        for(int i = 0; i<nextCharacter.length; i++){
            nextCharacter[i] = keywords[i].charAt(0);
        }
        for(int i = 0; i<target.length(); i++){
            char c = target.charAt(i);
            //iterate through keywords
            for(int j = 0; j<keywords.length; j++){
                char next = nextCharacter[j];
                if(next == '\n') continue; //we've already completed this keyword
                if(c == next){
                    nextCharacter[j] = '\n'; //we have completed one instance of the keyword
                    continue;
                }
                if(i == target.length() - 1){ //if we've reached the end and still haven't completed the keyword
                    return false;
                }
                nextCharacter[j] = keywords[j].charAt(0); //reset the keyword if we encountered an invalid partial
            }
        }
        return true;
    }

    public static boolean containsAllKeywordsSlow(String[] keywords, String target){
        for(String s : keywords){
            if(!target.contains(s)) return false;
        }
        return true;
    }
}
