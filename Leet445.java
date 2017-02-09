/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1!=null?l1:l2!=null?l2:null;
        //Corner case:下面的case超过了int
        //[3,9,9,9,9,9,9,9,9,9]
        //[7]
        //Corner case:这个case超过了long....这都什么case啊....
        //[2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,9]
        //[5,6,4,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,9,9,9,9]
        /**
        
        long a = 0,b = 0;
        String tmp = "";
        ListNode result = l1;
        while(l1 != null){
            a = a*10+l1.val;
            l1 = l1.next;
        }
        while(l2 != null){
            b = b*10+l2.val;
            l2 = l2.next;
        } 
        l1 = result;
        System.out.println(a+b+"");
        tmp = String.valueOf(a+b);
        for(int i = 0;i<=tmp.length()-2;i++){
            result.val = tmp.charAt(i)-'0';
            if(result.next == null) result.next = new ListNode(0);
            result = result.next;
        }
        result.val = tmp.charAt(tmp.length()-1)-'0';
        return l1;
        
        ***/
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        while(l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int add = 0;
        ListNode result = new ListNode(0);
        while(!stack1.empty() || !stack2.empty()){
            int num = stack1.empty()?stack2.pop():stack2.empty()?stack1.pop():stack1.pop()+stack2.pop();
            result.val = (num+add)%10;
            add = (num+add)/10;
            ListNode tmp2 = new ListNode(0);
            tmp2.next = result;
            result= tmp2;
        }
        if(add == 1){
            result.val = 1;
            return result;
        }
        return result.next;
    }
}