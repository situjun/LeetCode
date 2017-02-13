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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        helper(root,list,sum,0,"");
        return list;
    }
    public void helper(TreeNode node,List<List<Integer>> list,int target,int sum,String s){
        if(node != null){
            if(node.left == null && node.right == null){
                if(sum+node.val == target){
                    s = s+node.val;
                    String[] arr = s.split(",");
                    List<Integer> item = new ArrayList<>();
                    for(String num:arr) item.add(Integer.parseInt(num));
                    list.add(new ArrayList<>(item));
                }
            } else {
                helper(node.left,list,target,sum+node.val,s+node.val+",");
                helper(node.right,list,target,sum+node.val,s+node.val+",");
            }
        }
    }
}