public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        //2pointe+排序??
        if(g.length == 0|| s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0,index1 = 0,index2 = 0;
        while(index1 <= g.length-1 && index2 <= s.length-1){
            //Key point:下面的写法不全面，index2超过后也可能不满足，但result依然会++.Corner case:[1,2,3]，[1,1]会返回错误答案2
            //if(index2 <= s.length-1 && s[index2] < g[index1]) index2++;
            //改写成这样就好多了，而且也不用判断index2 <= s.length-1了，continue之后在while层就判断了
            
            //Key point:
            //if(s[index2++] < g[index1]) continue; 不能这么写，因为只要判断就会index2++,不管if是对是错...
            if(s[index2] < g[index1]){
                index2++;
                continue;
            }
            index1++;
            index2++;
            result++;
        }
        return result;
    }
}