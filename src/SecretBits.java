import java.util.Scanner;

import static utils.MiscUtils.safeSleep;

//UVA HSPC 2024
public class SecretBits {
    //input - binary string
    //two possible operations, performed at most once each:
    // 00 ==> 11
    // 01 ==> 10
    //find max number of new recruits
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.nextLine(); //we can infer length
        String str = scan.nextLine(); //101011
        while(str.contains("101") || str.contains("010") || str.startsWith("0")){
            str = str.replaceAll("01", "10");
            System.out.println(str);
            safeSleep(100);
        }
        str = str.replaceAll("00", "11");
        System.out.println(str);
    }
}
