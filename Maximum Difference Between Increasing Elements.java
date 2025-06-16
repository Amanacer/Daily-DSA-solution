class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int minElement = nums[0];
        int maxDiff =-1;

        for(int j=1; j<n; j++){
            if(nums[j]>minElement){
                maxDiff = Math.max(maxDiff, nums[j] - minElement);
            }else{
                minElement = nums[j];
            }
        }
        return maxDiff;
    }
}


