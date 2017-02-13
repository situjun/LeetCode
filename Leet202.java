public class Solution {
    public boolean isHappy(int n) {
        // //input 4 会出错
        // //这道题用brute force考虑的地方太多
        // //Math.sqrt() sqrt=>square root 开方。Math.pow(a,b) 求a的b次方
        // int count = 0;
        // //2和20会产生相同的结果。他们的count:2*2和2*2+0*0相同，所以不要判断n（无限循环）,判断起始数字2和20的count是否相同。
        // //int tmp = n;
        // int tmp = -1;
        // boolean flag = true;
        // //双层while，很大可能会 Time Limit Exceeded
        // while(n != 1 && flag){
        //     while(n/10 != 0){
        //         count += Math.pow(n%10,2);
        //         n = n/10;
        //     }
        //     count += Math.pow(n%10,2);
            
        //     //key point
        //     if(tmp<0)tmp = count;
           
        //     //2和20会产生相同的结果。他们的count:2*2和2*2+0*0相同，所以不要判断n（无限循环）,判断起始数字2和20的count是否相同。
        //     if(n == tmp){
        //         flag = false;
        //     }
        //     n = count;
        //      //count need to reset 0
        //     count = 0;
        // }
        // return flag;
        
        if(n == 1) return true;
        //Key:坑爹之处在于，有时候是从中间的某一个数开始循环，而不是从开头的n开始循环，所以只能判断以便所有数字了.....
        //int start = n,tmp = 0;
        int tmp = 0;
        Set<Integer> set = new HashSet<>();
        /*****
        while(n != 0){
            tmp += (int)Math.pow(n%10,2);
            n = n/10;
        }
        n = tmp;
        if(n == 1) return true;
        
        *******/
        while(set.add(n) && n!= 1){
            tmp = 0;
            while(n != 0){
                tmp += (int)Math.pow(n%10,2);
                n = n/10;
            }
            if(tmp == 1) return true;
            n = tmp;
        }
        return false;
    }
}