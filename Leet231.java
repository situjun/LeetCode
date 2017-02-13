public class Solution {
    public boolean isPowerOfTwo(int n) {
        //return Integer.toString(n, 2).matches("10*");
        
        
        //Key:matches("10*") *意味着匹配前面的0任意次
        //toString(n,3)  以n为base，非n进制！！！因为这个没法表示负数形式
        //效率非常慢....
        //return Integer.toString(n,3).matches("10*");
        //所以还是用下面这个吧
        return n>0 && (n==1 || (n%2==0 && isPowerOfTwo(n/2)));
    }
}