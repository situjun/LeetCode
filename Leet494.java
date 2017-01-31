public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        //Key point:用递归解
        if(nums.length == 0) return 0;
        return helper(nums,0,false,0,S) + helper(nums,0,true,0,S);  
    }
    public int helper(int[] nums,int sum,boolean tag,int index,int S){
        if(index >=0 && index <= nums.length-2){
            if(tag) sum+= nums[index];
            else sum-=nums[index];
            index++;
            //System.out.println("index "+index+" "+sum+"");
            return helper(nums,sum,false,index,S) + helper(nums,sum,true,index,S);
        }
        //System.out.println("index"+index+"......"+sum+"");
         //Key point:注意，因为需要在最后一个node处进行判断，所以写起来和TreeNode递归不太一样.....注意下面这句写法
        if(!tag && (sum-nums[index])==S || tag && (sum+nums[index] == S)) return 1;
        return 0;
    }
}