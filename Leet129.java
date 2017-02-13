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
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        helper(root,0);
        return sum;
    }
    public void helper(TreeNode node,int num){
        if(node != null){
            if(node.left == null && node.right == null){
                sum = sum+num*10+node.val;
            } else {
                helper(node.left,num*10+node.val);
                helper(node.right,num*10+node.val);
            }
        }
    }
}