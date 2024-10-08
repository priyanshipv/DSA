package dsa.arrayPatterns;
//O(n log n)
public class MergeSortPattern
{
    void msort(int[] nums) {
        msort(nums, 0, nums.length - 1);
    }
    void msort(int[] nums, int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;
        msort(nums, l, m);
        msort(nums, m + 1, r);
        merge(nums, l, r);
    }
    void merge(int[] nums, int l, int r) {
        int m = (l + r) / 2, i = l, j = m + 1;
        int[] arr = new int[r - l + 1];
        for (int k = 0; k < arr.length; k++) {
            if (j > r || (i <= m && nums[i] < nums[j])) arr[k] = nums[i++];
            else arr[k] = nums[j++];
        }
        for (int k = 0; k < arr.length; k++) {
            nums[l++] = arr[k];
        }
    }
}
