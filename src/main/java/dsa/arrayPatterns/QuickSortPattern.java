package dsa.arrayPatterns;

public class QuickSortPattern
{
    void qsort(int[] nums) {
        qsort(nums, 0, nums.length - 1);
    }
    void qsort(int[] nums, int l, int r) {
        if (l >= r) return;
        int p = partition(nums, l, r);
        qsort(nums, l, p - 1);
        qsort(nums, p + 1, r);
    }
    int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int p = r;
        for (int i = l; i < p; i++) {
            if (nums[i] >= pivot) {
                swap(nums, i, p - 1);
                swap(nums, p, p - 1);
                i--;
                p--;
            }
        }
        return p;
    }
    void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
