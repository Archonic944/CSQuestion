import utils.ParseUtility;

import java.util.ArrayList;

import static utils.MiscUtils.isInt;

//https://leetcode.com/problems/baseball-game/description/
public class BaseballGame {
    public static void main(String[] args) {
        ParseUtility parse = new ParseUtility();
        parse.readTable(1);
        String[] operations = parse.strArrayAt(0);
        ArrayList<Integer> scores = new ArrayList<>(operations.length); // capacity should be about the same as the number of operations, give or take a few
        for(String o : operations){
            if(isInt(o)){
                scores.add(Integer.parseInt(o));
            }else if(o.equals("+")){
                scores.add(scores.getLast() + scores.get(scores.size() - 2));
            }else if(o.equals("D")){
                scores.add(scores.getLast() * 2);
            }else if(o.equals("C")){
                scores.removeLast();
            }
        }
        scores.forEach(System.out::println);
        int sum = scores.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
