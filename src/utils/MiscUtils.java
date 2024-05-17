package utils;

public class MiscUtils {
    public static void safeSleep(int ms){
        try{
            Thread.sleep(ms);
        }catch(Exception ignored){

        }
    }
}
