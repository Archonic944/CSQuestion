package utils;

public class MiscUtils {
    public static void safeSleep(int ms){
        try{
            Thread.sleep(ms);
        }catch(Exception ignored){

        }
    }

    public static boolean isInt(String str){
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!(Character.isDigit(c) || (c == '-' && i == 0))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(double a, double b, double tolerance){
        return Math.abs(a - b) < tolerance;
    }
}
