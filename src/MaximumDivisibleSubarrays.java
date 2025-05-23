import utils.MiscUtils;

import java.util.HashMap;

/**
 * You are given an integer array nums and a target sum k.
 * Count the number of subarrays whose sum is divisible by k.
 * <p>
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - 1 <= k <= 10^4
 * <p>
 * Example:
 * Input:
 * nums = [4, 5, 0, -2, -3, 1], k = 5
 * <p>
 * Output:
 * 7
 */

public class MaximumDivisibleSubarrays {
    static int[] nums = {4,5,0,-2,-3,1};
    static final int k  = 5;
    public static void main(String[] args) {
        int[] prefixSum = MiscUtils.prefixSumArray(nums);
        int total = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i<prefixSum.length; i++){
            int remainder = Math.abs(prefixSum[i] % k);
            int count = map.getOrDefault(remainder, 0);
            total += count;
            map.put(remainder, count + 1);
        }
        System.out.println(total);
    }
}
