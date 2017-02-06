public class Solution {
    /**
     * @param A an array
     * @return total of reverse pairs
     */
    public long reversePairs(int[] A) {
        // Write your code here
        //这道题考点貌似是归并算法
        if(A.length == 0) return 0;
        long result = 0;
        for(int i = 0;i<=A.length-2;i++){
            for(int j = i+1;j<=A.length-1;j++){
                if(A[i]>A[j]) result++;
            }
        }
        return result;
    }
}