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

515. Find Largest Value in Each Tree Row
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
    public int[] findValueMostElement(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        //List<Integer> list = new ArrayList<>();
        if(root == null) return new int[0];
        helper(map,root,0);
        int[] result = new int[map.size()];
        int tmp = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            result[tmp++] = entry.getValue();
        }
        /*****
        
        for(Integer node:list){
            result[tmp] = node;
            tmp++;
        }
        *********/
        return result;
    }
    public void helper(Map<Integer,Integer> map,TreeNode node,int depth){
        if(node != null){
            System.out.println("node"+node.val);
            //if(list.size()==depth) list.add(node.val);
            //else if(list.size()==depth+1 && node.val > list.get(depth)) list.set(depth,node.val);
            map.put(depth,Math.max(node.val,map.getOrDefault(depth,node.val)));
            //if(list.get(depth)==n) list.add(node.val);
            //else if(list.size()==depth+1 && node.val > list.get(depth)) list.set(depth,node.val);
            //System.out.println(list);
            helper(map,node.left,depth+1);
            helper(map,node.right,depth+1);
        }
    }
}

513. Find Bottom Left Tree Value
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
    public int findLeftMostNode(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if(root == null) return 0;
        helper(list,root,0);
        return list.get(list.size()-1).val;
    }
    public void helper(List<TreeNode> list,TreeNode node,int depth){
        if(node != null){
            if(list.size()==depth) list.add(node);
            helper(list,node.left,depth+1);
            helper(list,node.right,depth+1);
        }
    }
}
