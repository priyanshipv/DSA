//package dsa.arrayPatterns;
////O(n)
//class SlidingWindowPattern
//{
//    int longestWindow(int[] nums){
//        int max;
//        for(int i=0, j=0; i<nums.length; i++){
//            // code using nums[i] to update the state
//            // that might invalidate the window
//            for (; invalid(); j++)
//            {
//                // code using nums[j] to update the state
//                // and shrink the left edge while the window is invalid
//            }
//            // longest window so far = length([i, j])
//            max = max(ans, i - j + 1);
//        }
//        return max;
//    }
//}
//}
//
////Non-shrinkable
//int longestWindow(int[] nums) {
//    int i = 0, j = 0;
//    for (; i < nums.length; i++) {
//        // code using nums[i] to update the state
//        // that might invalidate the window
//        if (invalid()) {
//            // code using nums[j] to update the state
//            // and shift the window sideways.
//            // the window grows if the state is valid
//            // and shifts if it's invalid.
//            j++;
//        }
//    }
//    return i - j; // the maximum size of the window
//}