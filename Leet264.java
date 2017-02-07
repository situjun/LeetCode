public class Solution {
    public int nthUglyNumber(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        int index2 = 0,index3 = 0,index5 = 0;
        //Key point:这么一看，倒的确是DP
        //相当于在2,3,5分别的最小值上乘以2或3或5.然后比较
        for(int i=1;i<=n-1;i++){
            arr[i] = Math.min(arr[index2]*2,Math.min(arr[index3]*3,arr[index5]*5));
            if(arr[i] == arr[index2]*2) index2++;
            if(arr[i] == arr[index3]*3) index3++;
            if(arr[i] == arr[index5]*5) index5++;
        }
        return arr[n-1];
    }
}