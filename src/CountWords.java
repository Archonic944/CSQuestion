import java.util.Scanner;

public class CountWords {
    //it's only a word if it has letters
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int count = 0;
        for(String s : input.split(" ")){
            if(isWord(s)){
                count++;
            }
        }
        System.out.println(count);
    }

    static boolean isWord(String str){
        for(char c : str.toCharArray()){
            if(Character.isLetter(c)){
                return true;
            }
        }
        return false;
    }
}
