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

    public static int subSum(int[] prefixSumArray, int from, int to){
        if(from == 0) return prefixSumArray[to];
        else return prefixSumArray[to] - prefixSumArray[from - 1];
    }

    public static int[] prefixSumArray(int[] a){
        int[] prefixSum = new int[a.length];
        prefixSum[0] = a[0];
        for(int i = 1; i<a.length; i++){
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }
        return prefixSum;
    }
}
