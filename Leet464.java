public class Solution {
    public int hammingDistance(int x, int y) {
        int remain1 = 0,remain2 = 0,count = 0;
        while(x!=0 || y !=0){
            remain1 = x%2;
            remain2 = y%2;
            if(remain1 != remain2) count++;
            x = x>>1;
            y = y>>1;
            //System.out.println("x"+x+"y"+y);
        }
        return count;
    }
}