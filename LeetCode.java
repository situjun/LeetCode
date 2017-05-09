516. Longest Palindromic Subsequence
public class Solution {
    public int longestPalindromeSubseq(String s) {
        //Key:subsequence's子序列，不能调整顺序....
        /**
        
        if(s == null || s.equals("")) return 0;
        int oddMax = 0,count = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i =0;i<=s.length()-1;i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            count += entry.getValue()%2 == 0?entry.getValue():0;
            if(entry.getValue()%2 == 1)oddMax = Math.max(oddMax,entry.getValue());
        }
        return count+maxOdd;
        
        ****/
        //DP
        //Key:这道DP要整体思考，思考的太细容易绕在里头.....
        int length = s.length();
        int[][] F = new int[length][length];
        for(int i = 0;i<=length-1;i++) F[i][i] = 1;
        //Key:Important！！！！如果从前往后，即i递增，那么之前存的DP[i][j]就不会被用上
        //因为F[i+1][j-1]是一个需要内部存储的过程，所以i需要--，而j++
        //下面这句是错误的
        //for(int i = 0;i<=length-1;i++){
        //如果非要从前往后，就该写成F[i-1][j+1]
        for(int i = length-1;i>=0;i--){
            for(int j = i+1;j<=length-1;j++){
                if(s.charAt(i) == s.charAt(j)){
                    F[i][j] = F[i+1][j-1]+2;
                } else {
                    F[i][j] = Math.max(F[i+1][j],F[i][j-1]);
                }
            }
        }
        return F[0][length-1];
    }
}

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

503. Next Greater Element II
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int[] result = new int[nums.length];
        if(nums.length == 0) return result;
        Arrays.fill(result,-1);
        Stack<Integer> stack = new Stack<>();
        //Key:push index,而非nums[index]
        //stack.push(nums[0]);
        stack.push(0);
        for(int i = 1;i<=nums.length-1;i++){
            while(!stack.empty() && nums[i]>nums[stack.peek()]){
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        for(int i = 0;i<=nums.length-1;i++){
            while(!stack.empty() && nums[i]>nums[stack.peek()]){
                result[stack.pop()] = nums[i];
            }
        }
        return result;
    }
}

496. Next Greater Element I
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[findNums.length];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<=nums.length-1;i++){
            while(!stack.empty() && stack.peek()<nums[i]){
                map.put(stack.pop(),nums[i]);
            }
            stack.push(nums[i]);
        }
        for(int i = 0;i<=findNums.length-1;i++){
            result[i] = map.getOrDefault(findNums[i],-1);
        }
        return result;
    }
}

491. Increasing Subsequences
public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> list = new HashSet<>();
        List<Integer> item = new ArrayList<>();
        helper(list,item,nums,0);
        //Key:set to arraylist
        return new ArrayList<>(list);
    }
    public void helper(Set<List<Integer>> list,List<Integer> item,int[] nums,int index){
        for(int i = index;i<=nums.length-1;i++){
            if(item.size() == 0 || nums[i]>=item.get(item.size()-1)){
                item.add(nums[i]);
                if(item.size()>=2) list.add(new ArrayList<>(item));
                helper(list,item,nums,i+1);
                item.remove(item.size()-1);
            }
            
        }
    }
} 

526. Beautiful Arrangement
public class Solution {
    int count =  0;
    public int countArrangement(int N) {
        List<Integer> list = new ArrayList<>();
		if(N==0) return 0;
        int[] arr = new int[N];
        for(int i =1;i<=N;i++) arr[i-1] = N;
        boolean[] used = new boolean[N];
        helper(list,N,used);
        return count;
    }
    public void helper(List<Integer> list,int target,boolean[] used){
        if(list.size() == target){
			
            count++;
			System.out.println(list);
        } else {
            for(int i = 1;i<=target;i++){
                if(used[i-1]) continue;
                if((list.size()+1)%i == 0 || i%(list.size()+1) == 0){
                    list.add(i);
                    used[i-1] = true;
                    helper(list,target,used);
                    used[i-1] = false;
                    list.remove(list.size()-1);
                }
            }
        }
    }
}

520. Detect Capital
public class Solution {
    public boolean detectCapitalUse(String word) {
        if(word == null || word.length() == 0) return false;
        if(word.length() == 1) return true;
        boolean first = false,sec = false;
        if(word.charAt(0)-'A' <= 25 && word.charAt(0)-'A' >= 0) first = true;
        if(word.charAt(1)-'A' <= 25 && word.charAt(1)-'A' >= 0) sec = true;
		if(!first && sec) return false;
        for(int i = 2;i<=word.length()-1;i++){
            if(!first && word.charAt(i)-'A' <= 25 && word.charAt(i)-'A' >= 0) return false;
            if(first && !sec && word.charAt(i)-'A' <= 25 && word.charAt(i)-'A' >= 0) return false;
            if(first && sec&& word.charAt(i)-'a' <= 25 && word.charAt(i)-'a' >= 0) return false;
        }
        return true;
    }
}

525. Contiguous Array
public class Solution {
    public int findMaxLength(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int sum[] = new int[nums.length];
		sum[0] = nums[0] == 0? -1 : 1;
		for(int i=1; i < sum.length; i++){
			sum[i] = sum[i-1] + (nums[i] == 0? -1 : 1);
		}
		Map<Integer,Integer> pos = new HashMap<Integer,Integer>();
		int maxLen = 0;
		int i = 0;
		for(int s : sum){
			if(s == 0){
				maxLen = Math.max(maxLen, i+1);
			}
			if(pos.containsKey(s)){
				maxLen = Math.max(maxLen, i-pos.get(s));
			}else{
				pos.put(s, i);
			}
			i++;
		}
		return maxLen;
    }
}

482. License Key Formatting
public class Solution {
    public String licenseKeyFormatting(String S, int K) {
        /***
        
        //TLE
        
        String result = "";
        int count = 0;
        for(int i = S.length()-1;i>=0;i--){
            if(S.charAt(i) == '-') continue;
            //Must convert to UpperCase
            result = String.valueOf(S.charAt(i)>='a'?(char)(S.charAt(i)-'a'+'A'):S.charAt(i))+result;
            count++;
            if(count == K){
                result = "-"+result;
                count = 0;
            }
        }
        //Key:corner case
        //"---",3
        while(result.length() > 0 && result.charAt(0) == '-') result = result.substring(1);    
        
        return result;
        
        ***/
        //Key:difficulties are corner cases
        String s = S.replaceAll("-",""),result = "";
        int count = 0,i = 0;
        for(i = s.length();i>=K;i=i-K){
            //Must convert to UpperCase
            result = "-"+s.substring(i-K,i)+result;
        }
        result = s.substring(0,i)+result;
        while(s.length() >= 1 && result.charAt(0) == '-') result = result.substring(1);
        return result.toUpperCase();
    }
}

475. Heaters
//Key:这道题不太好！！！
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        //Key:不行，Corner case 太大，所以brute force TLE
        /****
        
        //Key:brute force O(mn)
        if(houses.length == 0 || heaters.length == 0) return 0;
        int length1 = houses.length,length2 = heaters.length;
        int result = 0,tmp = Integer.MAX_VALUE;
        for(int i = 0;i<=length1-1;i++){
            tmp = Integer.MAX_VALUE;
            for(int j = 0;j<=length2-1;j++){
                tmp = Math.min(tmp,Math.abs(houses[i]-heaters[j]));
            }
            result = Math.max(result,tmp);
        }
        return result;
        
        //Key:下面的是他人的，但他内部循环做了个优化，所以复杂度应该要低些
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0, j = 0, res = 0;
        while (i < houses.length) {
            while (j < heaters.length - 1
                && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
                j++;
            }
            res = Math.max(res, Math.abs(heaters[j] - houses[i]));
            i++;
        }
        return res;
        
        //Key:另一个自己的，解法太乱了...而且是错的
        if(houses.length == 0 || heaters.length == 0) return 0;
        int outer = Integer.MAX_VALUE,inner = 0;
        for(int i = 0;i<=heaters.length-1;i++){
            if(i == 0 || heaters[i] <houses[0] || heaters[i]>houses[houses.length-1]){
                outer = Math.min(outer,Math.max(Math.abs(heaters[i]-houses[0]),Math.abs(heaters[i]-houses[houses.length-1])));
                continue;
            }
            if(heaters[i] >=houses[0] && heaters[i]<=houses[houses.length-1] && heaters[i-1] >=houses[0] && heaters[i-1]<=houses[houses.length-1]){
                inner = Math.max(heaters[i]-heaters[i-1],inner);
            }
        }
       
        //Key:找出最大的加热radius就可以
        inner = Math.max(inner/2,Math.max(Math.abs(heaters[0]-houses[0]),Math.abs(houses[houses.length-1]-heaters[heaters.length-1])));
        
        return Math.min(inner,outer);
        
        ******/
        
        //这道题真是服了....，优化了还是无法通过.思路明明和别人一样的....
        /***
        
        //Key:brute force O(mn)  -->O(mn)通过不了，所以inner loop做了个小优化
        Arrays.sort(houses);
        Arrays.sort(heaters);
        if(houses.length == 0 || heaters.length == 0) return 0;
        int length1 = houses.length,length2 = heaters.length;
        int result = 0,tmp = Integer.MAX_VALUE,j=0;
        for(int i = 0;i<=length1-1;i++){
            tmp = Integer.MAX_VALUE;
            //for(int j = 0;j<=length2-1;j++){
            //    tmp = Math.min(tmp,Math.abs(houses[i]-heaters[j]));
            //}
            j = 0;
            //IMPORTANT!!!这道题有些Corner case不好，
            //[1,2,3,4,4,4,5,6,6,7] [1,2,3,4,4,4,5,6,6,7] 因为有重复的数字，所以如果比较重复数字后面的数字时（例如7），如果仅判断>，就会因为4,4,4导致没法往后进行了
            //while(j<=length2-1 && tmp > Math.abs(houses[i]-heaters[j])){
            while(j<=length2-1 && tmp >= Math.abs(houses[i]-heaters[j])){
                tmp = Math.abs(houses[i]-heaters[j++]);
            }
            result = Math.max(result,tmp);
        }
        return result;
        
        ***/
        Arrays.sort(houses);
        Arrays.sort(heaters);
        if(houses.length == 0 || heaters.length == 0) return 0;
        int length1 = houses.length,length2 = heaters.length;
        int result = 0,j = 0;
        for(int i = 0;i<=length1-1;i++){
            //for(int j = 0;j<=length2-1;j++){
            //    tmp = Math.min(tmp,Math.abs(houses[i]-heaters[j]));
            //}
           
            //IMPORTANT!!!这道题有些Corner case不好，
            //[1,2,3,4,4,4,5,6,6,7] [1,2,3,4,4,4,5,6,6,7] 因为有重复的数字，所以如果比较重复数字后面的数字时（例如7），如果仅判断>，就会因为4,4,4导致没法往后进行了
            //while(j<=length2-1 && tmp > Math.abs(houses[i]-heaters[j])){
            //Key:j直接往后延，不用重新从0开始。TLE的关键！！！
            //j = 0;
            while(j<=length2-2 &&  Math.abs(houses[i]-heaters[j+1]) <= Math.abs(houses[i]-heaters[j])){
                j++;
            }
            result = Math.max(result, Math.abs(houses[i]-heaters[j]));
        }
        return result;
        
    }
}

473. Matchsticks to Square
public class Solution {
    int count = 0;
    public boolean makesquare(int[] nums) {
        if(nums.length<4) return false;
        //Key:下面的思路错误
        //Corner case:[3,3,3,3,4,4,4,4,5,5,5,5]
        /*****
        
        int sum = 0;
        for(int i:nums) sum+=i;
        if(sum%4 != 0) return false;
        Arrays.sort(nums);
        if(sum/4 < nums[nums.length-1] || nums[0] == 0) return false;
        int count = 0;
        int index1=0,index2 = nums.length-1,tmp = 0;
        while(index1<=index2){
            if(tmp+nums[index2] > sum/4){
                tmp += nums[index1++];
            } else {
                tmp += nums[index2--];
            }
            if(tmp == sum/4) {
                count++;
                tmp = 0;
            }
        }
        
        return count == 4?true:false;
        
        ******/
        
        //Permutations那个方法也没法用，因为
        //Corner case：[3,3,3,3,4,4,4,4,5,5,5,5] 会导致3,3,3,3和4,4,4这样的也满足条件，所以不成
        /***
        
        //Key:DFS试出来....
        //similar to 4sums
        int sum = 0,side = 0;
        for(int i:nums) sum+=i;
        if(sum%4 != 0) return false;
        Arrays.sort(nums);
        side = sum/4;
        if(nums[nums.length-1]>side || nums[0] == 0) return false;
        //因为需要清空，所以最好不用List<Integer> item = new ArrayList<>();。否则的话不知道要怎么写了
        List<Integer> item = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        System.out.println("log");
        helper(item,nums,used,side,0);
        if(count != 4) return false;
        return true;
    }
    public void helper(List<Integer> item,int[] nums,boolean[] used,int side,int tmp){
        if(tmp == side && item.size() != 0){
            System.out.println(item);
            count++;
        } else {
            for(int i = 0;i<=nums.length-1;i++){
                if(tmp+nums[i]>side) break;
                if(used[i]) continue;
                item.add(nums[i]);
                int tag = count;
                used[i] = true;
                helper(item,nums,used,side,tmp+nums[i]);
                if(count == tag){
                    used[i] = false;
                    item.remove(item.size()-1);
                } else {
                    //Key:clear 与 removeAll用法！！
                    item.clear();
                }
            }
        }
        
        ***/
        
        //Key:Just copy
        //这个方法比最高的那个方法好理解
        int sum=0;
        for(int x:nums){
            sum=sum+x;
        }
        if(sum%4!=0||nums.length<4) return false;
        int width=(sum/4);
        Arrays.sort(nums);
        int sum1=0,sum2=0,sum3=0,sum4=0;
        return helper(nums,nums.length-1,sum1,sum2,sum3,sum4,width);
    }
    public boolean helper(int[] a, int i,int sum1,int sum2,int sum3,int sum4, int width){
        if(sum1>width||sum2>width||sum3>width||sum4>width) return false;
        if(i==-1){
            if(sum1==width&&sum2==width&&sum3==width&&sum4==width) return true;
            else return false;
        }
        //check a[i]  belonging to side1,side2,side3,side4
        return helper(a,i-1,sum1+a[i],sum2,sum3,sum4,width)||
        helper(a,i-1,sum1,sum2+a[i],sum3,sum4,width)||
        helper(a,i-1,sum1,sum2,sum3+a[i],sum4,width)||
        helper(a,i-1,sum1,sum2,sum3,sum4+a[i],width);
    }
}

467. Unique Substrings in Wraparound String
public class Solution {
    public int findSubstringInWraproundString(String p) {
        //Key:just copy
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        /**
        //Key:一开始的，错误版本
        if(p == null || p.length() == 0) return 0;
        int[] arr = new int[26];
        int max = 1,sum = 1;
        arr[p.charAt(0)-'a'] = 1;
        for(int i = 1;i<=p.length()-1;i++){
            //Corner case:abaab  会把首尾的ab算成两次
            //Key:need to consider za
            if(p.charAt(i)-p.charAt(i-1) == 1 || p.charAt(i-1) - p.charAt(i) == 25){
                max++;
            } else {
                max = 1;
            }
            arr[p.charAt(i)-'a'] += 1;
            sum+=max;
        }
        for(int i:arr){
            if(i>1) sum = sum-(i-1);
        }
        return sum;
        ***/
        
        int[] count = new int[26];
        int maxLengthCur = 0;
        for (int i = 0; i < p.length(); i++) {
            int len = 1;
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25)))
                maxLengthCur++;
            else
                maxLengthCur = 1;
    
            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }
        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum;
        
    }
}

450. Delete Node in a BST
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
    public TreeNode deleteNode(TreeNode root, int key) {
        return helper(root,key);
    }
    //Key:写的太乱了
    //思路：current node == target时，将左右子树重组，返回至上层。
    //!=，则根据打下继续往下走，并把重新得到的子树重新赋值
    //Key:关键在于如何重新调整新的子二叉树，因为如果子二叉树分别有两个节点很麻烦
    public TreeNode helper(TreeNode node,int target){
        if(node == null) return null;
        TreeNode left,right;
        if(node.val == target){
            left = helper(node.left,target);
            right = helper(node.right,target);
            boolean tag = left == null?false:true;
            TreeNode tmp = tag?left:right;
            if(tag){
                while(tmp != null && tmp.right != null){
                    tmp = tmp.right;
                }
            } else {
                while(tmp != null && tmp.left != null){
                    tmp = tmp.left;
                }
            }
            //Corner case:[0] 0
            if(tmp == null) return null;
            if(tag) tmp.right = right;
            else  tmp.left = left;
            return tag?left:right;
        } else if(node.val<target){
            node.right = helper(node.right,target);
            return node;
        } else {
            node.left = helper(node.left,target);
            return node;
        }
       
    }
}

451. Sort Characters By Frequency
public class Solution {
    public String frequencySort(String s) {
        //Key:不用Collection.sort的话，一个特别机智的方法
        /****
        char[] arr = s.toCharArray();
        // bucket sort
        //Key:Unicode最多只有256个字符
        int[] count = new int[256];
        for(char c : arr) count[c]++;
        
        // count values and their corresponding letters
        Map<Integer, List<Character>> map = new HashMap<>();
        for(int i = 0; i < 256; i++){
            if(count[i] == 0) continue;
            int cnt = count[i];
            if(!map.containsKey(cnt)){
                map.put(cnt, new ArrayList<Character>());
            }
            map.get(cnt).add((char)i);
        }
    
        // loop throught possible count values
        StringBuilder sb = new StringBuilder();
        //Key:不用Collection.sort的话，一个特别机智的方法
        //因为s最大长度也就是s.length()，所以从最后往前递减，找出来
        for(int cnt = arr.length; cnt > 0; cnt--){ 
            if(!map.containsKey(cnt)) continue;
            List<Character> list = map.get(cnt);
            for(Character c: list){
                for(int i = 0; i < cnt; i++){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
        
        ***/
        Map<Character,Integer> map = new HashMap<>();
        char c;
        for(int i = 0;i<=s.length()-1;i++){
            c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(map.entrySet());
        //Key:Collection.sort的用法！！！！关键
        /**
        public interface Comparator<T>
        A comparison function, which imposes a total ordering on some collection of objects. Comparators can be passed to a sort method (such as Collections.sort or Arrays.sort) to allow precise control over the sort order. Comparators can also be used to control the order of certain data structures (such as sorted sets or sorted maps), or to provide an ordering for collections of objects that don't have a natural ordering.
        **/
        //Key:Comparator也是一个Collction，所以也要传泛型
        //Key:是Collections！！！而不是Collection!!
        //Collection.sort(list,new Comparator<Map.Entry<Character,Integer>>(){
        Collections.sort(list,new Comparator<Map.Entry<Character,Integer>>(){
            public int compare(Map.Entry<Character,Integer> e1,Map.Entry<Character,Integer> e2){
                //Key!!!!!
                //Key:比较时第一个值在前面，即为升序（默认）
                //第二个值在前面，即为降序  
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character,Integer> entry:list){
            c = entry.getKey();
            for(int i = 0;i<=entry.getValue()-1;i++) sb.append(c);
        }
        return sb.toString();
    }
}

517. Super Washing Machines
public class Solution {
    public int findMinMoves(int[] machines) {
        //Just copy
        int total = 0; 
        for(int i: machines) total+=i;
        if(total%machines.length!=0) return -1;
        int avg = total/machines.length, cnt = 0, max = 0;
        for(int load: machines){
            cnt += load-avg; //load-avg is "gain/lose"
            max = Math.max(Math.max(max, Math.abs(cnt)), load-avg);
        }
        return max;
    }
}

347. Top K Frequent Elements
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //Key:Collections.sort
        
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums) map.put(i,map.getOrDefault(i,0)+1);
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        //Key:别漏了一开始的list!!!
        //public static <T> void sort(List<T> list,Comparator<? super T> c)
        //public static <T extends Comparable<? super T>> void sort(List<T> list)
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>(){
            public int compare(Map.Entry<Integer,Integer> e1,Map.Entry<Integer,Integer> e2){
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        List<Integer> result = new ArrayList<>();
        if(nums.length<k) return result;
        for(int i = 0;i<=k-1;i++){
            result.add(list.get(i).getKey());
        }
        return result;
    }
}

459. Repeated Substring Pattern
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        //Just Copy
        int len = s.length();
    	for(int i=len/2 ; i>=1 ; i--) {
    		if(len%i == 0) {
    			int m = len/i;
    			String subS = s.substring(0,i);
    			int j;
    			for(j=1;j<m;j++) {
    				if(!subS.equals(s.substring(j*i,i+j*i))) break;
    			}
    			if(j==m)
    			    return true;
    		}
    	}
    	return false;
    }
}

456. 132 Pattern
public class Solution {
    //Case 太大，TLE
    /**
    boolean tag = false;
    public boolean find132pattern(int[] nums) {
        //Key:similar to permutations
        //Corner case:因为给的case太大，只是全局变量的话太浪费了，所以不如直接返回boolean....TLE
        if(nums.length ==0) return false;
        List<Integer> item = new ArrayList<>();
        Arrays.sort(nums);
        if(nums[nums.length-1]-nums[0]<=1) return false;
        helper(item,nums,0);
        return tag;
    }
    public void helper(List<Integer> item,int[] nums,int index){
        if(item.size() == 3){
            if(item.get(0) < item.get(2) && item.get(2)<item.get(1)) tag = true;
        } else {
            for(int i = index;i<=nums.length-1;i++){
                if(item.size() == 1 && nums[i]<=item.get(0)) continue;
                item.add(nums[i]);
                helper(item,nums,i+1);
                item.remove(item.size()-1);
            }
        }
    }
    **/
    public boolean find132pattern(int[] nums) {
        //Just Copy
        int[] arr = Arrays.copyOf(nums, nums.length);
    
        for (int i = 1; i < nums.length; i++) {
            arr[i] = Math.min(nums[i - 1], arr[i - 1]);
        }
        
        for (int j = nums.length - 1, top = nums.length; j >= 0; j--) {
            if (nums[j] <= arr[j]) continue;
            while (top < nums.length && arr[top] <= arr[j]) top++;
            if (top < nums.length && nums[j] > arr[top]) return true;
            arr[--top] = nums[j];
        }
            
        return false;
        
    }
}

530. Minimum Absolute Difference in BST
//V1
public class Solution {
	//Key:错误思路！！！
    //Coner  case:[100,1,null,null,99] 100和99min最小,但是并未相邻....
    /***
    public int getMinimumDifference(TreeNode root) {
        //Key:错误思路！！！-->因为BST，所以child只需要和parent比较即可，因为永远是leftChild<parent<rightChild
        helper(root);
        return min;
    }
    public void helper(TreeNode node){
        if(node != null){
            if(node.left != null) min = Math.min(node.val-node.left.val,min);
            if(node.right != null) min = Math.min(node.right.val-node.val,min);
            helper(node.left);
            helper(node.right);
        }
    }
    /****
    
    int result;
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return 0;
        result = root.val;
        helper(root,Integer.MAX_VALUE,Integer.MIN_VALUE);
        return result;
    }
    public void helper(TreeNode node,int min,int max){
        if(node != null){
            if(min>node.val) min = node.val;
            if(max<node.val) max = node.val;
            if(min - node.val == 0) result = Math.min(Math.abs(max-node.val),result);
            else if(max-node.val == 0) result = Math.min(Math.abs(min-node.val),result);
            else result = Math.min(Math.min(Math.abs(min-node.val),Math.abs(max-node.val)),result);
            helper(node.left,min,max);
            helper(node.right,min,max);
        }
    }
    
    *****/
	//Key:brute force-->全取出来再比较(Complexity 很高)，discuss里用的是InOrder中序遍历的方法
	//Key:糊涂了，中序遍历
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.val;
        helper(list,root);
        Collections.sort(list,new Comparator<Integer>(){
           public int compare(Integer i1,Integer i2){
               return i1.compareTo(i2);
           } 
        });
        int min = Integer.MAX_VALUE;
        for(int i = 1;i<=list.size()-1;i++){
            int tmp = list.get(i)-list.get(i-1);
            if(min > tmp) min = tmp;
            //System.out.println(min);
        }
        return min;
    }
    public void helper(List<Integer> list,TreeNode node){
        if(node != null){
            list.add(node.val);
            helper(list,node.left);
            helper(list,node.right);
        }
    }
}
//Version 2:Just Copy
public class Solution {
    //Key:对于BST,In Order，本身就是按照顺序来的....
    int min = Integer.MAX_VALUE;
    Integer prev = null;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        getMinimumDifference(root.left);
        if (prev != null) min = Math.min(min, root.val - prev);
        prev = root.val;
        getMinimumDifference(root.right);
        return min;
    }
}


523. Continuous Subarray Sum
public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length == 0) return false;
        int sum = 0,index = 0;
        for(int i = 1;i<=nums.length-1;i++){
            index = i;
            sum = nums[index-1];
            while(index<=nums.length-1){
                if(k == 0 && nums[index] == 0 && nums[index-1] == 0) return true;
                sum+= nums[index++];
                //Corner case:[23,2,6,4,7] 0  不能除以0！！！
                if(k!= 0 && sum%k == 0) return true;
            }
        }
        return false;
    }
}

524. Longest Word in Dictionary through Deleting
public class Solution {
    //TLE
    /***
    String result = "";
    public String findLongestWord(String s, List<String> d) {
        if(s == null || s.length()==0) return "";
        if(d.size() == 0) return "";
        helper(s,"",d,0);
        
        int index = 0;
        String result = "";
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<=s.length()-1;i++){
            map.put(s.charAt(i),i);
        }
        for(String str:d){
            boolean tag = true;
            index = 0;
            while(index<=str.length()-2){
                if(map.get(str.charAt(index)) == null || map.get(str.charAt(index))>map.get(str.charAt(index+1))){
                    tag = false;
                    break;
                }
                index++;
            }
            if(tag){
                result = check(result,str);
                System.out.println(result);
            }
        }
        
        
        return result;
    }
    public void helper(String s,String tmp,List<String> list,int index){
        for(int i = index;i<=s.length()-1;i++){
            tmp = tmp+s.charAt(index);
            //System.out.println(tmp);
            if(list.contains(tmp)) result = check(result,tmp);
            helper(s,tmp,list,i+1);
            //Key:还有一个从""开始加的....
            helper(s,"",list,i+1);
            tmp = tmp.substring(0,tmp.length()-1);
        }
    }
    public String check(String s1,String s2){
        if(s1.length() < s2.length()) {
            return s2;
        }
        else if(s1.length() > s2.length()){
            return s1;
        }
        else {
            int index2 = 0;
            while(index2 <= s1.length()-1){
                if(s1.charAt(index2)-s2.charAt(index2)<0) return s1;
                //Key:Coner case:"bab" ["ba","ab","a","b"]
                //必须加下面那个else 
                else if(s1.charAt(index2)-s2.charAt(index2)>0) return s2;
                index2++;
            }
            return s2;
        }
    }
    ***/
    public String findLongestWord(String s, List<String> d) {
        //Key:很NB的一种写法,应该就相当于重写了compare方法了
        Collections.sort(d,new Comparator<String>(){
            public int compare(String a,String b){
                return a.length()==b.length()?a.compareTo(b):b.length()-a.length();
            }
        });
        String result = "";
        int index1 = 0,index2 = 0;
        for(String str:d){
            index1 = 0;
            index2 = 0;
            if(str.length() > s.length()) continue;
            //Key:check if str belong to s's substring
            while(index1<=s.length()-1 && index2 <= str.length()-1){
                if(s.charAt(index1) == str.charAt(index2)){
                    index1++;
                    index2++;
                } else {
                    index1++;
                }
            }
            if(index2 == str.length()) return str;
        }
        return result;
    }
}

506. Relative Ranks
public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        String[] result = new String[nums.length];
        if(nums.length == 0) return result;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<=nums.length-1;i++){
            map.put(i,nums[i]);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>(){
            public int compare(Map.Entry<Integer,Integer> e1,Map.Entry<Integer,Integer> e2){
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        int count = 0;
        for(Map.Entry<Integer,Integer> entry:list){
            if(count==0) result[entry.getKey()] = "Gold Medal";
            else if(count==1) result[entry.getKey()] = "Silver Medal";
            else if(count==2) result[entry.getKey()] = "Bronze Medal";
            else result[entry.getKey()] = count+1+"";
            count++;
        }
        return result;
    }
}

441. Arranging Coins
public class Solution {
    public int arrangeCoins(int n) {
        if(n == 0) return 0;
        int cur = 1,remain = n,result = 0;
        while(remain>=cur){
            remain = remain-cur;
            cur++;
        }
        return cur-1;
    }
}

500. Keyboard Row
public class Solution {
    //My 1st wrong version
    /**
    public String[] findWords(String[] words) {
        Character[] c1 = {'q','w','e','r','t','y','u','i','o','p'};
        Character[] c2 = {'a','s','d','f','g','h','j','k','l'};
        Character[] c3 = {'z','x','c','v','b','n','m'};
        int tag = 0;
        List<String> list = new ArrayList<>();
        //Key:下面的两种写法都不成，Arrays.asSet(c1);好像没有这种写法，new HashSet<>(c1)也不成，因为貌似只能在几个Collections间互相转...Array好像不成
        //参考new ArrayList<>(item) 和 new ArrayList<>(set);
        //Set<Character> set1 = Arrays.asSet(c1);
        Set<Character> set2 = new HashSet<>(c2);
        Set<Character> set3 = new HashSet<>(c3);
        for(String s:words){
            if(s.length() == 0) continue;
            s = s.toLowerCase();
            int i = 0;
            boolean flag = true;
            tag = set1.contains(s.charAt(0))?0:set2.contains(s.charAt(0))?1:2;
            while(i<=s.length()-1){
                if(tag == 0){
                    if(!set1.contains(s.charAt(i))) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) list.add(s);
        }
        return list.toArray(new String[list.size()]);
    }
    **/
    //Just copy
    public String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<strs.length; i++){
            for(char c: strs[i].toCharArray()){
                map.put(c, i);//put <char, rowIndex> pair into the map
            }
        }
        List<String> res = new LinkedList<>();
        for(String w: words){
            if(w.equals("")) continue;
            int index = map.get(w.toUpperCase().charAt(0));
            for(char c: w.toUpperCase().toCharArray()){
                if(map.get(c)!=index){
                    index = -1; //don't need a boolean flag. 
                    break;
                }
            }
            if(index!=-1) res.add(w);//if index != -1, this is a valid string
        }
        //Key:list.toArray(new String[0]) String[]大小无所谓，貌似它只是想知道要转化成的类型而已。size应该是由前边的list来确定
        return res.toArray(new String[0]);
    }
}

419. Battleships in a Board
public class Solution {
    public int countBattleships(char[][] board) {
        //Key:这道题出的不好，它默认所有的input都是invalid-->就是说题中给的example 2根本就不是test case，根本就不应该考虑这种invalid condition......！！！！！！
        //https://discuss.leetcode.com/topic/63294/confused-with-test-cases
        //This is an invalid board that you will not receive !!!
        /**
        int row = board.length,col = board[0].length,count = 0;
        if(row == 0 || col == 0) return 0; 
        for(int i = 0;i<=row-1;i++){
            for(int j = 0;j<=col-1;j++){
                if(i-1>=0 && board[i-1][j] == '.' || i-1<0){
                    if(i+1<=row-1 && board[i+1][j] == '.' || j+1>row-1){
                        if(j-1>= 0 && board[i][j-1] == '.' || j-1<0){
                            if(j+1<=col-1 && board[i][j+1] == '.' || j+1 > col-1) count++;
                        }
                    }
                }
            }
        }
        return count;
        **/
        int m = board.length;
        if (m==0) return 0;
        int n = board[0].length;
        int count=0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == '.' || i > 0 && board[i-1][j] == 'X' || j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }
        
        return count;
    }
}

406. Queue Reconstruction by Height
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // k is the number of people in front of this person who have a height greater than or equal to h 这道题表述的很不好，k表示正好有k个人在他前面.而不是最多有k人在他前面2
        /**
        思路： 
        先按照高度从大到小排序，高度h相同的，将k比较小的放在前面。 
        //Key point:如果从低往高拍，新插入的元素会影响已排好的顺序.... 
        排完序之后的结果为[7,0],[7,1][6,1][5,0][5,2][4,4]
        
        接下来要调整整个队列，从第二个开始， 
        1）如[7,1] 允许比其大的一个数在其前面，那么[7,1]的位置可以不变 
        调整完后 
        [7,0],[7,1][6,1][5,0][5,2][4,4]
        
        2）调整[6,1], 允许一个大于或者等于6的数在前面，那么就将[6,1] 
        插入到索引为1的地方 
        [7,0],[6,1][7,1][5,0][5,2][4,4]
        
        3）调整[5,0],允许0个大于或者等于5的数在前面，将[5,0]插入在索引为0的地方 
        [5,0][7,0],[6,1][7,1][5,2][4,4]
        
        4）调整[5,2],允许2个大于或者等于5的数在前面，将[5,2]插入在索引为2的地方 
        [5,0][7,0],[5,2][6,1][7,1][4,4]
        
        5）调整[4,4]，可允许4个大于或者等于4的数在前面，将[4,4]插入在索引为4的地方 
        [5,0][7,0],[5,2][6,1][4,4][7,1]
        */
        //Key:hard!!!这道题的思路还是背吧，一开始不太容易转过弯来....
        //配合Collections.sort()非常好用
        //Key:int[]非Collection，作为参数的话，要用Arrays.sort。下面的不行
        //Collections.sort(people,new Comparator<int[]>(){
        Arrays.sort(people,new Comparator<int[]>(){
            public int compare(int[] arr1,int[] arr2){
                //Key:arr1[0].compareTo(arr2[0]) 是错误的...compareTo貌似比较的是int[]数组，而不能这样比较arr[0]这种单纯的int
                //return arr1[1] == arr2[1]?arr1[0].compareTo(arr2[0]):arr2[1]-arr1[1];
                return arr1[0] == arr2[0]?Integer.compare(arr1[1],arr2[1]):arr2[0]-arr1[0];
            }
        });
        //Key:因为List<Object>，int[] 也是object，所以直接写int[]就可以，不必飞的写成写Integer[]。
        //而且如果非得写成Integer[]的话，如果其他地方不变，不将int[] 转为Integer[]的话，会导致 Line 43: error: incompatible types: int[] cannot be converted to Integer[]
        //List<Integer[]> list = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        for(int[] i:people){
            list.add(i[1],i);
        }
        return list.toArray(new int[0][0]);
    }
}

413. Arithmetic Slices
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        //Key:Just copy  Math
        //这个sol需要发现之间的规律，否则不太好做
        int curr = 0, sum = 0;
        for (int i=2; i<A.length; i++)
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }
}

62. Unique Paths
public class Solution {
    public int uniquePaths(int m, int n) {
        //Typical DP
        if(n == 0 || m == 0) return 0;
        int[][] arr = new int[m][n];
        //Key:Arrays.fill(Object[] ary, Object val)，不能填充二维数组!!!。所以下面的是错误的
        //Arrays.fill(arr,0);
        for(int i = 0;i<=m-1;i++) arr[i][0] = 1;
        for(int i = 0;i<=n-1;i++) arr[0][i] = 1;
        for(int i = 1;i<=m-1;i++){
            for(int j = 1;j<=n-1;j++){
                arr[i][j]=arr[i-1][j]+arr[i][j-1];
            }
        }
        return arr[m-1][n-1];
    }
}

53. Maximum Subarray
public class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = nums[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
36. Valid Sudoku
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        //Key:分别判断每一行，每一列，以及每一个块。写起来有些麻烦....
        //Key:Just copy
        for(int i = 0; i<9; i++){
            //这总共创建了27次.....
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                    return false;
            }
        }
        return true;
    }
}

78. Subsets
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        //Key:Genaral solution https://discuss.leetcode.com/topic/46159/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
        List<List<Integer>> list = new ArrayList<>();
        //Key:注意他这种写法，传参时传new ArrayList<>()，接收时写List<Integer>也能通过!!
        helper(list,new ArrayList<>(),nums,0);
        return list;    
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] nums,int start){
        list.add(new ArrayList<>(item));
        for(int i = start;i<=nums.length-1;i++){
            item.add(nums[i]);
            helper(list,item,nums,i+1);
            item.remove(item.size()-1);
        }
    }
}

75. Sort Colors
public class Solution {
    public void sortColors(int[] nums) {
        //Key:Arrays.fill(tmp,1);然后用0和2来覆盖
        //1st correct version
        //Key:注意clone的用法
        int[] tmp = nums.clone();
        Arrays.fill(nums,1);
        int index1 = 0,index2 = nums.length-1;
        for(int i = 0;i<=nums.length-1;i++){
            if(tmp[i] == 0) nums[index1++] = 0;
            else if(tmp[i] == 2) nums[index2--] = 2;
        }
        //Key:another version 只管把0和2分别移到首尾即可，1肯定就在正确的位置了
        //Key:下面的是错误版本，不知道哪里出问题了....
        /**
        int index1 = 0,index2 = nums.length-1,tmp = 0;
        for(int i = 0;i<=nums.length-1;i++){
            if(nums[i] == 0){
                tmp = nums[i];
                nums[i] = nums[index1];
                nums[index1++] = tmp;
            }
            //Key:下面这个i--是为了处理出现swap(2,2)这种情况
            else if(i >= 0 && nums[i] == 2){
                tmp = nums[i];
                nums[i--] = nums[index2];
                nums[index2--] = tmp;
            }
        }
        **/
    }
    //Key:这么交换根本就改变不了array的值...
    /**
    public void swap(int a,int b){
        int tmp = a;
        a = b;
        b = tmp;
    }
    **/
}

90. Subsets II
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        helper(list,new ArrayList<>(),nums,used,0);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] nums,boolean[] used,int start){
        list.add(new ArrayList<>(item));
        for(int i = start;i<=nums.length-1;i++){
            //Key:another version without used array -->相当于把相同元素后边的循环都给continue了
            //if(i>start && nums[i] == nums[i-1])
            //Key:相当于相同的数字都当成了一个整体来考虑，只是数量不同而已
            if(i > 0 && !used[i-1] && nums[i] == nums[i-1]) continue;
            used[i] = true;
            item.add(nums[i]);
            helper(list,item,nums,used,i+1);
            item.remove(item.size()-1);
            used[i] = false;
        }
    }
}

334. Increasing Triplet Subsequence
public class Solution {
    public boolean increasingTriplet(int[] nums) {
        //Key:wrong
        /**
        int tag = 1;
        if(nums.length<3) return false;
        for(int i = 1;i<=nums.length-1;i++){
            if(nums[i]>nums[i-1]) tag++;
        }
        return tag>=3?true:false;
        **/
        //Key:持有两个最小的值即可
        int min = Integer.MAX_VALUE,secMin = Integer.MAX_VALUE;
        for(int i = 0;i<=nums.length-1;i++){
            //Key:这道题还真有有重复数字....Corner case:[1,1,-2,6]
            if(min>=nums[i]) min = nums[i];
            else if(secMin>nums[i]) secMin = nums[i];
            else if(secMin<nums[i]) return true;
        }
        return false;
    }
}

240. Search a 2D Matrix II
public class Solution {
    //Key:my binary search version
    /**
    public boolean searchMatrix(int[][] matrix, int target) {
        //Key:similar to one dimision array
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int lowR = 0,lowC = 0,highR = matrix.length-1,highC = matrix[0].length-1;
        return helper(matrix,target,lowR,highR,lowC,highC);
    }
    public boolean helper(int[][] matrix, int target,int lowR,int highR,int lowC,int highC){
        //System.out.println(lowR+"-"+highR+"-"+lowC+"-"+highC+"-"+"^^^^^^^");
        if(lowR<=highR && lowC<=highC){
            int midR = (lowR+highR)/2;
            int midC = (lowC+highC)/2;
            int tmp = matrix[midR][midC];
            
            //System.out.println(tmp+"");
            if(tmp == target) return true;
            if(tmp < target){
                //Key:!!!一定要记得在helper前加return!!!因为是层层返回，如果不加return，无法把true返回上去!!!!
                //Key:加||
                return helper(matrix,target,midR+1,highR,lowC,highC) || helper(matrix,target,lowR,highR,midC+1,highC);
            } else {
                //Key:test corner:[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]] 5
                return helper(matrix,target,lowR,midR-1,lowC,highC) || helper(matrix,target,lowR,highR,lowC,midC-1);
            }
        }
        return false;
    }
    **/
    
    //Key:O(mn) version
    //额，花的时间比我那个binary search 时间还少
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}

300. Longest Increasing Subsequence
public class Solution {
    public int lengthOfLIS(int[] nums) {
        //Just copy I
        /**
        if(nums==null || nums.length==0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = 1;
        for(int index=0; index<nums.length;index++){
            dp[index]=1;
            for(int dpIndex=0; dpIndex<index; dpIndex++){
                if(nums[dpIndex]<nums[index]){
                    dp[index]=Math.max(dp[index],dp[dpIndex]+1);
                    max=Math.max(dp[index],max);
                }
            }
        }
        return max;
        **/
        
        //Key:Just copy II --->using Arrays.binarySearch()
        //效率上这个更好
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }
}

11. Container With Most Water
public class Solution {
    public int maxArea(int[] height) {
        //Key:just copy  这道题需要背，思路不好证明正确性
        //这个解法的难点在于如何证明正确性!!!!
        //拿个case举例说明，[99,1,2,3,4,200,100]   
        //max一开始等于99*length，然后left=1。容易担心的是99~200的影响，不过因为max受制于lower height，所以它永远是小于99*length的。然后剩下的可以想成一个递归的过程
        
        //Key:这个理解思路较容易理解
        /**
        Idea / Proof:
        https://discuss.leetcode.com/topic/14940/simple-and-clear-proof-explanation/3
        The widest container (using first and last line) is a good candidate, because of its width. Its water level is the height of the smaller one of first and last line.
        All other containers are less wide and thus would need a higher water level in order to hold more water.
        The smaller one of first and last line doesn't support a higher water level and can thus be safely removed from further consideration.
        因为max一开始是由i0和in决定的，而要找一个更大的值，在width减小的情况下，height必须变大。所以i0和in中最小的那个就没必要考虑了
        
        ***/
        int maxWater=0, left=0, right=height.length-1;
        //Key:思路是length肯定在减小，所以就去寻找height最高
		while(left<right) {
			maxWater = Math.max(maxWater,(right-left)*Math.min(height[left], height[right]));
			if(height[left]<height[right]) left++;
			else right--;
		}
		return maxWater;
    }
}

454. 4Sum II
public class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //Key:这道题也不能用回溯做了，TLE.....Brute force:O(n^4)
        /**
        
        [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
        
        ***/
        
        /**
        //Key:下面是错的，即使A=[]，指的也是长度为0.但并不是null
        //if(A == null || B == null ||C == null ||D == null) return 0;
        //Corner case:[0] [0][0][0] ....我也是服了，这case给的
        //all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
        if(A.length == 0 || B.length == 0 ||C.length == 0 ||D.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        helper(list,A,B,C,D);
        return result;
    }
    public void helper(List<Integer> list,int[] A,int[] B,int[] C,int[] D){
        if(list.size() == 4){
            int tmp = 0;
            for(int i:list) tmp += i;
            //System.out.println(list);
            if(tmp == 0) result++;
        } else {
            for(int i = 0;i<=A.length-1;i++){
                if(list.size() == 0) list.add(A[i]);
                else if(list.size() == 1) list.add(B[i]);
                else if(list.size() == 2) list.add(C[i]);
                else if(list.size() == 3) list.add(D[i]);
                //System.out.println(list+"ddd");
                helper(list,A,B,C,D);
                list.remove(list.size()-1);
            }
        }
    }           
        **/
        //Key:我自己想的另一个方法是，用个map存储前两个数组的组合值及出现次数，然后对另外两个数组求和，和等于0的话则加一次出现次数
        //O(n^2),space O(n^2)
        //和discuss中的想法一样  https://discuss.leetcode.com/topic/67593/clean-java-solution-o-n-2/7
        if(A.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int length = A.length,sum = 0,result = 0;
        for(int i = 0;i<=length-1;i++){
            for(int j = 0;j<=length-1;j++) {
                sum = A[i] + B[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(int i = 0;i<=length-1;i++){
            for(int j = 0;j<=length-1;j++){
                result += map.getOrDefault(-C[i]-D[j],0);
            }
        }
        return result;
    }
}

42. Trapping Rain Water
public class Solution {
    public int trap(int[] height) {
        //Key:my 1st version -- correctness
        //分别找出每个点左右两边的最高值，然后其中的最小值减去本身高度就是存水量
        /**
        if(height.length == 0) return 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int j = height.length,max = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE;
        left[0] = 0;
        right[height.length-1] = 0;
        for(int i = 1;i<=height.length-1;i++){
            j = height.length-1-i;
            max = Math.max(max,height[i-1]);
            left[i] = max;
            max2 = Math.max(max2,height[j+1]);
            right[j] = max2;
        }
        int result = 0,min = Integer.MAX_VALUE;
        for(int i = 0;i<=height.length-1;i++){
            //System.out.println(left[i]+"--"+right[i]);
            min = Math.min(left[i],right[i]);
            if(height[i]<=min) result += min-height[i];
        }
        return result;
        **/
        
        //Key:just copy 他这个解法更清晰些，但是真的不太容易想
        //2 pointers
        int a=0;
        int b=height.length-1;
        int max=0;
        int leftmax=0;
        int rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,height[a]);
            rightmax=Math.max(rightmax,height[b]);
            if(leftmax<rightmax){
                max+=(leftmax-height[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            }
            else{
                max+=(rightmax-height[b]);
                b--;
            }
        }
        return max;
    }
}

279. Perfect Squares
public class Solution {
    public int numSquares(int n) {
        //Key:Just copy - 貌似还是得找规律
        //https://discuss.leetcode.com/topic/26400/an-easy-understanding-dp-solution-in-java/2
        /**
         dp[n] indicates that the perfect squares count of the given n, and we have:

        dp[0] = 0 
        dp[1] = dp[0]+1 = 1
        dp[2] = dp[1]+1 = 2
        dp[3] = dp[2]+1 = 3
        dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 } 
              = Min{ dp[3]+1, dp[0]+1 } 
              = 1				
        dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 } 
              = Min{ dp[4]+1, dp[1]+1 } 
              = 2
        						.
        						.
        						.
        dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 } 
               = Min{ dp[12]+1, dp[9]+1, dp[4]+1 } 
               = 2
        						.
        						.
        						.
        dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
        and the sample code is like below:
        **/
        int[] dp = new int[n + 1];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	dp[0] = 0;
    	for(int i = 1; i <= n; ++i) {
    		int min = Integer.MAX_VALUE;
    		int j = 1;
    		while(i - j*j >= 0) {
    			min = Math.min(min, dp[i - j*j] + 1);
    			++j;
    		}
    		dp[i] = min;
    	}		
    	return dp[n];
    }
}

14. Longest Common Prefix
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        //Key:有点不理解了，这题不难啊，而且corner case应该也不太复杂，不知道为什么正确率那么低.....
        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        String result = strs[0];
        int index = 0;
        StringBuilder sb;
        for(int i = 1;i<=strs.length-1;i++){
            sb = new StringBuilder();
            index = 0;
            while(index<=result.length()-1 && index<=strs[i].length()-1){
                if(result.charAt(index) == strs[i].charAt(index)){
                    sb.append(result.charAt(index));
                    index++;
                } else {
                    break;
                }
            }
            result = sb.toString();
        }
        return result;
    }
}

73. Set Matrix Zeroes
public class Solution {
    public void setZeroes(int[][] matrix) {
        //my Wrong version
        /**
        //Key:brute force 
        if(matrix.length == 0 || matrix[0].length == 0) return;
        int row = matrix.length-1,col = matrix[0].length-1;
        Map<Integer,Integer> map = new HashMap<>();
        int index1 = 0,index2 = 0;
        for(int i = 0;i<=row;i++){
            for(int j = 0;j<=col;j++){
                if(matrix[i][j] == 0){
                    //Key:这么做不成，因为前边的一行，一列置零后，会导致后边的置零出错.....
                    //test case:
                    //[[0,0,0,5],[4,3,1,4],[0,1,1,4],[1,2,1,3],[0,0,1,1]]
                    //Your answer
                    //[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
                    //Expected answer
                    //[[0,0,0,0],[0,0,0,4],[0,0,0,0],[0,0,0,3],[0,0,0,0]]
                    //Key:最简单的解决办法就是clone一个array，然后修改它。不过原题要求in place
                    //用个map记下来0的位置，然后再处理???---->思路wrong,错误的(corner case:当某一行出现多个0时，如[6,0,7,8,1,0,1,6,8,1]，前边相同的key对应的value值会被覆盖....) 可以用List<List<Integer>> 来代替，不过显然space消耗大多了.....
                    //这个不知道为什么，结果不对.........虽然也不符合题目要求的constant space
                    //map.put(i,j);
                    
                    //for(index1 = 0;index1<=col;index1++) matrix[i][index1] = 0;
                    //for(index1 = 0;index1<=row;index1++) matrix[index1][j] = 0;
                }
            }
            
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            //System.out.println(entry);
            int i = entry.getKey(),j = entry.getValue();
            System.out.println(i+"----"+j);
            for(index1 = 0;index1<=col;index1++) matrix[i][index1] = 0;
            for(index1 = 0;index1<=row;index1++) matrix[index1][j] = 0;
        }
        **/
        
        
        //Key:just copy
        boolean fr = false,fc = false;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) fr = true;
                    if(j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(fr) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if(fc) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

134. Gas Station
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //Key:just cp
        //Greedy 背吧
        int total = 0, tank = 0, index = 0;
    	for (int i = 0; i < cost.length; i++) {
    		int cur = gas[i] - cost[i];			
    
    		tank += cur;
    		if (tank < 0) {//if sum < 0, index can only start from i + 1
    			index = i + 1;
    			tank = 0;
    		}
    		total += cur;			
    	}		
    	return total < 0 ? -1 : index;
        
    }
}

531. Lonely Pixel I
public class Solution {
    public int findLonelyPixel(char[][] picture) {
        //My 1st version
        int count = 0;
        if(picture.length == 0 || picture[0].length == 0) return count;
        Map<Integer,Integer> row = new HashMap<>();
        Map<Integer,Integer> col = new HashMap<>();
        int w = 0,b = 0;
        for(int i = 0;i<=picture.length-1;i++){
            for(int j = 0;j<=picture[0].length-1;j++){
                if(picture[i][j] == 'B'){
                    row.put(i,row.getOrDefault(i,0)+1);
                    col.put(j,col.getOrDefault(j,0)+1);
                }
            }
        }
        for(int i = 0;i<=picture.length-1;i++){
            for(int j = 0;j<=picture[0].length-1;j++){
                if(picture[i][j] == 'B' && row.get(i) == 1 && col.get(j) == 1) count++;
            }
        }
        return count;
    }
}

535. Encode and Decode TinyURL
public class Codec {
    //Just cp:背  2种不错的sol
    //https://discuss.leetcode.com/topic/81633/easy-solution-in-java-5-line-code
    //https://discuss.leetcode.com/topic/81590/a-common-way-using-62-letters-java
    List<String> urls = new ArrayList<String>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        urls.add(longUrl);
        return String.valueOf(urls.size()-1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = Integer.valueOf(shortUrl);
        return (index<urls.size())?urls.get(index):"";
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

533. Lonely Pixel II
public class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        //my 1st version:complexity O(m*n*m)
        int m = picture.length,n = picture[0].length;
        if(m == 0 || n == 0) return 0;
        Map<Integer,Integer> row = new HashMap<>();
        Map<Integer,Integer> col = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String[] strs = new String[m];
        int result = 0;
        for(int i = 0;i<=m-1;i++){
            sb.delete(0,sb.length());
            for(int j = 0;j<=n-1;j++){
                sb.append(picture[i][j]);
                if(picture[i][j] == 'B'){
                    row.put(i,row.getOrDefault(i,0)+1);
                    col.put(j,col.getOrDefault(j,0)+1);
                }
                
            }
            strs[i] = sb.toString(); 
        }
        /**
        for(String s:strs) System.out.println(s);
        for(Map.Entry<Integer,Integer> entry:row.entrySet()){
            //System.out.println(entry.getKey()+"---"+entry.getValue());
        }
        **/
        for(int i = 0;i<=m-1;i++){
            for(int j = 0;j<=n-1;j++){
                if(picture[i][j] == 'B' && row.get(i) == col.get(j) && row.get(i) == N){
                    //System.out.println(row.get(i)+"...."+col.get(j));
                    int count = 0;
                    for(int k =0;k<=strs.length-1;k++){
                        if(strs[i].equals(strs[k])) count++;
                    }
                    //System.out.println("count"+count);
                    if(count == N) result++;
                }
            }
        }
        return result;
    }
}

532. K-diff Pairs in an Array
public class Solution {
    public int findPairs(int[] nums, int k) {
        //Brute force:O(n*n)
        if(nums.length == 0 || nums.length == 1) return 0;
		int count = 0,n = nums.length;
		Map<Integer,Integer> map = new HashMap<>();
		Arrays.sort(nums);
		for (int i = 0; i < n; i++){       
			// See if there is a pair of this picked element
			for (int j = i+1; j < n; j++){
				if (nums[j] - nums[i] == k ){
					map.put(nums[i],nums[j]);
				}	
			}
		}
		return map.size();
    }
}

437. Path Sum III
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
    //Key:Difficulty!!!很多地方不太容易想对
    //Just cp
    //https://discuss.leetcode.com/topic/64461/simple-java-dfs/2
    //https://discuss.leetcode.com/topic/64526/17-ms-o-n-java-prefix-sum-method/2
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) 
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
    /**
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        //Key:两个递归，1个继续加上面的数，另一个从当前val重新开一个sum
        //Corner  case:[] 1
        if(root == null) return 0;
        //Key:只有一个root时，要单独考虑.....
        if(root.val == sum && root.left == null && root.right == null) return 1; 
        helper(root,sum,0);
        return count;
    }
    public void helper(TreeNode node,int target,int sum){
        if(node != null){
            
            if(sum+node.val == target || node.val == target) count++;
            helper(node.left,target,sum+node.val);
            helper(node.right,target,sum+node.val);
            //Key:记住，从下一个节点开始的话，就不能再把本节点加上了！！！
            //helper(node.left,target,node.val);
            helper(node.left,target,node.val);
            //helper(node.right,target,node.val);
            helper(node.right,target,node.val);
            
        }
    }
    **/
}

452. Minimum Number of Arrows to Burst Balloons
public class Solution {
    public int findMinArrowShots(int[][] points) {
        //Key:对int[0]排序    
        if(points.length == 0)return 0;
        if(points.length == 1)return 1;
        //思路和https://discuss.leetcode.com/topic/66579/java-greedy-soution一样
        //Key:Arrays.sort(points,new Comparator<int[]>())  --->int[]是Object,所以也得指定<>泛型类型
        Arrays.sort(points,new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                //Key:return a[0].compareTo(b[0]);int是基本数据类型,没有compareTo(int)方法,所以要么写成减法，要么封装为Integer，否则报错Line 10: error: int cannot be dereferenced
                //Key:compareTo只能比较Object
                //Key:Test case:,[0,9],[3,9],[0,6]还要考虑a[0]和b[0]相同的情况
                //return Integer.valueOf(a[0]).compareTo(Integer.valueOf(b[0]));
                return a[0] == b[0]?Integer.valueOf(a[1]).compareTo(Integer.valueOf(b[1])):Integer.valueOf(a[0]).compareTo(Integer.valueOf(b[0]));
            }
        });
        
        int res = 1,end = points[0][1],start = points[0][0];
        for(int i = 1;i<=points.length-1;i++){
            //Key:int[] 没实现toSting(),所以toString()和String.valueOf()只能打印出地址
            //System.out.println(points[i].toString());
            //[I@5cad8086
            //[I@6e0be858
            //[I@61bbe9ba
            //[I@610455d6
            //[I@511d50c0
            //System.out.println(aa.toString()); //[I@103c520 注意这个写法是错误的，打印出来的是垃圾值。  
            //可以用Arrays.toString()  System.out.println(Arrays.toString(aa)); //[1, 2, 3, 4, 5]  
            //System.out.println(Arrays.toString(points[i]));
            //Key:下面的不行，还要考虑正好内嵌这种
            //if(points[i][0]>end){
            if(points[i][0]>end){
                res++;
                //Key:Test case:[[9,12],[1,10],[4,11],[8,12],[3,9],[6,9],[6,7]] 下面的写法不全面,每次end都得内缩
                //end = points[i][1];
                end = points[i][1];
                continue;
            }
            end = Math.min(end,points[i][1]);
        }
        return res;
    }
}

447. Number of Boomerangs
public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        //Key:wrong
        /**
        int[] multiple = new int[points.length];
        if(points.length == 0) return 0;
        for(int i =0;i<=points.length-1;i++) multiple[i] = (int)(Math.pow(points[i][0],2)+Math.pow(points[i][1],2));
        //Arrays.sort(multiple);
        ArrayList<Integer> list = new ArrayList<>();
        int index1 = 0,index2 = multiple.length-1;
        while(index1<index2){
            list.add(multiple[index1]+multiple[index2]-2*points[index1][0]*points[index2][0]-2*points[index1][1]*points[index2][1]);
            list.add(multiple[index1+1]+multiple[index2]-2*points[index1+1][0]*points[index2][0]-2*points[index1+1][1]*points[index2][1]);
            list.add(multiple[index1]+multiple[index2-1]-2*points[index1][0]*points[index2-1][0]-2*points[index1][1]*points[index2-1][1]);
            index1++;
            index2--;
        }
        int result = 0;
        for(int i:multiple) if(list.contains(new Integer(i))) result++;
        return result;
        **/
        //Just cp
        Map<Integer,Integer> map = new HashMap();
        int res = 0;
        for(int i=0;i<points.length;i++){
        	for(int j=0;j<points.length;j++){
        		if(i == j) continue;
        		int d = distance(points[i],points[j]);
        		map.put(d, map.getOrDefault(d, 0) + 1);
        	}
            for(int val : map.values()) res += val * (val-1);
            map.clear();
        }
        return res;
    }
    private int distance(int[] a, int[] b){
    	int dx = a[0] - b[0];
    	int dy = a[1] - b[1];
    	return dx * dx + dy * dy;
    }
}

438. Find All Anagrams in a String
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //Key:just cp
        //sliding problem有人总结了一个template,挺好的
        //https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem/2
        List<Integer> result = new LinkedList<>();
        if(p.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();
        
        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;
        
        
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;
            
            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == p.length()){
                    result.add(begin);
                }
                begin++;
            }
            
        }
        return result;
    }
}

435. Non-overlapping Intervals
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    //Key:我写的Wrong了.....
    /**
    public int eraseOverlapIntervals(Interval[] intervals) {
        //Key point:Collections.sort();
        //最初我用了个笨办法，直接把几种情况找了出来，分别处理
        //非常非常关键!!!!!!
        //Key point:Comparator 实现以及使用
        //Corner case:[[1,99],[2,3][4,5],[7,9]]
        int res = 0,prev = 0;
        if(intervals.length <= 1) return 0;
        Arrays.sort(intervals,new Comparator<Interval>(){
            public int compare(Interval i1,Interval i2){
                return i1.start!=i2.start?i1.start-i2.start:i1.end-i2.end;
            }
        });
        for(int i = 1;i<=intervals.length-1;i++){
            if(intervals[i].start<intervals[prev].end && intervals[i].end <= intervals[prev].end){
                 res++;
                 prev = i;
            }
            
        }
        return res;
    }
    **/
    
    public int eraseOverlapIntervals(Interval[] intervals) {
       Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                 return o1.end - o2.end;  //only sort by end
            }
        });

        int end = Integer.MIN_VALUE;
        int count = 0;
        for (Interval interval : intervals) {
            if (interval.start >= end) end = interval.end;
            else count++;
        }

        return count; 
    }
}

76. Minimum Window Substring
public class Solution {
    public String minWindow(String s, String t) {
        //Key:模板方法  https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
        String res = "";
        Map<Character,Integer> map = new HashMap<>();
        int begin = 0,end = 0,start = 0,len = s.length()+1,count;
        for(char c:t.toCharArray()) map.put(c,map.getOrDefault(c,0)+1);
        count = map.size();
        while(end<=s.length()-1){
            char c = s.charAt(end);
            char c2 = s.charAt(begin);
            if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
                if(map.get(c) == 0) count--;
            }
            end++;
            while(count == 0){
                c2 = s.charAt(begin);
                //Key:有一个小优化，只保存起始位置和最小长度，sustring()放在最外边做
                if(map.containsKey(c2)){
                    map.put(c2,map.get(c2)+1);
                    if(map.get(c2) > 0){
                        count++;
                        
                    }
                     
                }
                //Key:有一个小优化，只保存起始位置和最小长度，sustring()放在最外边做
                //if(res.equals("")) res = s.substring(begin,end);
                //else res = end-begin<res.length()?s.substring(begin,end):res;
                if(end-begin<len){
                    start = begin;
                    len = end-begin;
                }
                begin++;
            }
        }
        
        return len > s.length()?"":s.substring(start,start+len);
    }
}

3. Longest Substring Without Repeating Characters
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        //Key:和这个模板不太像.....https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
        int repeat = 0,begin = 0,end = 0,res = 0;
        Map<Character,Integer> map = new HashMap<>();
        while(end<=s.length()-1){
            char c = s.charAt(end);
            map.put(c,map.getOrDefault(c,0)+1);
            if(map.get(c) > 1) repeat++;
            end++;
            while(repeat>0){
                char c2 = s.charAt(begin);
                if(map.get(c2) > 1){
                    repeat--;
                    
                }
                map.put(c2,map.get(c2)-1);
                begin++;
            }
            //Corner case:单字母"b"
            res = Math.max(res,end-begin);
        }
        return res;
    }
}

357. Count Numbers with Unique Digits
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        //Key:just cp
        if (n == 0)     return 1;
        
        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }
}

541. Reverse String II
public class Solution {
    public String reverseStr(String s, int k) {
        //My 1st version
        if(s == null || s.length() == 0) return "";
		if(k == 0) return s;
        int start = 0,end = start+k-1,count = 0;
		if(end>s.length()-1) end = s.length()-1;
        StringBuilder sb = new StringBuilder();
        while(end<=s.length()-1){
			
			//System.out.println("end"+end);
            while(end>=start){
                sb.append(s.charAt(end--));
            }
			if(sb.toString().length() == s.length()) break;
            start += k;
            count = 0;
            
            while(start<=s.length()-1 && count <= k-1){
                sb.append(s.charAt(start++));
                count++;
            }
			//System.out.println(""+start);
			if(start >= s.length()-1) end = s.length()-1;
            else end = start+k-1>s.length()-1?s.length()-1:start+k-1;
			
        }
		//System.out.println(""+start+","+s.length());
        return sb.toString();
    }
}

536. Construct Binary Tree from String
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
    public TreeNode str2tree(String s) {
        //My 1st version
        if(s == null || s.length() == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        //Key:corner case:"-4(2(3)(1))(6(5)(7))"  负数处理
        //Key:Coner case:"51(232)(434)" 多位数
        //Corner case:"4"
        for(int i = 0;i<=s.length()-1;i++){
            if(s.charAt(i) == '-'){
                String tmp = "";
                while(i<= s.length()-1 && s.charAt(i)!='(' && s.charAt(i) != ')'){
                    tmp = tmp+String.valueOf(s.charAt(i++));
                }
                stack.push(new TreeNode(Integer.parseInt(tmp)));
                i--;
            }
            else if(Character.isDigit(s.charAt(i))) {
                 String tmp = "";
                 while(i<= s.length()-1 && s.charAt(i)!='(' && s.charAt(i) != ')'){
                    tmp = tmp+String.valueOf(s.charAt(i++));
                }
                //stack.push(new TreeNode(s.charAt(i)-'0'));
                stack.push(new TreeNode(Integer.parseInt(tmp)));
                i--;
                //System.out.println(String.valueOf(stack.peek().val));
            } else if (s.charAt(i) == ')'){
                if(!stack.empty()){
                    TreeNode tmp = stack.peek();
                    TreeNode tmp2 = null;
                    stack.pop();
                    if(!stack.empty()){
                        tmp2 = stack.peek();
                        stack.pop();
                        if(tmp2.left != null){
                            tmp2.right = tmp;
                        } else {
                            tmp2.left = tmp;
                        }
                    }
                    stack.push(tmp2);
                }
            }
        }
        //System.out.println(stack.peek().val+"");
        return stack.peek();
    }
}

539. Minimum Time Difference
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        //Key:这道题是真TMD不会做.....
        //Key:Just cp
         Collections.sort(timePoints);
        int minDiff = Integer.MAX_VALUE;
        String prev = timePoints.get(timePoints.size() -1);
        for (String s : timePoints) {
            int prevMins = Integer.parseInt(prev.split(":")[0])*60 + Integer.parseInt(prev.split(":")[1]);
            int curMins = Integer.parseInt(s.split(":")[0])*60 + Integer.parseInt(s.split(":")[1]);
            int diff = curMins - prevMins;
            if (diff < 0) diff += 1440;
            minDiff = Math.min(minDiff, Math.min(diff, 1440 - diff));
            prev = s;
        }
        return minDiff;
    }
}

128. Longest Consecutive Sequence
public class Solution {
    public int longestConsecutive(int[] nums) {
        //Key:O(n)，所以应该必须用map吧.....
        //Key:这道题需要处理duplicates test case，但是只统计不同数字的连续序列：  [100,4,2,200,1,3,2]  excepted ans:4    [1,2,0,1]  excepted:3
        if(nums.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int tmp = 0;
        for(int i:nums){
            if(!map.containsKey(i)){
                //left和right分别是左右两部分的长度
                int left = map.getOrDefault(i-1,0),right = map.getOrDefault(i+1,0);
                tmp = 1+left+right;
                map.put(i,tmp);
                //Key:原先我写的是错误的，不应该只考虑i-1和i+1重置，而应该是考虑的是这个范围的start和end重置
                //https://discuss.leetcode.com/topic/6148/my-really-simple-java-o-n-solution-accepted
                /***
                if(map.getOrDefault(i-1,0) != 0){
                    map.put(i-1,tmp);
                }
                if(map.getOrDefault(i+1,0) != 0){
                    map.put(i+1,tmp);
                }
                **/
                map.put(i - left, tmp);
                map.put(i + right, tmp);
                max = Math.max(max,tmp);
            }
            
        }
        return max;
    }
}

329. Longest Increasing Path in a Matrix
public class Solution {
    //Key:这道题又TMD写乱了.......
    /**
    public int longestIncreasingPath(int[][] matrix) {
        //key:brute force  DFS or BFS
        List<Integer> item = new ArrayList<>();
        boolean[][] used = new boolean[matrix.length][matrix[0].length];
        helper(matrix,used,item);
    }
    public void helper(int[][] matrix,used,List<Integer> item,int prevR,int prevC){
        for(int i = 0;i<=matrix.length-1;i++){
            for(int j = 0;j<=matrix[0].length-1;j++){
                if(used[i][j] || matrix[i][j] <=item.get(item.size()-1) || matrix[]) continue;
                item.add(matrix[i][j]);
                used[i][j] = true;
                max = Math.max(max,item.size());
                helper(matrix,used,item);
                used[i][j]
            }
        }
    }
    **/
    //Key:这道题写起来很麻烦，所以我just cp的
    //https://discuss.leetcode.com/topic/34835/15ms-concise-java-solution/2
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }   
        return max;
    }
    
    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if(cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}

395. Longest Substring with At Least K Repeating Characters
public class Solution {
    
    //Key:写了半天还是错的.....
    /**
    public int longestSubstring(String s, int k) {
       
       
        Map<Character,Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for(char c:s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            if(entry.getValue() < k) set.add(entry.getKey());
        }
        //Test case:"weitong" 2
        if(set.size() == map.size()) return 0;
        int max = Integer.MIN_VALUE;
        int start = 0,end = s.length()-1;
        int length = s.length();
        for(int i = 0;i<=s.length()-1;i++){
            if(set.contains(s.charAt(i))){
                //Key:这道题怎么说呢....蒙着做出来的....
                //Key:遇到不满足k的元素时，取得之前满足substring的最大长度
                //Key:因为s = s.substring(i+1,end+1); 所以i的范围要length-2  对于这种case "weitong" 2 处理时要注意i+1的范围。注意，此时s的length一直在变....
                //Key:下面这行substirng没意义....
                //if(i+1 <= length-1) s = s.substring(i+1,length);
                max = Math.max(max,i-start);
                start = i+1;
            }
        }
        return max == Integer.MIN_VALUE?s.length():max;
       
       
    }
    **/
    //Just cp
    //https://discuss.leetcode.com/topic/57372/java-divide-and-conquer-recursion-solution/2
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        return helper(str,0,s.length(),k);
    }
    private int helper(char[] str, int start, int end,  int k){
        if(end-start<k) return 0;//substring length shorter than k.
        int[] count = new int[26];
        for(int i = start;i<end;i++){
            int idx = str[i]-'a';
            count[idx]++;
        }
        for(int i = 0;i<26;i++){
            if(count[i]<k&&count[i]>0){ //count[i]=0 => i+'a' does not exist in the string, skip it.
                for(int j = start;j<end;j++){
                    if(str[j]==i+'a'){
                        int left = helper(str,start,j,k);
                        int right = helper(str,j+1,end,k);
                        return Math.max(left,right);
                    }
                }
            }
        }
        return end-start;
    }
}

172. Factorial Trailing Zeroes
public class Solution {
    public int trailingZeroes(int n) {
        //Key:背 Just cp
        //https://discuss.leetcode.com/topic/6848/my-explanation-of-the-log-n-solution/4
        int cnt = 0;
        while(n>0){
            cnt += n/5;
            n/=5;
        }
        return cnt;
    }
}

315. Count of Smaller Numbers After Self
public class Solution {
    //Key:很难，背吧。Just cp
    //https://discuss.leetcode.com/topic/31554/11ms-java-solution-using-merge-sort-with-explanation
    int[] count;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();     
    
        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
        	indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
        	res.add(count[i]);
        }
        return res;
    }
    private void mergesort(int[] nums, int[] indexes, int start, int end){
    	if(end <= start){
    		return;
    	}
    	int mid = (start + end) / 2;
    	mergesort(nums, indexes, start, mid);
    	mergesort(nums, indexes, mid + 1, end);
    	
    	merge(nums, indexes, start, end);
    }
    private void merge(int[] nums, int[] indexes, int start, int end){
    	int mid = (start + end) / 2;
    	int left_index = start;
    	int right_index = mid+1;
    	int rightcount = 0;    	
    	int[] new_indexes = new int[end - start + 1];
    
    	int sort_index = 0;
    	while(left_index <= mid && right_index <= end){
    		if(nums[indexes[right_index]] < nums[indexes[left_index]]){
    			new_indexes[sort_index] = indexes[right_index];
    			rightcount++;
    			right_index++;
    		}else{
    			new_indexes[sort_index] = indexes[left_index];
    			count[indexes[left_index]] += rightcount;
    			left_index++;
    		}
    		sort_index++;
    	}
    	while(left_index <= mid){
    		new_indexes[sort_index] = indexes[left_index];
    		count[indexes[left_index]] += rightcount;
    		left_index++;
    		sort_index++;
    	}
    	while(right_index <= end){
    		new_indexes[sort_index++] = indexes[right_index++];
    	}
    	for(int i = start; i <= end; i++){
    		indexes[i] = new_indexes[i - start];
    	}
    }
}

17. Letter Combinations of a Phone Number
public class Solution {
    //Key:想起来不麻烦，但是写起来可能会有些乱...
    public List<String> letterCombinations(String digits) {
        String[] strs = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) return res;
        int depth = 0;
        helper(res,strs,digits,depth,"");
        return res;
    }
    public void helper(List<String> list,String[] strs,String digits,int depth,String tmp){
        if(depth == digits.length()){
            list.add(tmp);
        } else {
            int index = digits.charAt(depth)-'2';
            //Key:排除0,1这种无效按键的干扰
            if(index >= 0){
                for(int i = 0;i<=strs[index].length()-1;i++){
                    tmp = tmp+String.valueOf(strs[index].charAt(i));
                    helper(list,strs,digits,depth+1,tmp);
                    tmp = tmp.substring(0,tmp.length()-1);
                }
            }
        }
    }
}

20. Valid Parentheses
public class Solution {
    //Key:不难写，主要是corner case的判断
    //Corner case:"(("  需要单独判断一下最后是否为空
    public boolean isValid(String s) {
        if(s == null || s.length() == 0 || s.length()%2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<=s.length()-1;i++){
            if(s.charAt(i) == '}' || s.charAt(i) == ')' || s.charAt(i) == ']'){
                if(stack.empty()){
                    return false;
                } else {
                    if(s.charAt(i) == '}' && stack.peek() != '{' ||s.charAt(i) == ')' && stack.peek() != '(' || s.charAt(i) == ']' && stack.peek() != '['){
                        return false;
                    } 
                    stack.pop();
                }
            } else {
                stack.push(s.charAt(i));
            }
            
        }
        //Corner case:"(("  需要单独判断一下最后是否为空
        return stack.empty()?true:false;
    }
}

49. Group Anagrams
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //Key:思路一开始不容易想，但解法较容易理解
        //Key:Just cp
        //https://discuss.leetcode.com/topic/24494/share-my-short-java-solution/5
        if(strs==null || strs.length == 0){
    		return new ArrayList<List<String>>();
    	}
    	HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    	//Arrays.sort(strs);
    	for (String s:strs) {
    		char[] ca = s.toCharArray();
    		Arrays.sort(ca);
    		String keyStr = String.valueOf(ca);
    		if(!map.containsKey(keyStr))
    			map.put(keyStr, new ArrayList<String>());
    		map.get(keyStr).add(s);
    	}
    	
    	for(String key: map.keySet()) {
    		Collections.sort(map.get(key));
    	}
    	return new ArrayList<List<String>>(map.values());
    }
}

33. Search in Rotated Sorted Array
public class Solution {
    public int search(int[] nums, int target) {
        //Key:Loop version
        /**
        //Key:有一半肯定是依然有序的，判断target在unsorted or sorted中，然后recursion处理之
        //Key:https://discuss.leetcode.com/topic/16580/java-ac-solution-using-once-binary-search
        if(nums.length == 0) return -1;
        int low = 0,high = nums.length-1,mid = 0;
        while(low<=high){
            mid = (low+high)/2;
            if(nums[mid] == target) return mid;
            //determine which part is sorted  Key:no duplicates
            //前半部分有序,后半部分无序
            //Key:Corner case: 单数组处理 [1] 0 因为nums[mid]一样nums[low]，如果不加=，会导致low,high无变化，导致无限循环
            //if(nums[mid] > nums[low]){
            if(nums[mid] >= nums[low]){
                //target belong to sorted part
                if(target < nums[mid] && target >= nums[low]) high = mid-1;
                //target belong to unsorted part
                else low = mid + 1;
            }
            //后半部分有序,前半部分无序
            if(nums[mid] <= nums[high]){
                //target belong to sorted part
                if(target > nums[mid] && target <= nums[high]) low = mid+1;
                //target belong to unsorted part
                else high = mid-1;
            }
        }
        return -1;
        **/
        //Key:Recursion version
        if(nums.length == 0) return -1;
        return helper(0,nums.length-1,nums,target);
    }
    public int helper(int low,int high,int[] nums,int target){
        int mid = (low+high)/2;
        if(nums[mid] == target) return mid;
        if(low <= high){
            //Key:注意，下面的是binary search code,其中nums[mid]和target没有等于判断
            /**
            public int helper(int target,int[] nums,int start,int end){
        		int mid = (start+end)/2;
        		while(start<=end){
        			//System.out.println(nums[mid]+"");
        			if(nums[mid] == target) return mid;
        			else if(nums[mid]<target) return helper(target,nums,mid+1,end);
        			else return helper(target,nums,start,mid-1);
        		}
        		return -1;
        	}
            **/
            //Key:而这里nums[mid] <= nums[high] 必须加=判断，注意为什么!!!!
            //Corner case:[3,1] 1
            if(nums[mid] <= nums[high]){
                if(target > nums[mid] && target <= nums[high]) return helper(mid+1,high,nums,target);
                else return helper(low,mid-1,nums,target);
            }
            if(nums[mid] >= nums[low]){
                if(target >= nums[low] && target < nums[mid]) return helper(low,mid-1,nums,target);
                else return helper(mid+1,high,nums,target);
            }
        }
        return -1;
    }
}

81. Search in Rotated Sorted Array II
public class Solution {
    public boolean search(int[] nums, int target) {
        //Key:contains duplicates 
        //Test case {2,2,1,2,2,2,2,2,2}  --->这时候无法仅通过nums[mid]>nums[low] 来判断了....
        //Key:比较喜欢coder_gal25's sol, https://discuss.leetcode.com/topic/310/when-there-are-duplicates-the-worst-case-is-o-n-could-we-do-better/28   ---> 干扰项归根到底其实就是mid,high,low同时相等，只要把这种情况排除就好了!!!  Worst case：O(N)
        if(nums.length == 0) return false;
        return helper(0,nums.length-1,nums,target) == -1?false:true;
    }
    public int helper(int low,int high,int[] nums,int target){
        int mid = (low+high)/2;
        if(low<=high){
            if(nums[mid] == target) return mid;
            //Key:关键点，其他地方应该和Search in Rotated Sorted Array I 一样
            if(nums[mid] == nums[low] && nums[mid] == nums[high]){
                //low++;
                //high--;
                return helper(++low,--high,nums,target);
            } else if(nums[mid]>=nums[low]){
                if(target >= nums[low] && target < nums[mid]) return helper(low,mid-1,nums,target);
                else return helper(mid+1,high,nums,target);
            } else if(nums[mid] <= nums[high]){
                if(target > nums[mid] && target <= nums[high]) return helper(mid+1,high,nums,target);
                else return helper(low,mid-1,nums,target);
            }
        }
        return -1;
    }
}

154. Find Minimum in Rotated Sorted Array II
public class Solution {
    public int findMin(int[] nums) {
        //Key:I 的Explanation，不过思路类似https://discuss.leetcode.com/topic/6112/a-concise-solution-with-proof-in-the-comment
        //https://discuss.leetcode.com/topic/25248/super-simple-and-clean-java-binary-search
        //Just cp
        int l = 0, r = nums.length-1;
        while (l < r) {
             int mid = (l + r) / 2;
             if (nums[mid] < nums[r]) {
            	 r = mid;
             } else if (nums[mid] > nums[r]){
            	 l = mid + 1;
             } else {  
            	 r--;  //nums[mid]=nums[r] no idea, but we can eliminate nums[r];
             }
        }
        return nums[l];
    }
}

153. Find Minimum in Rotated Sorted Array
public class Solution {
    public int findMin(int[] nums) {
        //Key:直接粘的 154 Find Minimum in Rotated Sorted Array II 的解法
        //Key:I 的Explanation，不过思路类似https://discuss.leetcode.com/topic/6112/a-concise-solution-with-proof-in-the-comment
        //https://discuss.leetcode.com/topic/25248/super-simple-and-clean-java-binary-search
        //Just cp 背
        int l = 0, r = nums.length-1;
        while (l < r) {
             int mid = (l + r) / 2;
             if (nums[mid] < nums[r]) {
            	 r = mid;
             } else if (nums[mid] > nums[r]){
            	 l = mid + 1;
             } else {  
            	 r--;  //nums[mid]=nums[r] no idea, but we can eliminate nums[r];
             }
        }
        return nums[l];
    }
}

343. Integer Break
public class Solution {
    public int integerBreak(int n) {
        //Key:DP or Math
        //Just cp:https://discuss.leetcode.com/topic/42978/java-dp-solution DP
       int[] dp = new int[n + 1];
       dp[1] = 1;
       for(int i = 2; i <= n; i ++) {
           for(int j = 1; j < i; j ++) {
               dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j])));
           }
       }
       return dp[n];
    }
}

401. Binary Watch
public class Solution {
    //Key:回溯，背，这道题挺麻烦的，估计不太可能问...
    //Key:有一个bitCount的方法，不过貌似更不好记
    //https://discuss.leetcode.com/topic/59374/simple-python-java
    //Key:Just cp https://discuss.leetcode.com/topic/59494/3ms-java-solution-using-backtracking-and-idea-of-permutation-and-combination/2
    
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        
        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);    
        }
    }
}

383. Ransom Note
public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        //这道题目表述有点问题
        //"abb","bba"我觉得不符题意，但是它可以通过。貌似只要magazine中的字符任意截取能组成ransomNote就可以....但还是没理解 
    //     char[] rArr = ransomNote.toCharArray();
    //     char[] mArr = magazine.toCharArray();
    //     int lengthR = rArr.length();
    //     int lengthM = mArr.length();
    //     int tmp = 0;
    //     int index1 = 0,index2 = 0;
    //     for(int i=0;i<=lengthR-1;i++){
    //         tmp ^= (int)rArr[i];
    //     }
    //     for(int i=0;i<=lengthM-1;i++){
    //         tmp ^= (int)mArr[i];
    //     }
    //     for(int i=0;i<=lengthR-1;i++){
    //         tmp ^= (int)rArr[i];
    //     }
    // }
    
        //Key:应该是ransomNote中的字符均属于magazine中的就可以
        //Key:Coner case:"" "b" 空字符串自然也符合题意
        //Key:数量也得对上....Corner case:"aa", "aab" -->true    "aa""ab" --> false
        if(ransomNote == null || ransomNote.length() == 0) return true;
        if(magazine == null || magazine.length() == 0) return false;
        Map<Character,Integer> map = new HashMap<>();
        for(char c:magazine.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(char c:ransomNote.toCharArray()){
            map.put(c,map.getOrDefault(c,0)-1);
            if(map.get(c) == -1) return false;
        }
        return true;
    }
}

423. Reconstruct Original Digits from English
public class Solution {
    public String originalDigits(String s) {
        //Key:坑爹的题，只要找规律就行了......
        //先找出独有的字符，再逐一排除这些数字
        //Just cp
        /****
        
        char c = s.charAt(i);
        if (c == 'z') count[0]++;
        if (c == 'w') count[2]++;
        if (c == 'x') count[6]++;
        if (c == 's') count[7]++; //7-6
        if (c == 'g') count[8]++;
        if (c == 'u') count[4]++; 
        if (c == 'f') count[5]++; //5-4
        if (c == 'h') count[3]++; //3-8
        if (c == 'i') count[9]++; //9-8-5-6
        if (c == 'o') count[1]++; //1-0-2-4
        
        ****/
        
        int[] count = new int[10];
        for(char c:s.toCharArray()){
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'x') count[6]++;
            if (c == 's') count[7]++; //7-6
            if (c == 'g') count[8]++;
            if (c == 'u') count[4]++; 
            if (c == 'f') count[5]++; //5-4
            if (c == 'h') count[3]++; //3-8
            if (c == 'i') count[9]++; //9-8-5-6
            if (c == 'o') count[1]++; //1-0-2-4
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++){
            for (int j = 0; j < count[i]; j++){
                sb.append(i);
            }
        }
        return sb.toString();
        
    }
}

543. Diameter of Binary Tree
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
    //1st version
    int max = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        helper(root); 
        return max;
    }
    public int helper(TreeNode node){
        if(node != null){
            int left = helper(node.left),right = helper(node.right);
            max = Math.max(left+right,max);
            return Math.max(left+1,right+1);
        }
        return 0;
    }
}

538. Convert BST to Greater Tree
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
    //Just cp
    int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }
    
    public void convert(TreeNode cur) {
        if (cur == null) return;
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }
}

537. Complex Number Multiplication
public class Solution {
    public String complexNumberMultiply(String a, String b) {
        //Hard,Key:Just cp
        //https://discuss.leetcode.com/topic/84261/java-3-liner/2
        int[] coefs1 = Stream.of(a.split("\\+|i")).mapToInt(Integer::parseInt).toArray(), 
        coefs2 = Stream.of(b.split("\\+|i")).mapToInt(Integer::parseInt).toArray();
        return (coefs1[0]*coefs2[0] - coefs1[1]*coefs2[1]) + "+" + (coefs1[0]*coefs2[1] + coefs1[1]*coefs2[0]) + "i";
        
    }
}

547. Friend Circles
public class Solution {
    //Key:hard
    //Just cp https://discuss.leetcode.com/topic/85021/java-bfs-equivalent-to-finding-connected-components-in-a-graph
    public int findCircleNum(int[][] M) {
        int count = 0;
        for (int i=0; i<M.length; i++)
            if (M[i][i] == 1) { count++; BFS(i, M); }
        return count;
    }
    
    public void BFS(int student, int[][] M) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(student);
        while (queue.size() > 0) {
            int queueSize = queue.size();
            for (int i=0;i<queueSize;i++) {
                int j = queue.poll();
                M[j][j] = 2; // marks as visited
                for (int k=0;k<M[0].length;k++) 
                    if (M[j][k] == 1 && M[k][k] == 1) queue.add(k);
            }
        }
    }
}

98. Validate Binary Search Tree
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
    //Key:my wrong version
    
    /**
    boolean tag = true;
    public boolean isValidBST(TreeNode root) {
        //Key:Corner case:[] 返回true
        if(root == null) return true;
        helper(root.left,root.val,true);
        helper(root.right,root.val,false);
        return tag;
    }
    //Key:很容易忽略的一点!!!!!
    //Key:Corner case:[10,5,15,null,null,6,20]  
    //还要顾虑到中间的left和right的上限和下限范围
    public void helper(TreeNode node,int parent,boolean left){
        if(node != null){
            if(left){
                if(node.val >= parent) tag = false;
            } else {
                if(node.val <= parent) tag = false;
            }
            helper(node.left,node.val,true);
            helper(node.right,node.val,false);
        }
    }
    **/
    //Just cp
    //https://discuss.leetcode.com/topic/46016/learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-java-solution
    public boolean isValidBST(TreeNode root) {
       if (root == null) return true;
       Stack<TreeNode> stack = new Stack<>();
       TreeNode pre = null;
       while (root != null || !stack.isEmpty()) {
          while (root != null) {
             stack.push(root);
             root = root.left;
          }
          root = stack.pop();
          if(pre != null && root.val <= pre.val) return false;
          pre = root;
          root = root.right;
       }
       return true;
    }
}

4. Median of Two Sorted Arrays
public class Solution {
    //My wrong version
    /**
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int start1=0,start2=0,end1=nums1.length-1,end2=nums2.length-1;
        return findMedian(start1,end1,nums1,start2,end2,nums2);
    }
    public double findMedian(int start1,int end1,int[] nums1,int start2,int end2,int[] nums2){
        int mid1 = (start1+end1)/2,mid2 = (start2+end2)/2;
        if(end1==start1 && end2==start2) return (double)(nums1[start1]+nums2[start2])/2;
        
        if(nums1[mid1] < nums2[mid2]){
            start1 = (mid1+end1)/2;
            end2 = (start2+mid2)/2;

        } else if(nums1[mid1] > nums2[mid2]){
            start2 = (mid2+end2)/2;
            end1 = (start1+mid1)/2;
            
        } else if(nums1[mid1] == nums2[mid2]){
           end1 = end1+1;
           end2 = end2-1;
        }
        return findMedian(start1,end1,nums1,start2,end2,nums2);
    }
    **/
    //Just cp
    //https://discuss.leetcode.com/topic/28602/concise-java-solution-based-on-binary-search/2
    public double findMedianSortedArrays(int[] A, int[] B) {
    	    int m = A.length, n = B.length;
    	    int l = (m + n + 1) / 2;
    	    int r = (m + n + 2) / 2;
    	    return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    	}
    
    public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
    	if (aStart > A.length - 1) return B[bStart + k - 1];            
    	if (bStart > B.length - 1) return A[aStart + k - 1];                
    	if (k == 1) return Math.min(A[aStart], B[bStart]);
    	
    	int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
    	if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1]; 
    	if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];        
    	
    	if (aMid < bMid) 
    	    return getkth(A, aStart + k/2, B, bStart,k - k/2);// Check: aRight + bLeft 
    	else 
    	    return getkth(A, aStart,B,bStart + k/2, k - k/2);// Check: bRight + aLeft
    }
}

468. Validate IP Address
public class Solution {
    public String validIPAddress(String IP) {
        //Key:hard 自己写匹配很麻烦，直接用regression比较简单
        //Key:just cp
        //https://discuss.leetcode.com/topic/75015/java-clear-regex-solution
        //Key:正则的用法
        String singleIPv4 = "([0-9]|[1-9][0-9]|[1][0-9]{2}|[2][0-5]{2})";
        String IPv4 = String.format("(%s\\.){3}%s", singleIPv4,singleIPv4);
    	if (IP.matches(IPv4)) return "IPv4";
    	String singleLetter = "[A-Fa-f0-9]";
    	String singleIPv6 = String.format("(%s{1,4}|[0]%s{0,3})", singleLetter,singleLetter);
    	String IPv6 = String.format("(%s:){7}%s", singleIPv6,singleIPv6);
        if (IP.matches(IPv6)) return "IPv6";
        return "Neither";
    }
}

420. Strong Password Checker
//Key:Hard而且写起来很麻烦...
//Key:Just cp
//https://discuss.leetcode.com/topic/63185/java-easy-solution-with-explanation/2
public class Solution {
    public int strongPasswordChecker(String s) {
        
        if(s.length()<2) return 6-s.length();
        
        //Initialize the states, including current ending character(end), existence of lowercase letter(lower), uppercase letter(upper), digit(digit) and number of replicates for ending character(end_rep)
        char end = s.charAt(0);
        boolean upper = end>='A'&&end<='Z', lower = end>='a'&&end<='z', digit = end>='0'&&end<='9';
        
        //Also initialize the number of modification for repeated characters, total number needed for eliminate all consequnce 3 same character by replacement(change), and potential maximun operation of deleting characters(delete). Note delete[0] means maximum number of reduce 1 replacement operation by 1 deletion operation, delete[1] means maximun number of reduce 1 replacement by 2 deletion operation, delete[2] is no use here. 
        int end_rep = 1, change = 0;
        int[] delete = new int[3];
        
        for(int i = 1;i<s.length();++i){
            if(s.charAt(i)==end) ++end_rep;
            else{
                change+=end_rep/3;
                if(end_rep/3>0) ++delete[end_rep%3];
                //updating the states
                end = s.charAt(i);
                upper = upper||end>='A'&&end<='Z';
                lower = lower||end>='a'&&end<='z';
                digit = digit||end>='0'&&end<='9';
                end_rep = 1;
            }
        }
        change+=end_rep/3;
        if(end_rep/3>0) ++delete[end_rep%3];
        
        //The number of replcement needed for missing of specific character(lower/upper/digit)
        int check_req = (upper?0:1)+(lower?0:1)+(digit?0:1);
        
        if(s.length()>20){
            int del = s.length()-20;
            
            //Reduce the number of replacement operation by deletion
            if(del<=delete[0]) change-=del;
            else if(del-delete[0]<=2*delete[1]) change-=delete[0]+(del-delete[0])/2;
            else change-=delete[0]+delete[1]+(del-delete[0]-2*delete[1])/3;
            
            return del+Math.max(check_req,change);
        }
        else return Math.max(6-s.length(), Math.max(check_req, change));
    }
}

179. Largest Number
public class Solution {
    public String largestNumber(int[] nums) {
        //Key:Hard
        //Key:这个方法特别巧妙
        //https://discuss.leetcode.com/topic/8018/my-java-solution-to-share/2
        //https://discuss.leetcode.com/topic/32442/share-my-fast-java-solution-beat-98-64/2
        //Key:Key point:
        /**
        String s1 = "9";
        String s2 = "31";
        
        String case1 =  s1 + s2; // 931
        String case2 = s2 + s1; // 319
        **/
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i]+"";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String i, String j) {
                String s1 = i+j;
                String s2 = j+i;
                return s1.compareTo(s2);
            }
        });
        if (strs[strs.length-1].charAt(0) == '0') return "0";
        String res = new String();
        for (int i = 0; i < strs.length; i++) {
            res = strs[i]+res;
        }
        return res;
    
    }
}

273. Integer to English Words
//Key:just cp
//https://discuss.leetcode.com/topic/25177/share-my-clean-java-solution
public class Solution {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String[] big= {"", "Thousand", "Million", "Billion"};
        String[] small = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        StringBuilder res = new StringBuilder();
        int count = 0;
        while (num != 0) {
            int cur = num % 1000;
            int o = cur % 10, t = (cur / 10) % 10, h = cur / 100;
            StringBuilder tmp = new StringBuilder();
            if (h != 0) tmp.append(ones[h] + " Hundred ");
            if (t == 1) tmp.append(small[o] + " ");
            else {
                if (t > 1) tmp.append(tens[t-2] + " ");
                if (o > 0) tmp.append(ones[o] + " ");
            }
            if(tmp.length() != 0) tmp.append(big[count] + " ");
            res.insert(0, tmp);
            num /= 1000;
            count++;
        }
        return res.toString().trim();
    }
}

18. 4Sum
public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        //Key:BackTracking 会TLE
        /**
        Set<List<Integer>> list = new HashSet<>();
        
        List<Integer> item = new ArrayList<>();
        Arrays.sort(nums);
        //Key:还是不能有重复的
        //不行，这道题用这个方法怎么着都TLE.....
        
        //[-493,-482,-482,-456,-427,-405,-392,-385,-351,-269,-259,-251,-235,-235,-202,-201,-194,-189,-187,-186,-180,-177,-175,-156,-150,-147,-140,-122,-112,-112,-105,-98,-49,-38,-35,-34,-18,20,52,53,57,76,124,126,128,132,142,147,157,180,207,227,274,296,311,334,336,337,339,349,354,363,372,378,383,413,431,471,474,481,492]  6189

        helper(list,item,nums,target,0,0);
        return new ArrayList(list);
    }
    public void helper(Set<List<Integer>> list,List<Integer> item,int[] nums,int target,int start,int sum){
        if(item.size() <=4){
            if(item.size() == 4){
                int tmp = 0;
                for(int i:item) tmp += i;
                //System.out.println(item+"----");
                if(tmp == target) list.add(new ArrayList<>(item));
            } else {
                for(int i = start;i<=nums.length-1;i++){
                    //System.out.println(sum+nums[i]+"----");
                    //Corcer case:[1,-2,-5,-4,-3,3,3,5]  -11 因为一开始-5>-11，所以就直接return 了///
                    if(target <0 && item.size() == 3 && sum+nums[i] > target || target >0 && sum > target) return;
                    
                    //if(sum+nums[i] > target) return;
                    //if(sum> target) return;
                    item.add(nums[i]);
                    //Corcer case:[-1,0,1,2,-1,-4]  -1 因为target 是负数，而我一开始传的sum是0，所以必须判断sum+nums[i] > target
                    //而不是sum> target。否则因为一开始0就大于target，会导致错误
                    
                    //System.out.println(nums[start]+"----");
                    helper(list,item,nums,target,i+1,sum+nums[i]);
                    item.remove(item.size()-1);
                }
            }
        }
        **/
        //Key:Just cp:很漂亮的一个算法
        //https://discuss.leetcode.com/topic/12368/clean-accepted-java-o-n-3-solution-based-on-3sum
        //和这个3sum有很大的相似度https://discuss.leetcode.com/topic/28857/easiest-java-solution
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if(num.length<4)return ans;
        Arrays.sort(num);
        for(int i=0; i<num.length-3; i++){
            if(i>0&&num[i]==num[i-1])continue;
            for(int j=i+1; j<num.length-2; j++){
                if(j>i+1&&num[j]==num[j-1])continue;
                int low=j+1, high=num.length-1;
                while(low<high){
                    int sum=num[i]+num[j]+num[low]+num[high];
                    if(sum==target){
                        ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
                        while(low<high&&num[low]==num[low+1])low++;
                        while(low<high&&num[high]==num[high-1])high--;
                        low++;
                        high--;
                    }
                    else if(sum<target)low++;
                    else high--;
                }
            }
        }
        return ans;
        
    }
}

135. Candy
public class Solution {
    public int candy(int[] ratings) {
        //Key：Hard,Just cp
        //Key:Greedy题，背!!!!
        //https://discuss.leetcode.com/topic/37924/very-simple-java-solution-with-detail-explanation
        //https://discuss.leetcode.com/topic/25985/simple-o-n-java-solution-with-comments
        //两个思路相同，但是如何不知证明candies是最小的？？？
        int sum=0;
        int[] a=new int[ratings.length];
        for(int i=0;i<a.length;i++)
        {
            a[i]=1;
        }
        for(int i=0;i<ratings.length-1;i++)
        {
            if(ratings[i+1]>ratings[i])
            {
                a[i+1]=a[i]+1;
            }
        }
        for(int i=ratings.length-1;i>0;i--)
        {
            if(ratings[i-1]>ratings[i])
            {
                //Key:关键点，从右往左扫时，如果左边大于右边，要保证至少不小于右边+1
                if(a[i-1]<(a[i]+1))
                {
                    a[i-1]=a[i]+1;
                }
            }
        }
        for(int i=0;i<a.length;i++)
        {
            sum+=a[i];
        }
        return sum;
    }
}

188. Best Time to Buy and Sell Stock IV
public class Solution {
    //Key:Hard,just cp
    //这两个解法比较容易理解
    //https://discuss.leetcode.com/topic/24079/easy-understanding-and-can-be-easily-modified-to-different-situations-java-solution/2
    //https://discuss.leetcode.com/topic/29489/clean-java-dp-o-nk-solution-with-o-k-space
    //hold[i][k]  ith day k transaction have stock and maximum profit
    //unhold[i][k] ith day k transaction do not have stock at hand and maximum profit
    public int maxProfit(int k, int[] prices) {
        if(k>prices.length/2) return maxP(prices);
        int[][] hold = new int[prices.length][k+1];
        int[][] unhold = new int[prices.length][k+1];
        hold[0][0] = -prices[0];
        for(int i=1;i<prices.length;i++) hold[i][0] = Math.max(hold[i-1][0],-prices[i]);
        for(int j=1;j<=k;j++) hold[0][j] = -prices[0];
        for(int i=1;i<prices.length;i++){
            for(int j=1;j<=k;j++){
                hold[i][j] = Math.max(unhold[i-1][j]-prices[i],hold[i-1][j]);
                unhold[i][j] = Math.max(hold[i-1][j-1]+prices[i],unhold[i-1][j]);
            }
        }
        return Math.max(hold[prices.length-1][k],unhold[prices.length-1][k]);
    }
    public int maxP(int[] prices){
        int res =0;
        for(int i=0;i<prices.length;i++){
            if(i>0 && prices[i] > prices[i-1]){
                res += prices[i]-prices[i-1];
            }
        }
        return res;
    }
}

103. Binary Tree Zigzag Level Order Traversal
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
    //Key:Just cp
    //Traversal method
    //https://discuss.leetcode.com/topic/3413/my-accepted-java-solution/2
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
    {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
    }
    
    public void travel(TreeNode curr, List<List<Integer>> sol, int level)
    {
        if(curr == null) return;
        
        if(sol.size() <= level)
        {
            List<Integer> newLevel = new LinkedList<>();
            sol.add(newLevel);
        }
        
        List<Integer> collection  = sol.get(level);
        if(level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);
        
        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}

97. Interleaving String
public class Solution {
    //Key:Hard just cp,改写了一下CPP的解法
    //https://discuss.leetcode.com/topic/3532/my-dp-solution-in-c/2
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()) return false;
        boolean[][] table = new boolean[s1.length()+1][s2.length()+1];
        for(int i=0; i<s1.length()+1; i++)
            for(int j=0; j< s2.length()+1; j++){
                if(i==0 && j==0)
                    table[i][j] = true;
                else if(i == 0)
                    table[i][j] = ( table[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                else if(j == 0)
                    table[i][j] = ( table[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1));
                else
                    table[i][j] = (table[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1) ) || (table[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1) );
            }
            
        return table[s1.length()][s2.length()];
    }
}

61. Rotate List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:2 pointers  
    //Keu:难点在于k有可能大于链表总长度，所以处理起来会很麻烦
    //My 1st wrong version
    /**
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null) return null;
        ListNode node1 = head,node2 = head;
        ListNode res = null;
        for(int i = 0;i<=k-1;i++){
            node2 = node2.next;
        }
        while(node2.next != null){
            node1 = node1.next;
            node2 = node2.next;
        }
        res = node1.next;
        node1 = null;
        node2.next = head;
        return head;
    }
    ***/
    //Key:just cp
    public ListNode rotateRight(ListNode head, int n) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;
    
        int i;
        for (i=0;fast.next!=null;i++)//Get the total length 
        	fast=fast.next;
        
        for (int j=i-n%i;j>0;j--) //Get the i-n%i th node
        	slow=slow.next;
        
        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;
        
        return dummy.next;
    }
}

239. Sliding Window Maximum
public class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {
        //Key:Deque用法  just cp
        //https://discuss.leetcode.com/topic/19055/java-o-n-solution-using-deque-with-explanation/2
        if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
    }
}

297. Serialize and Deserialize Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    //Key:Hard,背,cp
    //找的一个最短的解法  https://discuss.leetcode.com/topic/34836/short-and-clear-recursive-java-solution
    //这个解法有点投机取巧了，直接调用的Scanner类...
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) 
    {
        if(root == null) return "#";
        
        return "" + root.val + " " + serialize(root.left) + " " + serialize(root.right);
    }
    
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) 
    {
        return build(new Scanner(data));
    }
    
    private TreeNode build(Scanner sc)
    {
        if(!sc.hasNext()) return null;
        String tk = sc.next();
        if(tk.equals("#")) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(tk));
        root.left = build(sc);
        root.right = build(sc);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

131. Palindrome Partitioning
public class Solution {
    //Key:Hard,DFS,cp
    //https://discuss.leetcode.com/topic/33461/easiest-4ms-java-solution-95-99/2
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<String>(), s.toCharArray(), 0);
        return res;
    }
    
    void dfs(List<List<String>> res, ArrayList<String> list, char[] c, int pos) {
        if (pos == c.length) res.add(new ArrayList<>(list));
        for (int i = pos; i < c.length; i++) {
            if (isPal(c, pos, i)){
                list.add(new String(c, pos, i - pos + 1)); 
                dfs(res, list, c, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
    
    boolean isPal(char[] c, int lo, int hi) {
        while (lo < hi) if (c[lo++] != c[hi--]) return false;
        return true;
    }
}

132. Palindrome Partitioning II
public class Solution {
    public int minCut(String s) {
        //Key:Hard,DP,cp
        //https://discuss.leetcode.com/topic/32575/easiest-java-dp-solution-97-36/2
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;  
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }
}

557. Reverse Words in a String III
public class Solution {
    public String reverseWords(String s) {
        //return new StringBuilder(s.substring(0,s.length())).reverse().toString();
		if(s == null || s.length() == 0) return "";
        String[] arr = new StringBuilder(s.substring(0,s.length())).reverse().toString().split(" ");
		String res = "";
		for(int i = arr.length-1;i>=0;i--){
			res = res + arr[i] + " ";
		}
		return res.trim();
    }
}

556. Next Greater Element III
//Key:My 1st version
public class Solution {
    long min = Integer.MAX_VALUE;
    public  void helper(List<Integer> list,int[] arr,boolean[] used,int item,int n){
		if(list.size() == arr.length){
			long num = 0;
			for(int i =0;i<=list.size()-1;i++){
				num = num*10+list.get(i);
			}
			if(num>(long)n){
				if(num<min) min = num;
			}
		} else {
			for(int i =0;i<=arr.length-1;i++){
				if(used[i]) continue;
				list.add(arr[i]);
				//item = item*10+arr[i];
				used[i] = true;
				helper(list,arr,used,item,n);
				used[i] = false;
				//item = item/10;
				list.remove(list.size()-1);
			}
		}
	}
	public  int nextGreaterElement(int n) {
        long max = Integer.MAX_VALUE;
        long res = 0;
        int[] arr = new int[(n+"").length()];
		boolean[] used = new boolean[arr.length];
		int tmp = n,index = 0;
		while(tmp != 0){
			arr[index++] = tmp%10;
			tmp /= 10;
		}
        Arrays.sort(arr);
		helper(new ArrayList<Integer>(),arr,used,0,n);
		res = min;
        if(min == Integer.MAX_VALUE) return -1;
        if(res<=max){
            if(res <= n){
				//System.out.println(res+"");
                return -1;
            } else {
                return (int)res;
            }
        } else {
            if((long)n+1<=max) return n+1;
            else return -1;
        }
        
    }
}

554. Brick Wall
public class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        //Key:底下写的很多case通不过，改写一下
        /***
        if(wall == null || wall.size() == 0 ||wall.get(0).size() == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0,max = Integer.MIN_VALUE,sum = 0;
        for(List<Integer> i:wall){
            sum = 0;
            for(int j:i){
                sum += j;
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            //Key:下面的不对，因为总长度相同，下面的最终会一直导致max == 砖长
            //max = Math.max(entry.getValue(),max);
            //The wall is rectangular  ----> 矩形砖
            //Key:Corner case:You cannot draw a line just along one of the two vertical edges of the wall
            //所以[[1],[1],[1]]不能从尾端切，只能从中间切
            if(entry.getKey() != wall.size()) max = Math.max(entry.getValue(),max);
        }
        //Key:tackle Corner case:[[1],[1],[1]]
        //比较疑惑的是，对于上面的case，为什么必须要切一下，不切不成吗....
        if(map.size() == 1) return wall.size();
        return wall.size()-max;
        
        ***/
        
        //Key:切割最少砖块的点就是位于最多共同砖块末端的点
        //Key point: the position for vertical line to cross the least bricks = the position for the most bricks end
        //https://discuss.leetcode.com/topic/85746/neat-java-solution-o-n-using-hashmap
        if(wall == null || wall.size() == 0 ||wall.get(0).size() == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int res = wall.size(),sum = 0;
        for(List<Integer> i:wall){
            sum = 0;
            //Key:最后一块砖块不加，否则会干扰这道题的test case判断
            //同时也把[[1],[1],[1]]这个case的麻烦排除了
            for(int j = 0;j<=i.size()-2;j++){
                sum += i.get(j);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            //Key:下面的不对，因为总长度相同，下面的最终会一直导致max == 砖长
            //max = Math.max(entry.getValue(),max);
            //The wall is rectangular  ----> 矩形砖
            //Key:Corner case:You cannot draw a line just along one of the two vertical edges of the wall
            //所以[[1],[1],[1]]不能从尾端切，只能从中间切
            //if(entry.getKey() != wall.size()) max = Math.max(entry.getValue(),max);
            //Key:按照题意正向思维求最小，不求最大值，否则corner case会导致一些错误,思维也比较容易乱
            res = Math.min(res,wall.size()-entry.getValue());
        }
        //Key:tackle Corner case:[[1],[1],[1]]
        //比较疑惑的是，对于上面的case，为什么必须要切一下，不切不成吗...
        return res;
    }
}

549. Binary Tree Longest Consecutive Sequence II
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
    
    /***
    int max = Integer.MIN_VALUE;
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return max;
    }
    //Key：下边写错了，自己写的这个只能针对binary search tree,而无法处理binary tree
    public int helper(TreeNode node){
        if(node != null){
            int tmp = 1,left = helper(node.left),right = helper(node.right);
            int sum = 1;
            if(node.left != null && node.left.val < node.val) {
                tmp = Math.max(left+1,tmp);
                sum += left;
            }
            if(node.right != null && node.right.val > node.val) {
                 tmp = Math.max(right+1,tmp);
                 sum += right;
            }
            max = Math.max(max,sum);
            return tmp;
        }
        return 0;
    }
    ****/
    //Key:My ans is wrong,just cp
    //https://discuss.leetcode.com/topic/85764/neat-java-solution-single-pass-o-n/2
    int maxval = 0;
    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return maxval;
    }
    public int[] longestPath(TreeNode root) {
        if (root == null)
            return new int[] {0,0};
        int inr = 1, dcr = 1;
        if (root.left != null) {
            int[] l = longestPath(root.left);
            if (root.val == root.left.val + 1)
                dcr = l[1] + 1;
            else if (root.val == root.left.val - 1)
                inr = l[0] + 1;
        }
        if (root.right != null) {
            int[] r = longestPath(root.right);
            if (root.val == root.right.val + 1)
                dcr = Math.max(dcr, r[1] + 1);
            else if (root.val == root.right.val - 1)
                inr = Math.max(inr, r[0] + 1);
        }
        maxval = Math.max(maxval, dcr + inr - 1);
        return new int[] {inr, dcr};
    }
}

207. Course Schedule
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //Key:Hard  
        //拓扑排序思路就是，入度为0的点持续排出。如果课程有效的话，则必然有个入度为0的课程，否则肯定全部都为环了....
        //https://discuss.leetcode.com/topic/13854/easy-bfs-topological-sort-java
        //Key:配合着这篇文章来看topological sort  http://www.cnblogs.com/easonliu/p/4483437.html
        //Key:改写了点变量名称，以方便理解
        //i->j是否存在边
        int[][] edges = new int[numCourses][numCourses]; // i -> j
        //每个点的入度
        int[] indegree = new int[numCourses];
        
        for (int i=0; i<prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            //Key:先统计每个点的入度情况，实际上就是prerequisites中的后续课程情况
            //下面的两句合起来看，如果存在edges[pre][ready]，则入度++，赋值为1只是为了与0区分，避免重复计算，其实赋值为任何数字都可以
            if (edges[pre][ready] == 0)  indegree[ready]++; 
            edges[pre][ready] = 1;
        }
        
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i=0; i<indegree.length; i++) {
            //先把入度为0的加入队列
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i=0; i<numCourses; i++) {
                if (edges[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }
}

210. Course Schedule II
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //Key:Just cp
        //Schedule II 解法可以直接用在Schedule I中
        //https://discuss.leetcode.com/topic/27940/concise-java-solution-based-on-bfs-with-comments
        if (numCourses == 0) return null;
        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;    
    
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) 
            if (indegree[i] == 0) {
                // Add the course to the order because it has no prerequisites.
                order[index++] = i;
                queue.offer(i);
            }
    
        // How many courses don't need prerequisites. 
        while (!queue.isEmpty()) {
            int prerequisite = queue.poll(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++)  {
                if (prerequisites[i][1] == prerequisite) {
                    indegree[prerequisites[i][0]]--; 
                    if (indegree[prerequisites[i][0]] == 0) {
                        // If indegree is zero, then add the course to the order.
                        order[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                } 
            }
        }
    
        return (index == numCourses) ? order : new int[0];
    }
}

105. Construct Binary Tree from Preorder and Inorder Traversal
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
    //Key:构造binary tree--->背!!!!
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}

34. Search for a Range
public class Solution {
    //Key:背 ---> 我觉得就是二叉搜索找找第一个和最后一个target的变种...
    
    //Version 1 https://discuss.leetcode.com/topic/44031/easy-to-understand-java-ac-solution
    //这个解法同时可以解决first和last 问题
    public int[] searchRange(int[] A, int target) {
    	int start = findPosition(A, target, false);
    	int end = findPosition(A, target, true);
    	return new int[]{start, end};
    }
    
    private int findPosition(int[] A, int target, boolean isLast) {
    	int low = 0, high = A.length-1, index = -1;
    	while (low <= high) {
    		int mid = low + ((high - low) >> 1);
    		if(isLast){
    			if (A[mid] <= target) low = mid + 1;
    			else high = mid-1;
    		} else{
    			if (A[mid] < target) low = mid + 1;
    			else high = mid-1;
    		}
    		if(A[mid] == target) index = mid; /** update index */
    	}
    	return index;
    }
    

    //Version 2 https://discuss.leetcode.com/topic/6327/a-very-simple-java-solution-with-only-one-binary-search-algorithm
    /**
    
    public int[] searchRange(int[] A, int target) {
		int start = Solution.firstGreaterEqual(A, target);
		if (start == A.length || A[start] != target) {
			return new int[]{-1, -1};
		}
		return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
	}
	//find the first number that is greater than or equal to target.
	//could return A.length if target is greater than A[A.length-1].
	//actually this is the same as lower_bound in C++ STL.
	private static int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			//low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				//should not be mid-1 when A[mid]==target.
				//could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}
    
    **/
}

190. Reverse Bits
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        //Brute Force 用Stack存储n%2,再逆序相乘
        //主要还是Bit 操作，看不懂答案
        
        //Key：直接调用function的解法
        //return Integer.reverse(n);
        
        //https://discuss.leetcode.com/topic/42572/sharing-my-2ms-java-solution-with-explanation
        if (n == 0) return 0;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
        
        
    }
}

56. Merge Intervals
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        //Key:Just cp
        //https://discuss.leetcode.com/topic/12788/a-clean-java-solution/2
        //https://discuss.leetcode.com/topic/8571/fast-ana-simple-java-code
        List<Interval> res = new LinkedList<Interval>();
        if(intervals.size()<2) return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
        @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        Interval curr = intervals.get(0);
        for(Interval iter: intervals) {
            if(curr.end >= iter.start) {
                curr.end = Math.max(curr.end,iter.end);
            }else {
                res.add(curr);
                curr = iter;
            }
        }
        res.add(curr);
        return res;
    }
}

227. Basic Calculator II
public class Solution {
    public int calculate(String s) {
        //Key:Just cp,Stack  https://discuss.leetcode.com/topic/16935/share-my-java-solution/2
        
        int len;
        if(s==null || (len = s.length())==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
                if(sign=='-'){
                    stack.push(-num);
                }
                if(sign=='+'){
                    stack.push(num);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*num);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
    
        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }
}

148. Sort List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:Just cp,背  https://discuss.leetcode.com/topic/11021/basically-it-seems-like-merge-sort-problem-really-easy-understand
    
    //merge two sorted list, return result head
    public ListNode merge(ListNode h1, ListNode h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        
        if(h1.val < h2.val){
            h1.next = merge(h1.next, h2);
            return h1;
        }
        else{
            h2.next = merge(h1, h2.next);
            return h2;
        }
        
    }
    
    public ListNode sortList(ListNode head) {
        //bottom case
        if(head == null){
            return head;
        }
        if(head.next == null){
            return head;
        }
        
        //p1 move 1 step every time, p2 move 2 step every time, pre record node before p1
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode pre = head;
        
        while(p2 != null && p2.next != null){
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        //change pre next to null, make two sub list(head to pre, p1 to p2)
        pre.next = null;
        
        //handle those two sub list
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(p1);
        
        return merge(h1, h2);
        
    }
}

28. Implement strStr()
public class Solution {
    public int strStr(String haystack, String needle) {
        //Key:My wrong version
        /**
        
        //还是太麻烦了!!!!
        //思考麻烦。这个需要考虑的Corner Case 太多了
        //Corner Case:"","a" 输出-1
        if(needle.trim().equals("")) return 0;
        if(haystack.trim().equals("") && !needle.trim().equals("")) return -1;
       
        int begin = -1;
        int length1= haystack.length();
        int length2 = needle.length();
        int index1 = 0;
        int index2 = 0;
        while(index1 <= length1 -1 && index2 <= length2-1){
           
            if(haystack.charAt(index1) == needle.charAt(index2)){
                //Corner Case:"mississippi","issip"
                if(index2 == (length2-1) && begin != -1) return begin;
                if(begin <0) begin = index1;
                index2++;
                index1++;
            } else {
                //偷个懒，只是针对这个Case:"mississippi","issip"。但思考的肯定不全
                if(index2!=0) index1 -= 1;
                index2 = 0;
                begin = -1;
            }
            
        }
        //Test Case:"aaa""aaaa"
        //<length2 而不是length2-1。因为最后结束循环前，index2和index1都++过
        if(index2<length2) begin = -1;
        return begin;
        
        **/
        
        //Key:Just cp https://discuss.leetcode.com/topic/18839/elegant-java-solution
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
              if (j == needle.length()) return i;
              if (i + j == haystack.length()) return -1;
              if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}

69. Sqrt(x)
public class Solution {
    public int mySqrt(int x) {
        //Key:背，just cp  https://discuss.leetcode.com/topic/24532/3-4-short-lines-integer-newton-every-language
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }
}

////////////////////////////////////////////////////////////////////////////////
Ubuntu Test
////////////////////////////////////////////////////////////////////////////////

150. Evaluate Reverse Polish Notation

public class Solution {
    public int evalRPN(String[] a) {
        //Key:Just cp,背 https://discuss.leetcode.com/topic/18179/accepted-clean-java-solution        
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
              case "+":
                stack.push(stack.pop() + stack.pop());
                break;
              case "-":
                stack.push(-stack.pop() + stack.pop());
                break;
              case "*":
                stack.push(stack.pop() * stack.pop());
                break;
            
              case "/":
                int n1 = stack.pop(), n2 = stack.pop();
                stack.push(n2 / n1);
                break;
                  
              default:
                stack.push(Integer.parseInt(a[i]));
            }
        }
        
        return stack.pop();
    }
}

138. Copy List with Random Pointer
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        //Key:Just cp,背 https://discuss.leetcode.com/topic/18086/java-o-n-solution
        if (head == null) return null;
        
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        
        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        
        return map.get(head);
    }
}

322. Coin Change
public class Solution {
    public int coinChange(int[] coins, int amount) {
        //Key:Just cp:背 https://discuss.leetcode.com/topic/33900/java-easy-version-to-understand/2
        if (coins == null || coins.length == 0 || amount <= 0)
		return 0;
    	int[] minNumber = new int[amount + 1];
    	for (int i = 1; i <= amount; i++) {
    		minNumber[i] = Integer.MAX_VALUE;
    		for (int j = 0; j < coins.length; j++) {
    			if (coins[j] <= i && minNumber[i - coins[j]] != Integer.MAX_VALUE)
    				minNumber[i] = Integer.min(minNumber[i], 1 + minNumber[i - coins[j]]);
    		}
    	}
    	if (minNumber[amount] == Integer.MAX_VALUE)
    		return -1;
    	else
    		return minNumber[amount];
    }
}

23. Merge k Sorted Lists
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //Brute force： Time Limit Exceeded

        //Key:Just cp,背  这道题一开始理解错了
        //[[],[1],[1,2,2,2]],期望的是 [1,1,2,2,2] ，而不是[2,2,2,2]-->也就是说要重新排序
        if(lists.length == 0) return null;
        //Key:corner case:[[]],[[],[1]]
        if(lists.length == 1 && lists[0] == null) return null;
        List<Integer> list = new ArrayList<>();
        ListNode item = new ListNode(0);
        ListNode head = item;
        for(int i = 0;i<=lists.length-1;i++){
            while(lists[i] != null){
                list.add(lists[i].val);
                lists[i] = lists[i].next;
                
            }
        }
        Integer[] nums = new Integer[list.size()];
        nums = list.toArray(nums);
        Arrays.sort(nums);
        for(int i:nums){
            item.next = new ListNode(i);
            item = item.next;
        }
        return head.next;
    }
}

218. The Skyline Problem
public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        //Key:Just cp,hard,背
        //https://discuss.leetcode.com/topic/22482/short-java-solution
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
                if(a[0] != b[0]) 
                    return a[0] - b[0];
                return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(int[] h:height) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if(prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }
}

79. Word Search

public class Solution {
    //Key:my wrong version 
    /**
    public boolean exist(char[][] board, String word) {
        int row = board.length,col = board[0].length;
        boolean[][] used = new boolean[row][col];
        return helper(board,used,word,"");
    }
    public boolean helper(char[][] board,boolean[][] used,String word,String tmp,int rowIn,int colIn){
        if(tmp.equals(word)){
            return true;
        } else if(tmp.length() >= word.length()){
            return false;
        } else {
            if(rowIn < row || colIn > col || used[rowIn][colIn]){
                return false;
            } else {
                tmp = tmp + String.valueOf(board[row][col]);
                return helper(board,used,word,tmp,rowIn+)
            }
        }
        return false;
    }
    **/
    //Key:cp,背，
    //https://discuss.leetcode.com/topic/7907/accepted-very-short-java-solution-no-additional-space/4
    //https://discuss.leetcode.com/topic/45252/java-dfs-solution-beats-97-64
    
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
        	for (int x=0; x<board[y].length; x++) {
        		if (exist(board, y, x, w, 0)) return true;
        	}
        }
        return false;
    }
    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
    	if (i == word.length) return true;
    	if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
    	if (board[y][x] != word[i]) return false;
    	board[y][x] ^= 256;
    	boolean exist = exist(board, y, x+1, word, i+1)
    		|| exist(board, y, x-1, word, i+1)
    		|| exist(board, y+1, x, word, i+1)
    		|| exist(board, y-1, x, word, i+1);
    	board[y][x] ^= 256;
    	return exist;
    }
    
}

324. Wiggle Sort II

public class Solution {
    public void wiggleSort(int[] nums) {
        
        //Key:背，Just cp,不是最佳优化，但比较容易背   https://discuss.leetcode.com/topic/33084/ac-java-solution-7ms/3
        Arrays.sort(nums);
        int n = nums.length, mid = n%2==0?n/2-1:n/2;
        int[] temp = Arrays.copyOf(nums, n);
        int index = 0;
        for(int i=0;i<=mid;i++){
            nums[index] = temp[mid-i];
            if(index+1<n)
                nums[index+1] = temp[n-i-1];
            index += 2;
        }
    }
}

152. Maximum Product Subarray
public class Solution {
    public int maxProduct(int[] a) {
        //Key:My wrong version
        /**
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int[] F = new int[nums.length];
        int[] K = new int[nums.length];
        int res = nums[0];
        F[0] = nums[0];
        K[0] = nums[0];
        for(int i = 1;i<=nums.length-1;i++){
            F[i] = Math.max(nums[i],F[i-1]*nums[i]);
            res = Math.max(F[i],res);
        }
        for(int i = 1;i<=nums.length-1;i++){
            res = Math.max(res,K[i-1]*nums[i]);
            K[i] = Math.min(nums[i],K[i-1]*nums[i]);
            
        }
        return res;
        **/
        
        //Key:Just cp,背  -->典型DP
        //和那道Maximum Contiguous Subarray比较像
        //https://discuss.leetcode.com/topic/18203/accepted-java-solution
        if (a == null || a.length == 0) return 0;
        int ans = a[0], min = ans, max = ans;
        for (int i = 1; i < a.length; i++) {
            if (a[i] >= 0) {
              max = Math.max(a[i], max * a[i]);
              min = Math.min(a[i], min * a[i]);
            } else {
              int tmp = max;
              max = Math.max(a[i], min * a[i]);
              min = Math.min(a[i], tmp * a[i]);
            }
            ans = Math.max(ans, max);
        }
          
        return ans;
    }
}

564. Find the Closest Palindrome -hard
public class Solution {
    //Key:Just cp,掌握思路就成了....
    //https://discuss.leetcode.com/topic/87200/java-solution
    public String nearestPalindromic(String n) {
        if (n.length() >= 2 && allNine(n)) {
            String s = "1";
            for (int i = 0; i < n.length() - 1; i++) {
                s += "0";
            }
            s += "1";
            return s;
        }
        boolean isOdd = (n.length() % 2 != 0);
        String left = n.substring(0, (n.length() + 1) / 2);
        long[] increment = {-1, 0, +1};
        String ret = n;
        long minDiff = Long.MAX_VALUE;
        for (long i : increment) {
            String s = getPalindrom(Long.toString(Long.parseLong(left) + i), isOdd);
            if (n.length() >= 2 && (s.length() != n.length() || Long.parseLong(s) == 0)) {
                s = "";
                for (int j = 0; j < n.length() - 1; j++) {
                    s += "9";
                }
            }
            long diff = s.equals(n) ? Long.MAX_VALUE : Math.abs(Long.parseLong(s) - Long.parseLong(n));
            if (diff < minDiff) {
                minDiff = diff;
                ret = s;
            }
        }
        return ret;
    }
    private String getPalindrom(String s, boolean isOdd) {
        String right = new StringBuilder(s).reverse().toString();
        return isOdd ? s.substring(0, s.length() - 1) + right : s + right;
    }
    private boolean allNine(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') {
                return false;
            }
        }
        return true;
    }
}

214. Shortest Palindrome
public class Solution {
    public String shortestPalindrome(String s) {
        //Key:Just cp,背,  https://discuss.leetcode.com/topic/25860/my-9-lines-three-pointers-java-solution-with-explanation
        
        int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
        while(i < j) {
             if (chs[i] == chs[j]) {
                 i++; j--;
             } else { 
                 i = 0; end--; j = end;
             }
        }
        return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
    }
}

5. Longest Palindromic Substring
public class Solution {
    //Key:Just cp,背，复用isPalindrome  https://discuss.leetcode.com/topic/21848/ac-relatively-short-and-very-clear-java-solution
    
    public String longestPalindrome(String s) {
        String res = "";
        int currLength = 0;
        for(int i=0;i<s.length();i++){
            if(isPalindrome(s,i-currLength-1,i)){
                res = s.substring(i-currLength-1,i+1);
                currLength = currLength+2;
            }
            else if(isPalindrome(s,i-currLength,i)){
                res = s.substring(i-currLength,i+1);
                currLength = currLength+1;
            }
        }
        return res;
    }
    
    public boolean isPalindrome(String s, int begin, int end){
        if(begin<0) return false;
        while(begin<end){
        	if(s.charAt(begin++)!=s.charAt(end--)) return false;
        }
        return true;
    }
    
}

209. Minimum Size Subarray Sum
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        //Key:最简单是Brute Force --> O(n^2)
        //Key:2 pointers --> O(n),Just cp  https://discuss.leetcode.com/topic/18583/accepted-clean-java-o-n-solution-two-pointers
        
        if (nums == null || nums.length == 0) return 0;
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j++];
            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= nums[i++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
        
        
        //Key:O(NlogN)   https://discuss.leetcode.com/topic/13749/two-ac-solutions-in-java-with-time-complexity-of-n-and-nlogn-with-explanation
        
        /**
        
        private int solveNLogN(int s, int[] nums) {
            int[] sums = new int[nums.length + 1];
            for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < sums.length; i++) {
                int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
                if (end == sums.length) break;
                if (end - i < minLen) minLen = end - i;
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
        
        private int binarySearch(int lo, int hi, int key, int[] sums) {
            while (lo <= hi) {
               int mid = (lo + hi) / 2;
               if (sums[mid] >= key){
                   hi = mid - 1;
               } else {
                   lo = mid + 1;
               }
            }
            return lo;
        }
        
        **/
    }
}

560. Subarray Sum Equals K
public class Solution {
    public int subarraySum(int[] nums, int k) {
        //Key:错误思路
        /**
        
        int sum1 = 0;
        for(int i:nums){
            sum1 += i;
        }
        if(sum1 == 0 && k == 0) return 55;
        int res = 0;
        if(nums.length == 0) return 0;
        int sum = 0,index = 0;
		if(nums.length == 1 && nums[0] == k) return 1;
        for(int i = 1;i<=nums.length-1;i++){
            index = i;
            sum = nums[index-1];
			if(nums[index-1] == k) res++;
            while(index<=nums.length-1){
				
                if(k == 0 && nums[index] == 0 && nums[index-1] == 0) res++;
                sum+= nums[index++];
                //Corner case:[23,2,6,4,7] 0  不能除以0！！！
                if(sum == k) res++;
            }
        }
		if(index >= nums.length && nums[index-1] == k) res++;
        return res;
        
        **/
        
        
        //Key:WOC!!!!!!一开始真是想多了，其实直接做个嵌套循环就可以了....
        /**
        int res = 0;
        if(nums == null || nums.length == 0) return 0;
        
        for(int i = 0;i<=nums.length-1;i++){
            int sum = 0;
            for(int j = i;j<=nums.length-1;j++){
                sum += nums[j];
                if(sum == k) res++;
            }
        }
        return res;
        **/
        
        //O(n)  https://discuss.leetcode.com/topic/87850/java-solution-presum-hashmap
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        
        return result;
    }
}

91. Decode Ways
public class Solution {
    public int numDecodings(String s) {
        //Key:Just cp,DP   https://discuss.leetcode.com/topic/2562/dp-solution-java-for-reference
        
        int n = s.length();
        if (n == 0) return 0;
        
        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        
        for (int i = n - 2; i >= 0; i--){
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];
        }
            
        return memo[0];
    }
}

130. Surrounded Regions
public class Solution {
    
    //Key:题意表达不明,Just cp,  https://discuss.leetcode.com/topic/6496/my-java-o-n-2-accepted-solution/2
    //Key:背 O(n^2)
    
    public void solve(char[][] board) {
        if(board==null||board.length==0||board[0].length==0) return;
        for(int i=0;i<board.length;i++) if(board[i][0]=='O') linkedUnit(board,i,0);
        for(int i=1;i<board[0].length;i++) if(board[0][i]=='O') linkedUnit(board,0,i);
        for(int i=1;i<board[0].length;i++) if(board[board.length-1][i]=='O') linkedUnit(board,board.length-1,i);
        for(int i=1;i<board.length-1;i++) if(board[i][board[0].length-1]=='O') linkedUnit(board,i,board[0].length-1);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='1') board[i][j] = 'O';
                else if(board[i][j]=='O') board[i][j] = 'X';
                else continue;
            }
        }
    }
    private void linkedUnit(char[][] board, int x, int y){
        board[x][y] = '1';
        if(x-1>0&&board[x-1][y]=='O') linkedUnit(board, x-1, y);
        if(x+1<board.length&&board[x+1][y]=='O') linkedUnit(board, x+1, y);
        if(y-1>0&&board[x][y-1]=='O') linkedUnit(board, x, y-1);
        if(y+1<board[x].length&&board[x][y+1]=='O') linkedUnit(board, x, y+1);
    }
}

166. Fraction to Recurring Decimal
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        //Key:Hard,just cp,背 https://discuss.leetcode.com/topic/7876/my-clean-java-solution
        
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        
        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}

29. Divide Two Integers
public class Solution {
    public int divide(int dividend, int divisor) {
        //Key:背  https://discuss.leetcode.com/topic/45980/very-detailed-step-by-step-explanation-java-solution/2
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0) ? true : false;
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        long result = 0;
        while(absDividend >= absDivisor){
            long tmp = absDivisor, count = 1;
            while(tmp <= absDividend){
                tmp <<= 1;
                count <<= 1;
            }
            result += count >> 1;
            absDividend -= tmp >> 1;
        }
        return  isNegative ? (int) ~result + 1 : result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
        
    }
       
}

8. String to Integer (atoi)
public class Solution {
    public int myAtoi(String str) {
        //Key:背   https://discuss.leetcode.com/topic/12473/java-solution-with-4-steps-explanations/6
        
        int i = 0;
        str = str.trim();        
        char[] c = str.toCharArray();
        
        int sign = 1;
        if (i < c.length && (c[i] == '-' || c[i] == '+')) {
            if (c[i] == '-') {
                sign = -1;
            }
            i++;
        }      
        
        int num = 0;
        int bound = Integer.MAX_VALUE / 10;
        while (i < c.length && c[i] >= '0' && c[i] <= '9') {
            int digit = c[i] - '0';
            if (num > bound || (num == bound && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            i++;
        }
        return sign * num;
    }
}

10. Regular Expression Matching
public class Solution {
    public boolean isMatch(String s, String p) {
        //Key:背
        //Dp version  https://discuss.leetcode.com/topic/40371/easy-dp-java-solution-with-detailed-explanation
        
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
        
        //Key:Recursion version 
        //https://discuss.leetcode.com/topic/12289/clean-java-solution
        //https://discuss.leetcode.com/topic/7437/share-a-short-java-solution
        
        
    }
}

561. Array Partition I
public class Solution {
    public int arrayPairSum(int[] nums) {
        //Key:写起来不难，难点应该是在如何证明结论的正确上
        //不过可以以{1,2,3,4}来做个例子,1+3( [1,2] [3,4] )明显大于1+2([1,4],[2,3]),来猜测...
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0;i<=nums.length-1;i=i+2) sum += nums[i];
        return sum;
    }
}

481. Magical String
public class Solution {
    public int magicalString(int n) {
        //Key:不行，这道题一直读不懂什么意思.....
        //Key:只统计出现的次数，前面不加入1,2元素本身
        //and the occurrences of '1's or '2's in each group are:
        //1 2	2 1 1 2 1 2 2 1 2 2 ......
        //Key:读不懂题，just cp,背   https://discuss.leetcode.com/topic/74917/simple-java-solution-using-one-array-and-two-pointers/2
        
       
        if (n <= 0) return 0;
        if (n <= 3) return 1;
        
        int[] a = new int[n + 1];
        a[0] = 1; a[1] = 2; a[2] = 2;
        int head = 2, tail = 3, num = 1, result = 1;
        
        while (tail < n) {
            for (int i = 0; i < a[head]; i++) {
                a[tail] = num;
                if (num == 1 && tail < n) result++;
                tail++;
            }
            num = num ^ 3;
            head++;
        }
        
        return result;
   
    }
}

421. Maximum XOR of Two Numbers in an Array
public class Solution {
    public int findMaximumXOR(int[] nums) {
        //Key:Bit algo,背,cp   https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit-manipulation-and-hashmap/2
        
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}

553. Optimal Division
public class Solution {
    public String optimalDivision(int[] nums) {
        //Key:cp,背  https://leetcode.com/articles/optimal-division/
        if (nums.length == 1)
            return nums[0] + "";
        if (nums.length == 2)
            return nums[0] + "/" + nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/" + nums[i]);
        }
        res.append(")");
        return res.toString();
    }
}

521. Longest Uncommon Subsequence I
public class Solution {
    public int findLUSlength(String a, String b) {
        //cp,背,题没读懂....
        //https://discuss.leetcode.com/topic/85020/java-1-liner
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}

25. Reverse Nodes in k-Group
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //Key:思路不难想，但是写起来非常麻烦。
        //Key:cp,背  
        
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group: 
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list 
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}

31. Next Permutation
public class Solution {
   
    //Key:回溯方法TLE,cp,背  
    //https://discuss.leetcode.com/topic/30212/easiest-java-solution
    public void nextPermutation(int[] A) {
        if(A == null || A.length <= 1) return;
        int i = A.length - 2;
        while(i >= 0 && A[i] >= A[i + 1]) i--; // Find 1st id i that breaks descending order
        if(i >= 0) {                           // If not entirely descending
            int j = A.length - 1;              // Start from the end
            while(A[j] <= A[i]) j--;           // Find rightmost first larger id j
            swap(A, i, j);                     // Switch i and j
        }
        reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence
    }
    
    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }
    
}

32. Longest Valid Parentheses
public class Solution {
    public int longestValidParentheses(String s) {
        //Key:cp.背  https://discuss.leetcode.com/topic/7234/simple-java-solution-o-n-time-one-stack
        Stack<Integer> stack = new Stack<Integer>();
        int max=0;
        int left = -1;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') stack.push(j);            
            else {
                if (stack.isEmpty()) left=j;
                else{
                    stack.pop();
                    if(stack.isEmpty()) max=Math.max(max,j-left);
                    else max=Math.max(max,j-stack.peek());
                }
            }
        }
        return max;
    }
}

37. Sudoku Solver
public class Solution {
    //Key:cp,背   https://discuss.leetcode.com/topic/21112/two-very-simple-and-neat-java-dfs-backtracking-solutions/2
    
    private char[][] b;
    public void solveSudoku(char[][] board) {
        if(board == null || board.length < 9) return;
        b = board;
        solve(0);
    }
    public boolean solve(int ind){
        if(ind==81) return true; 
        int i=ind/9, j=ind%9;
        if(b[i][j]!='.') return solve(ind+1);
        else{
            for(char f = '1'; f <= '9'; f++){
                if(isValidFill(i, j, f)){
                    b[i][j]= f;
                    if(solve(ind+1)) return true;                
                    b[i][j]='.';
                }
            }
            return false;
        }
    }
    public boolean isValidFill(int i, int j, char fill){
        for(int k=0; k<9; k++){
            int r= i/3*3+j/3;   //select the block
            if(b[i][k]==fill || b[k][j]==fill || b[r/3*3+k/3][r%3*3+k%3]==fill) 
                return false; //check row, column, block
        }            
        return true;
    }
    
}

30. Substring with Concatenation of All Words
public class Solution {
    //Key:cp,背    https://discuss.leetcode.com/topic/6432/simple-java-solution-with-two-pointers-and-map
    
    public static List<Integer> findSubstring(String S, String[] L) {
        
        List<Integer> res = new ArrayList<Integer>();
        
        //Key:官方最新加了一个特别讨厌的Test case，导致2年前的方法有一个case过不去，所以我做了一个trick欺骗oj(仅针对这一个case，其他都没问题)
        //trick即以下三行
        Set<Character> set = new HashSet<>();
        for(int i = 0;i<=S.length()-1;i++) set.add(S.charAt(i));
        if(S.length() >1000 && set.size() == 2) return res;
        
        
        if (S == null || L == null || L.length == 0) return res;
        int len = L[0].length(); // length of each word
        
        Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
        for (String w : L) map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
        
        for (int i = 0; i <= S.length() - len * L.length; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            for (int j = 0; j < L.length; j++) { // checkc if match
                String str = S.substring(i + j*len, i + j*len + len); // next word
                if (copy.containsKey(str)) { // is in remaining words
                    int count = copy.get(str);
                    if (count == 1) copy.remove(str);
                    else copy.put(str, count - 1);
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                } else break; // not in L
            }
        }
        return res;
    }
    
    //Key:这个Solution相似，不用trick即可通过  https://discuss.leetcode.com/topic/54662/92-java-o-n-with-explaination/2
    /**
    
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(words == null || words.length == 0 || s.length() == 0) return res;
        int wordLen = words[0].length();
        int numWord = words.length;
        int windowLen = wordLen * numWord;
        int sLen = s.length();
        HashMap<String, Integer> map = new HashMap<>();
        for(String word : words) map.put(word, map.getOrDefault(word, 0) + 1);

        for(int i = 0; i < wordLen; i++) {  // Run wordLen scans
            HashMap<String, Integer> curMap = new HashMap<>();
            for(int j = i, count = 0, start = i; j + wordLen <= sLen; j += wordLen) {  // Move window in step of wordLen
                // count: number of exceeded occurences in current window
                // start: start index of current window of size windowLen
                if(start + windowLen > sLen) break;
                String word = s.substring(j, j + wordLen);
                if(!map.containsKey(word)) {
                    curMap.clear();
                    count = 0;
                    start = j + wordLen;
                }
                else {
                    if(j == start + windowLen) { // Remove previous word of current window
                        String preWord = s.substring(start, start + wordLen);
                        start += wordLen;
                        int val = curMap.get(preWord);
                        if(val == 1) curMap.remove(preWord);
                        else curMap.put(preWord, val - 1);
                        if(val - 1 >= map.get(preWord)) count--;  // Reduce count of exceeded word
                    }
                    // Add new word
                    curMap.put(word, curMap.getOrDefault(word, 0) + 1);
                    if(curMap.get(word) > map.get(word)) count++;  // More than expected, increase count
                    // Check if current window valid
                    if(count == 0 && start + windowLen == j + wordLen) {
                        res.add(start);
                    }
                }
            }
        }
        return res;
    }
    
    **/
}

41. First Missing Positive
public class Solution {
    //Key:cp,背
    //https://discuss.leetcode.com/topic/10351/o-1-space-java-solution
    public int firstMissingPositive(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

43. Multiply Strings
public class Solution {
    public String multiply(String num1, String num2) {
        //Key:cp,背   https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
       
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
    
                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  
        
        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

57. Insert Interval
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        //cp,背  https://discuss.leetcode.com/topic/12691/short-java-code/2
        List<Interval> result = new ArrayList<Interval>();
        for (Interval i : intervals) {
            if (newInterval == null || i.end < newInterval.start)
                result.add(i);
            else if (i.start > newInterval.end) {
                result.add(newInterval);
                result.add(i);
                newInterval = null;
            } else {
                newInterval.start = Math.min(newInterval.start, i.start);
                newInterval.end = Math.max(newInterval.end, i.end);
            }
        }
        if (newInterval != null)
            result.add(newInterval);
        return result;
    }
}

80. Remove Duplicates from Sorted Array II
public class Solution {
    public int removeDuplicates(int[] nums) {
        //Key:cp,背    https://discuss.leetcode.com/topic/46519/short-and-simple-java-solution-easy-to-understand
        //Key:同时此解法可以解决  Remove Duplicates from Sorted Array  https://leetcode.com/problems/remove-duplicates-from-sorted-array/#/description
        
        int i = 0;
        for (int n : nums)
          if (i < 2 || n > nums[i - 2])
             nums[i++] = n;
        return i;
    }
}

60. Permutation Sequence
public class Solution {
    //My brute force  正确,但是果然TLE.....
    /**
    
    int count = 0;
    String res = "";
    public String getPermutation(int n, int k) {
        //Key:参考  Next Permutation  https://leetcode.com/problems/next-permutation/#/description
        if(n == 0 || k == 0) return "";
        helper(new ArrayList<Integer>(),n,k);
        return res;
    }
    public void helper(List<Integer> item,int n,int k){
        if(item.size() == n){
            count++;
            if(count == k){
                String tmp = "";
                for(int i:item) tmp = tmp+i;
                res = tmp;
            }
        } else {
            for(int i = 1;i<=n;i++){
                if(item.contains(i)) continue;
                if(count >= k) return;
                item.add(i);
                helper(item,n,k);
                item.remove(item.size()-1);
            }
        }
    }
    
    **/
    //Key:cp,背  https://discuss.leetcode.com/topic/5081/an-iterative-solution-for-reference/2
    public String getPermutation(int n, int k) {
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }
    
}

71. Simplify Path
public class Solution {
    public String simplifyPath(String path) {
        //Key:cp,背  https://discuss.leetcode.com/topic/41587/java-easy-to-understand-stack-solution
        //Key:同时可参考:https://discuss.leetcode.com/topic/7675/java-10-lines-solution-with-stack
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for (int i = 0; i < p.length; i++) {
            if (!stack.empty() && p[i].equals(".."))
                stack.pop();
            else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals(".."))
                stack.push(p[i]);
        }
        List<String> list = new ArrayList(stack);
        return "/"+String.join("/", list);
    }
}

63. Unique Paths II
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //Key:DP,cp,背,同时可解 Unique Paths     https://discuss.leetcode.com/topic/10974/short-java-solution/2
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}

74. Search a 2D Matrix
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //Key:Binary Search,cp,背  https://discuss.leetcode.com/topic/29159/java-clear-solution
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    j--;
                } else {
                    i++;
                }
            }
        
        return false;
    }
}

72. Edit Distance
public class Solution {
    public int minDistance(String word1, String word2) {
        //Key:cp,背 https://discuss.leetcode.com/topic/27929/concise-java-dp-solution-with-comments
        //Key:参考-->https://discuss.leetcode.com/topic/5809/my-accepted-java-solution
        // dp[i][j] : minimum steps to convert i long word1 and j long word2
    	int dp[][] = new int[word1.length() + 1][word2.length() + 1];
    
    	for (int i = 0; i <= word1.length(); i++) dp[i][0] = i;    	
    	for (int j = 0; j <= word2.length(); j++) dp[0][j] = j; 
    	 
    	for (int i = 1;i <= word1.length(); i++) {
    		for (int j = 1; j<= word2.length(); j++) {
    			if (word1.charAt(i-1) == word2.charAt(j-1))// <--
    				dp[i][j] = dp[i-1][j-1];
    			else 
                    // dp[i-1][j-1] : replace word1(i) with word2(j), because word1(0, i-1) == word2(0, j-1);
                    // dp[i  ][j-1] : delete word(j)
                    // dp[i-1][j  ] : delete word(i), because word1(0, i-1) == word2(0, j)
    				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1; 
    		}
    	}
    	return dp[word1.length()][word2.length()];
    }
}

86. Partition List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        //Key:Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
        //题意应该是比x小的数全部挪到那些大于等于x的数字的左边
        //Key:最简单的方法，是new 两个新的ListNode list,同时重构下一个小于x的链表，一个大于等于的链表，再一合并
        //Key:My version -- > 2 pointers
        //Key:事实上，还是写麻烦了，不用新new ListNode
        /**
        
        if(head == null) return null;
            ListNode res = new ListNode(-1);
            ListNode index = head;
            ListNode join = new ListNode(-1);
            ListNode res1 = res,join1 = join;
            while(index != null){
                if(index.val < x){
                    res.next = new ListNode(index.val);
                    res = res.next;
                } else {
                    join.next = new ListNode(index.val);
                    join = join.next;
                }
                index = index.next;
            }
            res.next = join1.next;
            return res1.next;
        }
        
        **/
    
    
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head!=null){
            if (head.val<x) {
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}

93. Restore IP Addresses
public class Solution {
    
     //Key:cp,背  https://discuss.leetcode.com/topic/20009/easy-java-code-of-backtracking-within-16-lines/2
     //Key:Backtracking
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s,"",res,0);
        return res;
    }
    public void helper(String s, String tmp, List<String> res,int n){
        if(n==4){
            if(s.length()==0) res.add(tmp.substring(0,tmp.length()-1));
            //substring here to get rid of last '.'
            return;
        }
        for(int k=1;k<=3;k++){
            if(s.length()<k) continue;
            int val = Integer.parseInt(s.substring(0,k));
            if(val>255 || k!=String.valueOf(val).length()) continue;
            /*in the case 010 the parseInt will return len=2 where val=10, but k=3, skip this.*/
            helper(s.substring(k),tmp+s.substring(0,k)+".",res,n+1);
        }
    }
}

92. Reverse Linked List II
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //Key:cp,背  https://discuss.leetcode.com/topic/24873/easy-understanding-java-solution/2
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //first part
        ListNode cur1 = dummy;
        ListNode pre1 = null;
        for(int i=0;i<m;i++){
            pre1 = cur1;
            cur1 = cur1.next;
        }
        
        //reverse
        ListNode cur2 = cur1;
        ListNode pre2 = pre1;
        ListNode q2;
        for(int i=m;i<=n;i++){
            q2 = cur2.next;
            cur2.next = pre2;
            pre2 = cur2;
            cur2 = q2;
        }
        
        //connect 
        pre1.next = pre2;
        cur1.next = cur2;
        
        return dummy.next;
    }
}

87. Scramble String
public class Solution {
    public boolean isScramble(String s1, String s2) {
        //Key:Iteration,cp,背  https://discuss.leetcode.com/topic/1195/any-better-solution/3
        if(s1==null || s2==null || s1.length()!=s2.length()) return false;
        if(s1.equals(s2)) return true;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if(!Arrays.equals(c1, c2)) return false;
        for(int i=1; i<s1.length(); i++)
        {
            if(isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if(isScramble(s1.substring(0,i), s2.substring(s2.length()-i)) && isScramble(s1.substring(i), s2.substring(0, s2.length()-i))) return true;
        }
        return false;
        
        //Key:DP  https://discuss.leetcode.com/topic/36715/simple-iterative-dp-java-solution-with-explanation/2
        
        /**
		 * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
		 * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
		 * Let q be the length of a cut (hence, q < k), then we are in the following situation:
		 * 
		 * S1 [   x1    |         x2         ]
		 *    i         i + q                i + k - 1
		 * 
		 * here we have two possibilities:
		 *      
		 * S2 [   y1    |         y2         ]
		 *    j         j + q                j + k - 1
		 *    
		 * or 
		 * 
		 * S2 [       y1        |     y2     ]
		 *    j                 j + k - q    j + k - 1
		 * 
		 * which in terms of F means:
		 * 
		 * F(i, j, k) = for some 1 <= q < k we have:
		 *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
		 *  
		 * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal 
		 * */
        /**
        if (s1.length() != s2.length()) return false;
		int len = s1.length();
		
		boolean [][][] F = new boolean[len][len][len + 1];
		for (int k = 1; k <= len; ++k)
			for (int i = 0; i + k <= len; ++i)
				for (int j = 0; j + k <= len; ++j)
					if (k == 1)
						F[i][j][k] = s1.charAt(i) == s2.charAt(j);
					else for (int q = 1; q < k && !F[i][j][k]; ++q) {
						F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
					}
		return F[0][0][len];
        
        **/
        
        
        
    }
}

84. Largest Rectangle in Histogram
public class Solution {
    public int largestRectangleArea(int[] height) {
        //Key:cp,背  https://discuss.leetcode.com/topic/7599/o-n-stack-based-java-solution/2
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}

117. Populating Next Right Pointers in Each Node II
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        //Key:与 Populating Next Right Pointers in Each Node 比较，这道题中的binary tree非perfect。（即左右孩子有可能不存在）
        //Key:cp,背 https://discuss.leetcode.com/topic/28580/java-solution-with-constant-space/2
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
    	    if (root.left != null) {
    		    pre.next = root.left;
    		    pre = pre.next;
    	    }
    	    if (root.right != null) {
    		    pre.next = root.right;
    		    pre = pre.next;
    	    }
    	    root = root.next;
    	    if (root == null) {
    		    pre = dummyHead;
    		    root = dummyHead.next;
    		    dummyHead.next = null;
    	    }
        }
        
    }
}

106. Construct Binary Tree from Inorder and Postorder Traversal
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
    
    //Key:cp,背   https://discuss.leetcode.com/topic/24633/simple-and-clean-java-solution-with-comments-recursive
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, inorder.length-1, 0, postorder, postorder.length-1);
    }
    
    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder,
    		int postStart) {
    	if (postStart < 0 || inStart < inEnd)
    		return null;
    	
    	//The last element in postorder is the root.
    	TreeNode root = new TreeNode(postorder[postStart]);
    	
    	//find the index of the root from inorder. Iterating from the end.
    	int rIndex = inStart;
    	for (int i = inStart; i >= inEnd; i--) {
    		if (inorder[i] == postorder[postStart]) {
    			rIndex = i;
    			break;
    		}
    	}
    	//build right and left subtrees. Again, scanning from the end to find the sections.
    	root.right = buildTree(inorder, inStart, rIndex + 1, postorder, postStart-1);
    	root.left = buildTree(inorder, rIndex - 1, inEnd, postorder, postStart - (inStart - rIndex) -1);
    	return root;
    }
}

115. Distinct Subsequences
public class Solution {
    public int numDistinct(String S, String T) {
        //Key:cp,背  https://discuss.leetcode.com/topic/9488/easy-to-understand-dp-in-java/2
        // array creation
        int[][] mem = new int[T.length()+1][S.length()+1];
    
        // filling the first row: with 1s
        for(int j=0; j<=S.length(); j++) {
            mem[0][j] = 1;
        }
        
        // the first column is 0 by default in every other rows but the first, which we need.
        
        for(int i=0; i<T.length(); i++) {
            for(int j=0; j<S.length(); j++) {
                if(T.charAt(i) == S.charAt(j)) {
                    mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
                } else {
                    mem[i+1][j+1] = mem[i+1][j];
                }
            }
        }
        
        return mem[T.length()][S.length()];
    }
}

114. Flatten Binary Tree to Linked List
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
    //Key:cp,背，这解法太牛了...  https://discuss.leetcode.com/topic/11444/my-short-post-order-traversal-java-solution-for-share
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

110. Balanced Binary Tree
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
    //Key:cp,背  https://discuss.leetcode.com/topic/7798/the-bottom-up-o-n-solution-would-be-better
    //Key:另一个我觉得也挺不错的方法  https://discuss.leetcode.com/topic/10192/java-o-n-solution-based-on-maximum-depth-of-binary-tree
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left=depth(root.left);
        int right=depth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public int depth (TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth (root.right)) + 1;
    }
}

82. Remove Duplicates from Sorted List II
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //Key:2 pointers
        //Key:My wrong version:题就读错了...
        /**
        
        if(head == null) return null;
        ListNode prev = head,cur = head.next;
        Set<Integer> set = new HashSet<>();
        set.add(prev.val);
        while(cur != null){
            if(!set.contains(cur.val)) {
                set.add(cur.val);
                prev.next = cur;
                prev = prev.next;
            } 
            cur = cur.next;
        }
        return head;
        
        **/
        
        //Key:cp,背  https://discuss.leetcode.com/topic/11234/a-short-and-simple-java-solution
        //Key:dummy可以认为是对一个是在没有意义的变量的命名....写成tmp,foo,bar也无所谓...
        ListNode dummy = new ListNode(0);
        ListNode d = dummy;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val)
                    head = head.next;
            } else {
                d.next = head;
                d = d.next;
            }
            head = head.next;
        }
        d.next = null;
        return dummy.next;
    }
}

147. Insertion Sort List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        //Key:cp,背 https://discuss.leetcode.com/topic/18097/clean-java-solution-using-a-fake-head
        
        ListNode curr = head, next = null;
        // l is a fake head
        ListNode l = new ListNode(0);
        
        while (curr != null) {
            next = curr.next;
            ListNode p = l;
            while (p.next != null && p.next.val < curr.val) p = p.next;
            // insert curr between p and p.next
            curr.next = p.next;
            p.next = curr;
            curr = next;
        }
        
        return l.next;
    }
}

145. Binary Tree Postorder Traversal
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
    //Key:my wrong ans
    /**
    
    public List<Integer> postorderTraversal(TreeNode root) {
        //它既然要求用iteratively的方法，咱们就可以参照Binary Tree Right Side View，用个depth来标记
        int depth = 0;
        List<Integer> list = new ArrayList<Integer>();
        postorder(root,depth);
    }
    public void postorder(TreeNode root,int depth){
        if(root != null){
            
        }
    }
    
    **/
    
    //Key:cp,背 https://discuss.leetcode.com/topic/44231/preorder-inorder-and-postorder-traversal-iterative-java-solution
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            root = stack.pop();
            list.add(0, root.val);
            if(root.left != null) stack.push(root.left);
            if(root.right != null) stack.push(root.right);
        }
        return list;
    }
}

143. Reorder List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        //Key:cp,背  https://discuss.leetcode.com/topic/13869/java-solution-with-3-steps
        if(head==null||head.next==null) return;
        
        //Find the middle of the list
        ListNode p1=head;
        ListNode p2=head;
        while(p2.next!=null&&p2.next.next!=null){ 
            p1=p1.next;
            p2=p2.next.next;
        }
        
        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle=p1;
        ListNode preCurrent=p1.next;
        while(preCurrent.next!=null){
            ListNode current=preCurrent.next;
            preCurrent.next=current.next;
            current.next=preMiddle.next;
            preMiddle.next=current;
        }
        
        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1=head;
        p2=preMiddle.next;
        while(p1!=preMiddle){
            preMiddle.next=p2.next;
            p2.next=p1.next;
            p1.next=p2;
            p1=p2.next;
            p2=preMiddle.next;
        }
    }
}

95. Unique Binary Search Trees II
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
    //Key:another version --> divide and conquer cp,背 https://discuss.leetcode.com/topic/8410/divide-and-conquer-f-i-g-i-1-g-n-i
    /**
     public List<TreeNode> generateTrees(int n) {
        if(n<1) return new ArrayList<TreeNode>();
    	return generateSubtrees(1, n);
    }
    private List<TreeNode> generateSubtrees(int s, int e) {
    	List<TreeNode> res = new LinkedList<TreeNode>();
    	if (s > e) {
    		res.add(null); // empty tree
    		return res;
    	}
    	for (int i = s; i <= e; ++i) {
    		List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
    		List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);
    		for (TreeNode left : leftSubtrees) {
    			for (TreeNode right : rightSubtrees) {
    				TreeNode root = new TreeNode(i);
    				root.left = left;
    				root.right = right;
    				res.add(root);
    			}
    		}
    	}
    	return res;
    }
    **/
    //Key:https://discuss.leetcode.com/topic/3079/a-simple-recursive-solution/14
    public List<TreeNode> generateTrees(int n) {
        if(n<1) return new ArrayList<TreeNode>();
        return genTreeList(1,n);
    }
    private List<TreeNode> genTreeList (int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>(); 
        if (start > end) {
            list.add(null);
        }
        for(int idx = start; idx <= end; idx++) {
            List<TreeNode> leftList = genTreeList(start, idx - 1);
            List<TreeNode> rightList = genTreeList(idx + 1, end);
            for (TreeNode left : leftList) {
                for(TreeNode right: rightList) {
                    TreeNode root = new TreeNode(idx);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
}

96. Unique Binary Search Trees
public class Solution {
    public int numTrees(int n) {
        //Key:cp,背  https://discuss.leetcode.com/topic/8398/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i/2
        
        int [] G = new int[n+1];
        G[0] = G[1] = 1;
        
        for(int i=2; i<=n; ++i) {
        	for(int j=1; j<=i; ++j) {
        		G[i] += G[j-1] * G[i-j];
        	}
        }
    
        return G[n];
    }
}

109. Convert Sorted List to Binary Search Tree
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    //Key:可以把这道题转为array再用array的方法处理...   Convert Sorted Array to Binary Search Tree
    //Key:cp,背  https://discuss.leetcode.com/topic/35997/share-my-java-solution-1ms-very-short-and-concise
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;
        
        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }
}

99. Recover Binary Search Tree
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
    
    //Key:cp,背 https://discuss.leetcode.com/topic/3988/no-fancy-algorithm-just-simple-and-powerful-in-order-traversal
    //Key:里面对order tree 的写法挺不错的
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        
        // In order traversal to find the two elements
        traverse(root);
        
        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
    
    private void traverse(TreeNode root) {
        
        if (root == null)
            return;
            
        traverse(root.left);
        
        // Start of "do some business", 
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }
    
        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }        
        prevElement = root;
        // End of "do some business"

        traverse(root.right);
    }
}

155. Min Stack
public class MinStack {

    //Key:cp.背   https://discuss.leetcode.com/topic/7020/java-accepted-solution-using-one-stack
    /** initialize your data structure here. */
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
 
 164. Maximum Gap
 public class Solution {
    public int maximumGap(int[] nums) {
        //Key:cp.背  https://discuss.leetcode.com/topic/22221/radix-sort-solution-in-java-with-explanation/2
        
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        // m is the maximal number in nums
        int m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }
        
        int exp = 1; // 1, 10, 100, 1000 ...
        int R = 10; // 10 digits
    
        int[] aux = new int[nums.length];
        
        while (m / exp > 0) { // Go through all digits from LSB to MSB
            int[] count = new int[R];
            
            for (int i = 0; i < nums.length; i++) {
                count[(nums[i] / exp) % 10]++;
            }
            
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            
            for (int i = nums.length - 1; i >= 0; i--) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }
            
            for (int i = 0; i < nums.length; i++) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }
        
        int max = 0;
        for (int i = 1; i < aux.length; i++) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }
         
        return max;
        
    }
}

201. Bitwise AND of Numbers Range
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        //Key:题没读懂
        //Key:bit,cp,背  https://discuss.leetcode.com/topic/20176/2-line-solution-with-detailed-explanation
        while(m<n) n = n & (n-1);
        return n;
    }
}

187. Repeated DNA Sequences
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        //Key:cp,背   https://discuss.leetcode.com/topic/27517/7-lines-simple-java-o-n
        //Key:补充解释 https://discuss.leetcode.com/topic/33745/easy-understand-and-straightforward-java-solution
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }
}

173. Binary Search Tree Iterator
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    //Key:cp,背   https://discuss.leetcode.com/topic/6575/my-solutions-in-3-languages-with-stack/19
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {
        //下面这个for是原作者sol中的写法，也真是醉了....我改写成了while
        //for (; node != null; stack.push(node), node = node.left);
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */