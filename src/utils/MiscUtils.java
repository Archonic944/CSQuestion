package utils;

public class MiscUtils {

    public static IntPair[] vectors = {new IntPair(0, 1), new IntPair(0, -1), new IntPair(1, 0), new IntPair(-1, 0)};
    public static String[] vectorNames = {"down", "up", "right", "left"};

    public static IntPair vectorFromName(String vectorName){
        for (int i = 0; i < vectorNames.length; i++) {
            if(vectorNames[i].equals(vectorName)){
                return vectors[i];
            }
        }
        return null;
    }

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
