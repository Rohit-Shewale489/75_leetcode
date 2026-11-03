
class Solution {
    public int minMoves(int[] nums) {

        int max = nums[0];
        int sum = 0;

        // Find maximum and calculate sum
        for (int num : nums) {

            sum += num;

            if (num > max) {
                max = num;
            }
        }

        // Calculate minimum moves
        return (max * nums.length) - sum;
    }
}
