508. Most Frequent Subtree Sum

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        helper(map,root);
        List<Integer> list = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue() == max) list.add(entry.getKey());
        }
        //Key:list to array function
        //Integer[] result = new Integer[list.size()]; 
        //Key:不知道为什么，底下这句一直错误....
        //return list.toArray(new Integer[list.size()]);
        //return result;
        int[] result = new int[list.size()];
        int count = 0;
        for(Integer i:list) result[count++] = i;
        return result;
    }
    public int helper(Map<Integer,Integer> map,TreeNode node){
        if(node == null) return 0;
        int sum = node.val + helper(map,node.left)+helper(map,node.right);
        map.put(sum,map.getOrDefault(sum,0)+1);
        max = Math.max(max,map.get(sum));
        return sum;
    }
}


