package dsa.dp;

import java.util.Arrays;

public class LIS
{
        int[][] dp = new int[2502][2502];

        public LIS() {
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1);
            }
        }

        public int lengthOfLIS(int[] nums) {
            return solve(nums, 0, -1);  // Start with the first index and prev as -1 (no previous element)
        }

        int solve(int[] nums, int curr, int prev) {
            if (curr >= nums.length) {
                return 0;
            }

            if (prev != -1 && dp[curr][prev] != -1) {
                return dp[curr][prev];
            }

            int take = 0;
            if (prev == -1 || nums[curr] > nums[prev]) {
                take = 1 + solve(nums, curr + 1, curr);
            }

            int skip = solve(nums, curr + 1, prev);

            if (prev != -1) {
                dp[curr][prev] = Math.max(take, skip);
            }

            return Math.max(take, skip);
        }


        public int bottomsUp(int[] nums) {
            int n = nums.length;

            int[] t = new int[n];
            Arrays.fill(t, 1);

            int maxLis = 1;

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        t[i] = Math.max(t[i], t[j] + 1);
                    }
                }
                maxLis = Math.max(maxLis, t[i]);
            }

            return maxLis;  // Return the overall maximum LIS length
        }

}

