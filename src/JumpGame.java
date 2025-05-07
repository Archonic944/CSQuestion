public class JumpGame {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{5,9,3,2,1,0,2,3,0,1,0,0}));
    }

    public static boolean canJump(int[] nums) {
        for(int i = 0; i<nums.length - 1;){
            int maxJump = nums[i];
            int nextMax = 0;
            int nextIndex = i;
            for(int j = (i + 1); (j<=i+maxJump); j++){
                if(j >= nums.length - 1) return true;
                int adjustedNextMax = nums[j] + (j - i);
                if(adjustedNextMax > nextMax){
                    nextMax = adjustedNextMax;
                    nextIndex = j;
                }
            }
            if(i == nextIndex) return false;
            i = nextIndex;
        }
        return true;
    }
}
