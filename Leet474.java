public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs.length == 0) return 0;
        //Key:类似于01pack。value都是1，但是weight限制变成了一个二维数组。仔细想想，有些绕，但不算很难
        //
        int[][] arr = new int[m+1][n+1];
        //Key:fill只能填充一维数组
        /**
        public static void fill(int[] a,int val)
        Assigns the specified int value to each element of the specified array of ints.
        
        ****/
        //Arrays.fill(arr,0);
        //Key:为了防止数字被重复使用干扰，所以index需要从end开始
        for(int k = 0;k<=strs.length-1;k++){
            int count0 = 0,count1 = 0;
            for(int i = 0;i<=strs[k].length()-1;i++){
                if(strs[k].charAt(i) == '0') count0++;
                else count1++;
            }
            //Log:System.out.println(count0+"+"+count1);
            //Key:Corner case:["10","0","1"] 1 1
            //因为会出现这种单数字，所以除了[0][0],无意义外。[0][1],[1][0]这种也有意义
            for(int i = m;i>=0;i--){
                for(int j =n;j>=0;j--){
                    if(i>=count0 && j >= count1){
                        if(i-count0 >= 0 && j-count1 >= 0)arr[i][j] = Math.max(arr[i][j],arr[i-count0][j-count1]+1);
                    }
                }
            }      
        }
        return arr[m][n];
    }
}