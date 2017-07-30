/*170729*/
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length,n2 = nums2.length,left = (n1+n2+1)/2,right = (n1+n2+2)/2;
        return (findKth(nums1,nums2,0,0,left)+findKth(nums1,nums2,0,0,right))/2.0;
    }
    public int findKth(int[] nums1,int[] nums2,int aStart,int bStart,int k){
        //mark0.1:+k后是否-1，用aStart==0,k==1来进行判断
        if(aStart>=nums1.length) return nums2[bStart+k-1];
        if(bStart>=nums2.length) return nums1[aStart+k-1];
        if(k == 1) return Math.min(nums1[aStart],nums2[bStart]);
        //mark0.01：k/2是否-1,道理同mark0.1
        int midA = (aStart+k/2-1),midB = (bStart+k/2-1),midAVal = midA >= nums1.length?Integer.MAX_VALUE:nums1[midA],midBVal = midB >= nums2.length?Integer.MAX_VALUE:nums2[midB];
        if(midAVal < midBVal)
            return findKth(nums1,nums2,midA+1,bStart,k-k/2);
        else 
            return findKth(nums1,nums2,aStart,midB+1,k-k/2);
    }
}

/**/