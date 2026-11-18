class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
       int ans[]=new int[nums1.length] ;
       for(int i=0;i<nums1.length;i++){
        int target=nums1[i],index=-1;
        for(int j=0;j<nums2.length;j++){
            if(target==nums2[j]){
                index=j;
                break;
            }
        }
        int index1=-1,flag=1;
        for(int k=index+1;k<nums2.length;k++){
            if(nums2[k]>target){
                index1=k;
                flag=0;
                break;
            }
        }
        if(flag==0) ans[i]=nums2[index1];
        else ans[i]=-1;
       }
       return ans;
    }
}