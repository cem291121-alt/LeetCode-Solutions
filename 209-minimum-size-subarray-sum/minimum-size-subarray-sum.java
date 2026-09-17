class Solution {
    public int minSubArrayLen(int target, int[] nums) {
       int n=nums.length;
       int sum=0;
       int j=0;
       int minlen=Integer.MAX_VALUE;
       for(int i=0;i<n;i++) {
        sum+=nums[i];
        while(sum>=target){
            int len=i-j+1;
            if(len<minlen)minlen=len;
            sum-=nums[j];
            j++;
        }
       }
       if(minlen==Integer.MAX_VALUE)return 0;
       return minlen;
    }
}