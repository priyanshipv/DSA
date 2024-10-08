package dsa.bt;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution solution = new Solution();
        List<List<Integer>> permutations = solution.permute(nums);

        // Printing the permutations
        System.out.println("Permutations of [1, 2, 3]:");
        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(result, new ArrayList<>(), nums);
            return result;
        }

        private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
            if (tempList.size() == nums.length) {
                result.add(new ArrayList<>(tempList));
            }
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue;
                tempList.add(nums[i]);
                backtrack(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
