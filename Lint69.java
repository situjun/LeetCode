/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
 
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        //Key point:关键是要理解递归的过程及顺序
        //广度遍历???
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        helper(list,root,0);
        return list;
    }
    public void helper(ArrayList<ArrayList<Integer>> list,TreeNode node,int depth){
        //Key point:Recursion执行是自上至下的，有时候认为是自下至上，是因为那些algo需要处理return值->那自然就要自下而上的考虑了
        //Key point:List中如果删除了元素，index会自动改变的。相当于动态数组
        //所以如果depth如果不存在的话，会和arr一样报OutOfBound错误
        if(node != null){
            if(list.size()-1<depth) list.add(new ArrayList<Integer>());
            list.get(depth).add(node.val);
            helper(list,node.left,depth+1);
            helper(list,node.right,depth+1);
        }
    }
}