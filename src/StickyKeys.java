//https://leetcode.com/problems/long-pressed-name/
public class StickyKeys {
    public static void main(String[] args) {
        System.out.println(isLongPressedName("alex", "aaleex")); //true
        System.out.println(isLongPressedName("saeed", "ssaeeed")); //true
        System.out.println(isLongPressedName("saeed", "saed")); //false
    }

    static boolean isLongPressedName(String og, String candidate){
        //special cases
        if(og.equals(candidate)) return true;
        else if(og.length() == 1 && candidate.length() == 1) return false;
        //dual iteration
        int ogCount = 0;
        int i = 0,j = 0;
        while(true){
            if(i == og.length()){
                return j == candidate.length(); //once we reach the end of the string, return if there are extra characters
            }
            char c = og.charAt(i);
            int iCount = count(c, og, i);
            int jCount = count(c, candidate, j);
            if(jCount < iCount) return false;
            i += iCount;
            j += jCount;
        }
    }

    static int count(char target, String toSearch, int startFrom){
        //iterate through candidate up to next char change
        char currentInCandidate;
        int candidateCount = 0; //how many of the same character in a row the candidate has
        while(startFrom < toSearch.length()){
            currentInCandidate = toSearch.charAt(startFrom);
            if(currentInCandidate == target){
                candidateCount++;
                startFrom++;
            }
            else break;
        }
        return candidateCount;
    }
}
