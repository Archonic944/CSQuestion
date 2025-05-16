import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.IntFunction;

//TJIOI 2022 Problem A
public class ThirdFloorPool {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCases = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < testCases; i++) {
            scan.nextLine(); //waste a line
            Integer[] nums = Arrays.stream(scan.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new); //im so sorry for using Integer... please forgive me...
            Integer[] newNums = solve(nums);
            for (int j = 0; j < newNums.length; j++) {
                if(j == newNums.length - 1){
                    System.out.print(newNums[j]);
                }else{
                    System.out.print(newNums[j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static Integer[] solve(Integer[] floors){
        Arrays.sort(floors);
        HashMap<Integer, Integer> numbers = new HashMap<>();
        int sum = 0;
        for (int i = 1; i < floors.length; i++) { //start at 1 because we skip the zero at the beginning
            sum += floors[i];
            numbers.putIfAbsent(floors[i], i);
        }
        int i1 = -1;
        int i2 = -1;
        for (int i = floors.length - 1; i >= 0; i--) {
            int newSum = sum - floors[i];
            int difference = newSum - floors[i];
            if(difference % 2 == 1 || difference < 0) continue;
            int target = difference/2;
            Integer found = numbers.get(target);
            if(found != null){
                if(found == i){ //edge case
                    if(found + 1 < floors.length && floors[found + 1] == target){
                        found++;
                    }else{
                        continue;
                    }
                }
                i1 = i;
                i2 = found;
                break;
            }
        }
        if(i1 == -1 || i2 == -1) return new Integer[]{-1};
        //first, put the 3rd floor back, because it is a special snowflake and we should not have ever moved it in the first place
        swap(floors, 0, 2);
        if(i1 == 2) i1 = 0;
        if(i2 == 2) i2 = 0;
        //now let's do our swaps
        swap(floors, 0, i1);
        swap(floors, 1, i2);
        return floors;
    }

    static void swap(Integer[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
