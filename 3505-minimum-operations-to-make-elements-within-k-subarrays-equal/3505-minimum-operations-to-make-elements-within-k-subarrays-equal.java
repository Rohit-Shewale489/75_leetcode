
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long leftSum = 0;
    long rightSum = 0;
    int leftSize = 0;
    int rightSize = 0;

    void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1)
            map.remove(val);
        else
            map.put(val, count - 1);
    }

    void insert(int val) {
        if (left.isEmpty() || val <= left.lastKey()) {
            add(left, val);
            leftSum += val;
            leftSize++;
        } else {
            add(right, val);
            rightSum += val;
            rightSize++;
        }

        balance();
    }

    void erase(int val) {
        if (left.containsKey(val)) {
            remove(left, val);
            leftSum -= val;
            leftSize--;
        } else {
            remove(right, val);
            rightSum -= val;
            rightSize--;
        }

        balance();
    }

    void balance() {
        while (leftSize > rightSize + 1) {
            int val = left.lastKey();

            remove(left, val);
            leftSum -= val;
            leftSize--;

            add(right, val);
            rightSum += val;
            rightSize++;
        }

        while (leftSize < rightSize) {
            int val = right.firstKey();

            remove(right, val);
            rightSum -= val;
            rightSize--;

            add(left, val);
            leftSum += val;
            leftSize++;
        }
    }

    long getCost() {
        int median = left.lastKey();

        long leftCost =
            (long) median * leftSize - leftSum;

        long rightCost =
            rightSum - (long) median * rightSize;

        return leftCost + rightCost;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // Calculate cost of every window
        for (int i = 0; i < n; i++) {

            insert(nums[i]);

            if (i >= x) {
                erase(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Do not select current element
                dp[i][j] = dp[i - 1][j];

                // Select subarray ending at i-1
                if (i >= x &&
                    dp[i - x][j - 1] != INF) {

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
