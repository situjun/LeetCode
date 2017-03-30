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