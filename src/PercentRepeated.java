import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class PercentRepeated {
    //any repetition of a word after its first occurence is a "repeated word" what % of the total content is repeated?
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        input = input.toLowerCase().replace(",", "").replace(".", "").replace("?", "").replace("!", "").replace(":", "");
        String[] words = input.split(" ");
        System.out.println(1.0 - ((double) (uniqueWords(words)) / words.length));
    }

    public static int uniqueWords(String[] phrase){
        HashSet<String> words = new HashSet<>(Arrays.asList(phrase));
        return words.size(); //that was easy
    }
}
