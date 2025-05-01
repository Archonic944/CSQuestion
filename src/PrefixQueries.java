import java.util.Arrays;

/**
 * Given an integer array nums of length n, compute a prefix sum array.
 * Then, answer q queries where each query consists of two integers, l and r,
 * representing the start and end indices (inclusive) of a subarray.
 * <p>
 * For each query, return the sum of elements from index l to index r in the original array.
 * <p>
 * Constraints:
 * - 0 <= l <= r < n
 * - 1 <= q <= 10^5
 * - Array elements and query count fit in standard integer ranges
 * <p>
 * Example:
 * Input:
 * nums = [2, 4, 1, 7, 3]
 * queries = [(1, 3), (0, 4)]
 * <p>
 * Output:
 * [12, 17]
 */
public class PrefixQueries {
    static int[] nums = {2,4,1,7,3};
    static int[][] queries = {{1,3}, {0,4}};
    public static void main(String[] args) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1; i<prefixSum.length; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        System.out.println(Arrays.toString(prefixSum));
        for(int[] a : queries){
            if(a[0] == 0) System.out.println(prefixSum[a[1]]);
            else System.out.println(prefixSum[a[1]] - prefixSum[a[0] - 1]);
        }
    }
}
