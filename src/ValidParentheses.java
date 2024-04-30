import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ValidParentheses {
    //https://leetcode.com/problems/valid-parentheses/
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true) {
            String line = scan.nextLine();
            System.out.println(isValid(line));
        }
    }
    static List<Character> opens = Arrays.asList('(', '[', '{');
    static List<Character> closed = Arrays.asList(')', ']', '}');

    public static boolean isValid(String s) {
        Stack<Character> balance = new Stack<>();
        for(char c : s.toCharArray()){
            if(closed.contains(c)){
                if(balance.isEmpty() || balance.pop() != opens.get(closed.indexOf(c))) return false;
            }else balance.push(c);
        }
        return balance.isEmpty();
    }
}
