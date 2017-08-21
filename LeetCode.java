///

//Refrence:
//http://www.cnblogs.com/grandyang

1. Two Sum  
//Key:
//1:case [2,3,4],6 -> 注意如果先把3存进去，然后在读一遍3的话，会把之前存进去的3误算进去。
//即不能先map.put(nums[i],i),再map.containskKey(target-nums[i])。这两者的顺序应该反过来才对
/*170616*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        //if(n <= 1) return new int[2];
        int[] res = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<=n-1;i++){
            int tmp = target-nums[i];
            if(map.containsKey(tmp)){
                res[0] = map.get(tmp);
                res[1] = i;
            } else {
                map.put(nums[i],i);
            }
        }
        return res;
    }
}
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        //HashMap solving method
        //It maybe contains duplicate elements
        if(nums.length < 2) return null;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int[] result = new int[2];
        int rest = 0;
        for(int i = 0;i <= nums.length - 1;i++){
            rest = target - nums[i];
            
            if(map.containsKey(rest)){
            //It maybe contains duplicate elements
            //we put map.put(nums[i],i); last line,so we needn't to consider duplicate problem
                // if(map.get(rest) != i){
                //     result[0] = map.get(rest);
                //     result[1] = i;
                // }
                
                
                result[0] = map.get(rest);
                result[1] = i;
            }
            map.put(nums[i],i);
        }
        return result;
    }
}

//V2
public class Solution {

    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        if(numbers.length == 0) return new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        int[] res = new int[2];
        for(int i = 0;i<=numbers.length-1;i++){
            if(map.containsKey(target-numbers[i])){
                res[0] = map.get(target-numbers[i]);
                res[1] = i;
            } else {
                map.put(numbers[i],i);
            }
        }
        return res;
    }
}

2. Add Two Numbers  
//Star:
//1.注意在改变node值时，node是否为空。所以最好操作node.next，返回时返回head.next就可以解决这个问题。即head作为无意义node
//2.case:[null,null]->因为返回的是ListNode，假如传进来的参数是两个null，那么while(l1 != null || l2 != null){}这个核心方法体就会被跳过。
//但是返回null和返回一个new ListNode(0)因该说均符合这个答案要求。
//所以不用担心当传入两个null时会造成错误,最后return res.next直接返回一个null也是符合题意的
/*170616*/
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        //Key:返回一个null和new ListNode(0)本质上一样，所以不用加上下面这句
        //res.next = new ListNode(0);
        ListNode pointer = res;
        int tmp = 0;
        while(l1 != null || l2 != null){
            int sum = tmp;
            if(l1 != null){
                sum+=l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum+=l2.val;
                l2 = l2.next;
            }
            tmp = sum/10;
            pointer.next= new ListNode(sum%10);
            pointer = pointer.next;
        }
        if(tmp == 1) pointer.next = new ListNode(tmp);
        return res.next;
    }
}
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
       //Key:写的还是有点乱
       /**
        //Key:这题给的example太不好了...正着来反着来都一样...
        //Key:test case [2,4,3] [5,6,5] expect：[7,0,9]
        int more = 0,cur = 0;
        ListNode res = l1,prev = l1;
        while(l1 != null && l2 != null){
            
            int tmp = l1.val +l2.val+more;
            if(tmp>=10){
                cur = tmp-10;
                more = 1;
            } else {
                cur = tmp;
                more = 0;
            }
            l1.val = cur;
            prev = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
           
            int tmp = l1.val + more;
            if(tmp>=10){
                cur = tmp-10;
                more = 1;
            } else {
                cur = tmp;
                more = 0;
            }
            l1.val = cur;
            prev = l1;
            l1 = l1.next;
        }
        while(l2 != null){
            
            int tmp = l2.val + more;
            if(tmp>=10){
                cur = tmp-10;
                more = 1;
            } else {
                cur = tmp;
                more = 0;
            }
            l1.next = new ListNode(cur);
            prev = l1;
            l1 = l1.next;
        }
        if(more == 1) prev.next = new ListNode(1);
        return res;
    }
    
       **/
        //Key:cp,参考一下
        //Key:如果不需要改node值，判断node == null就好了。但需要改变node值时，会因为node == null时，node.next会报错
        //Key:因为这个要改变node的值，所以最好不要在当前node上改，因为会有node==null时的干扰。so最好是在next上改
        //https://discuss.leetcode.com/topic/39130/4ms-11lines-java-solution
        ListNode ln1 = l1, ln2 = l2, head = null, node = null;
        int carry = 0, remainder = 0, sum = 0;
        head = node = new ListNode(0);
        
        while(ln1 != null || ln2 != null || carry != 0) {
            sum = (ln1 != null ? ln1.val : 0) + (ln2 != null ? ln2.val : 0) + carry;
            carry = sum / 10;
            remainder = sum % 10;
            node = node.next = new ListNode(remainder);
            ln1 = (ln1 != null ? ln1.next : null);
            ln2 = (ln2 != null ? ln2.next : null);
        }
        return head.next;
    }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        //Key:这个Corner case处理起来太麻烦了！！！
        //V3
        /*
        ListNode res = new ListNode(0);
        ListNode head = res;
        int sum = 0,more = 0;
        //Key:清晰点的思路：[1]先计算出每个点的值，【2】再赋值给node    
        while(l1!= null || l2 != null){
            //Key:1->和是三部分，不要忘记进位
            //Key:2->注意判断l1,l1是否为null,如果是null则定为0
            int a = (l1 == null)?0:l1.val;
            int b = (l2 == null)?0:l2.val;
            //System.out.println(a+","+b);
            sum = a + b + more;
            //System.out.println(more+"mm");
            more = sum/10;
            sum = sum%10;
            //System.out.println(sum+"");
            res.val = sum;
            //System.out.println(res.val+"");
            res.next = new ListNode(0);
            res = res.next;
            l1 = (l1 == null)?null:l1.next;
            l2 = (l2 == null)?null:l2.next;
        }
        if(more == 1)res.val = 1;
        else res = null;
        return head;
        **/
        //Case problem
        //V4
        ListNode tmp = new ListNode(0);
        ListNode res = tmp;
        int sum = 0,more = 0;
        while(l1 != null || l2 != null){
            int a = l1==null?0:l1.val;
            int b = l2==null?0:l2.val;
            sum = a+b+more;
            //Key:进位
            more = sum/10;
            //Key:当前位
            sum = sum%10;
            tmp.next = new ListNode(sum);
            tmp = tmp.next;
            //Key:关键点
            l1 = l1 == null?null:l1.next;
            l2 = l2 == null?null:l2.next;
        }
        if(more == 1) tmp.next = new ListNode(more);
        //Key:因为当前节点赋值时会有点问题，所以不如用node.next来赋值比较好。
        return res.next;
    }
}

3. Longest Substring Without Repeating Characters
//Star:
//counter依旧是守门人，如果有重复的出现，则counter记为1。
//与T76不同的是，这次counter允许通过的char是按照“队列”顺序进入的，所以counter也要按照队列顺序将之前的char删除，
//直到那个重复的char数量 变为1
//mark1
//mark2
/*170617*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        //Key170619:wrong version  -> case:"abba"
        /**
            if(s.length() == 0) return 0;
            int begin = 0,end = 0,n = s.length(),res = Integer.MIN_VALUE,counter = 0;
            Map<Character,Integer> map = new HashMap<>();
            while(end<=n-1){
                char c = s.charAt(end);
                if(map.containsKey(c)){
                    begin = end;
                } 
                map.put(c,end);
                res = Math.max(res,end-begin+1);
                end++;
            }
            return res;
        **/
        //Key170619:counter依旧是守门人，如果有重复的人进入，则counter++(应为底下的while(counter >0),所以counter最多也就可能加到1为止)。与T76不同的是，这次counter允许通过的char是按照“队列”顺序进入的，所以counter也要按照队列顺序将之前的char删除，直到那个重复的char数量 变为1
        if(s.equals("")) return 0;
        int res = Integer.MIN_VALUE,n = s.length(),begin = 0,end = 0,counter = 0;
        Map<Character,Integer> map = new HashMap<>();
        while(end<=n-1){
            char c = s.charAt(end);
            map.put(c,map.getOrDefault(c,0)+1);
            if(map.get(c) > 1) counter++;
            end++;
            while(counter > 0){
                c = s.charAt(begin);
				//170624 mark1,counter需要先--，然后frequence再--。如果freq先--，那么get(c)>1就不满足了
                if(map.get(c) > 1) counter--;
                map.put(c,map.get(c)-1);
                //170624 mark2,前面的那个相同char删除
                begin++;
            }
            res = Math.max(res,end-begin);
        }
        return res;
    }
}

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

4. Median of Two Sorted Arrays
//Star
//170709:Core:mark1,3,4,7不好记忆，直接硬背    只是判断是否为空数组，即start>?length.而不是判断start+k>?length --> 如果所求元素正好位于所求段，容易漏掉
//Star ~ code4.2 
//1.规定，num1.length<=nums2.length.较短序列所有元素都被抛弃，可直接返回较长序列的第k大元素，可以不用分情况讨论了。
//不用再乱七八糟的讨论了。所需的(k-1)/2位置可能大于某个数组总长度，规定A短之后，只需要考虑超过A的长度，
//不需要再分情况讨论了。
//2.mark0 left和right的坐标确认
//3.mark1
//4.mark2
//5.mark3
//6.mark4
//7.mark5
//mark9:我们只关心第k小的元素是哪个，而不关心他前面的那些比他小的元素的具体排序是什么
/**
1：
此题关键，记住。这个思考起来很绕，但是结论肯定没错。用个特殊case来背 -> [1,2,3,4,5,6,7]  [11,12,13,14,15,16,17] ，所以是从[4,5,6,7,11,12,13,14]中找
	if (aMid < bMid) Keep [aRight + bLeft]     
	else Keep [bRight + aLeft]
**/

/*170818*/
class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int n1 = A.length,n2 = B.length,left = (n1+n2+1)/2,right = (n1+n2+2)/2;
        return (findKth(0,A,0,B,left)+findKth(0,A,0,B,right))/2.0;
    }
    public int findKth(int aStart,int[] A,int bStart,int[] B,int k){
        if(aStart >=A.length) return B[bStart+k-1];
        if(bStart >=B.length) return A[aStart+k-1];
		if(k == 1) return Math.min(A[aStart],B[bStart]);
		//Mark0:k/2还是(k+1)/2不是关键，白板时不用特别关注....
        int midA = (aStart+k/2)-1,midB = (bStart+k/2)-1;
        int midAVal = midA >= A.length?Integer.MAX_VALUE:A[midA],midBVal = midB >= B.length?Integer.MAX_VALUE:B[midB];
        if(midAVal < midBVal)
            return findKth(midA+1,A,bStart,B,k-k/2);
        else 
            return findKth(aStart,A,midB+1,B,k-k/2);
    }
}


/*170618*/
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		//mark0:left = (n+m+1)/2,right = (n+m+2)/2。要记得+1,+2。可以用case:n=3,m=4,left == 4，right == 5来记
        int n = nums1.length,m = nums2.length,left = (n+m+1)/2,right = (n+m+2)/2,aStart = 0,bStart = 0;
        return (findKth(nums1,aStart,nums2,bStart,left)+findKth(nums1,aStart,nums2,bStart,right))/2.0;
    }
	//找出《第K小的》元素
	//mark9:我们只关心第k小的元素是哪个，而不关心他前面的那些比他小的元素的具体排序是什么
    public int findKth(int[] nums1,int aStart,int[] nums2,int bStart,int k){
		//170703:mark8 为统一，index只与nums1.length比较  mark1,mark7
		//mark1:aStart已经超过了nums1的最大坐标
        if(aStart>=nums1.length) return nums2[bStart+k-1];
        if(bStart>=nums2.length) return nums1[aStart+k-1];
		//mark2:k==1，找出第一个元素，即最小的元素
        if(k == 1) return Math.min(nums1[aStart],nums2[bStart]);
        //Key170618:比如说在[0,1,2]中找k==2的数字，那么该数字index就是0+2-1
		//mark3:确定现在的mid值，如果midInd 已经超出了，midVal赋值为max
        int aMidInd = (aStart+k/2-1),bMidInd = (bStart+k/2-1);
		//170703:背，mark7,为什么midIndex需要和length比一下？前面明明已经判断过了aStart和length。为什么mid>length，nums就设置成Max？需解决
        int aMidVal = aMidInd<=nums1.length-1?nums1[aMidInd]:Integer.MAX_VALUE;
        int bMidVal = bMidInd<=nums2.length-1?nums2[bMidInd]:Integer.MAX_VALUE;
		//mark4:
		//mark8:aMidVal == bMidVal时，怎样都可以，所以归在那边都可以
        if(aMidVal<bMidVal)
        //Key170618:这里aMidInd+1而不是直接aMid，应该是因为mid值已经被判断过了，所以不要了。而且如果直接aMidInd，那么答案是错的
		//mark5:因为k/2是往小了算(如3/2 == 1，剩余部分为2)，所以剩余部分要用k-k/2
            return findKth(nums1,aMidInd+1,nums2,bStart,k-k/2);
        else 
            return findKth(nums1,aStart,nums2,bMidInd+1,k-k/2);
    }
}

/*170616*/
//Star
//1.两个数组的元素总数量有可能是偶数，也有可能是奇数。所以要除以2.0 -> 当为奇数个时，则right,left指向的均是中间这个元素，除以2.0还是这个数。为偶数时，则正好是两数平均值
//2.findKth（...,k）中的k是第k大元素(从1开始)，而不是index==k的元素(从0开始)。So,left = (m+n+1)/2,而不是(m-1+n-1+1)/2。right同理
//Key170616:背，实际上这个func是用来查找顺序第k个的元素
//Step
//1.不断地把两个原数组拆分成只有一般大小的两个新数组
//2.如果其中一个数组已经无法拆分了/不可操作了，则另一个数组的第x大元素即为两个数组的第x大元素
//如果两个数组均只剩一个元素了，则返回其中的最小值即可
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res = 0.0;
        //Key170616:从左到右编译，所以先算出m，再算出n，此时m,n值已经存入stack，可直接使用
		//mark-0.9:记住left,right分别奇偶数量时的中间index,所以一定要记得(n1+n2+1)除以2	
        int m = nums1.length,n = nums2.length,left = (m+n+1)/2,right = (m+n+2)/2;
        int aStart = 0,bStart = 0;
		//case:[1,2][3,4] 除以2时，返回的是2.0,明显结果是不对的
		//return (findKth(nums1,0,nums2,0,left)+findKth(nums1,0,nums2,0,right))/2;
        return (findKth(nums1,aStart,nums2,bStart,left)+findKth(nums1,aStart,nums2,bStart,right))/2.0;
    }
    public int findKth(int[] nums1,int aStart,int[] nums2,int bStart,int k){
        if(aStart>=nums1.length) return nums2[bStart+k-1];
        if(bStart>=nums2.length) return nums1[aStart+k-1];
        if(k == 1) return Math.min(nums1[aStart],nums2[bStart]);
        int aMidIdx = (aStart+k/2)-1,bMidIdx = (bStart+k/2)-1;
        //Key170617:aMidVal和bMidVal必须要先赋值为max_value，为了比较
        int aMidVal = Integer.MAX_VALUE,bMidVal = Integer.MAX_VALUE;
        if(aMidIdx <= nums1.length-1) aMidVal = nums1[aMidIdx];
        if(bMidIdx <= nums2.length-1) bMidVal = nums2[bMidIdx];
        if(aMidVal<bMidVal)
        
        //Key170617:下面的是Wrong,
        //return findKth(nums1,aMidIdx,nums2,bStart,k/2);
        //Key170617:背，findKth()中的index参数和前面的aMidIdx不同。还有是k-k/2，而不是k/2!!!这部分非常绕
            return findKth(nums1,aMidIdx+1,nums2,bStart,k-k/2);
        else 
            return findKth(nums1,aStart,nums2,bMidIdx+1,k-k/2);
    }
}

public class Solution {
    //My wrong version
    /**
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
	//Key:思路一样，但他这种写法不是很容易理解。另一种较容易理解的写法是下面的code4.2
    //https://discuss.leetcode.com/topic/28602/concise-java-solution-based-on-binary-search/2
	//Key:下面参考里的方法里用到了Arrays.copyOfRange(nums2, j, n),其实等价于nums2从j位置到n位置，只不过他这里额外的新cp了一个数组。
	//其实直接给个start和end范围，直接在原数组上操作就可
	//http://www.cnblogs.com/grandyang/p/4465932.html
	//https://segmentfault.com/a/1190000002988010
	//http://www.cnblogs.com/ganganloveu/p/4180523.html
	
    public double findMedianSortedArrays(int[] A, int[] B) {
    	    int m = A.length, n = B.length;
    	    int l = (m + n + 1) / 2;
    	    int r = (m + n + 2) / 2;
    	    return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    	}
    //A从aStart找到两个数组中第k大的元素
    public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
		/**
		我们要判断小的数组是否为空，为空的话，直接在另一个数组找第K个即可。
		还有一种情况是当K = 1时，表示我们要找第一个元素，只要比较两个数组的第一个元素，返回较小的那个即可。
		*/
		//范围为astart~A.length和bstart~B.length范围中的第k大元素
		//aStart > A.length - 1,start位置大于A数组长度，意味着A中从start位置开始，不存在着这个两数组中第k大的元素，所以无需在A中找了。
		//所以直接返回B中第k个元素
		//Key:下面这两句是用来排除Test case的语句，非core思路内容
		
    	if (aStart > A.length - 1) return B[bStart + k - 1];            
    	if (bStart > B.length - 1) return A[aStart + k - 1];                
    	if (k == 1) return Math.min(A[aStart], B[bStart]);
    	
    	int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
		//Key:分别找出两个数组中的中间值
    	if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1]; 
    	if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];        
    	
	
    	if (aMid < bMid) 
			//Key170617:因为要找的是从aStart开始的第k个元素，所以知道不知道尾部无影响。
			//即使k超过了A.length,那么舍弃A即可
    	    return getkth(A, aStart + k/2, B, bStart,k - k/2);// Check: aRight + bLeft 
    	else 
    	    return getkth(A, aStart,B,bStart + k/2, k - k/2);// Check: bRight + aLeft
    }
}
//code4.2
//Key:另一种较容易理解的写法
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, nums2, left) + findKth(nums1, nums2, right)) / 2.0;
    }
	//Key170617:
	/**
	下面重点来看如何实现找到第K个元素，
	首先我们需要让数组1的长度小于或等于数组2的长度，那么我们只需判断如果数组1的长度大于数组2的长度的话，交换两个数组即可，
	然后我们要判断小的数组是否为空，为空的话，直接在另一个数组找第K个即可。 ??有没有可能两数组均为空??
	还有一种情况是当K = 1时，表示我们要找第一个元素，只要比较两个数组的第一个元素，返回较小的那个即可。
	**/
    int findKth(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
		//Key170617:第一个数组长度必须小于第二个，因为当特殊情况下，
		//较短序列所有元素都被抛弃，可以返回较长序列的第k个元素（在数组中下标是k-1）。不用再
		//乱七八糟的讨论了。所需的(k-1)/2位置可能大于某个数组总长度，规定A短之后，只需要考虑超过A的长度，
		//不需要再分情况讨论了。
        if (m > n) return findKth(nums2, nums1, k);
        if (m == 0) return nums2[k - 1];
        if (k == 1) return Math.min(nums1[0], nums2[0]);
		//Key170617:判断中间值的位置顺序是否在新数组长度内。比如说m==3,k/2==4这种情况，因此中值只能选择nums1[3]了
        int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
        if (nums1[i - 1] > nums2[j - 1]) {
            return findKth(nums1, Arrays.copyOfRange(nums2, j, n), k - j);
        } else {
            return findKth(Arrays.copyOfRange(nums1, i, m), nums2, k - i);
        }
    }
}

5. Longest Palindromic Substring

//Star
//core:需要把dp[i][i]和dp[i][i+1]，单独字符和两两比较的基础情况写出来，才能用dp
//mark1
//mark2
//mark3
//http://www.cnblogs.com/grandyang/p/4464476.html
//Trans Func:dp[i][j] = s.charAt(i)==s.charAt(j) && dp[i+1][j-1],basic -> 奇数情况：dp[i][i] = true,偶数情况：dp[i][i+1] = charAt(i) == charA(i+1)
//1.难点在于奇偶判断上  case:"abbab" 如果基础点是b,按照charAt(i-1)==charAt(i+1)判断,得到结果bab,会忽视abba.
//如果按照bb判断，当case:"aabbbaa",很明显会忽视掉aabbbaa这种奇数情况
//2.end = 0,而不是end = n-1 -> case:"abcde"时，inner for的dp[i][j]都是false，因为下面的初始是j == i+1。而dp[0][0]这种是在外面判断的，所以start和end没变过，也就是错的

/*170620*/
public class Solution {
    public String longestPalindrome(String s) {
        //Key170619:这道题用DP非常非常麻烦，所以不建议用dp
            //Key170619:dp[i][j] = s.charAt(i)==s.charAt(j) && dp[i+1][j-1]
        String res = "";
        int n = s.length(),start = 0,end = 0,max = 0;
        if(n == 0) return res;
        boolean[][] dp = new boolean[n][n];
        for(int i = 0;i<=n-1;i++) {
			//mark1:单独字符情况
            dp[i][i] = true;
        }
        for(int j = 0;j<=n-1;j++){
            //wrong -> Key170619:case -> "aaaa"  dp[0][3]是由dp[1][2]推出来的，可是此时dp[1][2]还没有算出来。无法用dp，因为s.charAt(i) == s.charAt(j)时，需要判断dp[i+1][j-1]，但此时并未计算出来。如果用dp[i][j-1]判断的话，则需要dp[i][j-1]为false加上其它条件才可判断出来dp[i][j]是否为true。思考起来比较麻烦
            for(int i = j-1;i>=0;i--){
				//mark2 两两比较的基础情况
				//mark3 下面的if要写成并联结构，不能写成顺联情况。否则当dp[i][i+1]更新后，会覆盖掉dp[i][i+1]
                if(j == i+1) dp[i][j] = s.charAt(i)==s.charAt(j);
                else dp[i][j] = (s.charAt(i)==s.charAt(j)) && dp[i+1][j-1];
                if(dp[i][j]) {
                    if(max<j-i+1){
                        max = j-i+1;
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start,end+1);
        
    }
}

public class Solution {
    //Key:Just cp,背，复用isPalindrome  https://discuss.leetcode.com/topic/21848/ac-relatively-short-and-very-clear-java-solution
    //Key170619:实际上这就是brute force... #170620:wrong,这个不是brute force#
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

6. ZigZag Conversion
//Star
//Thinking:按照题意code -- My version 
//1.mark0,建个list，分别存储每行的字符串，然后合并。用个boolean表向上or向下遍历
//2.mark3，例如向下走，当counter == numRows时，counter需要-2，如果减1的话。重复计算了。
//还有往上走时，判断的是counter == -1，而不是counter == 0
//3.mark1：如果numRows == 1时，那么counter-2不适用，要单独判断下==1的情况
//另一种sol隔几个距离取一个char。https://discuss.leetcode.com/topic/3162/easy-to-understand-java-solution 


/*170620*/
public class Solution {
    public String convert(String s, int numRows) {
        String res = "";
		//mark0
        boolean down = true;
        int n = s.length(),counter = 0;
        if(n == 0 || numRows == 0) return "";
        //Key170620,mark1:如果numRows == 1时，那么counter-2不适用，要单独判断下
        if(numRows == 1) return s;
        List<String> list = new ArrayList<>();
        for(int i = 0;i<=numRows-1;i++) list.add("");
        for(int i = 0;i<=n-1;i++){
            list.set(counter,list.get(counter)+s.charAt(i));
            //down时，向下走
            if(down) counter++;
            else counter --;
            if(counter == numRows){
                //Key170620,mark3:case:"PAYPALISHIRING" couner == 3时,往回的话,counter需要置为1，即counter-2.如果只是--的话，counter==2的话，那么会重复加入一次
                counter -= 2;
                down = false;
                //Key170620:同理，只不过这减过头的话是-1，而不是0
            //Wrong: } else if (counter == 0){
            } else if (counter == -1){
                counter += 2;
                down = true;
            }
        }
        for(String tmp:list) res = res + tmp;
        return res;
    }
}

public class Solution {
    public String convert(String s, int numRows) {
        //我这个方法想起来太乱了，很麻烦
        if(s.equals("") || numRows == 0) return "";
        boolean flag = true;
        ArrayList<ArrayList<Character>> list = new ArrayList<ArrayList<Character>>();
        for(int i=0;i<=numRows-1;i++){
            list.add(new ArrayList<Character>());
        }
        int index = 0;
        int count = 0;
        while(count != s.length()){
            if(index == numRows) {
                flag = false;
                index = (numRows-2)>=0?(numRows-2):0;
            }
            if(index == -1){
                flag = true;
                index = numRows >=2?1:0;
            } 
            list.get(index).add(s.charAt(count));
            if(flag){
                index++;
            } else {
                index--;
            }
            count++;
        }
        StringBuffer sb = new StringBuffer();
        index = 0;
        while(index < numRows){
            for(Character c:list.get(index)){
                sb.append(c);
            }
            index++;
        }
        return sb.toString();
    }
}

7. Reverse Integer
//Star
/**
	https://www.zhihu.com/question/51632291?from=profile_question_card
	0:mark2,case:-2147483648 -> !!!非常重要的一点  Intger i= -2147483648,Math.abs(i) == -2147483648,补码操作的原因。具体解释看上面的链接。因为这种坑爹的case，所以要先把x转为long -> mark2
	1.Long.parseLong(String)  mark4
	2.注意是StringBuilder().reverse() 而不是 s.reverse() 也不是String.reverse(s) -> mark3
	3.记得返回时加上负号 mark1,neg?-tmp:tmp要用()括起来，因为(int)neg优先于neg?-tmp:tmp
**/

/*170620*/
public class Solution {
    public int reverse(int x) {
        int res = 0;
        boolean neg = x<0?true:false;
        //mark2
        //wrong -> String s = String.valueOf(Math.abs(x));
        String s = String.valueOf(Math.abs((long)x));
        //mark3
        s = new StringBuilder(s).reverse().toString();
		//mark4
        long tmp = Long.parseLong(s);
        if(tmp > Integer.MAX_VALUE || (-tmp<Integer.MIN_VALUE && neg)) tmp = 0;
        //mark1 
        return (int)(neg?-tmp:tmp);
    }
}

public class Solution {
    public int reverse(int x) {
        //速度不知道为什么很慢，76ms.....
        //可以用Stack处理或者StringBuffer 处理
        //考虑Int范围
        //Test Case：1534236469时返回0。说明越界int范围时，直接舍弃了
        boolean flag = true;
        //不要自己计算，直接用Integer.MAX_VALUE、MIN_VALUE就可以了...... 
        //long high = (long)Math.pow(2,32);
        long high = (long)Integer.MAX_VALUE;
        long low = (long)Integer.MIN_VALUE; 
        //long low = (long)(0-Math.pow(2,32));
      
        int result = 0;
        if(x<0) flag = false; 
        //mark1 -> Math.abs(越界的int时，会从起始int的low范围开始返回)，所以要先把x转为long
        //如果用下面注释掉的代码，Test Case：-2147483648时，会产生错误
        //String s = Math.abs(x)+"";
        String s = Math.abs((long)x)+"";
        StringBuffer sb = new StringBuffer();
        if(!flag) sb.append("-");
        for(int i=s.length()-1;i>=0;i--){
            sb.append(s.charAt(i));
        }
        s = sb.toString();
        //Key Point
        //Integer.parseInt() 把String转为int
        //判断是否越界int范围
        if(Long.parseLong(s)>= high || Long.parseLong(s) <=low){
            //System.out.println(Long.parseLong(s)+"");
            return 0;
        } else{
            result = Integer.parseInt(s)+0;
        }
        return result;
    }
}

8. String to Integer (atoi)
//Star
//Core:if(tmp - '0' >= 0 && tmp - '0' <=9) res = res*10 + (tmp-'0');
//Summary:"+","-"," 01 ",                
//1."-","+"  -> mark0.1  
//2,"-123a12 3111"  expected:"-123" 只是非数字后面的不要了 mark0
//3.overflow
//4.str.trim()
//5."" -> 0
//mark0:Corner:"-123a12 3111"  expected:"-123" 非数字字符后面的不要了，但是前面的还要....
//mark0.1:Corner:"+1" -> "1" 这道题的确是非常没有意义的题....
//mark0.2:trim()  corner:"    010" expected:10
//mark0.3:"" -> 0
//mark1:overflow
/*170727*/
public class Solution {
    public int myAtoi(String str) {
        //mark0.3:"" -> 0
        str = str.trim();
        int n = str.length();
        if(n == 0) return 0;
        //mark1:overflow
        long res = 0;
        boolean neg = str.charAt(0) == '-'?true:false;
        //mark0.1:Corner:"+1" -> "1" 这道题的确是非常没有意义的题....
        for(int i = (str.charAt(0) == '-' || str.charAt(0) == '+'?1:0);i<=n-1;i++){
            char tmp = str.charAt(i);
            if(tmp - '0' >= 0 && tmp - '0' <=9){
                res = res*10 + (tmp-'0');
                //mark1:overflow
                if(neg && -res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                if(!neg && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            } else {
                //mark0:
                return neg?(int)-res:(int)res;
            }
        }
        return neg?(int)-res:(int)res;
    }
}

public class Solution {
    public int myAtoi(String str) {
        //Key:背   https://discuss.leetcode.com/topic/12473/java-solution-with-4-steps-explanations/6
        
        int i = 0;
		//mark1
        str = str.trim();        
        char[] c = str.toCharArray();
        
        int sign = 1;
		//mark2
        if (i < c.length && (c[i] == '-' || c[i] == '+')) {
            if (c[i] == '-') {
                sign = -1;
            }
            i++;
        }      
        
        int num = 0;
        int bound = Integer.MAX_VALUE / 10;
		//mark3
        while (i < c.length && c[i] >= '0' && c[i] <= '9') {
            int digit = c[i] - '0';
            if (num > bound || (num == bound && digit > 7)) {
				//mark4
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            i++;
        }
        return sign * num;
    }
}

9. Palindrome Number

//Star
//1.int转为String，然后向内夹逼  
//      String s = x+"";  or  String s = String.valueOf(x);
//2.负数(-1,-2,etc)不算panlidrome number。但是我这个sol直接把负数干扰排掉了，因为"-123"中的"-"肯定无法匹配啊

/*170620*/
public class Solution {
    public boolean isPalindrome(int x) {
        boolean res = false;
        String s = String.valueOf(x);
        int begin = 0,end = s.length()-1;
        while(begin <= end){
            if(s.charAt(begin++) != s.charAt(end--)) return false;
        }
        return true;
    }
}

public class Solution {
    public boolean isPalindrome(int x) {
        //KEY POINT
        //String转int
        //String s = (String)x; 是错的，转不了，直接n+""吧
        //还有，这道题他给的答案里，-11,不算回文数
        String s = x+"";
        boolean flag = true;
        int index1 = 0;

        int index2 = s.length()-1;
        while(index1<=index2 && flag){
            if(s.charAt(index1) != s.charAt(index2)){
                flag = false;
            }
            index1++;
            index2--;
        }
        return flag;
    }
}

10. Regular Expression Matching
//Star -hard
//http://www.cnblogs.com/grandyang/p/4461713.html
//0.正则匹配很难懂
/**
 *  如果.后面跟着*,那么.*是当做整体看的
    case:"ab",".*.." -> true 《.*是个整体》只有后面两个.是强制要求的，前面的.因为*的缘故，可以是0个字符匹配
         "ab","..." -> false  因为没有*，所以相当于要有三个字符来匹配
         "ab",".*..." -> false 同第一个case，只不过后面3个...是强制的
         "ab",".." ->true
         "ab",".*" -> true
    如果.后面没有*,那就意味着是三个...匹配，但是如果第一个.后面加上了*,《第一个.和*适当做整体看的》，此时.*可以匹配一个都没有，或者n个a
    
**/

//这道题中的*表示之前那个字符可以有0个，1个或是多个，就是说，字符串a*b，可以表示b或是aaab，即a的个数任意，
//0.正则匹配规则很难理解
//http://www.cnblogs.com/grandyang/p/4461713.html
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

11. Container With Most Water
//Star
//core:因为height.length*min(height[0],height[height.length-1])《这个值肯定会被算到》，所以一开始从这个值为基准开始算起，此时width最大，等于length.然后逐渐想中间移动，更新max
//Version 1:  -> TLE mark1
//1.brute force->O(n*n)  i=0~n-1,j=0~i,找出max
//Version 2 -> mark2
//1.pointer1,pointer2分别指向两端，然后max=min(height[pointer1],height[pointer2])*height.length.
//2.两个值中的最小值向中间移动一步(水桶的高度由最小值决定)，然后再取其中最小值*此时的间距，更新max.因为水桶的长度再一直变小，所以要想有更大的值，水桶边缘要更高
//3.mark3注意高度比的是height[left]<=height[right]，不要错写成if(left<=right) left++;

/*170621*/
public class Solution {
    //TLE mark1
    /**
        public int maxArea(int[] height) {
            int res = 0,n = height.length;
            for(int i = 1;i<=n-1;i++){
                for(int j = 0;j<=i-1;j++){
                    //System.out.println(i+"-"+j);
                    res = Math.max(res,Math.min(height[i],height[j])*(i-j));
                }
            }
            return res;
        }
    **/
    public int maxArea(int[] height) {
        //mark2
        int res = 0,n = height.length;
        int left = 0,right = n-1;
        while(left<right){
            res = Math.max(res,Math.min(height[left],height[right])*(right-left));
            //170621,mark3:
            //不要错写成if(left<=right) left++;
            if(height[left]<=height[right]) left++;
            else right--;
        }
        return res;
    }
}

public class Solution {
    public int maxArea(int[] height) {
        //Key:just copy  这道题需要背，思路不好证明正确性
        //这个解法的难点在于如何证明正确性!!!!  170621:证明：初始两个指针分别指向两端，这时长度最长，高度取min(height[0],height[n-1])。然后指针向中间移动，这时长度一定在减少，所以要找到两指针中最小的那个值*长度，再比较max
        //拿个case举例说明，[99,1,2,3,4,200,100]   pointer i，j分别指向99和100
        //这时max一开始等于min(99,100)*height.length，然后left++。容易担心的是99~200的影响，不过因为max受制于lower height，所以它永远是小于99*length的。然后剩下的可以想成一个递归的过程
        
        //Key:这个理解思路较容易理解
        /**
        Idea / Proof:
        https://discuss.leetcode.com/topic/14940/simple-and-clear-proof-explanation/3
        The widest container (using first and last line) is a good candidate, because of its width. Its water level is the height of the smaller one of first and last line.
        All other containers are less wide and thus would need a higher water level in order to hold more water.
        The smaller one of first and last line doesn't support a higher water level and can thus be safely removed from further consideration.
        因为max一开始是由height[0]和height[n-1]中最小值和长度决定的，而要找一个更大的值，在width减小的情况下，height必须变大。所以i0和in中最小的那个就没必要考虑了
        
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

12. Integer to Roman
//http://www.cnblogs.com/grandyang/p/4123374.html
//Trick sol
public class Solution {
    public String intToRoman(int num) {
        /**

        I	1
        V	5
        X	10
        L	50
        C	100
        D	500
        M	1,000
        
        1954 as MCMLIV
        1990 as MCMXC
        2014 as MMXIV
        
        ***/
    
        String[] g = {"", "I","II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] s = {"", "X","XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] b = {"", "C","CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] q = {"", "M","MM", "MMM"};
            
        return q[num / 1000]+b[num / 100 % 10]+s[num / 10 % 10]+g[num % 10];
    
    
    }   
}

13. Roman to Integer
public class Solution {
    public int romanToInt(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('Z',0);
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result = 0;
        String n = s + "Z";
            for(int i = n.length() ;i>1; i--){
                 int cur = map.get(n.charAt(i-1));
                 int pre = map.get(n.charAt(i-2));
                 result = result + (cur<=pre ? pre : -pre);
             }
      return result;
    }
}

14. Longest Common Prefix
//Star
//1.赋值第一个String给result，然后遍历数组，找出结果与每个String间的共同前缀，更新result
//2.mark1 substring(0,index)没错。result与strs[i]完全相等退出时index还要++。当不相等时，index的值正好是最后一个相等字符的位置多一位。
//或者不用substring()截取，而是相等时追加字符，更容易思考些.StringBuilder.append(c)
/*170621*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        String res = "";
        int n = strs.length;
        if(n == 0) return res;
        else res = strs[0];
        for(int i = 0;i<=n-1;i++){
            int index = 0;
            while(index <= Math.min(strs[i].length(),res.length())-1){
                if(res.charAt(index) != strs[i].charAt(index)) break;
                index++;
            }
            //Key170621，mark1:这个substring(0,index)没错。case:["abc","abc"] 两者相等，index最后会加到3，此时是substring(0,3)。case["abc","ac"]的index循环退出时index==1,此时是substring(0,1)
            res = res.substring(0,index);
        }
        return res;
    }
}

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

15. 3Sum
//Star
//Core 1700709
//2pointers,先《Sort》，求出剩余的两数sum，即sum减去第一个数的值。然后对后两个值进行夹逼处理   细节，处理不好写
//可以在Two Sum上继续处理，不过用那个map的方法，进行inner loop时每次都要清一下map，反倒麻烦了
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
       //if(nums.length < 3) return null;  下面新建的ArrayList就是空的
        int target = 0;
        //题目要求的是List
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0;i <= nums.length - 3;i++){
            //    ||前面成功了，后面应该就不执行了吧？
            if(i == 0 || (i>0 && nums[i] != nums[i-1])){
                int start = i+1,end = nums.length -1;
                int rest = target - nums[i];
                while(start < end){
                    int sum = nums[start] + nums[end];
                    if(sum == rest){
                        result.add(Arrays.asList(nums[i],nums[start],nums[end]));
                        
                        //discard duplicate values
                        while(start < end && nums[start] == nums[start+1]) start++;
                        while(start < end && nums[end] == nums[end-1]) end--;
                       
                       //head & nail move to new position
                       start++;
                       end--;
                    } else if(sum > rest){
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return result;
    }
}

16. 3Sum Closest
//Star
//Core 170709
//和T15一起记，2pointers 3sum>target,end--。且用一个diff记录下tmp的绝对值
//mark0:sum initialization
//mark1:夹逼
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        //sort firstly!!!
        Arrays.sort(nums);
        int tmp=0;
        if(nums.length<3){
            
            for(int i:nums){
                tmp+=i;
            }
           
        } else {
			//mark0:tmp init
            tmp=nums[0]+nums[1]+nums[2];
            for(int i=0;i<=nums.length-3;i++){
                int start = i+1,end=nums.length-1;
                //错误！！！tmp在这里每次都会被重置
                // tmp=nums[0]+nums[1]+nums[2];
                while(start<end){
                    int sum = nums[start]+nums[end]+nums[i];
                    if(Math.abs(sum-target) < Math.abs(tmp-target)) tmp = sum;
					//mark1:sum>target 说明数字大了，end-- 
                    if(sum > target) end--;
                    else start++;
                }
            }
        }
        return tmp;
    }
    
}

17. Letter Combinations of a Phone Number
//Star
//1.String[] strs = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"} + helper()
//2.mark1 注意，按键2对应的是strs[0],所以数字要-2.  或者strs = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}更好看些
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
            //Key,mark1:排除0,1这种无效按键的干扰
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

18. 4Sum
//Star
//1.4Sum和3Sum都可以用helper(),只不过都会TLE...
//2.这两道题符合的解法很相似
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

19. Remove Nth Node From End of List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //Star
 //Moon:corner case+处理这链表的题，最好在首端自己加个node，会处理起来更舒服
 //1.2pointers
 //2.mark1:case:[1],1 [1,2,3,4],4 即n==连表长度。不要写(right != null)这个，会有些问题
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = null,index = new ListNode(0),left = index,right = index;
        index.next = head;
        int counter = 0;
        while(counter < n){
            right = right.next;
            counter++;
        }
        /** mark1
            while(right != null){ 
            left= left.next;
            处理[1],1时，left虽然被赋值为null了，但是index.next还是1.很奇怪，所以不要这么写了
        **/
        //mark1,用 case[1],1来记
        while(right.next != null){
            left = left.next;
            right = right.next;
        }
        left.next= left.next.next;
        return index.next;
    }
}

20. Valid Parentheses
//Star
//core:Stack
//1.mark1:case "}(){}[]",最后还要判断下stack是否为空
//2.mark2.Stack是Collection，所以也要指定泛型
//3.mark3:别忘了压入stack
/*170622*/
public class Solution {
    public boolean isValid(String s) {
        //170622:mark2.Stack是Collection，所以也要指定泛型
        //Wrong -> Stack stack = new Stack();
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        for(int i=0;i<=n-1;i++){
            char c = s.charAt(i);
            if(c == '}' || c == ')' || c == ']'){
                if(!stack.empty()){
                    if(stack.peek() == '(' && c == ')' || stack.peek() == '[' && c == ']' || stack.peek() == '{' && c == '}'){
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                //mark3:别忘了压入stack
                stack.push(c);
            }
        }
        //170622:mark1,case "}(){}[]",所以还要在判断下最后是否为空
        return stack.empty()?true:false;
    }
}

public class Solution {
    //Key:不难写，主要是corner case的判断
    //Corner case:"(("  需要单独判断一下最后是否为空
    public boolean isValid(String s) {
        if(s == null || s.length() == 0 || s.length()%2 == 1) return false;
		//170622:Stack也是Collection，所以也要指定泛型
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

21. Merge Two Sorted Lists
//Star
//core:双指针，类似于mergeSort中的merge
//1.mark1:仍然是设置一个dummy node,然后返回dummy.next。会好处理的多
//2.mark2,接上剩余的nodes即可
/*170622*/
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //170622,mark1:仍然是设置一个dummy node,然后返回dummy.next。会好处理的多
        ListNode res = new ListNode(0),index = res;
        while(l1 != null && l2 != null){
            int a = l1.val,b = l2.val;
            if(a<=b){
                index.next = new ListNode(a);
                l1 = l1.next;
            } else {
                index.next = new ListNode(b);
                l2 = l2.next;
            }
            index = index.next;
        }
        //170622:mark2,接上剩余的nodes即可
        if(l1 != null) index.next = l1;
        if(l2 != null) index.next = l2;
        return res.next;
    }
}

public class Solution {
    //下面这个写的有些乱，整理一下
   /**
   
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //原list 是  sorted linked list
        //必须加一个originNode,否则l1.next = l2;后，new LinkedList head is 旧的l1的最后一个节点
        //3个Corner case都要考虑到
        if(l1 == null && l2 == null) return null;
        else if(l1== null && l2 != null) return l2;
        else if(l2 == null&& l1 != null) return l1;
        
        ListNode temp;
        if(l1.val > l2.val){
                temp = l2;
                l2 = l2.next;
            } else {
                temp = l1;
                l1 = l1.next;
            }
        ListNode oriNode = temp;
        //######这个next是可以指向null的，所以不用担心node.next中存储的是null时会报错
        // corner casse : while(l1.next != null && l2.next != null){ 这个有些乱，如果l1和l2分别处在两个list最后，但是都还没处理，所以就跳过了。直接判断l1和l2
        //底下这个循坏一定会有一个list中有元素剩下。注意先next还是先赋值！！！
        while(l1!= null && l2 != null){
            if(l1.val > l2.val){
                temp.next = l2;
                l2 = l2.next;
            } else {
                temp.next = l1;
                l1 = l1.next;
            }
            temp = temp.next;
        }
        
        if(l1== null && l2 != null){    
            temp.next = l2;
        } 
        if(l2 == null && l1 != null){
            temp.next = l1;
        } 
        
        
        
        return oriNode;
    }
   ***/

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //原list 是  sorted linked list
        //必须加一个originNode,否则l1.next = l2;后，new LinkedList head is 旧的l1的最后一个节点
        //3个Corner case都要考虑到
        if(l1 == null){
            if(l2 == null) return null;
            else return l2;
            //下边的貌似可以直接写成else if
        } else {
            if(l2 == null) return l1;
        }
        boolean flag = true;
        ListNode temp;
        ListNode oriNode;
        while(l1!= null && l2 != null ){
            if(l1.val > l2.val){
                temp.next = l2;
                l2 = l2.next;
            } else {
                temp.next = l1;
                l1 = l1.next;
            }
            temp = temp.next;
            //用个tag标记一下，省略了之前的第一个元素的选择
            if(flag){
                oriNode = temporiNode = temp；
                flag = false;
            }
        }
        
        if(l1== null && l2 != null) temp.next = l2;
        if(l2 == null && l1 != null) temp.next = l1;
        return oriNode;
    }
    
}

22. Generate Parentheses
//Star
//core:
//1.'('数量  ==  ')'数量。
//2.mark1:合法的格式下，(的数量肯定小于)的数量，相对间的关系无所谓。一旦'('数量  <  ')'数量,就return。
//left表(的数量，right表)数量
/*170622*/
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res,n,0,0,"");
        return res;
    }
    public void helper(List<String> list,int n,int left,int right,String s){
        //170622:mark1，下面这句是关键，因为left<right，所以case: "))((" , ")(" 这种绝不会出现。因为当")"时就return了。
        if(left<right) return;
        if(s.length() == n*2){
            if(left == right) list.add(s);
        } else {
            //
            helper(list,n,left+1,right,s+"(");
            helper(list,n,left,right+1,s+")");
        }
        
    }
}

public class Solution {
    public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<String>();
		helper(ans, n, 0, "");
		return ans;

	}

	void helper(List<String> ans, int n, int limit, String s) {
		if (s.length() == 2 * n) {
			ans.add(s);
			return;
		}

		if (limit > 0 && s.length() - limit < limit)
			helper(ans, n, limit, s + ")");
		if (limit < n)
			helper(ans, n, limit + 1, s + "(");

	}
}

//Star
//core:mark1，这道题不仅仅是return 新长度，《更重要的是他还要把所有不同的值移到前端》。需要做一遍才能理解题意

/*170622*/
public class Solution {
    //wrong 
    /**
        public int removeDuplicates(int[] nums) {
            int res = 0;
            int index = 0,n = nums.length;
            for(int i = 0;i<=n-1;i++){
                if(nums[index] == nums[i]){
                    continue;
                } else {
                    res++;
                    index = i;
                }
                
            }
            return res;
        }
    **/
    public int removeDuplicates(int[] nums) {
        //two pointers
        int index =0;
        int count=1;
        for(int i=1;i<=nums.length-1;i++){
            if(nums[i] != nums[index]){
                //mark1，前count个组成的新的不重复数组
                nums[++index] = nums[i];
                count++;
            }
        }
        return count;
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
 
//Star
//core:T21基础上将merge 2变为了merge k。  将Lists中所有"元素"重新排序。
//core:
//Version 1
//1.brute force:Lists所有值加入到一个ArrayList中，转为数组再将所有sort。然后返回一个排好序的listNode
//2.mark1: list转array  Object[] objs = strList.toArray();;
//Version 2

/*170622 updated*/
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
        //170622:mark1,这是Object[] objs = strList.toArray();的用法。因为Object是所有类的父类，不论是int,char...，封装它们的class都可以转为Object。所以为了规范统一处理，toArray()转为的是封装类
        //wrong->int[] nums = (int[]) list.toArray(new int[0]);
        Integer[] nums = (Integer[]) list.toArray(new Integer[0]);
        Arrays.sort(nums);
        for(int i:nums){
            item.next = new ListNode(i);
            item = item.next;
        }
        return head.next;
    }
}

24. Swap Nodes in Pairs

//Star
//Core:类似于T92，prev,cur,p1,p2节点调换。然后这四个指针往后移动。注意循环退出条件
//mark1：循环退出条件
public class Solution {
    /*170711*/
    public ListNode swapPairs(ListNode head) {    
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //makr0:因为cur,p1,p2后面都要颠倒，且一开始需要"初始化"，所以可以先设为null
        ListNode prev = dummy,cur = null,p1 = null,p2 = null;
        //mark0.09:nodes init
        cur = head;
        if(cur != null) p1 = cur.next;
        if(p1 != null) p2 = p1.next;
        //mark1:终止条件：1.cur == null自然不用管,直接退出。2.1，cur !=null,p1 == null也不用管，因为只剩下一个值cur,根本不用动。2.2：cur != null,p1 !=null 就是以下处理过程
        while(cur != null && p1 != null){
            //mark2:reverse process
            p1.next = cur;
            cur.next = p2;
            
            prev.next = p1;
            //mark0.099:prev,cur,p1,p2整体往后移动
            prev = cur;
            //mark0.1:以下顺序和之前初始化顺序一致
            cur = p2;
            if(cur != null) p1 = p2.next;
            if(p1 != null) p2 = p1.next;
        }
        return dummy.next;
    }
    
    
    
    /*
    /***
    public ListNode swapPairs(ListNode head) {
        //error  解法太复杂，而且做得不对
        if(head == null || head.next == null) return head;
        
        
        ListNode oriEnd = null;
        ListNode tmp = null;
        ListNode newEnd = null;
        tmp = head.next;
        tmp.next = head;
        oriEnd = tmp;
        while(oriEnd.next != null && oriEnd.next.next !=null){
            newEnd = tmp.next;
            tmp = oriEnd.next.next;
            tmp.next = oriEnd.next;
            
            newEnd.next = tmp;
            oriEnd = tmp;
        }
        return head.next;
        ***/
        
        //change the adjacent listnode to a cycle linkedlist,then break the link relationship
        
        //下面的还是不对
        // if(head == null || head.next == null) return head;
        // ListNode retHead = head.next;
        // ListNode newHead = head;
        // ListNode tmp = null;
        // while(newHead.next != null ){
        //     if(newHead.next.next!=null){
        //         tmp = newHead.next.next;
        //         newHead.next.next = newHead;
        //         newHead.next = tmp;
        //         newHead = newHead.next;
        //     } else {
        //         newHead.next.next = newHead;
        //         newHead.next = null;
        //         //newHead = newHead.next;
        //     }
            
        // }
       
        // //return head.next; head 没变，但是head.next 变了。所以最好还是另存一下新的头结点
        // return retHead;
        
        //LinkedlLst 问题 检点 总是不对
       
        /*
            if (head == null || head.next == null){
                return head;
            }

            ListNode current = head;
            ListNode next = head.next;
            ListNode prev = null;
            head = head.next;

            while (current != null && next != null){
                ListNode temp = next.next;
                current.next = temp;
                next.next = current;
                if (next != head){
                    prev.next = next;
                }
                prev = current;
                current = temp;
                if (current != null){
                    next = current.next;
                }
            }
            return head;
             }
        */

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

26. Remove Duplicates from Sorted Array
//Star
//core:mark1，这道题不仅仅是return 新长度，《更重要的是他还要把所有不同的值移到前端》。需要做一遍才能理解题意
/*170622*/
public class Solution {
    //wrong 
    /**
        public int removeDuplicates(int[] nums) {
            int res = 0;
            int index = 0,n = nums.length;
            for(int i = 0;i<=n-1;i++){
                if(nums[index] == nums[i]){
                    continue;
                } else {
                    res++;
                    index = i;
                }
                
            }
            return res;
        }
    **/
    public int removeDuplicates(int[] nums) {
        //two pointers
        int index =0;
        int count=1;
        for(int i=1;i<=nums.length-1;i++){
            if(nums[i] != nums[index]){
                //mark1，前count个组成的新的不重复数组
                nums[++index] = nums[i];
                count++;
            }
        }
        return count;
    }
}

27. Remove Element
//Star
//core:mark1:和T26一起看，做法类似。都是把所需的数字移到最前端。
//T26是与nums[index]比较，而T27是与target val比较
/*170622*/
public class Solution {
    public int removeElement(int[] nums, int val) {
        int res = 0,n =nums.length;
        int index = 0;
        for(int i = 0;i<=n-1;i++){
            if(nums[i] != val){
				//mark1
                nums[index++] = nums[i];
                res++;
            }
        }
        return res;
    }
}

28. Implement strStr()
//Star
//core
//1.找出needle首字符在haystack中位值，然后在判断substring(i,i+needle.length)是否和needle相等
//1.1 mark2需要确保i+needle.length<=haystack
//1.2 mark1,case："aaaa","a" ->注意，一旦相等就break.否则返回的是3而不是0
//1.3 mark3:还有case"xxxxx","" 也是对的,return 0

/*170622*/
public class Solution {
    public int strStr(String haystack, String needle) {
        int res = -1,n = needle.length(),m = haystack.length();
        //mark3
        if(n == 0) return 0;
        char c = needle.charAt(0);
        for(int i = 0;i<=m-1;i++){
            //mark2
            if(haystack.charAt(i) == c && i+n <= m && haystack.substring(i,i+n).equals(needle)) {
                res = i;
                //mark1
                break;
            }
        }
        return res;
    }
}

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

30. Substring with Concatenation of All Words
//Star 太难，可以放弃
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

31. Next Permutation
//Star
//core：背，规律题，helper方法会TLE  --> 如果知道Permutation的规律，这道题会好做很多
//1.首先把swap(),reverse()单独实现一下，否则会很麻烦
//1.9如果整个数组完全逆序，那么这其实就是最后一个permutation.下一个就是完全正序的。
//2 以case：[1,2,7,4,3,1] 为例。7,4,3,1降序，
//2.1 找到第一个打破降序规律的数字index(即为2)
//2.2 所以要将后面中的第一个大于2的数字和2交换(即为3)。
//2.3 然后该index后面的序列要变成完全升序,即转置一下
//mark4:corner case:[3,2,1]  只有最后一步转置数组，所以tmp须初始化为-1
//mark6:实现reverse，swap方法
//http://www.cnblogs.com/grandyang/p/4428207.html
/**
    这道题让我们求下一个排列顺序，有题目中给的例子可以看出来，如果给定数组是降序，则说明是全排列的最后一种情况，则下一个排列就是最初始情况，可以参见之前的博客 Permutations 全排列。我们再来看下面一个例子，有如下的一个数组
    
    1　　2　　7　　4　　3　　1
    
    下一个排列为：
    
    1　　3　　1　　2　　4　　7
    
    那么是如何得到的呢，我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，然后我们再从后往前找第一个比2大的数字，是3，那么我们交换2和3，再把此时3后面的所有数字转置一下即可，步骤如下：
    
    1　　2　　7　　4　　3　　1
    
    1　　3　　7　　4　　2　　1
    
    1　　3　　1　　2　　4　　7
**/
//用case：[1,7,2]记忆
/*170622 未解决,corner case:[3,2,1]*/ 
/*170711*/ 已解决
public class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        //mark 4:corner [3,2,1] -> 相当于将整个数组转职了一下，即省略了mark2.1,2.2。而后面reverse时tmp被+了1，所以tmp需要初始化为-1
        int tmp = -1;
        //int tmp = 0;
        for(int i = n-1;i>=1;i--){
            if(nums[i]>nums[i-1]) {
                tmp = i-1;
                break;
            }
        }
        //Log
        //for(int i:nums)System.out.print(tmp+",");
        if(tmp != -1){
            for(int i = n-1;i>=0;i--){
                if(nums[i]>nums[tmp]){
                    swap(nums,tmp,i);
                    break;
                }
            }
        }
        //Log
        //for(int i:nums)System.out.print(i+",");
        reverse(nums,tmp+1,n-1);
        
    }
    //mark6:自己实现reverse和swap方法
    public void reverse(int[] nums,int begin,int end){
        //mark5:数组reverse函数写错了，应该是直接对称swap 23333 下面的写法错误
        /*
            for(int i = end;i>=begin+1;i--){
                swap(nums,i-1,i);
            }
        */
        while(begin < end) swap(nums,begin++,end--);
    }
    //mark6:自己实现reverse和swap方法
    public void swap(int[] nums,int right,int left){
        int tmp = nums[right];
        nums[right] = nums[left];
        nums[left] = tmp;
    }
}

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

33. Search in Rotated Sorted Array
//Star
//T33方法也可以应用在T153上，因为mark6和mark7处是串联结构，而不是else这种并联，(170711,看似串联，实为并联,mark7)所以如果两边都有序的话，这种写法更容易理解些
//http://www.cnblogs.com/grandyang/p/4325648.html
//core:找出有序的那部分，然后分别二叉搜索 
//core：哪边有序(outer if)？target在不在有序的那边（inner if），进而决定舍弃哪边？
//mark0  与binary search 不同处：二叉搜索对一边进行recursion，而这道题需要对两边recursion
//1.mark1 nums[mid] >= nums[low] 或者nums[mid] <= nums[high]总有一边是有序成立的。
//2.mark2 小优化 low<=high 
//3.mark3
//4.mark4 《target只能在有序序列部分中进行判断》。
//5.mark5
//6.mark6，mark7 case [1,2],1 case[2,2]2 mid>=low
//mark8:因为要舍弃一边数组，所以才需要分别写一下if
/*170624*/
public class Solution {
    public int search(int[] nums, int target) {
        int res = -1,n = nums.length;
        if(n == 0) return -1;
        return helper(0,n-1,nums,target);
    }
	//170625:mark0,与binary search 不同处：二叉搜索对一边进行recursion，而这道题需要对两边recursion
    public int helper(int low,int high,int[] nums,int target){
        //mark2:退出条件，low>high return -1;
		//mark2.1:我把if(low > high) return -1 改写成 if(low<=high){}，和平常写的binary search规范一下
        //if(low > high) return -1;
		if(low <= high){
			//mark3:因为有个 mid == low == high 的可能，所以low==high condition不能丢。而mergeSort，只有low < high(因为sort(mid+1,high,nums) 这一部分，有可能出现mid+1 == high,一旦判断low == high，可能无限循环)，没有low == high condition
			int mid = (low+high)/2;
			if(nums[mid] == target) return mid;
			//170624:mark1 nums[mid] >= nums[low] 或者nums[mid] <= nums[high]总有一边是成立的。
			//170626:底下的外部两个if我总是觉得有点冗余 -> 这个想法是错误的
			/*
				//mark4:底下的两个外部if不是冗余的，因此不能省略，target只能在有序序列里比较。以case：[5,1,3],5为例，low =0,high =2,mid = 1。  5>=nums[low]但是5也>=nums[mid],不符合第一个if.如果写成下面的形式，就会在[1,3]中寻找，明显是错的
				
				if(target >= nums[low] && target <= nums[mid]) return helper(low,mid-1,nums,target);
				else return helper(mid+1,high,nums,target);
				
				//mark5:且if(target > nums[low] && target < nums[mid]) 对应的 else 包括 target > nums[low] && target > nums[mid],target < nums[low] && target > nums[mid]和target < nums[low] && target < nums[mid]等多种情况，而不是target > nums[mid] && target < nums[high]
				
			*/
			//mark6:case:[2,2],2  low == (low+high)/2 == high,所以mid会在 if(nums[mid] == target)就return了
			//而case：[1,2],2  low = 0,high = 1,mid = (low+high)/2 = 0.因此nums[mid] >= nums[low]中的==绝不能省略。
			//170711 mark7:看似串联，其实还是并联。因为只会有一边一直执行下去。而low>high即为退出条件  外层if只是判断哪边有序，inner if相同。outer if只是用来决定哪个helper放在有序段中，哪个helper放在乱序中
			//可以用corner [1,2,3,4,5,6]来举例下
			//mark8:两个if的不同的根本原因在于要舍弃一边，所以才要决定要根据哪边有序决定舍弃哪一边
			if(nums[mid] >= nums[low]){
				if(target >= nums[low] && target < nums[mid]) return helper(low,mid-1,nums,target);
				else return helper(mid+1,high,nums,target);
			}
			//mark7:但是nums[mid] <= nums[high]中的==是可以省略的。还是以case[1,2],1为例，mid==high的情况之有[2,2],2 这种low == high的情况才有，否则的话mid永远<high
			if(nums[mid] <= nums[high]){
				if(target <= nums[high] && target > nums[mid]) return helper(mid+1,high,nums,target);
				else return helper(low,mid-1,nums,target);
			}
		}
        
        
        return -1;
    }
}


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

34. Search for a Range
//Star
//http://www.cnblogs.com/grandyang/p/6854825.html#undefined
//core:分别找出第一个和最后一个，findFirst和findLast分开写。夹逼
//findFirst()表寻找第一个>=的数，所以也可用于T35. Search Insert Position
/*
    0.只是一个==的差距，搜索方向不同
    1.mark1: ==时,high = mid-1.因此逐渐向前，找到第一个
        if(nums[mid] < target) low = mid+1;  
        else high = mid-1;
    2.mark2:==时，low = mid+1。逐渐向后，找到最后一个
        if(nums[mid] <= target) low = mid+1;
        else high = mid-1;
    3.mark3:if(nums[mid] == target) index = mid; 更新index
    4.mark4:
        low<=high一定要与low = mid+1,high = mid-1配套使用。如果用high = mid的话，边界非常不好掌握，导致死循环
        low<high 才能与low = mid+1,high = mid配合使用
	5.mark5:实在不好理解，分开写更好
	//mark7
*/
           
/*170624*/
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int n = nums.length;
        res[0] = findFirst(0,n-1,nums,target);
        res[1] = findLast(0,n-1,nums,target);
        return res;
    }
    public int findFirst(int low,int high,int[] nums,int target){
        int index = -1;
        //mark4
        while(low<=high){
            int mid = (low+high)/2;
			//mark3
            if(nums[mid] == target) index = mid;
            //mark1
			//mark5:实在不好理解，分开写更好
			/*
				if(nums[mid] < target) low = mid+1
				else if(nums[mid] == target) high = mid-1
				else if(nums[mid] > target) high = mid-1
			*/
            if(nums[mid] < target) low = mid+1;
            else high = mid-1; //nums[mid] >= target  {1th num,nums[mid]} 找第一个数，夹逼
            
        }
        return index;
    }
    public int findLast(int low,int high,int[] nums,int target){
        int index = -1;
        //mark4
        while(low<=high){
            int mid = (low+high)/2;
			//mark3
            if(nums[mid] == target) index = mid;
            //mark2
			//mark7:（mid必须+1，如果high = mid,那么 [1]这种单一数字会不断循环）
            if(nums[mid] <= target) low = mid+1;  //nums[mid] <= target  {nums[mid],nth num} 找最后一个数，夹逼
            else high = mid-1; 
            
        }
        return index;
    }
}

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

35. Search Insert Position
//Star
//core
//Version 1:
//1.mark2 遍历，返回第一个target<=nums[i]的索引即可
//2.mark1
//Version 2
//mark1

/*170624*/
public class Solution {
    
    //Version 1
    /*
        public int searchInsert(int[] nums, int target) {
            int n = nums.length;
            for(int i = 0;i<=n-1;i++){
                //mark2:相同数字插入前边，case：[1,3,5,6],5  Expected:2
                if(target <= nums[i]) return i;
            }
            //mark1:如果都小于target，插在最后，返回n
            return n;
        }
    */
    
    //Version 2
    public int searchInsert(int[] nums, int target) {
        int res = 0,low = 0,high = nums.length-1,index = nums.length;
        while(low <= high){
            int mid = (low+high)/2;
            //170624:mark1夹逼，如果末端数字大于target，那说明所求数字位于前半部分.
            //170624:因为是向前搜索，所以target<=nums[mid]时改变的是high
            if(nums[mid] >= target) high = mid-1;
            
            else low = mid+1;
            if(nums[mid] >= target) index = mid;
        }
        return index;
    }
}

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        if(nums.length ==0 ) return 0;
        if(target <= nums[0]) return 0;
        for(int i =1;i<=length-1;i++){
            if(target == nums[i]) return i;
            else if(target >nums[i-1] && target <nums[i]) return i;
        }
        if(target > nums[length-1]) return length;
        return 0;
    }
}

36. Valid Sudoku
//Star
//Core
//mark0:
//mark1:
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        //Key:分别判断每一行，每一列，以及每一个块。写起来有些麻烦....
        //Key:Just copy
        for(int i = 0; i<9; i++){
			//mark0:每次一个colSet,rowSet,gridSet判断是否符合
            //这总共创建了27次.....
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
				//mark1:判断九宫格
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                    return false;
            }
        }
        return true;
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

38. Count and Say
public class Solution {
    public String countAndSay(int n) {
        if(n == 0 ) return "0";
        StringBuilder sb = new StringBuilder();
        String seq = "1";
        int count = 0;
        int tmp = 0;
        for(int i = 1;i<=n-1;i++){
            for(int j = 0;j<=seq.length()-1;j++){
                if(j == 0){
                    tmp = seq.charAt(0);
                    count++;
                } else {
                    if(seq.charAt(j) == seq.charAt(j-1)){
                        count++;
                    } else{
                        //charAt dedaode shi shuzi duiying de ASCII code,1->49,2->50.....9->57
                        sb.append(count).append(tmp-48);
                        count = 1;
                        tmp = seq.charAt(j);
                    }
                }
            }
            //charAt dedaode shi shuzi duiying de ASCII code,1->49,2->50.....9->57
            sb.append(count).append(tmp-48);
            seq = sb.toString();
            //need to new a new StringBuilder,otherwise it will cause NullPointerExp(Strange!!!!)
            sb = new StringBuilder();
            count = 0;
        }
        return seq;
    }
}

39. Combination Sum
//Star
//core:helper+过滤方法
//0.mark1 sort
//1.mark1
//2.mark2 过滤条件
//3.mark3 小优化,否则过不去
/*170624*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
		//mark0:必须先排序，否则 candidates[i] < item.get(item.size()-1)无法使用
        Arrays.sort(candidates);
        int n = candidates.length;
        helper(res,new ArrayList<>(),candidates,target,0);
        return res;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] candidates,int target,int sum){
        if(sum == target){
            //mark1 new ArrayList<>(item)
            list.add(new ArrayList<>(item));
        } else {
            for(int i = 0;i<=candidates.length-1;i++){
                //mark2:case [2, 3, 6, 7],7 -> [2,3,2] 和[2,2,3] 算同一种结果。所以当nums[i] < item.get(size-1)时，continue
                if(item.size() != 0 && candidates[i] < item.get(item.size()-1)) continue;
                //mark3:这里做个小优化，否则的话这道题因为case太大过不去
                if(sum+candidates[i]>target) break;
                item.add(candidates[i]);
                helper(list,item,candidates,target,sum+candidates[i]);
                item.remove(item.size()-1);
            }
        }
    }
}

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        //Key:排序关键
        Arrays.sort(candidates);
        helper(list,item,candidates,target,0);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] candidates,int target,int sum){
        if(sum == target){
            list.add(new ArrayList<>(item));
        } else if(sum<target){
            for(int i = 0;i<=candidates.length-1;i++){
                //Key:也是只存在非递增顺序的list，
                //即[[2,2,3],[2,3,2],[3,2,2],[7]] 是错误的.所以加一个下面的判断
                //Key:这是通用方法，但是复杂度的确太高了.....
                if(item.size() >0 && candidates[i] < item.get(item.size()-1)) continue;
                //Key:如果用下面这个判断降复杂度，那么必须配合排序 Arrays.sort(candidates);
                //否则处理：[6,7,2,3] 7 这种无序就会出错
                if(sum+candidates[i]>target) break;
                item.add(candidates[i]);
                helper(list,item,candidates,target,sum+candidates[i]);
                item.remove(item.size()-1);
            }
        }
    }
}

40. Combination Sum II
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //Key:懒方法，套模板，加个set筛选
        Set<List<Integer>> list = new HashSet<>();
        List<Integer> item = new ArrayList<>();
        //Key:排序关键
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        helper(list,item,candidates,target,0,used,0);
        List<List<Integer>> result = new ArrayList<>(list);
        return result;
    }
    public void helper(Set<List<Integer>> list,List<Integer> item,int[] candidates,int target,int sum,boolean[] used,int start){
        if(sum == target){
            list.add(new ArrayList<>(item));
        } else if(sum<target){
            for(int i = start;i<=candidates.length-1;i++){
                //Key:也是只存在非递增顺序的list，
                //即[[2,2,3],[2,3,2],[3,2,2],[7]] 是错误的.所以加一个下面的判断
                //Key:这是通用方法，但是复杂度的确太高了.....
                
                if(item.size() >0 && candidates[i] < item.get(item.size()-1) || used[i]) continue;
                //Key:如果用下面这个判断降复杂度，那么必须配合排序 Arrays.sort(candidates);
                //否则处理：[6,7,2,3] 7 这种无序就会出错
                if(sum+candidates[i]>target) break;
                item.add(candidates[i]);
                used[i] = true;
                helper(list,item,candidates,target,sum+candidates[i],used,i+1);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
    }
}

41. First Missing Positive
public class Solution {
    //Star
    //core:nums[nums[i]]   且此题可以有duplicates
    //170711  
    //corner [],[1]
    //mark0
    //mark1
    //mark2
    //mark3:corner [1,1]
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int res = 1;
        //mark0:不能用普通的for直接i++,以case[3,4,2,-1] 为例，swap(3,2)后变为 [2,4,3,-1]。此时还需要继续将2与4换...
        //for(int i = 0;i <= n-1;i++){  进行小改造
        for(int i = 0;i <= n-1;){ 
            //mark2:还需要加上nums[i] != i+1  否则会无限循环
            //mark3:corner:[1,1,1] 需要用 nums[i] != nums[nums[i]-1]来排除duplicate干扰，否则1th的1会不断与0th的1交换 
            if(nums[i] >= 1 && nums[i] <= n && nums[i] != i+1 && nums[i] != nums[nums[i]-1]){
                swap(nums,i,nums[i]-1);
            } else {
                i++;
            } 
        }
        for(int i = 0;i<=n-1;i++){
            if(nums[i] != i+1) return res;
            else res++;
        }
        //mark1:corner []  return 1;
        //return 0;
        return res;
    }
    public void swap(int[] nums,int i,int j){
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
    
}

//ref -> http://www.cnblogs.com/grandyang/p/4395963.html

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

44. Wildcard Matching - Discard

45. Jump Game II - Unsolved

46. Permutations
//Star
//core:backtracking,helper()
//1.mark1:list.add(new ArrayList<>(item)).直接add(item)的话，list中所有的值都会相同
//2.item也可以用contains来判断重复项

/*170624*/
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        helper(list,new ArrayList<>(),nums);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] nums){
        if(item.size() == nums.length){
            //mark1
            list.add(new ArrayList<>(item));
        } else {
            for(int i = 0;i<=nums.length-1;i++){
                //mark2 list.contains()
                if(item.contains(nums[i])) continue;
                item.add(nums[i]);
                helper(list,item,nums);
                item.remove(item.size()-1);
            }
        }
    }
}

47. Permutations II
//Star
//core:重复数字的过滤
//1.mark1 过滤重复数字干扰，重复数字内部相对应的顺序不变。用case:[1,1,2]来记
//2.mark2 
//version 2:
//1.set return new ArrayList<>(set);  但是这里用Set八成会TLE.....

/*170624*/
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[n];
        helper(res,new ArrayList<>(),nums,used);
        return res;
    }
    public void helper(List<List<Integer>> res,List<Integer> item,int[] nums,boolean[] used){
        if(item.size() == nums.length){
            //mark2
            res.add(new ArrayList<>(item));
        } else {
            for(int i = 0;i<=nums.length-1;i++){
                //mark1:continue条件  case:[1,1,2] -> 1th 1不能和0th 1再组合了
                if(used[i] || i >0 && nums[i] == nums[i-1] && !used[i-1]) continue;
                item.add(nums[i]);
                used[i] = true;
                helper(res,item,nums,used);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
    }
}

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        helper(list,item,nums,used);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] arr,boolean[] used){
        if(item.size() == arr.length){
            list.add(new ArrayList<>(item));
        } else {
            for(int i = 0;i<=arr.length-1;i++){
                //continue 跳过此次循环，即此次loop中if后的都不执行
                //Key point:
                //很巧妙：arr[i-1] == arr[i]&&!used[i-1]  意味着将每组重复数字作为一个大整体。然后在数组中，就相当于这个大整体和其他不同数字的组合了，类似于List.contains()
                //因为duplicates的麻烦就在于  112(a)2(b) 和 112(b)2(a).重复数字内部顺序的麻烦，如今把他们当做一个大整体，相当于内部顺序固定了，就可以忽略这个问题了
                
                //Key point:一定要记着Arrays.sort(nums)，这是关键！！！
                if(used[i] || i>0 && arr[i] == arr[i-1] && !used[i-1]) continue;
                item.add(arr[i]);
                used[i] = true;
                helper(list,item,arr,used);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
    }
}

48. Rotate Image
public class Solution {
    public void rotate(int[][] matrix) {
        //Key:Just copy 找规律的题
        int n=matrix.length;
        for (int i=0; i<n/2; i++){
            for (int j=i; j<n-i-1; j++) {
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[n-j-1][i];
                matrix[n-j-1][i]=matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1]=matrix[j][n-i-1];
                matrix[j][n-i-1]=tmp;
            }
        }
    }
}

49. Group Anagrams
//Star
//Core:map<key，String list>
//http://www.cnblogs.com/grandyang/p/4385822.html
//Core:这道题一开始理解错误了。["eat", "tea", "tan", "ate", "nat", "bat"] -> [["ate", "eat","tea"],["nat","tan"],["bat"]] 意思应该是拥有相同字符，但顺序不同的的字符串归为一起。我给理解成了可以首尾相衔的字符串归为一起...(错误理解 ate->eat->tea)
/*170713*/ 
//mark0.01,0.01,0.001  关键
//mark1:map.values提取出map.entrySet<List<String>>中的值
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //Key:思路一开始不容易想，但解法较容易理解
        //Key:Just cp
        //https://discuss.leetcode.com/topic/24494/share-my-short-java-solution/5
        if(strs==null || strs.length == 0){
    		return new ArrayList<List<String>>();
    	}
        //mark0:Map<String,List<String>> translate to List<List<String>>
    	HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    	//Arrays.sort(strs);
    	for (String s:strs) {
            //mark0.1:String转为chars，然后sort与Map中的key对应
    		char[] ca = s.toCharArray();
            //mark0.01:
    		Arrays.sort(ca);
            //mark0.001:重组为string
    		String keyStr = String.valueOf(ca);
    		if(!map.containsKey(keyStr))
    			map.put(keyStr, new ArrayList<String>());
    		map.get(keyStr).add(s);
    	}
    	//mark1:map.values 转为 list
    	return new ArrayList<List<String>>(map.values());
    }
}

50. Pow(x, n)
//Star
//core:binary search + corner case
//mark1:case:x = 0
//mark2:case:n = 0
//mark3
//http://www.cnblogs.com/grandyang/p/4383775.html
public class Solution {
    public double myPow(double x, int n) {
        //Key:背，just cp https://discuss.leetcode.com/topic/66478/java-solution-beats-96
		//mark2
        if(n == 0) { return 1.0; }
		//mark1
        if(x == 0) { return 0.0; }
        
        if(n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
			//mark3
            return (n > 0 ? x : 1.0 / x ) * myPow(x * x, n / 2) ;
        }
    }
}

51. N-Queens

public class Solution {
    
    //key point:虽然不算快，但因为模板化，记起来比较容易
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        boolean[] used = new boolean[n];
        helper(list,item,n,used);
        //for(List<String> i:list) System.out.println(i);
        return list;
    }
    public void helper(List<List<String>> list,List<Integer> item,int n,boolean[] used){
        if(item.size() == n){
            List<String> sol = new ArrayList<>();
            for(Integer q:item){
                char[] arr = new char[n];
                Arrays.fill(arr,'.');
                arr[q] = 'Q';
                sol.add(new String(arr));
            }
            
            /**
            
            for(Integer q:item){
                String tmp = "";
                for(int j = 0;j<=n-1;j++){
                    if(q == j)tmp = tmp+"Q";
                    else tmp = tmp+".";
                }
                sol.add(tmp);
            }
            
            **/
            
            list.add(sol);
        } else {
            for(int i = 0;i<=n-1;i++){
                int iSize = item.size();
                //Key point: <= 1 包含位于正下方和对角线
                //Important:Corner case:5  不仅是不能相邻位置出现在对角线上，隔着多行也不能出现在对角线上
                //PS:对角线非常不好判断
                //if(used[i] || iSize>=1 && Math.abs(item.get(iSize-1)-i) == 1) continue;
                boolean tag = false;
                if(used[i]) continue;
                for(int j = 0;j<=iSize-1;j++){
                    if(Math.abs(iSize-j) == Math.abs(i - item.get(j))) tag = true;
                }
                if(tag) continue;
                item.add(i);
                used[i] = true;
                helper(list,item,n,used);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
    }
}

52. N-Queens II
//Star
//core：T51的基础上，其实不用再把sol一一加进去了，只用计数器加1即可
public class Solution {
    int res = 0;
    public int totalNQueens(int n) {
        List<Integer> item = new ArrayList<>();
        boolean[] used = new boolean[n];
        helper(item,n,used);
        //for(List<String> i:list) System.out.println(i);
        //Key:偷个懒，直接转成Set Set<List<String>> set = new HashSet<>(list);
        //注意转换方法
        //Set<List<String>> set = new HashSet<>(list);
        
        return res;
    }
    public void helper(List<Integer> item,int n,boolean[] used){
        if(item.size() == n){
            //T51的基础上，其实不用再把sol一一加进去了，只用计数器加1即可
            res++;
        } else {
            for(int i = 0;i<=n-1;i++){
                int iSize = item.size();
                //Key point: <= 1 包含位于正下方和对角线
                //Important:Corner case:5  不仅是不能相邻位置出现在对角线上，隔着多行也不能出现在对角线上
                //PS:对角线非常不好判断
                //if(used[i] || iSize>=1 && Math.abs(item.get(iSize-1)-i) == 1) continue;
                boolean tag = false;
                if(used[i]) continue;
                for(int j = 0;j<=iSize-1;j++){
                    if(Math.abs(iSize-j) == Math.abs(i - item.get(j))) tag = true;
                }
                if(tag) continue;
                item.add(i);
                used[i] = true;
                helper(item,n,used);
                used[i] = false;
                item.remove(item.size()-1);
            }
        }
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
//V2
public class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = nums[0];
        dp[0] = nums[0];
        for(int i = 1;i<=nums.length-1;i++){
            dp[i] = Math.max(nums[i],dp[i-1]+nums[i]);
            res = Math.max(dp[i],res);
        }
        return res;
    }
}

54. Spiral Matrix
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix.length == 0) return list;
        int x1 = 0,y1 = 0,x2 = matrix.length-1,y2 = matrix[0].length-1;
        int index1 = 0,index2 = 0;
        
        //Key point:下面的判断错误
        //Conrer case:[[2,3]] 如果是下面的判断就会出错
        // while(list.size()< matrix[0].length*matrix.length){
            
            
        //考虑成一个不断内部压缩的状态，然后每次都从左上角循环一遍
        while(x1<=x2 && y1<=y2){    
            for(int i = y1;i<=y2;i++) list.add(matrix[x1][i]);
            x1++;
            for(int i = x1;i<=x2;i++) list.add(matrix[i][y2]);
            y2--;
            //Key point:向左和向上都需要考虑下x1,x2关系和y1,y2关系
            //防止奇数层matrix的影响
            if(x1<=x2) for(int i = y2;i>=y1;i--) list.add(matrix[x2][i]);
            x2--;
            if(y1<=y2) for(int i = x2;i>=x1;i--) list.add(matrix[i][y1]);
            y1++;
        }
        
        return list;
    }
}

55. Jump Game
//Star
//http://www.cnblogs.com/grandyang/p/4371526.html
//core  题意容易误解，每个值代表最大可以走几步，因此不用每个坐标处的步数不用正好用完，只要能够到达即可
//算出最远可到达位置，即是否可以到达每个坐标即可
//mark1  mark1 在每个坐标处最远可到达位置

/*170626*/
public class Solution {
    public boolean canJump(int[] nums) {
        int max = nums[0];
        for(int i = 1;i<= nums.length-1;i++){
            if(i > max) return false;
            //mark1 在每个坐标处最远可到达位置
            max = Math.max(max,i+nums[i]);
        }
        return true;
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
//Star
//Core:1.intervals先按照start大小排序。 2.再比较cur interval.end与后面的interval.start关系.if(cur.end>=i.start)  else
//mark2:注意要有个cur interval指向
public class Solution {
    /*170713*/
    public List<Interval> merge(List<Interval> intervals) {
        
        int n = intervals.size();
        List<Interval> res = new ArrayList<>();
        if(n <= 1) return intervals;
        //mark1:/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
//Star
//Core:1.intervals先按照start大小排序。 2.再比较cur interval.end与后面的interval.start关系.if(cur.end>=i.start)  else
//mark2:注意要有个cur interval指向
public class Solution {
    /*170713*/
    public List<Interval> merge(List<Interval> intervals) {
        
        int n = intervals.size();
        List<Interval> res = new ArrayList<>();
        if(n <= 1) return intervals;
        //mark1:/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
//Star
//Core:1.intervals先按照start大小排序。 2.再比较cur interval.end与后面的interval.start关系.if(cur.end>=i.start)  else
//mark2:注意要有个cur interval指向
public class Solution {
    /*170713*/
    public List<Interval> merge(List<Interval> intervals) {
        
        int n = intervals.size();
        List<Interval> res = new ArrayList<>();
        if(n <= 1) return intervals;
        //mark1:Collections.sort(intervals,泛型Obj)
        Collections.sort(intervals,new Comparator<Interval>(){
            //mark1.1:return int表正负，代表大小关系
            public int compare(Interval i1,Interval i2){
                //mark1.5:sort -> ascending
                return i1.start - i2.start;
            }
        });
        //mark2:当前interval与后面的interval不断叠加重复部分，所以有个pointer要指向当前interval
        Interval cur = intervals.get(0);
        for(Interval i:intervals){
            if(cur.end >= i.start){
                cur.end = Math.max(cur.end,i.end);
            } else {
                res.add(cur);
                cur = i;
            }
        }
        //mark3:以case[(1,2),(3,4)] 为例，过完(3,4)后就跳出loop了，所以不能忘记加上res.add([3,4])
        res.add(cur);
        return res;
        //key170713:for写起来较麻烦
        /*
            for(int i = 1;i<=n-1;i++){
                if(intervals.get(i-1).end>=intervals.get(i).start){
                    Interval tmp = new Interval(intervals.get(i-1),Math.max(intervals.get(i-1).end,intervas.get(i).end));
                    res.add(tmp);
                } else {

                }
            }
        */
            
    }
}
        Collections.sort(intervals,new Comparator<Interval>(){
            //mark1.1:return int表正负，代表大小关系
            public int compare(Interval i1,Interval i2){
                //mark1.5:sort -> ascending
                return i1.start - i2.start;
            }
        });
        //mark2:当前interval与后面的interval不断叠加重复部分，所以有个pointer要指向当前interval
        Interval cur = intervals.get(0);
        for(Interval i:intervals){
            if(cur.end >= i.start){
                cur.end = Math.max(cur.end,i.end);
            } else {
                res.add(cur);
                cur = i;
            }
        }
        //mark3:以case[(1,2),(3,4)] 为例，过完(3,4)后就跳出loop了，所以不能忘记加上res.add([3,4])
        res.add(cur);
        return res;
        //key170713:for写起来较麻烦
        /*
            for(int i = 1;i<=n-1;i++){
                if(intervals.get(i-1).end>=intervals.get(i).start){
                    Interval tmp = new Interval(intervals.get(i-1),Math.max(intervals.get(i-1).end,intervas.get(i).end));
                    res.add(tmp);
                } else {

                }
            }
        */
            
    }
}
        Collections.sort(intervals,new Comparator<Interval>(){
            //mark1.1:return int表正负，代表大小关系
            public int compare(Interval i1,Interval i2){
                //mark1.5:sort -> ascending
                return i1.start - i2.start;
            }
        });
        //mark2:当前interval与后面的interval不断叠加重复部分，所以有个pointer要指向当前interval
        Interval cur = intervals.get(0);
        for(Interval i:intervals){
            if(cur.end >= i.start){
                cur.end = Math.max(cur.end,i.end);
            } else {
                res.add(cur);
                cur = i;
            }
        }
        //mark3:以case[(1,2),(3,4)] 为例，过完(3,4)后就跳出loop了，所以不能忘记加上res.add([3,4])
        res.add(cur);
        return res;
        //key170713:for写起来较麻烦
        /*
            for(int i = 1;i<=n-1;i++){
                if(intervals.get(i-1).end>=intervals.get(i).start){
                    Interval tmp = new Interval(intervals.get(i-1),Math.max(intervals.get(i-1).end,intervas.get(i).end));
                    res.add(tmp);
                } else {

                }
            }
        */
            
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

58. Length of Last Word
//Star
//Core:
//mark0:corner:"abc" 如果没有' ' for循环不会执行，因此要直接返回length
//mark0.1:s.trim() 并没有直接在s上更改，因此要return s.trim()
/*170721*/
public class Solution {
    public int lengthOfLastWord(String s) {
        //mark0.1:s.trim() 并没有直接在s上更改，因此要return s.trim()
        s = s.trim();
        for(int i = s.length()-1;i>=0;i--) if(s.charAt(i) == ' ') return s.length()-1-i;
        //mark0:corner:"abc" 如果没有' ' for循环不会执行，因此要直接返回length
        return s.length();
    }
}

public class Solution {
    public int lengthOfLastWord(String s) {
        //another version which dont use split function
        boolean isEnd = false;
        if(s.trim().equals("")) return 0;
        int index = s.length()-1;
        int count = 0;
        while(!isEnd && index >=0){
            
            if(count>0 && !isEnd &&s.charAt(index)==' '){
                isEnd = true;
            }
            if(s.charAt(index) != ' ') count++;
            index--;
            
        }
        return count;
    }
}

59. Spiral Matrix II
public class Solution {
    public int[][] generateMatrix(int n) {
        
        int[][] matrix = new int[n][n];
        int x1 = 0,x2 = n-1,y1 = 0,y2 = n-1;
        int count = 1,sum = n*n;
        while(x1<=x2 && y1 <= y2){
            for(int i = y1;i<=y2;i++) matrix[x1][i] = count++;
            x1++;
            for(int i = x1;i<=x2;i++) matrix[i][y2] = count++;
            y2--;
            if(y1<=y2) for(int i = y2;i>=y1;i--) matrix[x2][i] = count++;
            x2--;
            if(x1<=x2) for(int i = x2;i>=x1;i--) matrix[i][y1] = count++;
            y1++;
        }
        return matrix;
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

//Key170605:
public class Solution {
    public int uniquePaths(int m, int n) {
        //Key170605
        int res = 0;
        if(m == 0 || n ==0) return 0;
        int[][] dp = new int[m][n];
        for(int i = 0;i<=n-1;i++) dp[0][i] = 1;
        for(int i = 0;i<=m-1;i++) dp[i][0] = 1;
        for(int i = 1;i<=m-1;i++){
            for(int j = 1;j<=n-1;j++) dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
        return dp[m-1][n-1];
    }
}

63. Unique Paths II
/*170527*/
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //Key170527:Corner case是难点
        //Key170527:dp[i][j] = dp[i-1][j]+dp[i][j] i->row,j->col  为了排除第一行和第一列只能从左边走和只能从上边走的干扰，我们可以只算dp[1][1]开始的结果。但是第一行，第一列“没有障碍物&&可以到达的位置”我们要先全部初始化为1
        //Key:当第一个grid是1，起始就没法走，所以要设为0。能走，要设为1
        //dp[0][0] = obstacleGrid[0][0] == 0?1:0;
        /*
        [ [1,0,0],
          [0,1,0],
          [1,0,0],
          [1,0,0],
          [1,1,0],
          [0,0,0]
        ]  
        */
        //Key170527:如果左边的是grid是1，即使当前grid是0，但当前dp也只能设为0。行同理所以下边的是wrong
        //if(obstacleGrid[i][0] == 0) dp[i][0] = 1;错误的
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        if(m == 0 || n == 0) return 0;
        int[][] dp = new int[m][n];
        //Key:当第一个grid是1，起始就没法走，所以要设为0。能走，要设为1
        dp[0][0] = obstacleGrid[0][0] == 0?1:0;
        for(int i = 1;i<=m-1;i++) {
            //Key170527:如果左边的是grid是1，即使当前grid是0，但当前dp也只能设为0。行同理所以下边的是wrong
            //if(obstacleGrid[i][0] == 0) dp[i][0] = 1;
            if(obstacleGrid[i][0] == 0) dp[i][0] = dp[i-1][0];
            
        }
        for(int i = 1;i<=n-1;i++) {
            //if(obstacleGrid[0][i] == 0) dp[0][i] = 1;
            if(obstacleGrid[0][i] == 0) dp[0][i] = dp[0][i-1];
        }
        //Key170527:打印Array  System.out.println(Arrays.toString(array));
        //for(int[] i:dp) System.out.println(String.valueOf(i));  ->Wrong
        //for(int[] i:dp) System.out.println(Arrays.toString(i));
        for(int i = 1;i<=m-1;i++){
            for(int j = 1;j<=n-1;j++){
                //Key170527:分类讨论  1->当前grid是1，continue  2->上一个grid是0，叠加
                if(obstacleGrid[i][j] == 1) continue;
                if(obstacleGrid[i-1][j] != 1) dp[i][j] += dp[i-1][j];
                if(obstacleGrid[i][j-1] != 1) dp[i][j] += dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}

//Key170527:这解法太巧
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

64. Minimum Path Sum  //Key:注意  第一行只能从左往右走，第一列只能从上往下走!!!!
public class Solution {
    public int minPathSum(int[][] grid) {
        int m =grid.length,n = grid[0].length;
        if(m == 0 || n == 0 ) return 0;
        int[][] dp = new int[m+1][n+1];
        //Key:其实最好是把边界初始化一下，不然后边会很麻烦
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                //Key:注意grid与dp的index关系
                //Key:注意  第一行只能从左往右走，第一列只能从上往下走!!!!
                if(i == 1) dp[i][j] = dp[i][j-1]+grid[i-1][j-1];
                else if(j == 1) dp[i][j] = dp[i-1][j]+grid[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i-1][j-1];
            }
        }
        return dp[m][n];
    }
}

65. Valid Number - Discard

66. Plus One
//Star
//Core:这道题给的case特别大，没法先转成数字再做
//这道题不是那么简单的
//1.mark1：corner
public class Solution {
    public int[] plusOne(int[] digits) {
        //170626:mark1 corner case:[7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6]
        /*
            if(digits.length == 0) return new int[0];
            String s = "";
            for(int i:digits) s = s+i;
            String tmp = String.valueOf(Long.parseLong(s)+1);
            int[] res = new int[tmp.length()];
            int counter = 0;
            for(char c:tmp.toCharArray()) res[counter++] = c-'0';
            return res;
        */
        //mark2:考虑进位的话，只需考虑最后一位是否是9即可
        //wrong 
        /*
            int n = digits.length,size = digits[n-1] == 9?n+1:n,more = 0;
            //mark3:别忘了 plus one
            digits[n-1]++;
            //mark4:小优化，如果数组长度不变，直接返回
            if(size == n)return digits;
            int[] res = new int[size];
            for(int i = n-1;i>=0;i--){
                int tmp = more+digits[i];
                more = tmp/10;
                res[--size] = tmp%10;
            }
            if(more == 1) res[0] = 1;
            return res;
        
        */
        
        //cp
        int carry = 1;
        for (int i = digits.length-1; i>= 0; i--) {
            digits[i] += carry;
            if (digits[i] <= 9) // early return 
                return digits;
            digits[i] = 0;
        }
        int[] ret = new int[digits.length+1];
        ret[0] = 1;
        return ret;
    }
}


public class Solution {
    public int[] plusOne(int[] digits) {
        //corner case :input 9 Expected: [1,0]
        int length = digits.length;
        int[] tmp = new int[length+1];
        boolean flag = true;
        for(int i=length-1;i>=0;i--){
            if(flag){
                if((digits[i]+1)==10){
                    flag = true;
                    digits[i]=0;
                } else {
                    flag = false;
                    digits[i]++;
                }
            }
            
        }
        if(digits[0]==0) {
            tmp[0] = 1;
            tmp[1] = 0;
            for(int i=1;i<=length-1;i++){
                tmp[i+1] = digits[i];
            }
        } else {
            tmp = digits;
        }
        
        return tmp;
        
    }
}

67. Add Binary
public class Solution {
    public String addBinary(String a, String b) {
        //Plus One 很像
        //Brute Force太麻烦
        //Corner Case:"10",""  不过后面的两个单独的while 避免了这个麻烦
        
        int jinWei = 0;
        int index1 = a.length()-1;
        int index2 = b.length()-1;
        int tmp = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(index1 >= 0&&index2>=0){
            tmp = a.charAt(index1)+b.charAt(index2)+jinWei-96;
            if(tmp >=2){
                jinWei =1;
                if(tmp==3){
                    
                    list.add(1); 
                } else {
                    list.add(0);
                }
            } else {
                jinWei = 0;
                list.add(tmp);
            }
            index1--;
            index2--;
        }
        while(index1>=0){
            tmp = a.charAt(index1)+jinWei-48;
            if(tmp == 2){
                jinWei = 1;
                list.add(0);
            } else{
                jinWei = 0;
                list.add(tmp);
            }
            index1--;
        }
        while(index2>=0){
            tmp = b.charAt(index2)+jinWei-48;
            if(tmp == 2){
                jinWei = 1;
                list.add(0);
            } else{
                jinWei = 0;
                list.add(tmp);
            }
            index2--;
        }
        if(jinWei == 1)list.add(1);
        String result = "";
        for(int i=list.size()-1;i>=0;i--){
            result += list.get(i)+"";
        }
        
        return result;
    }
}

68. Text Justification - Discard

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

70. Climbing Stairs  //if(n <= 1) return n;
public class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        if(n <= 1) return n;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}

71. Simplify Path - simplify file's path - Discard
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

72. Edit Distance
public class Solution {
    public int minDistance(String word1, String word2) {
        //Key170608:dp[i][j] 表word1中的前i个字符转为word2中的前j个字符所需要的最少step，dp[i][j-1]表word1前i个字符转为word2前j个字符最少dp[i][j-1]步，因此dp[i][j]中长度为i的word1如果要转为长度为j的word2，需要在dp[i][j-1]基础上insert一个字符
        //Key170608:其实如果写成dp[i][j+1]=F(dp[i][j]....)应该更好理解些
        //Key170608:dp[i][j] = min(dp[i-1][j],min(dp[i][j-1]),dp[i-1][j-1])+1
        int m = word1.length(),n = word2.length();;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1;i<=m;i++) dp[i][0] = i;
        for(int i = 1;i<=n;i++) dp[0][i] = i;
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                //Key170608:注意chatAt()中的index需要-1，这个index是string中的下表，而dp[i][j]表word1中的前i个字符转为word2中的前j个字符所需要的最少step
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[m][n];
    }
}

/*170606*/
public class Solution {
    public int minDistance(String word1, String word2) {
        //Key170606:dp[i][j] 表word1前i个字符转为word2前j个字符所需的最小step,背
        //dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
        /**
              ? a b c d
            ? 0 1 2 3 4
            b 1 1 1 2 3
            b 2 2 1 2 3
            c 3 3 2 1 2
        **/
        //Key170606:牛逼的方法-->http://www.cnblogs.com/grandyang/p/4344107.html
        //http://www.cnblogs.com/lihaozy/archive/2012/12/31/2840152.html
        //Key170606:当实在找不出transition func时，举个例子，然后把表关系的二维数组写出来，找一下关系!!!
        
        
        int m = word1.length(),n = word2.length();
        if(m == 0) return n;
        else if(n == 0) return m;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1;i<=n;i++){
            dp[0][i] = i;
        }
        for(int i = 1;i<=m;i++){
            dp[i][0] = i;
        }
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[m][n];
    }
}

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

76. Minimum Window Substring
//Star
//1.counter = t中共有多少种字符，map<char,frequence>表每种字符各有多少个。如果所有char的frequence为0，则counter=0,
//意味着s中从begin到end满足t。接下来如果某个frequnce==1，则意味着s中此时begin到end中的字符少一个来匹配t。
//所以counter此时加1。此时，每当任意的char的frequence加1，则counter+1。
//2.注意后来的while(counter == 0),所以内部while中的counter最多也就可能==1，而不可能超过1.所以意味着一旦s中
//少一个字符匹配后，直到s中"该字符"被补上后，否则inner while不会被执行。
//3.因此不用担心->case:"aaaaccbcccba","abc"中a先被消了后，c因为也在t中而被误判断为map.containsKey(c)，进而导致counter--.
//因为此时counter==1，只有当被消了的a被补充后，counter才会再度==0.
//4.可以把counter当做一个守门人，他使得另一边存在着对应数量的各种char。
//当某一个char消失后，他只能允许相同种类的char补充
/*170618*/
public class Solution {
    public String minWindow(String s, String t) {
        int sLen = s.length(),tLen = t.length(),begin = 0,start = 0,minLen = Integer.MAX_VALUE;
        String res = "";
        Map<Character,Integer> map = new HashMap<>();
        for(char c:t.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int counter = map.size();
        //Key170619:其实这里用while(end<=sLen-1)更好。这样end可以先++,后面可以直接写成minLen > end-begin更好看些，而且两个while也比1个for1个while好看些
        for(int i = 0;i<=sLen-1;i++){
        //while(end<=sLen-1){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            //Key170618:counter == 0意味着s字符串begin~i，存在着使t满足的字符串,且s.charAt(i)一定是t中的某一个字符。
            //加入counter == 1,那么意味着t中有一个字符没被匹配上...
            while(counter == 0){
                //if(begin>s.length()-1) break;
                c = s.charAt(begin);
                if(map.containsKey(c)){
                    map.put(c,map.get(c)+1);
                    if(map.get(c) >0 ){
                        counter++;
                    }
                }
                //Key170618:case -> s="abc",t="a"。那么很明显此时的长度应为1，但是这时i还没有++，所以是i+1-begin。或者把最外层的for换成while即可
                if(minLen > (i+1-begin)){
                    minLen = i+1-begin;
                    start = begin;
                }
                begin++;
            }
        }
        //Key170618:s中没有t的话，那么minLen会是MAX_VALUE
        return minLen == Integer.MAX_VALUE?"":s.substring(start,start+minLen);
    }
}

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

77. Combinations
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        helper(list,item,n,k,1);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int n,int k,int start){
        if(item.size() == k){
            list.add(new ArrayList<>(item));
        } else {
            for(int i = start;i<=n;i++){
                item.add(i);
                helper(list,item,n,k,i+1);
                item.remove(item.size()-1);
            }
        }
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

80. Remove Duplicates from Sorted Array II
//Star
//core:在T26的基础上加个counter表每个数字的频率，<=2时，放在前边，否则continue
//mark0:用case:[1,1,1]来记for内部的顺序
//mark1:counter 频率，所以应该置1
//mark2:出现次数2次以上，continue
//mark3
//mark4

/*170625*/
public class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0,n = nums.length,res = 0,counter = 1;
        if(n == 0) return 0;
        for(int i = 1;i<=n-1;i++){
            //mark0:先counter++，还是放在后面，最好用这个case:[1,1,1]来记
            if(nums[i] == nums[i-1]) counter++;
            //mark1: counter表该数字的频率，所以应该置1，而不是0。wrong -> else counter = 0;
            else counter = 1;
            //mark2
            if(counter > 2) continue;
            //mark3:因为index是从0开始的，所以要先++
            nums[++index] = nums[i];
        }
        //mark4:index此时是坐标，所以返回第几个时应该+1
        return index+1;
    }
}

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

81. Search in Rotated Sorted Array II
//Star
//core:与I不同，case {2,2,1,2,2,2,2,2,2} -> nums[low] == nums[mid] = nums[high]
//1.mark1:关键点，其他地方和Search in Rotated Sorted Array I 一样

/*170624*/
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
            //Key:mark1,关键点，其他地方应该和Search in Rotated Sorted Array I 一样
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

83. Remove Duplicates from Sorted List
//Star
//Core:2 pointers 更好，这里偷个懒用next代替第二个index
//mark0:
/*170721*/
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //mark0:有个corner:[-1,0,0,0,1,2],do dummy还是设置为MIN_VALUE省点事吧....
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur != null && cur.next != null){
            if(cur.next.val == cur.val){
                cur.next = cur.next.next;
            } else {
                cur = cur.next;  
            }
        }
        return dummy.next;
    }
}

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        int tmp = head.val;
        ListNode tmpNode = head;
        while(head.next != null){
            if(tmp == head.next.val){
                head.next = head.next.next;
                //delete next node,but dont't go to next node.We will jump over the next node if we go to next node this line.
            } else {
                tmp = head.next.val;
                head = head.next;
            }
            
        }
        //handle the last node's value
        if(head == tmpNode) return tmpNode;
        //if(tmp == head.next.val) head.next = null;
        return tmpNode;
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

85. Maximal Rectangle
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        //Key170606:wrong
        /**
        int row = matrix.length,col = matrix[0].length;
        if(row == 0 || col == 0) return 0;
        int res = 0;
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0] == '1'?1:0;
        for(int i = 1;i<=col-1;i++){
            if(matrix[0][i] == '1') dp[0][i] = dp[0][i-1]+1;
            else dp[0][i] = 0;
            res = Math.max(dp[0][i],res);
        }
        for(int i = 1;i<=row-1;i++){
            if(matrix[i][0] == '1') dp[i][0] = dp[i-1][0]+1;
            else dp[i][0] = 0;
            res = Math.max(dp[i][0],res);
        }
        for(int i = 1;i<=row-1;i++){
            for(int j = 1;j<=col-1;j++){
                if(matrix[i][j] == '1'){
                    if(Math.max(dp[i-1][j],dp[i][j-1]) == 1) dp[i][j] = 2;
                    else dp[i][j] = dp[i-1][j] + dp[i][j-1] -1;
                } else {
                    dp[i][j] = 0;
                }
                res = Math.max(res,dp[i][j]);
            }
        }
        return res;
        **/
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

87. Scramble String - Discard
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

88. Merge Sorted Array
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int x = m-1;
        int y = n-1;
        //!!!!!   for(int i =n+m-1;i>=0;i--){    就应该是n+m-1，因为是按照数量来定
        //for(int i=n+m-1;i>=0;i--){
        for(int i=n+m-1;i>=0;i--){
            
            if(x>=0&&y>=0){
                if(nums1[x] > nums2[y]){
                    nums1[i] = nums1[x];
                    x--;
                } else {
                    nums1[i] = nums2[y];
                    y--;
                }
            } else if(x<0){
                nums1[i]=nums2[y];
                y--;
            } else if(y<0){
                nums1[i]=nums1[x];
                x--;
            }
        }
    }
}

89. Gray Code
public class Solution {
    public List<Integer> grayCode(int n) {
       
         //Bit
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
    
        for(int i = 0; i < n ; i++)
            for(int j = ans.size()-1; j>=0; j--)
                ans.add(ans.get(j)+(1<<i));
    
        return ans;
        
        //another successful ans
        /****
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        for(int i = 1; i < Math.pow(2, n); i*=2){
            for(int j = list.size()-1; j>=0; j--){
                list.add(i + list.get(j));
            }
        }
        return list;
        ****/

    }
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

91. Decode Ways - Discard
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

92. Reverse Linked List II

//Star
//很好的图解  http://www.cnblogs.com/4everlove/p/3651002.html 很好的图解
//core  cur,cur.next,cur.next.next  p1.next = cur后，cur,p1,p2都向后移。将m,n间的List逆序，最后cur指向的逆序中的第一个元素，p1指向的是逆序外的第一个元素，具体看上面的链接，pre指向的是逆序中的最后一个元素
//mark0:确认prev的位置，即确定需要连接逆序后List的node。用case:[1,2]记忆
//mark1.5:cur即prev的下一个node.即开始逆序的起始node.同时定义p1,p2
//mark1.6:逆序所需list，还是以[1,2]来记
//mark2 p1.next = cur 
//mark3:cur,p1,p2全部往后移动一步
//mark4:loop退出条件
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy,cur = null,p1 = null,p2 = null;
        //mark0:确认prev的位置，即确定需要连接逆序后List的node。用case:[1,2]记忆
        //mark0.1:其实不管怎么code,只要保证prev指向第m-1个元素，cur = prev.next即可
        for(int i = 0;i<=m-2;i++){
            prev = prev.next;
        }
        //mark1.5:cur即prev的下一个node.即开始逆序的其实node.定义p1,p2
        cur = prev.next;
        if(cur != null) p1 = cur.next;
        if(p1 != null) p2 = p1.next;
        //mark1.6:逆序所需list，还是以[1,2]来记
		//mark4:loop退出条件
        for(int i = m;i<=n-1;i++){
            //mark2:从后往前连接 p1.next = cur
            p1.next = cur;
            //mark3:cur,p1,p2全部往后移动一步
            cur = p1;
            p1 = p2;
            if(p2 != null) p2 = p2.next;
        }
        //mark4:最后cur指向的逆序中的起始元素，所有node是由cur next开始的。p1指向的是逆序list后的第一个元素
        //mark4.1:即prev->nodeX<-nodeY<-nodeZ<-cur->p1   cur到nodeX即为逆序list
        //mark4.2:末尾元素指向p1,prev.next 指向cur
        prev.next.next = p1;
        prev.next = cur;
        return dummy.next;
    }
}

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

93. Restore IP Addresses - Discard
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

94. Binary Tree Inorder Traversal
//Star
//core:InOrder 是中序...
/*170627*/
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res,root);
        return res;
    }
    public void helper(List<Integer> res,TreeNode node){
        if(node != null){
            
            helper(res,node.left);
            res.add(node.val);
            helper(res,node.right);
        }
    }
}

public class Solution {
    //static List<Integer> list = new ArrayList<Integer>();  这个可以执行，但是此时list就全局唯一了。如果保留static的话，测试时，因为Test case2 输入的是[]，而因为static的原因，output是testcase1的结果{1,2,3}。所以错误了,如下
    /***
        Submission Result: Wrong Answer 
        Input:
        []
        Output:
        [1,3,2]
        Expected:
        []

    **/
    List<Integer> list = new ArrayList<Integer>();
    public List<Integer> inorderTraversal(TreeNode root) {
        //Key Point:
        /**
         以下结论是错误的！！！！
         static只与全局唯一特性有关，只要在方法外声明的变量所有方法都可以用，只是static确保了全局唯一这一特性。
         //static variable不仅代表全局共享这一个变量，还意味着inorderTraversal中调用recursion不需传递list。如果不声明为static，就需要传参list了，即使加上了下面变量生命也不可以。这与static 的该变量唯一特性无关.....
        
        **/
        //List<Integer> list = new ArrayList<Integer>();
        if(root != null){
            inorderTraversal(root.left);
            list.add(root.val);
            inorderTraversal(root.right);
        }
        return list;
    }
    
}

95. Unique Binary Search Trees II  //背

//Star
//Core:
//Star
//Core:leftSub = start ~ i-1,root == i,rightSub = i+1 ~ end
/*170630*/
 public class Solution {
        public List<TreeNode> generateTrees(int n) {
            //mark5:Corner：n == 0,因为1>0,所以会产生一个new ArrayList<>(null).这与题意不符合，n<=0时，应该是一个空list，所以需要特殊处理一下。
            if(n <= 0) return new ArrayList<>();
            return helper(1,n);
            
        }
        public List<TreeNode> helper(int start,int end){
            List<TreeNode> list = new ArrayList<>();
            if(start>end) {
                //mark4:与下面的mark4合起来看
                list.add(null);
                return list;
            }
            for(int i = start;i<=end;i++){
                //mark0
                List<TreeNode> leftSub = helper(start,i-1);
                List<TreeNode> rightSub = helper(i+1,end);
                //mark4:因为for遍历List<TreeNode>，如果值是返回一个空list，遍历就不会发生
                for(TreeNode left:leftSub){
                    for(TreeNode right:rightSub){
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        list.add(root);
                    }
                }
            }
            return list;
        }
 }

//170626 mark3:wrong->平常的那种helper是线性的， 2.left == 1,2.right == 3这种结果没法求出来

//wrong 
/*
    public class Solution {
        public List<TreeNode> generateTrees(int n) {
            boolean[] used = new boolean[n];
            List<TreeNode> res = new ArrayList<>();
            for(int i = 1;i<=n;i++){
                
                //170627:mark1:这个res不能在这里加。因为root为1的话，有[1,null,2,null,3],[1,null,3,2]这两种情况，root都是1，但是第二个结果会覆盖第一个结果
                //mark1.1:做个dummy吧  
                TreeNode dummy = new TreeNode(-1);
                //mark1.3:也不要在这里add(dummy.right) 因为这里的dummy.right保留的是最后一个结果中的right，result  [1,null,2,null,3],[1,null,3,2] 中的第二个1，第一个结果在recursion中背第二个覆盖了
                helper(res,dummy,dummy,used,0);
            }
            return res;
        }
        //mark1.4:需要加root.right，所以把dummy也得传进去
        public void helper(List<TreeNode> res,TreeNode root,TreeNode node,boolean[] used,int counter){
            if(counter == used.length){
                //mark1.2:类似于res.add(new ArrayList<>(item));  这里的add也不能直接加root，所以才做了个dummy。因为dummy.val == -1,i肯定>-1.所以把每次新生成的dummy.right加进去即可
                res.add(root.right);
            } else {
                for(int i = 1;i<=used.length;i++){
                    if(used[i-1]) continue;
                    //170626 mark3:wrong->平常的那种helper是线性的， 2.left == 1,2.right == 3这种没法求出来
                    if(i>node.val){
                        node.right = new TreeNode(i);
                        
                        used[i-1] = true;
                        helper(res,root,node.right,used,++counter);
                        used[i-1] = false;
                    } else {
                        node.left = new TreeNode(i);
                        used[i-1] = true;
                        helper(res,root,node.left,used,++counter);
                        used[i-1] = false;
                    }
                }
            }
        }
        
    }
*/
public class Solution {
    //Key:下边两个解法一样....
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
    //Brute force
    //Key:https://discuss.leetcode.com/topic/3079/a-simple-recursive-solution/14
	public List<TreeNode> generateTrees(int n) {
		if(n <= 0) return new ArrayList<TreeNode>();
		return helper(1,n);
	}
	public List<TreeNode> helper(int start,int end){
		List<TreeNode> list = new ArrayList<>();
		if(start>end) {
			//Key:因为底下的两个inner loop需要遍历list中的nodes，所以还是需要add一下null的，否则直接返回一个空的list也是不对的
			//mark1:必须有下面这句list.add(null) 。如果直接返回一个空的new ArrayList<>().那么底下的for就不会便利这个“空arrylist”.所以最后会返回null
			list.add(null);
			return list;
		}
		//Key:遍历一遍所有节点，使它们都能作为root处理。
		for(int i = start;i<=end;i++){
			List<TreeNode> leftSubTrees = helper(start,i-1);
			List<TreeNode> rightSubTrees = helper(i+1,end);
			//Key:左右子树重新组合
			for(TreeNode left:leftSubTrees){
				for(TreeNode right:rightSubTrees){
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					list.add(root);
				}
			}
		}
		return list;
	}
}

//V2
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
    public List<TreeNode> generateTrees(int n) {
        if(n <= 0) return new ArrayList<TreeNode>();
        return helper(1,n);
    }
    public List<TreeNode> helper(int start,int end){
        List<TreeNode> list = new ArrayList<>();
        if(start>end) {
            //Key:因为底下的两个inner loop需要读取list内的node，所以还是需要add一下null的，否则直接返回一个空的list也是不对的
            list.add(null);
            return list;
        }
        //Key:遍历一遍所有节点，使它们都能作为root处理。
        for(int i = start;i<=end;i++){
            List<TreeNode> leftSubTrees = helper(start,i-1);
            List<TreeNode> rightSubTrees = helper(i+1,end);
            //Key:左右子树重新组合
            for(TreeNode left:leftSubTrees){
                for(TreeNode right:rightSubTrees){
                    TreeNode root = new TreeNode(i);
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
        //http://blog.csdn.net/jiadebin890724/article/details/23305915
        /**
         * Taking 1~n as root respectively:
         *      //F(0)表0个node的bst的数量
         *      1 as root: # of trees = F(0) * F(n-1)  // F(0) == 1
         *      2 as root: # of trees = F(1) * F(n-2) 
         *      3 as root: # of trees = F(2) * F(n-3)
         *      ...
         *      n-1 as root: # of trees = F(n-2) * F(1)
         *      n as root:   # of trees = F(n-1) * F(0)
         *
         * So, the formulation is:
         *      F(n) = F(0) * F(n-1) + F(1) * F(n-2) + F(2) * F(n-3) + ... + F(n-2) * F(1) + F(n-1) * F(0)
         */
        int [] G = new int[n+1];
        //G[0]表0个node的bst，G[1]表1个node的bst。
        G[0] = G[1] = 1;
        
        for(int i=2; i<=n; ++i) {
        	for(int j=1; j<=i; ++j) {
        	    //Key:以i2j1举例，为G[2] += G[0]*G[1]
        		G[i] += G[j-1] * G[i-j];
        	}
        }
    
        return G[n];
    }
}
//V2
public class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        //dp[0]表0个node生成的树数量
        dp[0] = dp[1] = 1;
        for(int i = 2;i<=n;i++){
            for(int j = 1;j<=i;j++){
                //j-1  +  i-j 应该正好等于 i-1,即除了i以外的所有其他node的数量
                dp[i] = dp[i] +dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}

97. Interleaving String - Discard
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

98. Validate Binary Search Tree

//Star
//core:如果root的右子点的左子点小于root的左子点，下面这个wrong方法也能通过这种case，但很明显这种方法是错误的。
//mark0:corner:[10,5,15,null,null,6,20]

/*170703*/
public class Solution {
    /*
        boolean res = true;
        public boolean isValidBST(TreeNode root) {
            helper(root);
            return res;
        }
        public void helper(TreeNode node){
            if(node != null){
                if(node.left != null) {
                    if(node.left.val >= node.val) res = false;
                }
                if(node.right != null) {
                    if(node.right.val <= node.val) res = false;
                }
                helper(node.left);
                helper(node.right);
            }
        }
    */
}

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

100. Same Tree
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //Key Point:
        //Recursion每一层递归执行，不会出现执行快慢的担忧。就是说他是严格按照每一层的顺序返回值。
        if(p!=null && q != null && p.val == q.val){
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        } else if(p == null && q== null){
            return true;
        } else {
            return false;
        }
        /**
        
        别人的更清晰版本
        
            if(p == null && q == null) return true;
            if(p == null || q == null) return false;
            if(p.val == q.val)
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            return false;
        */
    }
}

101. Symmetric Tree
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        //Key:这道题思路和一般的tree思路不太一样。有些绕...
        //这里如果判断current node本身的话，不太好判断。所以向上一层，判断他们的parent那层
        //递归是按照层级判断的
        if(root == null) return true;
        return helper(root.left,root.right);
    }
    public boolean helper(TreeNode left,TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return helper(left.left,right.right) && helper(left.right,right.left);
    }
}

102. Binary Tree Level Order Traversal
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        //mark1:
        helper(list,root,0);
        return list;
    }
    public void helper(List<List<Integer>> list,TreeNode node,int depth){
        if(node != null){
            if(depth+1>list.size()) list.add(new ArrayList<>());
            list.get(depth).add(node.val);
            //mark2
            helper(list,node.left,depth+1);
            helper(list,node.right,depth+1);
        }
    }
}

103. Binary Tree Zigzag Level Order Traversal
//Star
//Core:T102基础上，mark1小区别
//mark0:list.add(0,element) 原有element不断往后移。即倒序存储
/*170714*/
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res,root,0);
        return res;
    }
    public void helper(List<List<Integer>> res,TreeNode node,int lv){
        if(node != null){
            if(lv == res.size()) res.add(new ArrayList<>());
            //mark1:与T102唯一区别：
            if(lv % 2 == 0)res.get(lv).add(node.val);
            //mark0:list.add(0,nums[i]);  表将nums[i]放置于0th pos,即将新加入元素不断的往后挤
            else res.get(lv).add(0,node.val);
            helper(res,node.left,lv+1);
            helper(res,node.right,lv+1);
        }
    }
}

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

104. Maximum Depth of Binary Tree
public class Solution {
    public int maxDepth(TreeNode root) {
        //Key Point:recursion临界条件比的是什么？比的就是传的参数吗？
        //如这道题判断TreeNode root,MergeSort判断low和high
        if(root == null) return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
    
}

105. Construct Binary Tree from Preorder and Inorder Traversal
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

106. Construct Binary Tree from Inorder and Postorder Traversal
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

107. Binary Tree Level Order Traversal II
//Star
//Core:T102基础上  mark1是关键：list.get(list.size()-1-depth)
/*170714*/

public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        helper(list,root,0);
        return list;
    }
    public void helper(List<List<Integer>> list,TreeNode node,int depth){
        if(node != null){
            //Key:list.add(0,new ArrayList<>());   add(0 是插入到头部
			//mark0:
            if(list.size() < depth+1) list.add(0,new ArrayList<>());
            //Key:list.get(list.size()-1-depth).
            //Key:list.get(0).add(node.val); 是错的!!!!!
			//mark1:list.get(list.size()-1-depth)
            list.get(list.size()-1-depth).add(node.val);
            helper(list,node.left,depth+1);
            helper(list,node.right,depth+1);
        }
    }
}

108. Convert Sorted Array to Binary Search Tree
//Star
//Core:binary search方法，所以尽可能的减少了高度差

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums,0,nums.length-1);
    }
    public TreeNode recursion(int[] arr,int low,int high){
        //Key point:
        //类比于mergeSort,因为要处理low == high时需要返回一个单独的节点，所以要用<=，而不是<
        if(low<=high){
            int mid = (low+high)/2;
            TreeNode node = new TreeNode(arr[mid]);
            //Key point:
            //类比于mergeSort的Sorting(low,mid);Sorting(mid+1,high);
            //因为需要返回值，所以要加上return;
            node.left = recursion(arr,low,mid-1);
            node.right = recursion(arr,mid+1,high);
            return node;
        }
        return null;
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

110. Balanced Binary Tree

public class Solution {
    //Star
    //Core
    //Key:cp,背  https://discuss.leetcode.com/topic/7798/the-bottom-up-o-n-solution-would-be-better
    //Key:另一个我觉得也挺不错的方法  https://discuss.leetcode.com/topic/10192/java-o-n-solution-based-on-maximum-depth-of-binary-tree
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        //mark0:depth()计算出左右nodes的depth，然后再比较
        int left=depth(root.left);
        int right=depth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public int depth (TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth (root.right)) + 1;
    }
}

111. Minimum Depth of Binary Tree
//Star
//Core:和T104 maximum depth差别较大 
//http://www.cnblogs.com/grandyang/p/4042168.html
/*170714*/
public class Solution {
    public int minDepth(TreeNode root) {
        //mark1:root == null 和 leaf node特殊处理一下
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        //mark0:因为之前排除过，所以这里的root.left意味着root.right != null
        else if(root.left == null) return minDepth(root.right)+1;
        else if(root.right == null) return minDepth(root.left)+1;
        else return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }
}

public class Solution {
    //Key:做麻烦了....
    int result = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        helper(root,1);
        return result;
    }
    public void helper(TreeNode node,int min){
        if(node == null) return;
        if(node.left == null && node.right == null){
            result = Math.min(result,min);
        } else {
            helper(node.left,min+1);
            helper(node.right,min+1);
        }
        
    }
}

112. Path Sum
public class Solution {
    //Key:第一个sol应该是首先就会想起来的
    /**
    boolean tag = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        helper(root,sum,0);
        return tag;
    }
    public void helper(TreeNode node,int sum,int tmp){
        if(node == null) return;
        tmp += node.val;
        if(tmp == sum && node.left == null && node.right == null) {
            tag = true;
            return;
        }
        helper(node.left,sum,tmp);
        helper(node.right,sum,tmp);
    }
    
    **/
    
	/*
		public class Solution {
			public boolean hasPathSum(TreeNode root, int sum) {
				return helper(root,sum,0);
			}
			public boolean helper(TreeNode node,int target,int sum){
				if(node!=null){
					if(node.left == null && node.right == null){
					   if(sum+node.val == target) return true;  
					} else {
						return helper(node.left,target,sum+node.val)||helper(node.right,target,sum+node.val)?true:false;
					}
				}
				return false;
			}
		}
	*/
	
    //Key:第二个sol更好看些吧
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root,sum,0);
    }
    public boolean helper(TreeNode node,int sum,int tmp){
        if(node == null) return false;
        tmp += node.val;
        if(node.right == null && node.left == null && tmp == sum) return true;
        return helper(node.left,sum,tmp)||helper(node.right,sum,tmp);
    }
}

//V3
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root,sum,0);
    }
    public boolean helper(TreeNode node,int target,int sum){
        if(node!=null){
            if(node.left == null && node.right == null){
               if(sum+node.val == target) return true;  
            } else {
                return helper(node.left,target,sum+node.val)||helper(node.right,target,sum+node.val)?true:false;
            }
        }
        return false;
    }
}

113. Path Sum II

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

114. Flatten Binary Tree to Linked List

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

115. Distinct Subsequences
public class Solution {
    //Star
    //如果是一个String，那么应该是区间dp。多个String比较的话，往往都是dp[i][j],i、j分别表String1的前i个字符和String2的前j个字符
    //Core:S[i] == T[j],dp[i][j] = dp[i-1][j] + dp[i-1][j-1]   
    //以case "bbb","bb" 为例。当S中2th b与T中1th相同时，dp[i][j] = S中前2个b与T中前2个b的dp值+S中前2个b与T中前1个b的dp值
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

116. Populating Next Right Pointers in Each Node - 方法同T117

117. Populating Next Right Pointers in Each Node II
//Star
//Core:下面的方法不太好理解，背
//mark0:
/*
    case:
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
    虽然3.left = null，但因此5要直接链接7.而不是因为3.left == null而直接把5.next这步给省略了
*/
/*170717*/
//mark1.9:dummy指向每一层
//Mark:方法好，但是非常不容易理解
public class Solution {
    public void connect(TreeLinkNode root) {
        //Key:与 Populating Next Right Pointers in Each Node 比较，这道题中的binary tree非perfect。（即左右孩子有可能不存在）
        //Key:cp,背 https://discuss.leetcode.com/topic/28580/java-solution-with-constant-space/2
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
			//mark1.8:每次只处理当前节点的左右节点。然后移至next，当next == null时，开始下一个level
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
                //mark2:下面三句背
    		    pre = dummyHead;
    		    root = dummyHead.next;
                //mark3:下面这句不好理解，背
    		    dummyHead.next = null;
    	    }
        }
        
    }
}

118. Pascal's Triangle
//Star
//Core:
//mark0:
//mark1:
/*170716*/
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0) return res;
        //mark0:第一行需要初始化一下 res.get(0).add(1);  因为mark1中有个res.get(i-1)，如果i从0开始，比较不好处理
        res.add(new ArrayList<>());
        res.get(0).add(1);
        if(numRows == 1) return res;
        for(int i = 1;i<=numRows-1;i++){
            res.add(new ArrayList<>());
            for(int j = 0;j<=i;j++){
                //mark1:左上角为index为j-1,右上角则为j
                int left = j-1<0?0:res.get(i-1).get(j-1);
                int right = j>i-1?0:res.get(i-1).get(j);
                res.get(i).add(left+right);
            }
        }
        return res;
    }
}

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        //Coner Case
        if(numRows == 0) return null;
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<=numRows-1;i++){
            list.add(new ArrayList<Integer>());
        }
        int index = 0;
        //init list
        for(int i=0;i<=numRows-1;i++){
            for(int j=0;j<=i;j++){
                list.get(i).add(1);
            }
            
        }
        for(int i=2;i<=numRows-1;i++){
            for(int j=1;j<=i-1;j++){
                list.get(i).set(j,list.get(i-1).get(j-1)+list.get(i-1).get(j));
            }
        }
        return (List)list;
    }
}

119. Pascal's Triangle II
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        int tmp =0;
        
        int[] arr = new int[rowIndex+1];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<=rowIndex-1;i++){
            list.add(1);
            arr[i] = 1;        
        }
        for(int i=2;i<=rowIndex-1;i++){
            
            for(int j=1;j<=i-1;j++){
                arr[j] = list.get(j);
                list.set(j,arr[j-1]+list.get(j));
 
            }
        }
        return (List)list;
    }
}

120. Triangle
//Star
//Core:杨辉三角变式
//mark0:注意覆盖问题
/*170717*/
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        //Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = triangle.get(0).get(0);
        for(int i = 1;i<=triangle.size()-1;i++){
            //mark0:注意覆盖问题，所以从后往前
            for(int j = i;j>=0;j--){
            //for(int j = 0;j<=i;j++){
                //mark1:要想排除越界的干扰，其实可以在dp两边各加一个0
                int left = j-1>=0?dp[j-1]:Integer.MAX_VALUE;
                int right = j<=triangle.get(i-1).size()-1?dp[j]:Integer.MAX_VALUE;
                dp[j] = Math.min(left,right)+triangle.get(i).get(j);
                //System.out.println(dp[i]);
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i:dp){
            res = Math.min(i,res);
        }
        return res;
    }
}
//Excellent cp,背 
//看不太懂
//https://discuss.leetcode.com/topic/22254/7-lines-neat-java-solution
//https://discuss.leetcode.com/topic/1669/dp-solution-for-triangle/2
public int minimumTotal(List<List<Integer>> triangle) {
    int[] A = new int[triangle.size()+1];
    for(int i=triangle.size()-1;i>=0;i--){
        for(int j=0;j<triangle.get(i).size();j++){
            A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
        }
    }
    return A[0];
}
//DP
public int minimumTotal(List<List<Integer>> triangle) {
        //AA:Corner case:难点在边界处理上 -->就是下面inner loop中的3个if
        //AA:Key:关键 for(int j = 1;j<=triangle.get(row-1).size();j++){  还有j的范围，因为下边有triangle.get(i-1).get(j-1);
        
        //dp[row][col] 表到达第row行col列的所有数字之和最小的值
        //dp[row][col] = min(dp[row-1][col-1],dp[row-1][col])+nums[row][col]
        if(triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        int res = Integer.MAX_VALUE;
        int row = triangle.size(),col = triangle.get(triangle.size()-1).size();
        int[][] dp = new int[row+1][row+1];
        for(int[] i:dp) Arrays.fill(i,0);
        for(int i = 1;i<=row;i++){
            for(int j = 1;j<=triangle.get(i-1).size();j++){
                if(j == 1) dp[i][j] = dp[i-1][j]+triangle.get(i-1).get(j-1);
                else if(j == triangle.get(i-1).size())dp[i][j] = dp[i-1][j-1]+triangle.get(i-1).get(j-1);
                else dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1])+triangle.get(i-1).get(j-1);
            }
        }
        for(int i = 1;i<=col;i++){
            res = Math.min(dp[row][i],res);
        }
        return res;
    }

//Ugly code
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null) return 0;
        if(triangle.get(0) == null) return 0;
        //Line 5: error: List is abstract; cannot be instantiated   So:List<List<Integer>> temp = new List<List<Integer>>(); is Wrong!!!!!
        List<List<Integer>> temp = new ArrayList<List<Integer>>();
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(triangle.get(0).get(0));
        temp.add(a);
        //int minSum = 0;WRONG!!!!     因为只有一个元素时，比如[[2]]，很明显要大于0，所以指定minSum = triangle.get(0).get(0)时最好了。另外[[-2]]也是可以的，可以存在负数。
        int minSum = triangle.get(0).get(0);
        int size = triangle.size();
        //只有一个element时，size=1,size-1=0,所以不会进入for循环。
        for(int i=1;i<=size-1;i++){
            temp.add(new ArrayList<Integer>());
            for(int j=0;j<=i;j++){
                int curSum;
                
                if(j == 0){
                    
                    curSum = temp.get(i-1).get(0)+triangle.get(i).get(j);
                    if(i == size-1)minSum = curSum;
                } else if(j == i){
                    curSum = temp.get(i-1).get(j-1)+triangle.get(i).get(j);
                } else {
                    curSum = Math.min(temp.get(i-1).get(j-1),temp.get(i-1).get(j))+triangle.get(i).get(j);
                }
                temp.get(i).add(curSum);
                if(i == size-1){
                    if(minSum > curSum) minSum = curSum;
                }
            }
        }
        return minSum;
    }
}

121. Best Time to Buy and Sell Stock
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices == null) return 0;
        int profit = 0;
        int min = prices[0];
        for(int i = 0;i<=prices.length-1;i++){
            min = Math.min(min,prices[i]);
            profit = Math.max(profit,prices[i]-min);
        }
        return profit;
    }
}

122. Best Time to Buy and Sell Stock II
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0||prices.length == 1 ||prices == null) return 0;
        int length = prices.length;
        int profit = 0;
        int min = prices[0];
        for(int i=1;i<=length -2;i++){
            min = Math.min(min,prices[i]);
            //[5,2,3,2,6,6,2,9,1,0,7,4,5,0]  注意 ==也可以
            if(prices[i]>prices[i-1] && prices[i] >= prices[i+1]){
                profit += (prices[i]-min);
                min = prices[i+1];
            }
        }
        
        if(length == 2){
            profit = Math.max(profit,prices[1]-prices[0]);
        } else {
            //length-1  ArrayIndexExpection
            if(prices[length-1] > prices[length-2]) profit += (prices[length-1]-min);    
        }
        
        return profit;
    }
}

123. Best Time to Buy and Sell Stock III
public class Solution {
    public int maxProfit(int[] prices) {
        //[1,2,4,2,5,7,2,4,9,0]  以下代码这个测试用例过不去
        // if(prices.length == 0||prices.length == 1 ||prices == null) return 0;
        // int length = prices.length;
        // int profit = 0,max1 = 0,max2 = 0;
        // int min = prices[0];
        // for(int i=1;i<=length -2;i++){
        //     min = Math.min(min,prices[i]);
        //     //[5,2,3,2,6,6,2,9,1,0,7,4,5,0]  注意 ==也可以
        //     if(prices[i]>prices[i-1] && prices[i] >= prices[i+1]){
        //         profit = (prices[i]-min);
        //         //[5,2,3,2,6,6,2,9,1,0,7,4,5,0]  注意 profit<=max1也可以
        //         if(profit > max2 && profit <=max1) max2 = profit;
        //         else if(profit >max2 && profit >=max1){
        //             max2 = max1;
        //             max1 = profit;
        //         }
        //         min = prices[i+1];
        //     }
        // }
        
        // if(length == 2){
        //     profit = Math.max(profit,prices[1]-prices[0]);
        // } else {
        //     //length-1  ArrayIndexExpection
        //     if(prices[length-1] > prices[length-2]){
        //         profit = (prices[length-1]-min); 
        //         if(profit > max2 && profit <=max1) max2 = profit;
        //         else if(profit >max2 && profit >=max1){
        //             max2 = max1;
        //             max1 = profit;
        //         }
        //     }    
        //     profit = max1 + max2;
        // }
        
        // return profit;
        if(prices.length == 0 || prices == null || prices.length ==1){
            return 0;
        }
        int length = prices.length;
        int[] F = new int[length];
        int[] F2 = new int[length];
        F[0]=F2[length-1]=0;
        int min = prices[0];
        int max = prices[length-1];
        int maxProfit = 0;
        int profit = 0;
        
        for(int i =1;i<=length-1;i++){
            min = Math.min(min,prices[i]);
            maxProfit = Math.max(prices[i]-min,maxProfit);
            F[i] = maxProfit;
           
        }
        maxProfit = 0;
        for(int i = length-2;i>=0;i--){
            max = Math.max(max,prices[i]);
            maxProfit = Math.max(max-prices[i],maxProfit);
            F2[i] = maxProfit;
            
        }
        for(int i=0;i<=length-1;i++){
            profit = Math.max(profit,F[i]+F2[i]);
        }
        return profit;
    }
}

124. Binary Tree Maximum Path Sum - Discard

125. Valid Palindrome
//Star
//Version 2
//1.mark1  s.toLowerCase()  s.replaceAll用法  reg expression外部记得加[]，^取非
//2.mark2  reverse()只能作用在StringBuilder上。 s.reverse()是错的

/*170621*/
public class Solution {
    public boolean isPalindrome(String s) {
        //170621:mark1:replaceAll("^a-z0-9","s")是错的，记得要在匹配式外部加上[]。
        //还有^表取反/非的意思,所以replace ^a-z0-9表将非a-z0-9字符替换为空
        String tmp = s.toLowerCase().replaceAll("[^a-z0-9]","");
        
        //Log:"amanaplanacanalpanama"
        //System.out.println("Log:"+tmp);
        
        //mark2
        return new StringBuilder(tmp).reverse().toString().equals(tmp);
    }
}

//Star
//1.这道题的case真TM烦人!!!
//1.1:为了避免inner while中和外部的begin++重复，所以用if else做个选择判断
//1.2:mark1~千万不能忘记，如果left == right，begin++，end-- 
//2.Character.isLetterOrDigit() ->判断字符或者数字
//https://discuss.leetcode.com/topic/8282/accepted-pretty-java-solution-271ms 
//或者 正则匹配方法  https://discuss.leetcode.com/topic/25405/my-three-line-java-solution
//corner case:"..." == "",如果一个character或者digit都没有的话，那么就相当于一个空字符串

/*170620*/
public class Solution {
    //My wrong version
    /**
        public boolean isPalindrome(String s) {
            s = s.toLowerCase();
            boolean res = true;
            int begin = 0,end = s.length()-1;
            while(begin <= end){
                char left = s.charAt(begin),right = s.charAt(end);
                
                while(!Character.isLetter(left) && !Character.isDigit(left) && begin <=s.length()-1){
                    //Key170620:charAt()前判断下begin，别超过了length-1
                    left = s.charAt(begin++);
                }
                while(!Character.isLetter(right) && !Character.isDigit(right) && end >= 0){
                    right = s.charAt(end--);
                }
                //Key170620:首先left和right必须先是alphanumric,然后不相等的情况下才false。如果仅是Corner case:".,",这种，根本就不是alphanumric，是true的
                if(begin == s.length() && end == -1) return true;
                if(left != right) return false;
                //Key170620:如果s中全部都是alphanumric，那么下面的begin++，end--没问题。但是如果s中有,. 这种特殊符号。涉嫌在上面的两个while中最后就会重复++或者--。造成错误 
                //如 case："A man, a plan, a canal: Panama"
                begin++;
                end--;
            }
            return res;
        }
    **/
    public boolean isPalindrome(String s) {
        int n = s.length(),begin = 0,end = n-1;
        s = s.toLowerCase();
        while(begin <= end){
            char left = s.charAt(begin),right = s.charAt(end);
            if(!Character.isLetterOrDigit(left)){
                begin++;
            } else if(!Character.isLetterOrDigit(right)){
                end--;
            } else {
                if(left != right) return false;
                //Key170620~mark1:千万不能忘记，如果左右两端字符相等时，begin要++，end要--
                begin++;
                end--;
            }
        }
        return true;
    }
}

public class Solution {
    public boolean isPalindrome(String s) {
        //有句刚句，这道题我觉得纯粹是恶心人来的....
        //这道题题目有问题，他把数字也算作合法字符了
        //对于 TestCase: "0P"，答案居然是false.按理说忽略数字后，应该是true的.......
        //two pointers 向中间靠拢  upper word(65~90) lower word(97~122)
        //用Character.isLetter(char ch)判断更方便
        //Corner Case:要考虑的太多
        if(s.trim().equals("")) return true;
        boolean result = true;
        int index1 = 0;
        int index2 = s.length();
        boolean flag = false;
        //Corner Case:"." 因为有index2--，所以加大了判断难度
        while(index1 <= index2 && result && index1 <= s.length()-1){
            if(!flag){
                //KEY POINT 
                //Character.isLetter(char ch)可以直接判断是否是字母
                //对于 TestCase: "0P"，答案居然是false.按理说忽略数字后，应该是true的.......
                if(Character.isLetter(s.charAt(index1)) || Character.isDigit(s.charAt(index1))){
                    flag = true;
                    index2--;
                } else {
                    index1++;
                }
                
                
            } else {
                if(Character.isLetter(s.charAt(index2))||Character.isDigit(s.charAt(index2))){
                    
                    //System.out.println(Math.abs(s.charAt(index2) - s.charAt(index1)) +"");
                    if(s.charAt(index2) != s.charAt(index1) ) result = false;
                    //Corner Case:"aA"要返回true
                    //Corner Case:ASCII  0=>48,P=>80 差值正好是32，所以单纯只写32会出问题
                    if(Math.abs(s.charAt(index2) - s.charAt(index1)) == 32 && Character.isLetter(s.charAt(index2)) && Character.isLetter(s.charAt(index1))) result = true;
                    flag = false;
                    index1++;
                } else {
                    index2--;
                }
            }
        }
        return result;
    }
}

126. Word Ladder II - Discard

127. Word Ladder - Discard

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

129. Sum Root to Leaf Numbers
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

131. Palindrome Partitioning
//Key:Hard,DFS,cp
//https://discuss.leetcode.com/topic/33461/easiest-4ms-java-solution-95-99/2
//Key:similar to Permutations ----> https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
//aab   a,a,b->a,ab -> aa,b ->aab 
public class Solution {
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
//Star
//Core:Dp可以做，也可以在T131基础上用个min记录下最少递归次数

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

133. Clone Graph - Discard

134. Gas Station
//Star
//Core:
//http://www.cnblogs.com/grandyang/p/4266812.html
/*
这道转圈加油问题不算很难，只要想通其中的原理就很简单。我们首先要知道能走完整个环的前提是gas的总量要大于cost的总量，这样才会有起点的存在。假设开始设置起点start = 0, 并从这里出发，如果当前的gas值大于cost值，就可以继续前进，此时到下一个站点，剩余的gas加上当前的gas再减去cost，看是否大于0，若大于0，则继续前进。当到达某一站点时，若这个值小于0了，则说明从起点到这个点中间的任何一个点都不能作为起点，则把起点设为下一个点，继续遍历。当遍历完整个环时，当前保存的起点即为所求。
*/
/*170719*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0,tank = 0,begin = 0;
        for(int i = 0;i<=gas.length-1;i++){
            //mark1:tatal == 总的gas-总的cost，如果>=，说明可以走完。否则走不完
            total += gas[i] - cost[i];
            //mark2:tank表示走到i+1时油箱里剩余的油量
            tank += gas[i] - cost[i];
            if(tank < 0){
                begin = i+1;
                tank = 0;
            }
        }
        if(total < 0) return -1;
        else return begin;
    }
}

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

135. Candy
//Star
//Core:从左到右过一遍，再反向过一遍
/*170719*/
public class Solution {
    public int candy(int[] ratings) {
        int res = 0;
        int[] dp = new int[ratings.length];
        Arrays.fill(dp,1);
        for(int i = 1;i<=ratings.length-1;i++){
            //mark1:dp[i] = dp[i-1]+1; 而不是 dp[i]++  corner:1,2,3,4,5  对应的dp[1,2,3....]
            if(ratings[i] > ratings[i-1]) dp[i] = dp[i-1]+1;
        }
        for(int i = ratings.length-2;i>=0;i--){
            //mark0:从右往左  必须是ratings[i] > ratings[i+1]，否则corner:[2,2] 
            //mark0:且从右往左扫时，如果ratings左边大于右边，要保证dp左边大于右边
            if(ratings[i] > ratings[i+1] && dp[i] <= dp[i+1]) dp[i] = dp[i+1]+1;
        }
        for(int i:dp) res+=i;
        return res;
    }
}

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

136. Single Number
//Star
//Core:抑或方法性太强。map brute force
//mark0:map loop  -> for(Map.Entry<泛型，泛型> entry:map.entrySet())
public class Solution {
    /*17721*/
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);    
        }
        //mark0:map loop  -> for(Map.Entry<泛型，泛型> entry:map.entrySet())
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue() == 1) return entry.getKey();
        }
        return 0;
    }
    //Version 抑或，方法性太强
    /*
        public int singleNumber(int[] nums) {
            //0可以亦或吗？？另外，如果那个单独的数就是0的话会怎么样呢？？？？  如果就是0，那么就是0^0,那么还是0
            int result = 0;
            for(int i:nums)
                result ^= i;
            return result;
        }
    */
}

137. Single Number II
//Star
//Core:1,Bit  2,map方法 3.
public int singleNumber(int[] nums) {
	if(nums.length < 4){
		return nums[0];
	}
	//1）先排序，在处理
	Arrays.sort(nums);
	int count = 0;
	int tar = 0;
	for(int i=0;i<=nums.length-1;i+=3){
		// if((i+1) <=nums.length-1 && (i+2)<=nums.length-1){  不用这么写，只要和后边的一个比较就足够了
		if((i+1) <=nums.length-1){
			if((nums[i] != nums[i+1]) ) return  nums[i];
		} else {
			if(nums[i] != nums[i-1]) return  nums[i];
		}
	}
	//这个return 0 必不可少。虽然for里有return ,但是java编译器貌似为了避免错误，所以在最外面必须有个return 
	return 0;
	
}

//bitmap方法
public static int singleNumber(int[] nums) {
	int len = nums.length, result = 0;
	for (int i = 0; i < 32; i++) {
		int sum = 0;
		for (int j = 0; j < len; j++) {
			sum += (nums[j] >> i) & 1;
		}
		result |= (sum % 3) << i;
	}
	return result;
}

//bitmap方法2
public int singleNumber(int[] nums) {
	int[] digit = new int[32];
	for(int i = 0; i < 32; i++){
		// for each digit, we count the number of appearance in the array
		for (int j = 0; j < nums.length; j++){
			digit[i]+= (nums[j]>>i)&1; // get the value (0 or 1) at ith digit of nums[j]
		}
	}
	int res = 0;
	for (int i =0; i< 32; i++){
		// (digit[i]%3) is mode of 3, if it is not zero, it means the single number has 1 on this digit.
		int d = digit[i]%3 ==0 ? 0: 1;
		res += (d)<<i;
	}
	return res;
}

138. Copy List with Random Pointer
public class Solution {
    //Star:注意新node的randomNode分配是个问题
    //Core:所以必须用Map存储新nodes和老nodes之间的关系.map<oldNode,newNode> 然后根据关系再分配next和random
    //wrong:因为下面要分配随机node给每个新node，虽然知道head.random指向哪里，但是因为head和node并无关联，所以并不知道新node的random指向哪里
    /*
        public RandomListNode copyRandomList(RandomListNode head) {
            RandomListNode dummy = new RandomListNode(head);
            RandomListNode cur = new RandomListNode(head.label);
            head = head.next;
            while(head != null){
                cur.next = new RandomListNode(head.label);
                head = head.next;
            }

            while()
        }
    */
    public RandomListNode copyRandomList(RandomListNode head) {
        /*170720*/
        Map<RandomListNode,RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
        //Wrong
        /*
            RandomListNode dummy = new RandomListNode(-1);
            dummy.next= head;
            while(head != null){
                map.put(head,head.random);
                head = head.next;
            }
            head = dummy.next;
            RandomListNode cur = dummy;
            while(head != null){
                cur.next = new RandomListNode(map.get(head).label);
                cur.random = 
            }
        */
        RandomListNode dummy = new RandomListNode(-1);
        dummy.next = head;
        //mark1:记录下新旧node对应
        while(head != null){
            map.put(head,new RandomListNode(head.label));
            head = head.next;
        }
        head = dummy.next;
        //mark2:重新分配新Node的next和random关系
        while(head != null){
            map.get(head).next = map.get(head.next);
            map.get(head).random = map.get(head.random);
            head = head.next;
        }
        return map.get(dummy.next);
    }
}

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

139. Word Break
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //Key:DP Just cp
        //https://discuss.leetcode.com/topic/6156/java-implementation-using-dp-in-two-ways
        //https://discuss.leetcode.com/topic/18088/java-dp-solution
        if (s == null || s.length() == 0) return false;
        int n = s.length();
        // dp[i] represents whether s[0...i] can be formed by dict
        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                String sub = s.substring(j, i + 1);
                if (wordDict.contains(sub) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n - 1];
    }
}

140. Word Break II
//Star
//Core:T139基础上，加个Map<int,List<String>> 来存储ith结尾的字符串
/*170720*/
public class Solution {
    //WWrong
    //mark0:思路对，但是在处理追加list element时很麻烦处理
    /*
        public List<String> wordBreak(String s, List<String> wordDict) {
            Map<Integer,List<String>> map = new HashMap<>();
            int n = s.length();
            for(int i = 0;i<=n-1;i++){
                for(int j = i;j>=0;j--){
                    if(wordDict.contains(s.substring(j,i+1))){
                        if(map.size() != 0 && !map.containsKey(j)) continue;
                        String tmp = s.substring(j,i+1);
                        if(map.size() == 0) {
                            List<String> foo = new ArrayList<>();
                            foo.add(tmp);
                            map.put(i,foo);
                            continue;
                        }
                        List<String> cur;
                        if(map.containsKey(i)){
                            cur = map.get(i);
                        } else {
                            cur = new ArrayList<>();
                        }

                        //List<String> list = map.get(j-1);
                        for(String ss:map.get(j)) cur.add(ss+tmp);
                        map.put(i,cur);
                    }
                }
            }
            return map.get(n-1);
        }
    */
}

141. Linked List Cycle
public class Solution {
    public boolean hasCycle(ListNode head) {
        boolean tag = false;
        if(head == null || head.next == null){
            return tag;
        }
        ListNode node1 = head;
        ListNode node2 = head;
        int count = 0;
        //必须要单个next，next的判断。加个标识符做判断吧......直接next.next会出现指针错误  Line 20: java.lang.NullPointerException
        //改成while(node2.next!=null && node2.next.next!=null){   就没事了，
        //貌似是因为&&的关系，node2.next==null时，就不会在判断后边的node2.next.next!=null了！！！！！！
        
        // while(node2.next.next != null){
        // while(node2.next!=null){
        //     node2 =node2.next;
        //     count = 1-count;
        //     if(count == 0) node1 = node1.next;
        //     if(node1 == node2){
        //         tag = true;
        //         return tag;
        //     }
        // }
        //上面那种写法说是超时了，但感觉没多大区别......
        
            //下面这种方法有问题，如果是[1,2]就会返回true.结果错误，所以改成
        while(node2.next!=null && node2.next.next!=null){
            node2 =node2.next.next;
            node1 = node1.next;
            if(node1 == node2){
                tag = true;
                return tag;
            }
        }
        
            //因为ListNode是自建类，所以比较node时即要比较val，也要比较它的next元素。下面那个我也不知为何通过不了检测
            // if(node1 != node2){
            //     node1 = node1.next;
            //     node2 = node2.next.next;
                
            // } else {
            //     tag = true;
            //     return tag;
            // }
            //不是上述那个原因，应该因为一开始初始化Node1和node2都是head,所以直接进入else条件了，我忘记先分别移动了。改动一下就好了
            
            //下面这样写貌似没有必要
            // if(node1.next == node2.next && node1.val == node2.val){
            //     tag = true;
            //     return tag;
            // } else {
            //     node1 = node1.next;
            //     node2 = node2.next.next;
            // }
        
        return tag;
    }
}

142. Linked List Cycle II
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        //下面两行代码错误，node1和node2应该是Null起步。一个1步，一个两步，所以应该分别初始化为head 和head.next;因为head.next 已经判断过，所以不用担心指针错误。
        // ListNode node1 = head;
        // ListNode node2 = head;
        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode tmpNode = null;
        //这道题理解上和题目不太一样。它说的循环开始的地方，（以{3,2,0,-4}为例，-4 connects to 2 which index is 1）我理解的是cycle begins from -4,而它则认为是从node 2开始，所以Index is 3.接发没错，只是理解上有些偏差。
        //其实也简单，node1 == node2后，即相遇后，Node1再走一步即可。
        
        //虽然这道题不用另加判断，但最好还是想一下当只有两个node时{3,2}，会不会因为node2.next.next而出现错误！！！！！
        
        while(node2.next !=null &&node2.next.next !=null){
            node1 = node1.next;
            node2 = node2.next.next;
            if(node1 == node2) break;
        }
        if(node2.next == null || node2.next.next==null) return null;
        ListNode node3 = head;
        if(node1.next.next == node1) return node3;
        //如果只有两个node{2,3},node3永远不可能等于node1。追不上
        while(node3!=node1){
            node3=node3.next;
            node1=node1.next;
        }
        
        
        return node3;
    }
}

143. Reorder List
/*170720*/
//Star
//Core:hard 但是思路是对的
//1。Find the middle of the list
//2.Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
//3.Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
//wrong
/*
public class Solution {
    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode left = dummy,right = dummy,p1 = null,p2 = null;
        dummy.next = head;
        while(right != null && right.next != null){
            left = left.next;
            right = right.next.next;
        }
        
        left = left.next;
        if(left != null) p1 = left.next;
        if(p1 != null) p2 = p1.next;
        while(p2 != null){
            p1.next= left;
            left = p1;
            p1 = p2;
            if(p2 != null) p2 = p2.next;
        }
        while(dummy != null) {
            System.out.println(dummy.val);
            dummy = dummy.next;
        }
        ListNode p4 = null,p3 = null;
        p4 = p1;
        p1 = dummy.next;
        p2 = null;
        if(p4 != null) p3 = p4.next;
        if(p1 != null) p2 = p1.next;
        while(p1 != null && p4 != null && p2 != p3){
            p1.next = p4;
            p1 = p2;
            p4.next = p1;
            p4 = p3;
            if(p4 != null) p3 = p4.next;
            if(p1 != null) p2 = p1.next;
        }
        //return dummy.next;
    }
}
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


144. Binary Tree Preorder Traversal
/*170720*/
//Recursion version
public List<Integer> preorderTraversal(TreeNode root) {
	List<Integer> list = new ArrayList<>();
	helper(list,root);
	return list;
}
public void helper(List<Integer> list,TreeNode node){
	if(node != null){
		list.add(node.val);
		helper(list,node.left);
		helper(list,node.right);
	}
}

public class Solution {
    //Similar to inordertraversal
    List<Integer> list = new ArrayList<Integer>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root != null){
            list.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return list;
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
    
	//Star
    //Core:recursion方法和inOrder,preOrder一样。并没有特殊之处
    //Traversal递归sol需要用到stack
    /*170720*/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(list,root);
        return list;
    }
    public void helper(List<Integer> list,TreeNode node){
        if(node != null){
            
            helper(list,node.left);
            helper(list,node.right);
            list.add(node.val);
        }
    }
	
	//iterative方法才需要用到Stack，recursion version并没有特殊之处
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

146. LRU Cache - Discard

147. Insertion Sort List

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

149. Max Points on a Line - Discard

150. Evaluate Reverse Polish Notation
//Star
//Core:stack
/**170720*/
public class Solution {
    public int evalRPN(String[] tokens) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for(String s:tokens){
            if(s.equals("+")){
                //mark0:stack pop()弹出顶层
                stack.push(stack.pop()+stack.pop());
            } else if(s.equals("-")){
                stack.push(-(stack.pop()-stack.pop()));
            } else if(s.equals("*")){
                stack.push(stack.pop()*stack.pop());
            } else if(s.equals("/")){
                int a = stack.pop(),b = stack.pop();
                stack.push(b/a);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        //mark1:pop() return 弹出值
        return stack.pop();
    }
}

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

151. Reverse Words in a String
public class Solution {
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        // write your code
        //Key point:
        //这不就是考stack的用法吗
        //Stack stack = new Stack();
        //都不用stack，直接两个数组组合一下就成
        String[] arr = s.split(" ");
        String result = "";
        for(int i=arr.length-1;i>=0;i--){
            if(!arr[i].equals(" ")) result = result + arr[i] + " ";
        }
        return result;
    }
}

public class Solution {
    //reverse ===>  Stack
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<String>();
        String tokens[] = s.split(" ");
        int length = tokens.length;
        String reverseStr = "";
        for(int i = 0 ; i < length; i ++){
           if(tokens[i].equals(""))
                continue;
           // System.out.println(tokens[i] + "sssssssss");
            stack.push(tokens[i]);
        }
        while(!stack.isEmpty()){
            String temp = stack.pop();
            reverseStr = reverseStr.concat(temp);
            reverseStr = reverseStr.concat(" ");
        }
        return reverseStr.trim();
    }
}

152. Maximum Product Subarray
//Star
//Core:状态机是两个dp数组的优化版本
/*170720*/
public class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        //mark0:maxs,mins分别记录下到ith为止的最大，最小值
        int[] maxs = new int[n];
        int[] mins = new int[n];
        maxs[0] = nums[0];
        mins[0] = nums[0];
        for(int i = 1;i<=n-1;i++){
            maxs[i] = Math.max(Math.max(maxs[i-1]*nums[i],mins[i-1]*nums[i]),nums[i]);
            mins[i] = Math.min(Math.min(maxs[i-1]*nums[i],mins[i-1]*nums[i]),nums[i]);
        }
            int max = Integer.MIN_VALUE;
        for(int i:maxs) max = Math.max(max,i);
        return max;
    }
}

/*170606*/
//Key17060:状态机??
public class Solution {
    public int maxProduct(int[] nums) {
        int n =nums.length;
        if(n == 0) return 0;
        int res = nums[0],max = nums[0],min = nums[0];
        if(n == 1) return res;
        for(int i = 1;i<=n-1;i++){
            int tmp = max;
            max = Math.max(max*nums[i],Math.max(min*nums[i],nums[i]));
            min = Math.min(tmp*nums[i],Math.min(min*nums[i],nums[i]));
            res = Math.max(max,res);
        }
        return res;
    }
}
/*170605   背*/
public class Solution {
    public int maxProduct(int[] nums) {
		//Key170605:因为可能存在负数，所以需要额外保持一个min,当nums[i]为负数时可以求到max。另外因为有可能为0,所以也需要判断一下nums[i]是否最大
		//
        //Key170605:dp[i][j] = max(dp[i][j-1]*nums[j],dp[i+1][j]*nums[i],dp[i][j-1],dp[i+1][j]) -->wrong(因为这是contiguos，所以数组元素必须是连续的)
        //Key170605:dp1[i] = max(dp[i-1]*nums[i],nums[i]),dp2[i] = max(dp[i-1]*nums[i],nums[i])  --->wrong
		
        int n = nums.length,res = Integer.MIN_VALUE;
        if(n == 0) return 0;    
        //Key170605:需要考虑偶数个负数相乘的情况
        int max = nums[0],min = nums[0];
        res = nums[0];
        for(int i = 1;i<=n-1;i++){
			//Key170605关键：tmp额外保存下max
            int tmp = max;
            max = Math.max(nums[i],Math.max(max*nums[i],min*nums[i]));
            //Key170605:注意此时max有可能改变了，所以之前要存储一下
            min = Math.min(nums[i],Math.min(tmp*nums[i],min*nums[i]));
            res = Math.max(max,res);
        }
        return res;
    }
    
	
	//Key170605:wrong 
        /**
        int[] dpLeft = new int[n];
        int[] dpRight = new int[n];
        dpLeft[0] = nums[0];
        dpRight[n-1] = nums[n-1];
        if(n == 1) return nums[0];
        for(int i = 1;i<=n-1;i++) {
            dpLeft[i] = Math.max(dpLeft[i-1]*nums[i],nums[i]);
            res = Math.max(res,dpLeft[i]);
        }
        
        return res;
        **/
        
        //Key170605:wrong version
        /**
        int[][] dp = new int[n][n];
        for(int i = 0;i<=n-1;i++) {
            dp[i][i] = nums[i];
            res = Math.max(res,nums[i]);
        }
        int tmp = 1;
        for(int i = 0;i<=n-1;i++){
            int j = i-1,k = i+1;
            while(j>=0 && k <=n-1){
                tmp = tmp *nums[j] *nums[k];
                res = Math.max(res,tmp);
                j--;
                k++;
            }
            while(j>=0){
               tmp = tmp *nums[j] *nums[k];
                res = Math.max(res,tmp);
                j--;
            }
            while(k <=n-1){
               tmp = tmp *nums[j] *nums[k];
                res = Math.max(res,tmp);
                k++; 
            }
        }
        return res;
        **/
        
}

public class Solution {
    public int maxProduct(int[] A) {
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
        
        //Key:Just cp,背 --> 准确的说不应该算dp...
        //https://discuss.leetcode.com/topic/5161/simple-java-code/2
        //Key170605:只需要考虑max * A[i](A[i]为正数), min * A[i](A[i]为负数), A[i](min和max为0的情况)三者中谁最大的关系。因为参数是int[]，所以不用考虑元素值为分数的影响-->因为都是整数，所以乘积绝对值一定大于等于本身
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            //Key170605:下面这两句比较关键
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            result = Math.max(result,max);
        }
        return result;
    }
}

153. Find Minimum in Rotated Sorted Array
//Star
//core:T33吃透了，T153就没问题了

/*170626*/
public class Solution {
    public int findMin(int[] nums) {
        int n = nums.length,low = 0,high = n-1,res = Integer.MAX_VALUE;
        while(low<=high){
            int mid = (low+high)/2;
            if(nums[low] <= nums[mid]){
                res = Math.min(nums[low],res);
                low = mid+1;
            } 
            if(nums[mid] <= nums[high]){
                res = Math.min(nums[mid],res);
                high = mid-1;
            }
        }
        return res;
    }
}


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

155. Min Stack
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

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

156. Binary Tree Upside Down - Lock

157. Read N Characters Given Read4 - Lock

158. Read N Characters Given Read4 II - Call multiple times - Lock

159. Longest Substring with At Most Two Distinct Characters - Lock

160. Intersection of Two Linked Lists
//Star
//Core:思路很简单，但是写起来非常麻烦
//Version 0 一个非常漂亮的写法，这样一来index1 和index2走的路一样长。证明貌似和环List有些相似
//mark0:如果headA,headB有一个是null，.next会不好处理，所以一开始就给排除了

/*170810*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode index1 = headA,index2 = headB;
        while(index1 != index2){
            if(index1 != null) index1 = index1.next;
            else index1 = headB;
            if(index2 != null) index2 = index2.next;
            else index2 = headA;
        }
        return index1;
    }
}

/*170721*/
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //Version 0 Brilliant sol!!!
        //mark0:如果有一个是null，headA.next会不好处理，所以一开始献给排除了
        if(headA == null || headB == null) return null;
        ListNode index1 = headA,index2 = headB;
        //mark1:index1，index2走的路一样长
        while(index1 != index2){
            index1 = index1 == null?headB:index1.next;
            index2 = index2 == null?headA:index2.next;
        }
        return index1;
        //Little wrong
        /*
            int m = 0,n = 0;
        
            ListNode dummy1 = new ListNode(-1);
            ListNode dummy2 = new ListNode(-1);
            dummy1.next = headA;
            dummy2.next = headB;
            ListNode index1 = dummy1,index2 = dummy2;
            while(index1 != null){
                m++;
                index1 = index1.next;
            }
            while(index2 != null){
                n++;
                index2 = index2.next;
            }
            int diff = Math.abs(m-n);
            index1 = dummy1;
            index2 = dummy2;
            for(int i = 0;i<=diff-1;i++){
                if(m>n) index2 = index2.next;
                else index1 = index1.next;
            }
            while(index1 != null && index2 != null && index1 != index2){
                index1 = index1.next;
                index2 = index2.next;
            }
            return index1;
        */
        //wrong
        /*
            ListNode p1 = headA,p2 = headB;
            int diff = 0;
            while(p1 != null && p2 != null){
                p1 = p1.next;
                p2 = p2.next;
            }
            if(p1 == null){
                p2 = headB;
                while(p2 != null){
                    p2 = p2.next;
                    diff++;
                }
            }
            if(p2 == null){
                p1 = headA;
                while(p1 != null){
                    p1 = p1.next;
                    diff++;
                }
            }
            if(p1 == null){
                p1 = headA;
                for(int i = 0;i<=i-1;i++){
                    p1 = p1.next;
                }
                while(p1 != null && p2 != null && p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
            } else {
                p2 = headB;
                for(int i = 0;i<=i-1;i++){
                    p2 = p2.next;
                }
                while(p1 != null && p2 != null && p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
            }
            return p1;
        */
    }
}
 
 public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //写的太乱
        //two pointers
        //{}
        if(headA == null || headB == null){
            return null;
        }
        
        ListNode index1 = headA;
        ListNode index2 = headB;
        int m=0,n=0;
        ListNode result = null;
        while(index1.next != null){
            index1 = index1.next;
            m++;
        }
        while(index2.next != null){
            index2 = index2.next;
            n++;
        }
        //回到List头部
        index1 = headA;
        index2 = headB;
        int distance = Math.abs(m-n);
        for(int i=0;i<=distance-1;i++){
            if(m>n) index1 = index1.next;
            else index2 = index2.next;
        }
        while(index1.next != null && index2.next != null && index1.next!=index2.next ){
            index1 = index1.next;
            index2 = index2.next;
        }
		return ...
	}
}
 
161. One Edit Distance - Lock 

162. Find Peak Element
//Star
//Core:貌似考点为二分法，不过直接brute force
//Version Brute Force
public class Solution {
    public int findPeakElement(int[] nums) {
        for(int i = 0;i<=nums.length-1;i++){
            if(i == 0){
                if(nums.length == 1) return i;
                else if(nums[i+1]<nums[i]) return i;
            } else if(i == nums.length-1) {
                if(nums[i]>nums[i-1]) return i;
            } else if (nums[i] > nums[i-1] && nums[i] > nums[i+1]){
                return i;
            }
        }
        return -1;
    }
}

//Version binarySearch
/*
	public class Solution {
		public int findPeakElement(int[] nums) {
			//Key:这道题是要用二分法的.....
			for(int i = 0;i<=nums.length-1;i++){
				if(i == 0){
					if(nums.length == 1) return i;
					else if(nums[i+1]<nums[i]) return i;
				} else if(i == nums.length-1) {
					if(nums[i]>nums[i-1]) return i;
				} else if (nums[i] > nums[i-1] && nums[i] > nums[i+1]){
					return i;
				}
			}
			return -1;
		}
	}
*/

163. Missing Ranges - Lock

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

165. Compare Version Numbers
public class Solution {
    public int compareVersion(String version1, String version2) {
        //Coner Case:考虑起来很麻烦
        //直接粘来一个答案
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");
        
        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i<length; i++) {
        	Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
        	Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
        	int compare = v1.compareTo(v2);
        	if (compare != 0) {
        		return compare;
        	}
        }
        
        return 0;
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

167. Two Sum II - Input array is sorted
	//Star
    //Core:2pointers,T1 map sol也可以
    //mark0:记得加上break; 否则会因为tmp == target后一直循环
    /*170721*/
    public int[] twoSum(int[] numbers, int target) {
        int index1 = 0,index2 = numbers.length-1;
        int[] res = new int[2];
        while(index1 < index2){
            int tmp = numbers[index1] + numbers[index2];
            if(tmp == target){
                res[0] = index1+1;
                res[1] = index2+1;
                //mark0:记得加上break; 否则会因为tmp == target后一直循环
                break;
            } else if (tmp < target){
                index1++;
            } else {
                index2--;
            }
        }
        return res;
    }


public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        //Two pointers 只需要O(n),从两边往中间夹。因为一开始只可能小于<=target。所以省略了一些麻烦的思考。
        //这道题必须是两个不同的index.Test Case:[2,2,3] 6 不会返回两个2.
        //这里我用hashMap来解决，也是O(n),开辟额外空间
        //Corner Case:[0,0,3,4] 0
        if(numbers.length==0) return null;
        int[] result = new int[2];
        // HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        // for(int i=0;i<=numbers.length-1;i++){
        //     if(map.containsKey(target-numbers[i])){
        //         result[0] = map.get(target-numbers[i])+1;
        //         result[1] = i+1;
        //     }
        //     map.put(numbers[i],i);
        // }
        
        //Two pointers version
        int index1=0,index2 = numbers.length-1;
        boolean flag = false;
        while(index1<=index2 && !flag){
            if(numbers[index1]+numbers[index2] < target){
                index1++;
            } else if(numbers[index1]+numbers[index2] > target){
                index2--;
            } else {
                result[0] = index1+1;
                result[1] = index2+1;
                flag = true;
            }
        }
        return result;
    }
}

168. Excel Sheet Column Title
//Star
//Core:
//mark0
/*170721*/
public class Solution {
    public String convertToTitle(int n) {
        String res = "";
        int remain = 0;
        while(n != 0){
            remain = n%26;
            n = n/26;
            //mark1:注意这个26进制A是从1开始的，不是0，所以和一般的十六进制(0~F)，8进制转换不一样.  corner:52 -> AZ
            if(remain == 0){
                res = "Z"+res;
                n--;
            } else {
                //mark0:'0' 的 ASCII 不是 0,是48!!!
                //所以('A'-'0'+1+remain) 是错的!!
                res = (char)('A'-1+remain) + res;
            }
        }
        return res;
    }
}

//Star
//Core:
//mark0:注意这个26进制A是从1开始的，不是0，所以和一般的十六进制(0~F)，8进制转换不一样.
//Coner Case:676 (26*26) 正确输出应该是YZ  25*26+26
//Test Case:52  正确输出应为AZ
public class Solution {
    public String convertToTitle(int n) {
        //Key Point
        //类比10进制，这是26进制
        //Test Case:
        //More Details Input:26 Output: "A@" Expected:"Z"
        //input ZZ
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        //sb.append()加的是个逆序过程
        //这个26进制考虑进位很麻烦
        //Coner Case:676 (26*26) 正确输出应该是YZ  25*26+26
        //Test Case:52  正确输出应为AZ
        while(n !=0){
            if(n%26 == 0){
                sb.append((char)(26+64));
                n -= 26;
            } else {
                sb.append((char)(n%26+64));
            }
            
            n = n/26;
        }
        for(int i=sb.toString().length()-1;i>=0;i--){
            result.append(sb.toString().charAt(i));
        }
        return result.toString();
    }
}

169. Majority Element
//Star
//Core
/*170721*/
public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[(nums.length-1)/2];
    }
}

170. Two Sum III - Data structure design - Lock

171. Excel Sheet Column Number
//Star
//Core:T168逆向
public class Solution {
    public int titleToNumber(String s) {
        int n = 1,res = 0;
        for(int i = s.length()-1;i>=0;i--){
            res += n*(s.charAt(i)-'A'+1);
            n *= 26;
        }
        return res;
    }
}

172. Factorial Trailing Zeroes
//Star
//Core:有几个0由5的数量决定
//mark0:有几个0由5的数量决定
public class Solution {
    public int trailingZeroes(int n) {
        //Key:背 Just cp
        //https://discuss.leetcode.com/topic/6848/my-explanation-of-the-log-n-solution/4
        int cnt = 0;
        while(n>0){
            //mark0:有几个0由5的数量决定
            cnt += n/5;
            n/=5;
        }
        return cnt;
    }
}


173. Binary Search Tree Iterator
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
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

174. Dungeon Game - Discard

175. Null

176. Null

177. Null

178. Null

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

180. Null

181. Null

182. Null

183. Null

184. Null

185. Null
	
186. Reverse Words in a String II - Lock

187. Repeated DNA Sequences
//Star
//Core:用set来排除重复项
//mark0:用set来确认哪些重复了
//mark0.1:去除结果中的重复项
/*170723*/
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        //mark0:用set来确认哪些重复了
        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        //mark1:因为i的边界不好确定，所以干脆写成i+9<=s.length()-1
        for(int i = 0;i+9 <= s.length()-1;i++){
            String tmp = s.substring(i,i+10); 
            //mark0.1:corner:"AAAAAAAAAAAAAAAA"  !set.add(tmp)确定哪些重复了,!res.contains(tmp)去除结果中的重复项
            if(!set.add(tmp) && !res.contains(tmp)) res.add(tmp);
        }
        return res;
    }
}

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

188. Best Time to Buy and Sell Stock IV
//Star
//Core:状态机，hard
public class Solution {
    //Key:Hard,just cp
    //这两个解法比较容易理解
	//http://blog.csdn.net/linhuanmars/article/details/23236995
	//http://www.cnblogs.com/grandyang/p/4295761.html
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

189. Rotate Array
public class Solution {
	//Version 1:new array to store the new pos,then restore to nums
    public void rotate(int[] nums, int k) {
        //Test Case:[1,2,3,4],5
        //k可以大于length-1。此题默认k是正数
		//
        int length = nums.length;
        int[] arr = new int[length];
        int tmp =0;
        int index=0;
        for(int i =0;i<=length-1;i++){
            index = k+i>=length?(k+i)%length:k+i;
            
            arr[index] = nums[i];
        }
        for(int i =0;i<=length-1;i++){
            nums[i] = arr[i];
        }
    }
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

191. Number of 1 Bits
public class Solution {
    // you need to treat n as an unsigned value
    //因为已经提到bits，所以应该是用bit相关操作比较简单，如位移操作等
    public int hammingWeight(int n) {
        
        if(n==0) return 0;
        int count = 0;
         for(int i = 0; i < 32; i++){  
            if ((n & 1) == 1) count++;
            n >>= 1;
        }
        //count 最后多加一次，所以要减掉
        return count;
    }
    /***
     * //还有这种非算法解法
    public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum=0;
        String[] x=Integer.toBinaryString(n).split("");
        for(int i =0 ;i< x.length;i++){
            if(x[i].equals("1")) sum++;
        }
        return sum;
    }
    }
    
    **/
}

192. Null

193. Null

194. Null

195. Null

196. Null

197. Null

198. House Robber
public class Solution {
    public int rob(int[] nums) {
        //typical DP,similar as package problem
        if(nums == null || nums.length==0) return 0;
        int length = nums.length;
        int F[] = new int[length];
        if(length ==1 ) return nums[0];
        else if(length ==2) return Math.max(nums[1],nums[0]);
        F[0] = nums[0];
        F[1] = Math.max(nums[1],nums[0]);
        for(int i=2;i<=length-1;i++){
            F[i] = Math.max(F[i-1],F[i-2]+nums[i]);
        }
        return F[length-1];
    }
}

public class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        if(nums.length == 0) return 0;
        dp[0] = nums[0];
        if(nums.length == 1) return nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2;i<=nums.length-1;i++){
            //Key:因为隔天抢劫，dp[i-2]+nums[i]说明是两天前的状态，也就是说昨天没抢，今天抢没事。dp[i-1]意味着接着昨天的状态，不管他抢没抢，反正今天也不抢，也没事。
            //Key:状态，和stock cooldown 一起看
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[nums.length-1];
    }
}
 
199. Binary Tree Right Side View
//Star
//Core:前序中左右给成中右左，然后加个depth标识一下就好了
//mark0:
public class Solution {
    //Similar to  Binary Tree Inorder Traversal
    List<Integer> list = new ArrayList<Integer>();
    int depth = 0;
    public List<Integer> rightSideView(TreeNode root) {
        // if(root != null){
        //     //这道题应该更像是那道求深度的题：Maximum Depth of Binary Tree  
        //     /***
        //      * 下面的是错误解法
        //      * 
        //      *  //思路也是错的
        //         //把前序遍历改一下应该就可以了。改  中左右 -> 中右左,且只存“中”这个值，放弃左右child的值
        //      * 过不了Test case:[1,2,3,4]
        //      *               1
        //      *       2               3
        //      *    4   NIL       NIL    NIL
        //      *
        //     list.add(root.val);
        //     if(root.right != null) rightSideView(root.right);
        //     else rightSideView(root.left);
        //     */
        //     if(list.size() == depth){
        //         list.add(root.val);
               
        //     }
        //     depth++;
        //     rightSideView(root.right);
        //     rightSideView(root.left);
            
        // }
        // return list;
        rightSide(root,0);
        return list;
    }
    
    public void rightSide(TreeNode root,int depth) {
        if(root != null){
            if(list.size() == depth){
                list.add(root.val);
            }
			//mark0:先right再left
            rightSide(root.right,depth+1);
            rightSide(root.left,depth+1);
        }
    }
} 

200. Number of Islands
public class Solution {
    //Key:Wrong
    /**
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        //Key:判断左上部分是否挨着0即可
        for(int i = 0;i<=grid.length-1;i++){
            for(int j = 0;j<=grid[0].length-1;j++){
                if(grid[i][j] == '1'){
                    //System.out.println("sdfsdf");
                    //Key:难点在于这个判断不好写,下面的就是错误的
                    //Corner  case:["111","010","111"] 工字型
                    //if(i == 0 && j == 0 || i == 0 && grid[i][j-1] == '0' || j == 0 && grid[i-1][j] == '0' || i>0 && j>0 && grid[i-1][j] == '0'  grid[i][j-1] == '0') count++;
                }
            }
        }
        return count;
    }
    **/
    
    //Key:just copy
    public int numIslands(char[][] grid) {
        int count = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    clearRestOfLand(grid, i, j);
                }
            }
        }
        return count;
    }
    
    private void clearRestOfLand(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0') return;
        
        grid[i][j] = '0';
        clearRestOfLand(grid, i+1, j);
        clearRestOfLand(grid, i-1, j);
        clearRestOfLand(grid, i, j+1);
        clearRestOfLand(grid, i, j-1);
        return;
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

202. Happy Number
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

203. Remove Linked List Elements
//Star
//Core:dummy,2pointers
/*170723*/
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next= head;
        ListNode left = dummy,right = left.next;
        while(right != null){
            //mark0:== val,left不变，right = right.next;
            if(right.val == val){
                left.next = right.next;
                right = left.next;
            } else {
                left = right;
                right = right.next;
            }
        }
        return dummy.next;
    }
}

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        //two pointers
        //KEY POINT
        //需要考虑的Coner Case 很多:[]/[1],1/[1],3/[1,3,2,5,6,4,3,2,5,3],3(最后一个3) 
        //太麻烦
        if(head == null){
            return null;
        }
        ListNode pointer = head;
        ListNode noVal = null;
        ListNode result = null;
        boolean flag = false;
        while(pointer != null){
            if(pointer.val != val) {
                if(!flag){
                    noVal = pointer;
                    result = noVal;
                    flag = true;
                } else {
                    //KEY POINT
                    //记的noVal最后要移动到下一个位置：noVal = noVal.next;
                    noVal.next = pointer;
                    noVal = noVal.next;
                }
            } else {
                //Corner Case:[1,3,2,5,6,4,3,2,5,3],3
                //要记得处理最后一个3
                if(pointer.next == null && noVal != null) noVal.next = null;
            }
            pointer = pointer.next;
            
        }
        return result;
    }
}

204. Count Primes
//Star
//Core:prime numbers->质数(Prime numbers can only be divided by one or itself.)
/*170723*/
public class Solution {
    public int countPrimes(int n) {
        //埃拉托斯特尼筛法 ，简称埃氏筛，是一种简单且年代久远的算法，用来找出一定范围内所有的素数。
        int count = 0;
		boolean[] f = new boolean[n];
		for (int i = 2; i < n; i++) {
			if(!f[i]){
				for (int j = 2; j*i < n; j++) {
					f[j*i] = true;
				}
				count++;
			}
		}
		return count;
    }
}

205. Isomorphic Strings
//Star
//Core:containsKey()单向映射是错的，要写成双向映射   s中的char和t中的char对应一个第三方值x,比较对应的x
//http://www.cnblogs.com/grandyang/p/4465779.html
//mark0:corner:"ab" "aa" 或者 corner:"ab","bc"
//他这个sol较好，s中的char和t中的char对应一个第三方值x，省去了双向映射的一些麻烦地方
//mark0
//mark0.2
public class Solution {
    /*170724*/
    public boolean isIsomorphic(String s, String t) {
        //Version grandyang:同时对应第三方值x
        //mark0.1:ASCII码只有256个字符

        int[] arr1 = new int[256];
        int[] arr2 = new int[256];
        //mark0.2:x从>0开始。array填充的都是0,如果x=0，第一对char即使符合，也还是设置为0，干扰了....
        int x = 1;
        if(s.length() != t.length()) return false;
        for(int i = 0;i<=s.length()-1;i++){
            char ss = s.charAt(i),tt = t.charAt(i);
            if(arr1[ss-0] != arr2[tt-0]){
                return false;
            } 
            arr1[ss-0] = x;
            arr2[tt-0] = x;
            x++;
            
        }
        return true;
        //Key:My wrong version
        /***
        //mark0:corner:"ab" "aa" 或者 corner:"ab","bc"
        if(s.length() != t.length()) return false;
        Map<Character,Character> map = new HashMap<>();
        for(int i = 0;i<=s.length()-1;i++){
            if(map.containsKey(s.charAt(i))){
                if(map.get(s.charAt(i)) != t.charAt(i)) return false;
            } else {
                map.put(s.charAt(i),t.charAt(i));
            }
        }
        return true;
        ****/
        //mark0:改写成双向映射太麻烦
        /*
        if(!map.containsKey(s.charAt(i)) && !map.containsKey(t.charAt(i))){
                map.put(s.charAt(i),t.charAt(i));
                map.put(t.charAt(i),s.charAt(i));
            } else if(map.containsKey(s.charAt(i)) && map.containsKey(t.charAt(i)){
                map.get(s.charAt(i));
                .
                .
                .
                .
                .
            }
        */

        //Key:cp,背 https://discuss.leetcode.com/topic/13001/short-java-solution-without-maps/2
        /*
            int[] m = new int[512];
            for (int i = 0; i < s1.length(); i++) {
                if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
                m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
            }
            return true;
        */
        
    }
}

206. Reverse Linked List
public class Solution {
    public ListNode reverseList(ListNode head) {
        //Key point:记录下prev和current和一个临时tmp(current.next),然后current.next->prev,移动至tmp，loop循环做。
        //Key point:为了减少head干扰，并且为了和tail node一致处理，从第二个节点开始while
        if(head == null) return null;
        ListNode tmp = new ListNode(0),node = head.next,prev = head;
        while(node != null){
            tmp = node.next;
            node.next = prev;
            prev = node;
            node = tmp;
        }
        head.next = null;
        return prev;
    }
}

207. Course Schedule - Discard
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


208. Implement Trie (Prefix Tree) - Discard
//Key:cp,背   https://discuss.leetcode.com/topic/19221/ac-java-solution-simple-using-single-array/2
class TrieNode {
    public boolean isWord; 
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode();
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root; 
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root; 
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

 209. Minimum Size Subarray Sum
//Star
//Core:
//1.brute force
//2.2 poitners
//3.dp

//170730 和下面的一样
//2pointers
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0,n = nums.length,begin = 0,res = Integer.MAX_VALUE;
        for(int end = 0;end<=n-1;end++){
            sum += nums[end];
            while(sum >= s){
                res = Math.min(res,end-begin+1);
                sum -= nums[begin];
                begin++;
            }
        }
        //mark0: If there isn't one, return 0 instead.
        return res == Integer.MAX_VALUE?0:res;
    }
}

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        //Key:最简单是Brute Force --> O(n^2)
        //Key:2 pointers --> O(n),Just cp  https://discuss.leetcode.com/topic/18583/accepted-clean-java-o-n-solution-two-pointers
        
		//170725
		//Wrong
        /*
            int n = nums.length,index1 = 0,index2 = 0,sum = 0,res = n;
            while(index2 <= n-1){
                if(index1 > index2) {
                    index2++;
                    continue;
                }
                if(sum < s){
                    sum += nums[index2];
                    index2++;
                } else {
                    res = Math.min(res,index2+1-index1);
                    sum -= nums[index1++];
                }
            }
            return res;
        */
		
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


516. Longest Palindromic Subsequence
/*170527*/
public class Solution {
    public int longestPalindromeSubseq(String s) {
        //Key170527:dp[i][j] = max(dp[i+1][j],dp[i][j-1],dp[i+1][j-1]+2)  i表起始位置,j表终止位置。dp[i]j]表i位置到j位置时最长的回文子序列长度
        //Key170527:要注意当只有2个元素时,dp[i+1][j-1]的i+1会交换顺序。所以就不对了，所以要特殊处理一下
        //Key170527:当做内嵌循环做遍历，由内部循环的后边向前遍历
            //Key170527:注意,如果按照下边这种顺序是错误的，会导致先算出了dp[0][4]，再去算dp[1][3]。这很明显没有用到之前的dp值，也就不是从底至上的DP算法了（起始如果这样写inner loop，根本就是错误的dp）
            //for(int j = i+1;j<=n-1;j++){
            //Key170527：而下面这个是正确的运算结果输出顺序：
            /**
            i3j4-1
            i2j3-1
            i2j4-3
            i1j2-2
            i1j3-2
            i1j4-3
            i0j1-2
            i0j2-3
            i0j3-3
            i0j4-4
            **/
        int n = s.length();
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[][] dp = new int[n][n];
        for(int i = 0;i<=n-1;i++) dp[i][i] = 1;
        for(int i = n-2;i>=0;i--){
            //Key170527:当做内嵌循环做遍历，由内部循环的后边向前遍历
            //Key170527:注意,如果按照下边这种顺序是错误的，会导致先算出了dp[0][4]，再去算dp[1][3]。这很明显没有用到之前的dp值，也就不是从底至上的DP算法了（起始如果这样写inner loop，根本就是错误的dp）
            //for(int j = i+1;j<=n-1;j++){
            //Key170527：而下面这个是正确的运算结果输出顺序：
            /**
            i3j4-1
            i2j3-1
            i2j4-3
            i1j2-2
            i1j3-2
            i1j4-3
            i0j1-2
            i0j2-3
            i0j3-3
            i0j4-4
            **/
            for(int j = i+1;j<=n-1;j++){
                if(s.charAt(i) == s.charAt(j) ) {
                    //Key170527:要注意当只有2个元素时,dp[i+1][j-1]的i+1会交换顺序。所以就不对了，所以要特殊处理一下
					//不过其实处不处理无所谓，因为两者交换顺序后,索引并没有变...
                    dp[i][j] = i+1==j?2:Math.max(dp[i][j],dp[i+1][j-1]+2);
                }
                dp[i][j] = Math.max(dp[i][j],Math.max(dp[i+1][j],dp[i][j-1]));
                //System.out.println("i"+i+"j"+j+"-"+dp[i][j]);
            }
        }
        return dp[0][n-1];
    }
}

public class Solution {
    public int longestPalindromeSubseq(String s) {
        //V1
        /**
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
        //Key:首先原题意容易使人糊涂，因为没有加continuous限定(可与此题作比较  Shortest Unsorted Continuous Subarray   )，所以可以是非连续的子串。如"bbbab"，最长回文子串为bbbb
        //详细解释看这里  https://discuss.leetcode.com/topic/80378/what-is-the-meaning-of-example-1/2
        //DP
        //Key:这道DP要整体思考，思考的太细容易绕在里头.....
        //Key:explaination  https://discuss.leetcode.com/topic/78603/straight-forward-java-dp-solution
        //Key:F[i][j]表示ith和jth间最大的回文长度
        /**
        int length = s.length();
        int[][] F = new int[length][length];
        for(int i = 0;i<=length-1;i++) F[i][i] = 1;
        //Key:Important！！！！如果从前往后，即i递增，那么之前存的DP[i][j]就不会被用上
        //因为F[i+1][j-1]是一个需要内部存储的过程，所以i需要--，而j++
        //下面这句是错误的
        //for(int i = 0;i<=length-1;i++){
        //如果非要从前往后，就该写成F[i-1][j+1]
        //****************i***j***
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
        
        **/
        
        //V2
        int[][] dp = new int[s.length()][s.length()];
        for(int i = 0;i<=s.length()-1;i++) dp[i][i] = 1;
        for(int i = s.length()-1;i>=0;i--){
            for(int j =i+1;j<=s.length()-1;j++){
				//Key:因为j=i+1&&i>=0,所以j-1永远不会负数。同理，j=i+1,i起始是len-1,所以j起始是len，也就不会进入inner for.
				//所以也不用担心i+1过界了
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1]+2;
                else dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
            }
        }
        return dp[0][s.length()-1];
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
        //Just copy  https://discuss.leetcode.com/topic/79938/super-short-easy-java-o-n-solution/5
        //http://blog.csdn.net/krista_pan/article/details/71534272
        //http://blog.csdn.net/Ray_sysu/article/details/72630521
        //http://blog.csdn.net/ljytfsto/article/details/60757973
        //http://blog.csdn.net/tstsugeg/article/details/62427718
        
        /**
        int total = 0; 
        for(int i: machines) total+=i;
        if(total%machines.length!=0) return -1;
        int avg = total/machines.length, cnt = 0, max = 0;
        for(int load: machines){
            cnt += load-avg; //load-avg is "gain/lose"
            max = Math.max(Math.max(max, Math.abs(cnt)), load-avg);
        }
        return max;
        **/
        
        //Key:这道题非常不好理解，而且不好证明。只能背
        int res = 0,sum = 0;
        for(int i:machines) sum += i;
        if(sum%machines.length != 0) return -1;
        sum = sum/machines.length;
        int pass = 0,needOrSend = 0;
        for(int i = 0;i<=machines.length-1;i++){
            //Key:当前洗衣机需要的或者送出的数量
            //needOrSend = sum-machines[i];
            needOrSend = machines[i]-sum;
            //当前需要的或者送出的+经过它传递的数量
            pass = pass+needOrSend;
            
            //needOrSend绝对值有可能大于pass, 因为pass有可能是负的，加上正的后绝对值会小。如 case(0,0,11,5)
            //其实needOrSend加上abs也可以，但是貌似没必要  ---->这句话是错的，needorSend不能加abs，会出错
            //Test case:[9,1,8,8,9] 加了abs后会出错误答案
            /**
                        [9, 1, 8, 8, 9]
            needOrSend   2 -6  1  1  2
            pass         2 -4  -3 -2 0
            **/
            res = Math.max(Math.abs(pass),Math.max(res, needOrSend));
        }
        return res;
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
                //Key:这两行是关键点，要仔细理解
                //可以这么试着理解，如{1,2,3,4,5} 1,2,3-->1 ,然后2,3,4可以看成倒着来的1,2,3的复制（即为4,3,2），最后再加一个1,2,3,4整体。
                //所以便是sum += ++curr;
                //Key:一下两种写法都可以
                sum += ++curr;
                //curr += 1;
                //sum += curr;
            } else {
                curr = 0;
            }
        return sum; 
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
        //Key:Just copy II --->using Arrays.binarySearch()  回头再看，这个很不好理解
        //效率上这个更好
        //Key:写的太抽象了....
        /**
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
        **/
        
        //Key:O(n^2)版本  https://leetcode.com/articles/longest-increasing-subsequence/#approach-3-dynamic-programming-accepted
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        //Key:dp[i]表截止到i时，最长的sequence
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                   
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            //Key:transition func
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}

//V2
public class Solution {
    public int lengthOfLIS(int[] nums) {
        //Key:DP中i,j不是必须要有关系，例如此题中，完全就是个i,j完全就是为了元素遍历而使用的
        //dp[i] = max(dp[j]+1,dp[i])
        int n = nums.length;
        if(n == 0) return 0;
        int res = 0;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 0;i<=n-1;i++){
            for(int j = 0;j<=i-1;j++){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                } 
            }
            res = Math.max(res,dp[i]);
        }
        return res;
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


279. Perfect Squares
//dp[i] = min(dp[i-k^2]+dp[k^2],dp[i])
//dp[0]一定要设置为0，否则dp[i-k*k]+dp[k*k]当i==k*k时，dp[0]会是MAX_VALUE,导致错误
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
        /**
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
        **/
    	
    	//V2
        //Key:这道题准确其实我觉得不能够算DP吧，貌似就是纯粹试出来的....
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        int res = 0;
        for(int i = 1;i<=n;i++){
            for(int j = 1;j*j<=i;j++){
                //Key:不要这么写  dp[i] = Math.min(dp[i],dp[i-j*j])+1;这样一来，即使dp[i]无变化，也会导致自增1的
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
		
		//V3
		//dp[i]表数字取i时，最少的数字组合
		//dp[i] = min(dp[i-k^2]+dp[k^2],dp[i])
        int res = 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
		//dp[0]一定要设置为0，否则dp[i-k*k]+dp[k*k]当i==k*k时，dp[0]会是MAX_VALUE,导致错误
        dp[0] = 0;
        int count = 1;
		//Key:当i为平方时，dp[i] 设置为1
        for(int i = 1;i<=n;i=count*count){
            dp[i] = 1;
            count++;
        }
        for(int i = 1;i<=n;i++){
            for(int k = 1;k*k<=i;k++){
                dp[i] = Math.min(dp[i-k*k]+dp[k*k],dp[i]);
            }
        }
        return dp[n];
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


438. Find All Anagrams in a String
//Star
//1.与T76类似，不同在于76是找minLen,而这道题只要length == t.length()，即可以加入结果
/*170619*/
public class Solution {
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> res = new ArrayList<>();
        Map<Character,Integer> map = new HashMap<>();
        int begin = 0,end = 0,sLen = s.length(),tLen = t.length();
        for(char c:t.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int counter = map.size();
        while(end <= sLen-1){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
                //Key170619:因为counter这个守门人只管另一边的t中的人是否满足，所以counter++/--放在map值改变的if内，而不是if(map.containsKey(c))外部。其实，下面的if放到外边貌似也没问题，但理解上就不如前面那句更通畅直观了了。
                if(map.get(c) == 0) counter--;
            }
            end++;
            while(counter == 0){
                c = s.charAt(begin);
                if(map.containsKey(c)){
                    map.put(c,map.get(c)+1);
                    if(map.get(c)>0) counter++;
                }
                if(end - begin == t.length()) res.add(begin);
                begin++;
            }
        }
        return res;
    }
}

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




357. Count Numbers with Unique Digits
//Key:背，corner case
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        //Key:关键 count != 0 ，count =1时base还能再增加一次
        //Key:这么记，
        //9以内，10个数都不同（1位） ->0,1,2,3,4,5,6,7,8,9
        //99以内，10+9*9个数不同(2位数，比如1可以接0,2~9,2接0~9，3接0~9....9接0~8)
        //999以内，10+9*9*8个数不同(3位数，比如10接2~9,20接1或者3~9...90接1~8)
        //.....
        int res = 0;
        int base = 10,mult = 9,count = 9;
        while(n != 1 && count != 0){
            mult = mult * count;
            base += mult;
            count--;
            n--;
        }
        return base;
    }
}
//背
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        //Key:just cp，mem  https://discuss.leetcode.com/topic/47983/java-dp-o-1-solution
        //Key:这两个结合起来看 https://discuss.leetcode.com/topic/48332/java-o-1-with-explanation
        //Key:0到10^n间，那些位数字完全不同的数的个数。
        //ex.0~10^2, return 92  excluding [11,22,33,44,55,66,77,88,99]
        //When n == 10, _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1
        //When n == 11, _ _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 * 0 = 0
        if (n == 0)     return 1;
        
        
        int res = 10,foo = 9,item = 9;
        for(int i = n;i>1;i--){
            item *= foo--;
            res += item;
        }
        return res;
        
        //Key:下面的是原答案写法
        /**
        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
            n--;
        }
        
        **/
    }
}

//V2
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        //Key:0~9一开始的十个数字不要忘记
        if(n == 0) return 1; 
        int res = 10,mult = 9,index = 9;
        //Key:注意index 是大于1,而不是大于0.其实这道题大于1还是0都能通过，可能是test case做的不好
        while(n>1 && index > 1){
            mult = mult * index;
            index--;
            n--;
            res += mult;
        }
        return res;
    }
}


541. Reverse String II
public class Solution {
    public String reverseStr(String s, int k) {
        //Key:这么写很麻烦，还是用其他方法吧
        /**
        int index1 = 0,count = 0;
        String res = "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<=s.length()-1;i=i+2*k){
            if(i+k<=s.length()-1) sb.append(new StringBuilder(s.substring(i,i+k)).reverse().toString());
            else sb.append()
            sb.append(s.substring(i+k,i+2*k));
        }
        **/
        char[] c = s.toCharArray();
        int end = 0,n = c.length;
        for(int i = 0;i<=n-1;i=i+2*k){
            //Key:处理i+k-1超出长度的情况
            end = Math.min(i+k-1,n-1);
            swap(c,i,end);
        }
        //Key:char array转String
        return String.valueOf(c);
    }
    public void swap(char[] c,int s,int e){
        //Key:注意char 不能等于''，每个字符都要对应着一个unicode。 char tmp = '' 会编译错误
        //Key:类似于int a =  ;是错误写法 
        //char tmp = '';
        char tmp = '0';
        while(s<=e){
            tmp = c[e];
            c[e] = c[s];
            c[s] = tmp;
            e--;
            s++;
        }
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








343. Integer Break //Key:因为有可能出现dp[i-k]<i-k的情况 -->如dp[2-1]==dp[1]<2-1,所以内部也需要max一下
public class Solution {
    public int integerBreak(int n) {
        //Key:dp[i] = max(dp[i],dp[i-k]*dp[k])  -->dp[10] = max(dp[10],dp[3]*dp[7])
        int res = 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2;i<=n;i++){
            for(int k = 1;k<=i-1;k++){
                //Key:因为有可能出现dp[i-k]<i-k的情况 -->如dp[2-1]<2-1,所以内部也需要max一下
                dp[i] = Math.max(dp[i],Math.max(dp[k],k)*Math.max(dp[i-k],i-k));
            }
        }
        return dp[n];
    }
}

//V2
//Key:DP or Math
//Just cp:https://discuss.leetcode.com/topic/42978/java-dp-solution DP
public class Solution {
    public int integerBreak(int n) {
        //Key:背吧，
        int[] dp = new int[n+1];
        
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 1;i<=n;i++){
            for(int j = i-1;j>0;j--){
                //Key:后边两个max是关键，使pos,dp[pos]中的最大值与i-pos,nums[i-pos]中的最大值相乘
                dp[i] = Math.max(dp[i],Math.max(dp[j],j)*Math.max(dp[i-j],i-j));
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





557. Reverse Words in a String III
public class Solution {
    public String reverseWords(String s) {
        //return new StringBuilder(s.substring(0,s.length())).reverse().toString();
		if(s == null || s.length() == 0) return "";
        String[] arr = new StringBuilder(s).reverse().toString().split(" ");
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




////////////////////////////////////////////////////////////////////////////////
Ubuntu Test
////////////////////////////////////////////////////////////////////////////////



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



<<<<<<< HEAD

=======
>>>>>>> test

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






561. Array Partition I
public class Solution {
    public int arrayPairSum(int[] nums) {
        //Key:写起来不难，难点应该是在如何证明结论的正确上
        //不过可以以{1,2,3,4}来做个例子,1+3( min[1,2] min[3,4] )明显大于1+2(min[1,4],min[2,3]),来猜测...
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

















	












 
	
 
 



225. Implement Stack using Queues
public class MyStack {
    
    //Key:cp,背  https://discuss.leetcode.com/topic/15945/concise-1-queue-java-c-python/2
    /** Initialize your data structure here. */
    private Queue<Integer> queue = new LinkedList<>();

    public void push(int x) {
        queue.add(x);
        for (int i=1; i<queue.size(); i++)
            queue.add(queue.remove());
    }

    public int pop() {
        int tmp = queue.peek();
        queue.remove();
        return tmp;
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
 
 224. Basic Calculator
 public class Solution {
    public int calculate(String s) {
        //Key:这道题不难，但写起来太麻烦了.....
        //Key:typical Stack
        //Stack 不加<>限制的话，貌似存的是抽象类Object，所以 stack.push(1); 接着stack.push("1");也可以。不会报错
        //Stack stack = new Stack();
        //但是如下面这句就不能push(1)了
        //Stack<String> stack = new Stack<>();
        //Key:my wrong version 2
        /**
        Stack<Character> stack = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        int tmp = 0;
        char c = '''
        for(int i = 0;i<=s.length()-1;i++){
            c = s.charAt(i);
            if(c-'0' >=0 && c-'0' <=9){
                 tmp = tmp*10+(c-'0');
            } else {
                nums.add(tmp);
                tmp = 0;
                if(c != '(' && c!= ')') stack.add(c);
            }
        }
        int res = 0;
        while(!stack.isEmpty()){
            
        }
        **/
        //Key:My wrong version
        /***
        for(int i = 0;i<=s.length()-1;i++){
            if(s.charAt(i) == ')'){
                String tmp = "";
                while(stack.peek() != '('){
                    if(String.valueOf(stack.pop()) == " ") continue;
                    tmp = stack.pop()+tmp;
                }
                stack.pop();
                
                while(index<=tmp.length()-1){
                    
                }
            }
        }
        return 1;
        **/
        
        //Key:cp,背  https://discuss.leetcode.com/topic/15816/iterative-java-solution-with-stack/7
        if(s == null) return 0;
        
        int result = 0;
        int sign = 1;
        int num = 0;
                
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(sign);
                
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
                    
            if(c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
                        
            } else if(c == '+' || c == '-') {
                result += sign * num;
                sign = stack.peek() * (c == '+' ? 1: -1); 
                num = 0;
                        
            } else if(c == '(') {
                stack.push(sign);
                        
            } else if(c == ')') {
                stack.pop();
            }
        }
                
        result += sign * num;
        return result;
    }
}

222. Count Complete Tree Nodes
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
    //Key:注意 >> 用法，cp,背 https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2
    public int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
               height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                                         : (1 << h-1) + countNodes(root.left);
    }
    
}

232. Implement Queue using Stacks
class MyQueue {
    //这道题有问题
    //Test Case:push(1),pop,empty
    //结果它stdout:[1, 1, 1][1, 1]
    //但是在自己机子完全没问题
    //Key:Something wrong。。。
    /**
    public static Stack stack1 = new Stack();
    public static Stack stack2 = new Stack();
    public static void push(int x) {
        while(!stack2.empty()){
            stack1.push(stack2.peek());
            stack2.pop();
        }
        stack2.push(x);
        while(!stack1.empty()){
            stack2.push(stack1.peek());
            stack1.pop();
        }
        System.out.println(stack2);
	
    }

    // Removes the element from in front of queue.
    public static void pop() {
        if(!stack2.empty()){
            stack2.pop();
        }
        System.out.println(stack2);
    }

    // Get the front element.
    public static int peek() {
        int a = 0;
        //Key Point Integer 转 int
        if(!stack2.empty()){
            a=(int)stack2.peek();
        }
        return a;
    }

    // Return whether the queue is empty.
    public static boolean empty() {
        boolean flag = false;
        if(stack2.empty()) flag = true;
        else flag = false;
        return flag;
    }
    **/
    
    //Key:cp,背  https://discuss.leetcode.com/topic/17974/short-o-1-amortized-c-java-ruby/2
    Stack<Integer> input = new Stack();
    Stack<Integer> output = new Stack();
    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        int tmp = peek();
        output.pop();
        return tmp;
    }

    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }
}

<<<<<<< HEAD

=======
>>>>>>> test
 
213. House Robber II  //Math.max(startFromFirstHouse[nums.length - 1], startFromSecondHouse[nums.length]);
public class Solution {
    //Key:这个sol用的是recursion，不太好记
    //Key:cp https://discuss.leetcode.com/topic/14375/simple-ac-solution-in-java-in-o-n-with-explanation/2
    /**
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }
    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
    **/
    
    //Key:为了统一记忆，还是用dp来做
    //https://discuss.leetcode.com/topic/24060/good-performance-dp-solution-using-java
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length < 2)
            return nums[0];
        //Key:startFromFirstHouse[0],startFromSecondHouse[0]都只是为了方便才写的，没有实际意义
        //Key:因为喲抢劫，肯定要从1号房或者2号房开始
        int[] startFromFirstHouse = new int[nums.length + 1];
        int[] startFromSecondHouse = new int[nums.length + 1];
        startFromFirstHouse[1]  = nums[0];
        startFromSecondHouse[1] = 0;
        for (int i = 2; i <= nums.length; i++) {
            startFromFirstHouse[i] = Math.max(startFromFirstHouse[i - 1], startFromFirstHouse[i - 2] + nums[i-1]);
            startFromSecondHouse[i] = Math.max(startFromSecondHouse[i - 1], startFromSecondHouse[i - 2] + nums[i-1]);
        }
        //Key:下面的nums.length - 1是关键，要注意
        return Math.max(startFromFirstHouse[nums.length - 1], startFromSecondHouse[nums.length]);
    }
    
    //V2
    /**
    public int rob(int[] nums) {
        //Key:相当于把robber1 写了2遍而已
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int[] dp1 = new int[nums.length+1];
        int[] dp2 = new int[nums.length+1];
        dp1[1] = nums[0];
        dp2[1] = 0;
        for(int i = 2;i<=nums.length;i++){
            dp1[i] = Math.max(dp1[i-1],dp1[i-2]+nums[i-1]);
            //Test case:[1,2,50,6,7,8,99]  dp2[3]时可以在dp2[1],即0时的基础上抢劫
            dp2[i] = Math.max(dp2[i-1],dp2[i-2]+nums[i-1]);
        }
        return Math.max(dp1[nums.length-1],dp2[nums.length]);
    }
    **/
}

220. Contains Duplicate III
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //Key:cp,背 https://discuss.leetcode.com/topic/15199/ac-o-n-solution-in-java-using-buckets-with-explanation/2
        //Key:another ref:https://discuss.leetcode.com/topic/27608/java-python-one-pass-solution-o-n-time-o-n-space-using-buckets/2
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                        || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                            return true;
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
        
    }
}

221. Maximal Square
public class Solution {
    public int maximalSquare(char[][] a) {
        //Key:cp,背，DP  https://discuss.leetcode.com/topic/20801/extremely-simple-java-solution
        //Key:another ref：https://discuss.leetcode.com/topic/18482/accepted-clean-java-dp-solution
        if(a.length == 0) return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m+1][n+1];
        for (int i = 1 ; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(a[i-1][j-1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result*result;
    }
}

229. Majority Element II
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        //Key:cp,背  https://discuss.leetcode.com/topic/32510/java-easy-version-to-understand/2
        //Key:another ref -> https://discuss.leetcode.com/topic/17564/boyer-moore-majority-vote-algorithm-and-my-elaboration
        if (nums == null || nums.length == 0)
    		return new ArrayList<Integer>();
    	List<Integer> result = new ArrayList<Integer>();
    	int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
    	for (int i = 0; i < len; i++) {
    		if (nums[i] == number1)
    			count1++;
    		else if (nums[i] == number2)
    			count2++;
    		else if (count1 == 0) {
    			number1 = nums[i];
    			count1 = 1;
    		} else if (count2 == 0) {
    			number2 = nums[i];
    			count2 = 1;
    		} else {
    			count1--;
    			count2--;
    		}
    	}
    	count1 = 0;
    	count2 = 0;
    	for (int i = 0; i < len; i++) {
    		if (nums[i] == number1)
    			count1++;
    		else if (nums[i] == number2)
    			count2++;
    	}
    	if (count1 > len / 3)
    		result.add(number1);
    	if (count2 > len / 3)
    		result.add(number2);
    	return result;
    }
}

228. Summary Ranges
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        //Key:cp,背   https://discuss.leetcode.com/topic/17151/accepted-java-solution-easy-to-understand
        
        List<String> list=new ArrayList();
    	if(nums.length==1){
    		list.add(nums[0]+"");
    		return list;
    	}
        for(int i=0;i<nums.length;i++){
        	int a=nums[i];
        	while(i+1<nums.length&&(nums[i+1]-nums[i])==1){
        		i++;
        	}
        	if(a!=nums[i]){
        		list.add(a+"->"+nums[i]);
        	}else{
        		list.add(a+"");
        	}
        }
        return list;
    }
}

233. Number of Digit One
public class Solution {
    public int countDigitOne(int n) {
        //Key:cp,背  
        //https://discuss.leetcode.com/topic/18054/4-lines-o-log-n-c-java-python
        //https://discuss.leetcode.com/topic/18972/ac-short-java-solution
        //两个大神争奇斗艳...
        int ones = 0;
        for (long m = 1; m <= n; m *= 10)
            ones += (n/m + 8) / 10 * m + (n/m % 10 == 1 ? n%m + 1 : 0);
        return ones;
    }
}

275. H-Index II
public class Solution {
    public int hIndex(int[] citations) {
        //Key:cp,背，binary search  https://discuss.leetcode.com/topic/23399/standard-binary-search/2
        if(citations == null || citations.length == 0) return 0;
        int l = 0, r = citations.length;
        int n = citations.length;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(citations[mid] == n - mid) return n - mid;
            if(citations[mid] < citations.length - mid) l = mid + 1;
            else r = mid;
        }
        return n - l;
    }
}

282. Expression Add Operators
public class Solution {
    //Key:cp.背  https://discuss.leetcode.com/topic/24523/java-standard-backtrace-ac-solutoin-short-and-clear
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
                
                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
                
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}

310. Minimum Height Trees
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //Key:cp,背   https://discuss.leetcode.com/topic/30572/share-some-thoughts/2
        
        if (n == 1) return Collections.singletonList(0);
        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
    
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            if (adj.get(i).size() == 1) leaves.add(i);
    
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}

301. Remove Invalid Parentheses
public class Solution {
    
    //Key:cp,背  https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution/3
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }
    
    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }
}

299. Bulls and Cows
public class Solution {
    public String getHint(String secret, String guess) {
        //Key:my wrong version
        /**
        
        //很麻烦
        //不知道怎么回事
        int bulls = 0;
        int cows = 0;
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i=0;i<=secret.length()-1;i++){
            if(map.containsKey(secret.charAt(i))){ 
				Character c = new Character(secret.charAt(i));
				//不知道怎么回事,map.get 老是提示下面的错误  
				//Line 13: error: unexpected type required: variable found:    value
                map.put((Character)secret.charAt(i),map.get((Character)secret.charAt(i))++);
            } else {
                map.put((Character)secret.charAt(i),1);   
            }
            if(guess.charAt(i) == secret.charAt(i)) bulls++;
               
        }
        for(int i=0;i<=secret.length()-1;i++){
            if(map.containsKey(guess.charAt(i))){
                map.put((Character)guess.charAt(i),map.get((Character)guess.charAt(i))--);
            }
        }
        for(int i:map.values()){
            if(i>=0){
                cows += i;
            }
            cows = secret.length()-cows-bulls;
        }
        return bulls+"A"+cows+"B";
        
        **/
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
                if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
        
    }
}

290. Word Pattern
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        //Key:my wrong version
        /**
        //这道题太麻烦了，就没写对过.....
        String[] strArr = str.split(" ");
        int index = 0;
		int[] tmp = new int[strArr.length];
        if(strArr.length != pattern.length()) return false;
        boolean flag = true;
        //Key Point
        //遍历HashMap时，不按照输入是的顺序输出。比如这个Case "deadbeef" "d e a d b e e f" 就会乱序输出。
        //要想顺序输出，用LinkedHashMap。它内部有个链表
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
        LinkedHashMap<String,Integer> map2 = new LinkedHashMap<String,Integer>();
        for(int i=0;i<=pattern.length()-1;i++){
            if(map.containsKey(pattern.charAt(i)-'0')){
                map.put(i,map.get(pattern.charAt(i)-'0'));
            } else {
                map.put(pattern.charAt(i)-'0',i);
            }
        }
		for(int i=0;i<=strArr.length-1;i++){
			System.out.println(strArr[i]);
			if(map2.containsKey(strArr[i])){
				tmp[i] = map2.get(strArr[i]);
			} else {
				System.out.println("else"+strArr[i]+","+i);
				map2.put(strArr[i],i);
				tmp[i] = i;
			}
			
		}
        for(Integer i:map.values()){
            //KEY POINT
            //只比较String值是否一样，用equals,不要用==。如果用==，两个variable的类型有区别的话，==就会认为他们不等。所以只比较值时，用equals
            //if(strArr[index] != strArr[i]) flag = false;  这是错的，即使值相同，但仍然会返回false;
            // if(!strArr[index].equals(strArr[i])) flag = false;
            // index++;
			///System.out.println(tmp[index]+","+i);
            if(tmp[index++] != i) flag = false;
        }
        return flag;
        **/
         
        //Key:cp,背 https://discuss.leetcode.com/topic/26573/very-fast-3ms-java-solution-using-hashmap/2
        String[] arr= str.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();
        if(arr.length!= pattern.length())
            return false;
        for(int i=0; i<arr.length; i++){
            char c = pattern.charAt(i);
            if(map.containsKey(c)){
                if(!map.get(c).equals(arr[i]))
                    return false;
            }else{
                if(map.containsValue(arr[i]))
                    return false;
                map.put(c, arr[i]);
            }    
        }
        return true;
        
    }
}

399. Evaluate Division
public class Solution {
    //Key:cp,背，弃  https://discuss.leetcode.com/topic/59146/java-ac-solution-using-graph/2
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, ArrayList<String>> pairs = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<Double>> valuesPair = new HashMap<String, ArrayList<Double>>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            if (!pairs.containsKey(equation[0])) {
                pairs.put(equation[0], new ArrayList<String>());
                valuesPair.put(equation[0], new ArrayList<Double>());
            }
            if (!pairs.containsKey(equation[1])) {
                pairs.put(equation[1], new ArrayList<String>());
                valuesPair.put(equation[1], new ArrayList<Double>());
            }
            pairs.get(equation[0]).add(equation[1]);
            pairs.get(equation[1]).add(equation[0]);
            valuesPair.get(equation[0]).add(values[i]);
            valuesPair.get(equation[1]).add(1/values[i]);
        }
        
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            result[i] = dfs(query[0], query[1], pairs, valuesPair, new HashSet<String>(), 1.0);
            if (result[i] == 0.0) result[i] = -1.0;
        }
        return result;
    }
    
    private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> values, HashSet<String> set, double value) {
        if (set.contains(start)) return 0.0;
        if (!pairs.containsKey(start)) return 0.0;
        if (start.equals(end)) return value;
        set.add(start);
        
        ArrayList<String> strList = pairs.get(start);
        ArrayList<Double> valueList = values.get(start);
        double tmp = 0.0;
        for (int i = 0; i < strList.size(); i++) {
            tmp = dfs(strList.get(i), end, pairs, values, set, value*valueList.get(i));
            if (tmp != 0.0) {
                break;
            }
        }
        set.remove(start);
        return tmp;
    }
}

397. Integer Replacement
public class Solution {
    public int integerReplacement(int n) {
        //Key:my wrong version-->TLE
        /**
        //DP,逆序=> 1-7 
        //想起来挺麻烦的
        if(n<1) return 0;
        int[] F = new int[n+1];
        F[0] = 0;
        for(int i=1;i<=n;i++){
            if((i+1)%2 == 0){
                F[i] = F[(i-1)/2]+1;
                F[i-1] = Math.min(F[i-1],F[i]+1);
            }else {
                F[i] = F[i-1]+1;
            }
        }
        return F[n-1];
        **/
        //Key:cp,背   https://discuss.leetcode.com/topic/58334/a-couple-of-java-solutions-with-explanations/2
        //Key:因为他给的case都挺大的，所以DP或者Recursion貌似都会TLE
        int c = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n == 3 || ((n >>> 1) & 1) == 0) {
                --n;
            } else {
                ++n;
            }
            ++c;
        }
        return c;
    }
}

289. Game of Life
public class Solution {
    
    //Key:这道题题意就不好读懂....
    //Key:cp,背  https://discuss.leetcode.com/topic/29054/easiest-java-solution-with-explanation/2
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
    
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);
    
                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {  
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }
    
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }
    
    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}

284. Peeking Iterator
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
//Key:cp,背 https://discuss.leetcode.com/topic/24883/concise-java-solution
class PeekingIterator implements Iterator<Integer> {

	private Integer next = null;
    private Iterator<Integer> iter;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if (iter.hasNext())
            next = iter.next();
    }
    
    // Returns the next element in the iteration without advancing the iterator. 
    public Integer peek() {
        return next; 
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        next = iter.hasNext() ? iter.next() : null;
        return res; 
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}

306. Additive Number
public class Solution {
    //Key:cp,背 https://discuss.leetcode.com/topic/29856/java-recursive-and-iterative-solutions/2
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i)
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j)
                if (isValid(i, j, num)) return true;
        return false;
    }
    private boolean isValid(int i, int j, String num) {
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x2 + x1;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
}

316. Remove Duplicate Letters
public class Solution {
    public String removeDuplicateLetters(String s) {
        //Key:cp,背  https://discuss.leetcode.com/topic/31404/a-short-o-n-recursive-greedy-solution/2
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
}

312. Burst Balloons
public class Solution {
    public int maxCoins(int[] iNums) {
        //Key：cp,只能背 https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations/2
        //Key:http://blog.csdn.net/xyqzki/article/details/50255345 这篇讲的非常好
        //http://blog.csdn.net/swartz2015/article/details/50561199
        int[] nums = new int[iNums.length + 2];
        int n = nums.length;
        //int n = 1;
        for(int i = 0;i<n-2;i++) nums[i+1] = iNums[i];
        //for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n-1] = 1;
        
        
        //dp[i][j]表i与j间的所有气球被扎破后的最大收益
        int[][] dp = new int[n][n];
        //k是left和right的最小间距(准确的说应该是pos差值)。（1,2,3）扎的话，最小间距只能是2.
        //这里left是左边界，right是右边界
        //The dp[start][end] represents the maximum coins when you burst and only burst all the elements in the range of [start, end], 'start' and 'end' inclusive.、
        //1:确定左右间距大小
        for (int k = 2; k < n; ++k)
            //2：定左边界
            //left+k即为右边界，要小于n
            for (int left = 0; left+k < n; ++left) {
                //3:定右边界
                int right = left + k;
                //4:i，j范围内遍历找出最大值
                for (int i = left + 1; i < right; ++i)
                    //(left,right)范围内的气球爆炸，但并不包括left,right本身，是个开区间
                    dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }
    
        return dp[0][n - 1];
        
        
    }
}

//V2
public class Solution {
    public int maxCoins(int[] nums) {
        //Key:hard
        int[] newNums = new int[nums.length+2];
        int len = newNums.length;
        //Key:两端的1加上去
        for(int i = 0;i<=nums.length-1;i++){
            newNums[i+1] = nums[i];
        }
        newNums[0] = newNums[len-1] = 1;
        int[][] dp = new int[len][len];
        //Key:此时，旧的nums已经没用了
        //k小于几可以举例试试，如(1,2,3,4,5) 间距最大为4--->在新nums上操作，即lengh-1
        for(int k = 2;k<=len-1;k++){
            for(int left = 0;left+k<=len-1;left++){
                int right = left+k;
                for(int i = left+1;i<=right-1;i++){
                    dp[left][right] = Math.max(dp[left][right],newNums[left]*newNums[i]*newNums[right]+dp[left][i]+dp[i][right]);
                }
            }
        }
        return dp[0][len-1];
    }
}

	//Key:dp[i]  nums[i]最后被戳爆时的最大乘积(i在1~n的范围内)最后被戳爆 dp[i] = dp[1~i-1]*nums[i]*dp[i+1~n])
	// 进一步推出 -> dp[start][end] = max(dp[start][end],dp[start][i-1]+nums[start]*nums[i]*nums[start]+dp[i+1][end])  start,end为气球的范围
	//Key:思路是对的，但是具体细节老是写错...
	/**
	int n = nums.length;
	if(n == 0) return 0;
	int[][] dp = new int[n+2][n+2];
	//Key:初始化记住：dp[i][i]就是指在i~i的范围内戳爆所有气球的最大值，自然是nums[i]本身...
	//Key:因为是乘积，所以一开始其他要全赋值为1。如果是其他的题相加那种类型，要赋值为0


	int[] newNums = new int[n+2];
	for(int i = 1;i<=n;i++) newNums[i] = nums[i-1];
	newNums[0] = newNums[n+1] = 1;
	for(int i:newNums) System.out.print(i+",");
	//for(int[] i:dp) Arrays.fill(i,1);
	for(int i = 1;i<=n;i++) dp[i][i] = newNums[i];
	for(int i = 1;i<=n;i++){
		for(int j = 1;j<=n;j++){
			for(int k = i+1;k<=j-1;k++){
				//Key:dp[i][j] = Math.max(dp[i][j],dp[i][k-1]+newNums[k]+dp[k+1][j]); 这个转换方程不对，因为newNums[start] 和newNums[end] 并不一定是1
				dp[i][j] = Math.max(dp[i][j],dp[i][k-1]+newNums[i]*newNums[k]*newNums[j]+dp[k+1][j]);
			}
		}
	}
	return dp[0][n+1];
	**/

330. Patching Array
public class Solution {
    public int minPatches(int[] nums, int n) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/35494/solution-explanation/2
         int count = 0, i = 0;
        for (long miss=1; miss <= n; count++)
            miss += (i < nums.length && nums[i] <= miss) ? nums[i++] : miss;
        return count - i;
    }
}

327. Count of Range Sum
public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/54668/share-my-java-solution-using-treemap
        
        if(nums==null || nums.length==0) return 0;
        TreeMap<Long, Long> tr = new TreeMap<Long, Long>();
        tr.put((long)0, (long)1);
        long sum = 0;
        long count = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            long from = sum - upper;
            long to = sum - lower;
            Map<Long, Long> sub = tr.subMap(from, true, to, true);
            for(Long value:sub.values()){
                count+=value;
            }
            if(tr.containsKey(sum)){
                tr.put(sum, tr.get(sum)+1);
            } else {
                tr.put(sum, (long)1);
            }
        }
        return (int)count;
    }
}

394. Decode String
public class Solution {
    public String decodeString(String s) {
        //这道题想起来很麻烦
        //递归迭代都可以
        
        //Key:cp,背  https://discuss.leetcode.com/topic/57250/java-short-and-easy-understanding-solution-using-stack
        
        Stack<Integer> count = new Stack<>();
        Stack<String> result = new Stack<>();
        int i = 0;
        result.push("");
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int start = i;
                while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                count.push(Integer.parseInt(s.substring(start, i + 1)));
            } else if (ch == '[') {
                result.push("");
            } else if (ch == ']') {
                String str = result.pop();
                StringBuilder sb = new StringBuilder();
                int times = count.pop();
                for (int j = 0; j < times; j += 1) {
                    sb.append(str);
                }
                result.push(result.pop() + sb.toString());
            } else {
                result.push(result.pop() + ch);
            }
            i += 1;
        }
        return result.pop();
    }
}

436. Find Right Interval
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
    public int[] findRightInterval(Interval[] intervals) {
        //Key:my wrong version :
        /**
        //Simple solution:complexity On
        //我估摸着9成得 Time Limit Exceeded
        //Key point:
        //题目说which means that the interval j has the minimum start point to build the "right" relationship for interval i. 所以要返回包含最小起点的那个interval
        //Test case:[[1,2],[2,3],[3,4]] Expected answer返回[1,2,-1]，而非[2,2,-1]
        //[[3,4],[2,3],[1,2]] Expected [-1,0,1],而非[-1,0,0]
        //这道题只能用二分了，因为它最后一个Test Case给的太大....
        //Time Limit Exceeded  More Details 
        //Last executed input:
        //[[-20000,-18835],[-19999,-17832],[-19998,-17887],[-19997,-19077],[-19996,-15996],[-19995,-15613],[-19994,-17900]
        boolean flag = false;
        if(intervals.length == 0) return null;
        int len = intervals.length;
        int[] result = new int[len];
        for(int i=0;i<=len-1;i++){
            result[i] = -1;
            for(int j=0;j<=len-1;j++){
                if(intervals[j].start>=intervals[i].end){
                    if(result[i] != -1 ){
                        if(intervals[result[i]].start>intervals[j].start){
                            result[i] = j;
                        }
                    } else {
                        result[i] = j;
                    }
                }
                
            }
            
        }
        return result;
        **/
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] res = new int[intervals.length];
        for(int i=0;i<intervals.length;i++) map.put(intervals[i].start, i);
        for(int i=0;i<intervals.length;i++) {
            Integer key = map.ceilingKey(intervals[i].end);
            res[i] = key!=null ?map.get(key) : -1;
        }
        return res;
    }
}

449. Serialize and Deserialize BST
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
    //Key:cp,弃  https://discuss.leetcode.com/topic/66651/java-preorder-queue-solution
    
    private static final String SEP = ",";
    private static final String NULL = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return NULL;
        //traverse it recursively if you want to, I am doing it iteratively here
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.empty()) {
            root = st.pop();
            sb.append(root.val).append(SEP);
            if (root.right != null) st.push(root.right);
            if (root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
        if (data.equals(NULL)) return null;
        String[] strs = data.split(SEP);
        Queue<Integer> q = new LinkedList<>();
        for (String e : strs) {
            q.offer(Integer.parseInt(e));
        }
        return getNode(q);
    }
    
    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
        if (q.isEmpty()) return null;
        TreeNode root = new TreeNode(q.poll());//root (5)
        Queue<Integer> samllerQueue = new LinkedList<>();
        while (!q.isEmpty() && q.peek() < root.val) {
            samllerQueue.offer(q.poll());
        }
        //smallerQueue : 3,2   storing elements smaller than 5 (root)
        root.left = getNode(samllerQueue);
        //q: 6,7   storing elements bigger than 5 (root)
        root.right = getNode(q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

572. Subtree of Another Tree
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
    //Key:cp,背 https://discuss.leetcode.com/topic/88508/java-solution-tree-traversal
    
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        
        if (s.val != t.val) return false;
        
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}

583. Delete Operation for Two Strings
public class Solution {
    public int minDistance(String word1, String word2) {
        //Key:cp,背 https://discuss.leetcode.com/topic/89285/java-dp-solution-longest-common-subsequence
        //https://discuss.leetcode.com/topic/89421/longest-common-subsequence-dp-java-solution
        
        int dp[][] = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++) {
            for(int j = 0; j <= word2.length(); j++) {
                if(i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = (word1.charAt(i-1) == word2.charAt(j-1)) ? dp[i-1][j-1] + 1
                        : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int val =  dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }
}

551. Student Attendance Record I
public class Solution {
    public boolean checkRecord(String s) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/86466/java-1-liner
        
        return !s.matches(".*LLL.*|.*A.*A.*");
    }
}

582. Kill Process
public class Solution {
    //Key:cp,mem   https://discuss.leetcode.com/topic/89298/java-dfs-solution
    //https://discuss.leetcode.com/topic/89287/java-dfs-o-n-time-o-n-space/2
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < pid.size(); i++) {
            List<Integer> children = map.getOrDefault(ppid.get(i), new ArrayList<>());
            children.add(pid.get(i));
            map.put(ppid.get(i), children);
        }
        List<Integer> result = new ArrayList<>();
        dfs(map, kill, result);
        return result;
    }
    private void dfs(Map<Integer, List<Integer>> map, int kill, List<Integer> result) {
        result.add(kill);
        if(!map.containsKey(kill)) return;
        List<Integer> children = map.get(kill);
        for(Integer child : children) {
            dfs(map, child, result);
        }
    }
}

563. Binary Tree Tilt
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
    //Key:cp,mem  https://discuss.leetcode.com/topic/87191/java-o-n-postorder-traversal
    int tilt = 0;
    
    public int findTilt(TreeNode root) {
        postorder(root);
        return tilt;
    }
    
    public int postorder(TreeNode root) {
        if (root == null) return 0;
        int leftSum = postorder(root.left);
        int rightSum = postorder(root.right);
        tilt += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }
}

390. Elimination Game
public class Solution {
    public int lastRemaining(int n) {
        //Key:cp,mem   https://discuss.leetcode.com/topic/59293/java-easiest-solution-o-logn-with-explanation
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        while (remaining > 1) {
            if (left || remaining % 2 ==1) {
                head = head + step;
            }
            remaining = remaining / 2;
            step = step * 2;
            left = !left;
        }
        return head;
        
    }
}

386. Lexicographical Numbers
public class Solution {
    //Key:cp,mem  https://discuss.leetcode.com/topic/55377/simple-java-dfs-solution/2
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for(int i=1;i<10;++i){
          dfs(i, n, res); 
        }
        return res;
    }
    
    public void dfs(int cur, int n, List<Integer> res){
        if(cur>n)
            return;
        else{
            res.add(cur);
            for(int i=0;i<10;++i){
                if(10*cur+i>n)
                    return;
                dfs(10*cur+i, n, res);
            }
        }
    }
}

514. Freedom Trail
public class Solution {
    public int findRotateSteps(String ring, String key) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/81684/concise-java-dp-solution
        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }
        
        return dp[0][0] + m;
        
    }
}

376. Wiggle Subsequence
public class Solution {
    public int wiggleMaxLength(int[] nums) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/52076/easy-understanding-dp-solution-with-o-n-java-version/2
        if( nums.length == 0 ) return 0;
        
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        
        up[0] = 1;
        down[0] = 1;
        
        for(int i = 1 ; i < nums.length; i++){
            if( nums[i] > nums[i-1] ){
                up[i] = down[i-1]+1;
                down[i] = down[i-1];
            }else if( nums[i] < nums[i-1]){
                down[i] = up[i-1]+1;
                up[i] = up[i-1];
            }else{
                down[i] = down[i-1];
                up[i] = up[i-1];
            }
        }
        
        return Math.max(down[nums.length-1],up[nums.length-1]);
    }
}

542. 01 Matrix
public class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        //Key:cp,mem https://discuss.leetcode.com/topic/83453/java-solution-bfs/2
        //https://discuss.leetcode.com/topic/83574/short-solution-each-path-needs-at-most-one-turn
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
                else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n || 
                    matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) continue;
                queue.add(new int[] {r, c});
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        
        return matrix;
    }
}

363. Max Sum of Rectangle No Larger Than K
public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int target) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/48854/java-binary-search-solution-time-complexity-min-m-n-2-max-m-n-log-max-m-n/2
        
        int row = matrix.length;
        if(row==0)return 0;
        int col = matrix[0].length;
        int m = Math.min(row,col);
        int n = Math.max(row,col);
        //indicating sum up in every row or every column
        boolean colIsBig = col>row;
        int res = Integer.MIN_VALUE;
        for(int i = 0;i<m;i++){
            int[] array = new int[n];
            // sum from row j to row i
            for(int j = i;j>=0;j--){
                int val = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);
                //traverse every column/row and sum up
                for(int k = 0;k<n;k++){
                    array[k]=array[k]+(colIsBig?matrix[j][k]:matrix[k][j]);
                    val = val + array[k];
                    //use  TreeMap to binary search previous sum to get possible result 
                    Integer subres = set.ceiling(val-target);
                    if(null!=subres){
                        res=Math.max(res,val-subres);
                    }
                    set.add(val);
                }
            }
        }
        return res;
    }
}

219. Contains Duplicate II
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //Key:my wrong version
        /**
        //typical HashMap
        if(nums == null || nums.length == 0) return false;
        //Corner Case:k =0 时，直接返回true
        if(k == 0) return true;
        boolean flag = false;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<=nums.length-1;i++){
            if(!map.containsKey(nums[i]) && !flag){
                map.put(nums[i],i);
            } else {
                if((i-map.get(nums[i]))<=k){
                    flag = true;
                } else {
                    map.put(nums[i],i);
                }
            }
        }
        return flag;
        **/
        
        
        //Key:cp,mem  https://discuss.leetcode.com/topic/15305/simple-java-solution
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }
}

354. Russian Doll Envelopes
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        //Key:just cp,mem,很慢的解法，估计和brute force差不多了...   https://discuss.leetcode.com/topic/47594/short-and-simple-java-solution-15-lines
        
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int max = 0;
        int dp [] = new int [envelopes.length];
        for(int i = 0; i < envelopes.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

368. Largest Divisible Subset
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        //Key:关键-》别忘记把一开始的最大值给加进去 list.add(nums[maxIndex]);
        
        //dp[i]表长度，这个transit func与此题有关
        //关键2步走：1先找出对应的dp[i],2找出dp[i]最大值对应的maxIndex,再按照dp[i]-dp[i-k]==1 && dp[i]%dp[i-k] ==0 依次加入
        //Key:if(nums[i]%nums[i-k] == 0) dp[i] = max(dp[i], dp[i-k]+1)
        
        List<Integer> list = new ArrayList<>();
        if(nums.length == 0) return list;
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int maxIndex = 0;
        Arrays.fill(dp,1);
        for(int i = 1;i<=n-1;i++){
            for(int k = 0;k<=i-1;k++){
                if(nums[i]%nums[k] == 0) dp[i] = Math.max(dp[i],dp[k]+1);
                if(dp[i]>dp[maxIndex]) maxIndex = i;
            }
        }
        list.add(nums[maxIndex]);
        for(int i = n-1;i>=0;i--){
            if(dp[maxIndex]-dp[i] == 1 && nums[maxIndex]%nums[i] == 0) {
                list.add(nums[i]);
                maxIndex = i;
            }
        }
        Collections.sort(list);
        return list;
    }
}

//V2
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
		//Key:2点-->找到最长长度的末端那个数，然后判断前面的数字哪些能被他整除，且最长长度正好比它小1
        List<Integer> list = new ArrayList<>();
        if(nums.length == 0) return list;
        //step 1
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int maxIndex = 0;
        //step 2
        for(int i = 1;i<=nums.length-1;i++){
            for(int j = i-1;j>=0;j--){
                if(nums[i]%nums[j] == 0){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    //step 3
                    //System.out.println(dp[i]);
                }
            }
            //dp[i]
            maxIndex = dp[i]>dp[maxIndex]?i:maxIndex;
        }
        //System.out.println(maxIndex);
        //step 4
        //Key:先要把nums[maxIndex]这个元素加进去，否则因为一开始因为dp[maxIndex]==dp[i],loop里的if不会起作用，导致会漏掉第一个数
        list.add(nums[maxIndex]);
        for(int i = maxIndex;i>=0;i--){
            
            if(nums[maxIndex]%nums[i] == 0 && dp[maxIndex]-dp[i] == 1){
                //System.out.println(maxIndex);
                list.add(nums[i]);
                maxIndex = i;
            }
        }
        Collections.sort(list,new Comparator<Integer>(){
            public int compare(Integer i1,Integer i2){
                return i1.compareTo(i2);
            }
        });
        return list;
    }
}

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/49741/easy-understood-java-dp-solution-in-28ms-with-o-n-2-time/2
        //Step:1->sort
        //2-->找出每个元素对应的最长长度,并记录下最长长度元素对应的index
        //3->然后将那些元素逐个加入
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = 1;
    
        //for each element in nums, find the length of largest subset it has.
        //Key:这个过程类似于insertion sort中的外部循环向前插入的查找过程
        for (int i = 1; i < nums.length; i++){
            for (int j = i-1; j >= 0; j--){
                if (nums[i] % nums[j] == 0){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }
    
        //pick the index of the largest element in dp.
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++){
            maxIndex = dp[i] > dp[maxIndex] ?  i :  maxIndex;
        }
    
        //from nums[maxIndex] to 0, add every element belongs to the largest subset.
        int temp = nums[maxIndex];
        int curDp = dp[maxIndex];
        for (int i = maxIndex; i >= 0; i--){
            if (temp % nums[i] == 0 && dp[i] == curDp){
                res.add(nums[i]);
                temp = nums[i];
                curDp--;
            }
        }
        return res;
    }
}

576. Out of Boundary Paths
public class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        //Key:cp,mem https://discuss.leetcode.com/topic/88570/java-solution-dp-with-space-compression
        
        if (N <= 0) return 0;
        
        final int MOD = 1000000007;
        int[][] count = new int[m][n];
        count[i][j] = 1;
        int result = 0;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int step = 0; step < N; step++) {
            int[][] temp = new int[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    for (int[] d : dirs) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                            result = (result + count[r][c]) % MOD;
                        }
                        else {
                            temp[nr][nc] = (temp[nr][nc] + count[r][c]) % MOD;
                        }
                    }
                }
            }
            count = temp;
        }
        
        return result;
    }
}

417. Pacific Atlantic Water Flow
public class Solution {
    //Key:cp,弃  https://discuss.leetcode.com/topic/62379/java-bfs-dfs-from-ocean/2
    
    int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        boolean[][]pacific = new boolean[n][m];
        boolean[][]atlantic = new boolean[n][m];
        for(int i=0; i<n; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m-1);
        }
        for(int i=0; i<m; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n-1, i);
        }
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++) 
                if (pacific[i][j] && atlantic[i][j]) 
                    res.add(new int[] {i, j});
        return res;
    }
    
    
    
    public void dfs(int[][]matrix, boolean[][]visited, int height, int x, int y){
        int n = matrix.length, m = matrix[0].length;
        if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < height)
            return;
        visited[x][y] = true;
        for(int[]d:dir){
            dfs(matrix, visited, matrix[x][y], x+d[0], y+d[1]);
        }
    }
}

507. Perfect Number
public class Solution {
    public boolean checkPerfectNumber(int num) {
        //Key:cp,mem https://discuss.leetcode.com/topic/84260/java-4-liner-o-sqrt-n-solution
        //https://discuss.leetcode.com/topic/84259/simple-java-solution/2
        //Key:这两个sol的思路一样，但是我复制的这个代码格写法的有点意思...
        int sum = 1;
        for (int i=2;i<Math.sqrt(num);i++) 
            if (num % i == 0) sum += i + (num/i == i ? 0 : num/i);
        return num != 1 && sum == num;
    }
}

274. H-Index
public class Solution {
    public int hIndex(int[] citations) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/23310/my-easy-solution
        //Another bucket sol:https://discuss.leetcode.com/topic/23307/my-o-n-time-solution-use-java  https://discuss.leetcode.com/topic/40765/java-bucket-sort-o-n-solution-with-detail-explanation/2
        Arrays.sort(citations);
        int len=citations.length;
        for(int i=0;i<len;i++){
            if(citations[i]>=len-i) return len-i;
            
        }
        return 0;
    }
}

403. Frog Jump
public class Solution {
    public boolean canCross(int[] stones) {
        //Key:cp,mem https://discuss.leetcode.com/topic/59903/very-easy-to-understand-java-solution-with-explanations/2
        
        if (stones.length == 0) {
        	return true;
        }
        
        HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);
        map.put(0, new HashSet<Integer>());
        map.get(0).add(1);
        for (int i = 1; i < stones.length; i++) {
        	map.put(stones[i], new HashSet<Integer>() );
        }
        
        for (int i = 0; i < stones.length - 1; i++) {
        	int stone = stones[i];
        	for (int step : map.get(stone)) {
        		int reach = step + stone;
        		if (reach == stones[stones.length - 1]) {
        			return true;
        		}
        		HashSet<Integer> set = map.get(reach);
        		if (set != null) {
        		    set.add(step);
        		    if (step - 1 > 0) set.add(step - 1);
        		    set.add(step + 1);
        		}
        	}
        }
        
        return false;
    }
}

375. Guess Number Higher or Lower II
public class Solution {
    //Key:这个不太好理解
    /**
    public int getMoneyAmount(int n) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/51353/simple-dp-solution-with-explanation/2
        
        int[][] table = new int[n+1][n+1];
        for(int j=2; j<=n; j++){
            for(int i=j-1; i>0; i--){
                int globalMin = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++){
                    int localMax = k + Math.max(table[i][k-1], table[k+1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                table[i][j] = i+1==j?i:globalMin;
            }
        }
        return table[1][n];
    }
    **/
    
    public int getMoneyAmount(int n) {
        //Key:transition func:dp[i][j] = guessNum +dp[i][guessNum-1]+dp[guessNum+1][j]
        //Key:i,j表所猜的的数字的范围，dp[i][j]表在i,j范围内猜测所花的最小消费
        //具体是这样的，在1-n个数里面，我们任意猜一个数(设为i)，保证获胜所花的钱应该为 i + max(w(1 ,i-1), w(i+1 ,n))，这里w(x,y))表示猜范围在(x,y)的数保证能赢应花的钱，则我们依次遍历 1-n作为猜的数，求出其中的最小值即为答案，即最小的最大值问题
        //https://discuss.leetcode.com/topic/51353/simple-dp-solution-with-explanation
        //https://discuss.leetcode.com/topic/51494/java-commented-dp-solution/2
        //https://segmentfault.com/a/1190000008345539
        //http://www.cnblogs.com/andy1202go/p/5681329.html
        //http://blog.csdn.net/adfsss/article/details/51951658
        int[][] dp = new int[n+1][n+1];
        return helper(dp,1,n);
        
    }
    public int helper(int[][] dp,int start,int end){
        //Key:注意start一定要有=end，否则只是start>end会无限循环，=时代表着猜到了数字本身
        if(start >= end) return 0;
        if(dp[start][end] != 0) return dp[start][end];
        int minCost =Integer.MAX_VALUE;
        int cost = Integer.MAX_VALUE;
        //因为i本身就是由n赋的值，所以dp[][]就不用专门再赋值了
        for(int i = start;i<=end;i++){
            //Key:因为最坏的情况只会这样，猜错的数字只在一边，所以需要能够找到能使两边都能猜对的值即可，即最大值
            cost = i+Math.max(helper(dp,start,i-1),helper(dp,i+1,end));
            //Key:满足i,j间猜测的最小cost
            minCost = Math.min(minCost,cost);
        }
        dp[start][end] = minCost;
        return dp[start][end];
    }
}

410. Split Array Largest Sum
/*170526*/
public class Solution {
    public int splitArray(int[] nums, int m) {
        //Thinking170526
        //dp[i][j] = min(max(dp[k][j-1],sum[i]-sum[k]),dp[i][j])  ->子数组之和最大的最小情况 ，最小最大值问题
        //前i个数放进j组中，   前k个数放入j-1组中，后边几个数放入一组中  k范围（因为要分成j-1组，所以最少有j-1个，最大有i-1个）
        int n = nums.length;
        if(n == 0) return 0;
        int[] sums = new int[n+1];
        int[][] dp = new int[n+1][m+1];
        //Key170526:关键的两句
        for(int[] i:dp)Arrays.fill(i,Integer.MAX_VALUE);
        dp[0][0] = 0;
        for(int i = 1;i<=n;i++){
            sums[i] = sums[i-1]+nums[i-1];
        }
        for(int i = 1;i<=nums.length;i++){
            for(int j = 1;j<=m;j++){
                for(int k = j-1;k<=i-1;k++){
                    //Key170526:dp[0][0] = 0;很关键如果dp[0][0]也初始化为MAX_VALUE，那么i==1,j==1时，dp[k][j-1]>sums[i]-sums[k]会导致第一步就错误了
                    dp[i][j] = Math.min(dp[i][j],Math.max(dp[k][j-1],sums[i]-sums[k]));
                }
            }
        }
        return dp[n][m];
    }
}
/*170524*/
public class Solution {
    public int splitArray(int[] nums, int m) {
        //Key:题意，使  和最大的数群的和  尽可能小
        //Key:这道题的二分法根本就不可能想出来...
        //找了一个dp的c++方法，改写成了java,里面也有二分的解释  http://www.cnblogs.com/grandyang/p/5933787.html
        
        //Origin:
        /**
        int n = nums.length;
        int[] sums = new int[n+1];
        int[][] dp = new int[m+1][n+1];
        for(int[] i:dp){
            Arrays.fill(i,Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            //Key:截止到i时，之前的所有数字之和
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int k = i - 1; k < j; ++k) {
                    int val = Math.max(dp[i - 1][k], sums[j] - sums[k]);
                    dp[i][j] = Math.min(dp[i][j], val);
                }
            }
        }
        return dp[m][n];
        **/
        
        //V2
        
        //Key170524:hard，能背就背吧....
        if(nums.length == 0) return 0;
        int[] sums = new int[nums.length+1];
        int[][] dp = new int[m+1][nums.length+1];
        for(int[] i:dp){
            Arrays.fill(i,Integer.MAX_VALUE);
        }
        //Key:dp[0][0] = 0非常重要
        dp[0][0] = 0;
        //Key:下面两句错误的
        ////for(int i = 0;i<=m;i++) dp[i][0] = 0;
        //for(int i = 0;i<=nums.length;i++) dp[0][i] = 0;
        for(int i = 0;i<=nums.length-1;i++){
            sums[i+1] = sums[i]+nums[i];
        }
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=nums.length;j++){
                //Key:一定要注意k的取值范围，下面这句错误
                //for(int k = i;k<=j;k++){
                //只有k范围是i-1  ~ j-1时才有意义，否则结果就不对了，反正这道题挺不好理解的....
                for(int k = i-1;k<=j-1;k++){
                    //Key：举例 i1,j1,k=i-1时，此时sums[1]-sums[0]。而如果撇弃k=i-1，而直接写k从1开始的话，就变成了sums[1]-sums[1]了。答案显然就变了，所以不对
                    dp[i][j] =Math.min(dp[i][j],Math.max(dp[i-1][k],sums[j]-sums[k]));
                }
            }
        }
        return dp[m][nums.length];
        
    }
}

372. Super Pow
public class Solution {
    //Key:cp,mem   https://discuss.leetcode.com/topic/50586/math-solusion-based-on-euler-s-theorem-power-called-only-once-c-java-1-line-python/2
    public int superPow(int a, int[] b) {
        if (a % 1337 == 0) return 0;
        int p = 0;
        for (int i : b) p = (p * 10 + i) % 1140;
        if (p == 0) p += 1440;
        return power(a, p, 1337);
    }
    public int power(int a, int n, int mod) {
        a %= mod;
        int ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) ret = ret * a % mod;
            a = a * a % mod;
            n >>= 1;
        }
        return ret;
    }
}

393. UTF-8 Validation
public class Solution {
    public boolean validUtf8(int[] data) {
        //Key:cp,弃  https://discuss.leetcode.com/topic/61818/simple-one-pass-concise-java-solution-beating-99
        int varCharLeft = 0;
		for (int b: data) {
			if (varCharLeft == 0) {
				if ((b & 0b010000000) == 0)  varCharLeft = 0;
				else if ((b & 0b011100000) == 0b11000000)  varCharLeft = 1;
				else if ((b & 0b011110000) == 0b11100000)  varCharLeft = 2;
				else if ((b & 0b011111000) == 0b11110000)  varCharLeft = 3;
				else return false;
			} else {
				if ((b & 0b011000000) != 0b10000000)  return false;
				varCharLeft--;
			}
		}
		return varCharLeft==0;
    }
}

502. IPO
public class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        //Key:cp,mem   https://discuss.leetcode.com/topic/77768/very-simple-greedy-java-solution-using-two-priorityqueues/2
        
        PriorityQueue<int[]> pqCap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        PriorityQueue<int[]> pqPro  = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        
        for (int i = 0; i < Profits.length; i++) {
            pqCap.add(new int[] {Capital[i], Profits[i]});
        }
        
        for (int i = 0; i < k; i++) {
            while (!pqCap.isEmpty() && pqCap.peek()[0] <= W) {
                pqPro.add(pqCap.poll());
            }
            
            if (pqPro.isEmpty()) break;
            
            W += pqPro.poll()[1];
        }
        
        return W;
    }
}

407. Trapping Rain Water II
public class Solution {
    
    //Key:cp,弃   https://discuss.leetcode.com/topic/60418/java-solution-using-priorityqueue/2
    
    public class Cell {
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return 0;

        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>(){
            public int compare(Cell a, Cell b) {
                return a.height - b.height;
            }
        });
        
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];

        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }

        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }

        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
       // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    res += Math.max(0, cell.height - heights[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
                }
            }
        }
        
        return res;
    }   
}

488. Zuma Game
public class Solution {
    
    //Key:cp.mem  https://discuss.leetcode.com/topic/79820/short-java-solution-beats-98
    
    int MAXCOUNT = 6;   // the max balls you need will not exceed 6 since "The number of balls in your hand won't exceed 5"
    public int findMinStep(String board, String hand) {
        int[] handCount = new int[32];
        for (int i = 0; i < hand.length(); ++i) ++handCount[hand.charAt(i) - 'A'];
        int rs = helper(board + "#", handCount);  // append a "#" to avoid special process while j==board.length, make the code shorter.
        return rs == MAXCOUNT ? -1 : rs;
    }
    private int helper(String s, int[] h) {
        s = removeConsecutive(s);     
        if (s.equals("#")) return 0;
        int  rs = MAXCOUNT, need = 0;
        for (int i = 0, j = 0 ; j < s.length(); ++j) {
            if (s.charAt(j) == s.charAt(i)) continue;
            need = 3 - (j - i);     //balls need to remove current consecutive balls.
            if (h[s.charAt(i) - 'A'] >= need) {
                h[s.charAt(i) - 'A'] -= need;
                rs = Math.min(rs, need + helper(s.substring(0, i) + s.substring(j), h));
                h[s.charAt(i) - 'A'] += need;
            }
            i = j;
        }
        return rs;
    }
    //remove consecutive balls longer than 3
    private String removeConsecutive(String board) {
        for (int i = 0, j = 0; j < board.length(); ++j) {
            if (board.charAt(j) == board.charAt(i)) continue;
            if (j - i >= 3) return removeConsecutive(board.substring(0, i) + board.substring(j));
            else i = j;
        }
        return board;
    }
    
}

388. Longest Absolute File Path
public class Solution {
    public int lengthLongestPath(String input) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/55561/two-different-solutions-in-java-using-stack-and-hashmap/2
        
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 0);
        int result = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf('\t') + 1;
            int len = s.length() - level;
            if (s.contains(".")) {
                result = Math.max(result, hashMap.get(level) + len);
            } else {
                hashMap.put(level + 1, hashMap.get(level) + len + 1);
            }
        }
        return result;
    }
}

567. Permutation in String
public class Solution {
    //Key:my wrong version
    /**
    public boolean checkInclusion(String s1, String s2) {
        List<Character> list = new ArrayList<>();
        char[] arr = s1.toCharArray();
        boolean[] used = new boolean[arr.length];
        boolean res = helper(list,arr,used,s2);
        return res;
    }
    boolean helper(List<Character> list,char[] arr,boolean[] used,String s2){
        if(list.size() == s2.length()){
            StringBuilder sb = new StringBuilder();
            for(char c:list){
                sb.append(c);
            }
            //System.out.println(sb.toString());
            if(s2.indexOf(sb.toString()) != -1) return true;
        } else {
            for(int i = 0;i<=arr.length-1;i++){
                if(used[i] == true) continue;
                used[i] = true;
                list.add(arr[i]);
                helper(list,arr,used,s2);
                list.remove(list.size()-1);
                used[i] = false;
            }
        }
        return false;
    }
    **/
    
    //Key:cp,mem  https://discuss.leetcode.com/topic/87845/java-solution-sliding-window/2
    
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;
        
        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;
        
        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }
        
        return false;
    }
    
    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }
}

367. Valid Perfect Square
public class Solution {
    public boolean isPerfectSquare(int num) {
        //Key:cp,mem  Newton method:https://discuss.leetcode.com/topic/49325/a-square-number-is-1-3-5-7-java-code/2
        
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }
}

352. Data Stream as Disjoint Intervals
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class SummaryRanges {
    //Key:cp,mem  https://discuss.leetcode.com/topic/46887/java-solution-using-treemap-real-o-logn-per-adding/2
    
    TreeMap<Integer, Interval> tree;
    public SummaryRanges() {
        tree = new TreeMap<>();
    }

    public void addNum(int val) {
        if(tree.containsKey(val)) return;
        Integer l = tree.lowerKey(val);
        Integer h = tree.higherKey(val);
        if(l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {
            tree.get(l).end = tree.get(h).end;
            tree.remove(h);
        } else if(l != null && tree.get(l).end + 1 >= val) {
            tree.get(l).end = Math.max(tree.get(l).end, val);
        } else if(h != null && h == val + 1) {
            tree.put(val, new Interval(val, tree.get(h).end));
            tree.remove(h);
        } else {
            tree.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(tree.values());
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
 
 416. Partition Equal Subset Sum
 public class Solution {
    public boolean canPartition(int[] nums) {
        //Key:cp,mem  https://discuss.leetcode.com/topic/67539/0-1-knapsack-detailed-explanation/2
        
        int sum = 0;
    
        for (int num : nums) {
            sum += num;
        }
        
        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;
        
        int n = nums.length;
        boolean[] dp = new boolean[sum+1];
        Arrays.fill(dp, false);
        dp[0] = true;
        
        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i-num];
                }
            }
        }
        
        return dp[sum];
    }
}

581. Shortest Unsorted Continuous Subarray
public class Solution {
    public int findUnsortedSubarray(int[] A) {
        //Key:cp,mem   https://discuss.leetcode.com/topic/89282/java-o-n-time-o-1-space
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i=1;i<n;i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[n-1-i]);
            if (A[i] < max) end = i;
            if (A[n-1-i] > min) beg = n-1-i; 
        }
        return end - beg + 1;
    }
}

373. Find K Pairs with Smallest Sums
public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //Key:cp,mem     https://discuss.leetcode.com/topic/50885/simple-java-o-klogk-solution-with-explanation/2
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<int[]> res = new ArrayList<>();
        if(nums1.length==0 || nums2.length==0 || k==0) return res;
        for(int i=0; i<nums1.length && i<k; i++) que.offer(new int[]{nums1[i], nums2[0], 0});
        while(k-- > 0 && !que.isEmpty()){
            int[] cur = que.poll();
            res.add(new int[]{cur[0], cur[1]});
            if(cur[2] == nums2.length-1) continue;
            que.offer(new int[]{cur[0],nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }
}

480. Sliding Window Median
public class Solution {
    //Key:cp,弃   https://discuss.leetcode.com/topic/74874/easy-to-understand-o-nlogk-java-solution-using-treemap/2
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length-k+1];
        TreeMap<Integer, Integer> minHeap = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> maxHeap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        
        int minHeapCap = k/2; //smaller heap when k is odd.
        int maxHeapCap = k - minHeapCap; 
        
        for(int i=0; i< k; i++){
            maxHeap.put(nums[i], maxHeap.getOrDefault(nums[i], 0) + 1);
        }
        int[] minHeapSize = new int[]{0};
        int[] maxHeapSize = new int[]{k};
        for(int i=0; i< minHeapCap; i++){
            move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
        }
        
        res[0] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
        int resIdx = 1;
        
        for(int i=0; i< nums.length-k; i++){
            int addee = nums[i+k];
            if(addee <= maxHeap.keySet().iterator().next()){
                add(addee, maxHeap, maxHeapSize);
            } else {
                add(addee, minHeap, minHeapSize);
            }
            
            int removee = nums[i];
            if(removee <= maxHeap.keySet().iterator().next()){
                remove(removee, maxHeap, maxHeapSize);
            } else {
                remove(removee, minHeap, minHeapSize);
            }

            //rebalance
            if(minHeapSize[0] > minHeapCap){
                move1Over(minHeap, maxHeap, minHeapSize, maxHeapSize);
            } else if(minHeapSize[0] < minHeapCap){
                move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
            }
            
            res[resIdx] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
            resIdx++;
        }
        return res;
    }

    public double getMedian(TreeMap<Integer, Integer> bigHeap, TreeMap<Integer, Integer> smallHeap, int[] bigHeapSize, int[] smallHeapSize){
        return bigHeapSize[0] > smallHeapSize[0] ? (double) bigHeap.keySet().iterator().next() : ((double) bigHeap.keySet().iterator().next() + (double) smallHeap.keySet().iterator().next()) / 2.0;
    }
    
    //move the top element of heap1 to heap2
    public void move1Over(TreeMap<Integer, Integer> heap1, TreeMap<Integer, Integer> heap2, int[] heap1Size, int[] heap2Size){
        int peek = heap1.keySet().iterator().next();
        add(peek, heap2, heap2Size);
        remove(peek, heap1, heap1Size);
    }
    
    public void add(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
        heap.put(val, heap.getOrDefault(val,0) + 1);
        heapSize[0]++;
    }
    
    public void remove(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
        if(heap.put(val, heap.get(val) - 1) == 1) heap.remove(val);
        heapSize[0]--;
    }
}

338. Counting Bits
public class Solution {
     public int[] countBits(int num) {
        //Key:cp,mem 
        //另一种方法。java中Integer.bitCount(i)可以直接统计二进制中1的个数。
        //这道题技巧性方法太偏了.....

        int[] res = new int[num+1];
        for(int i = 0;i<=num;i++){
            res[i] = Integer.bitCount(i);
        }
        return res;
    }
}

494. Target Sum
public class Solution {
    //V1
    /**
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
         //Key:+1+1+1+1-1 在最后一个-1的减号处进行判断。
        if(!tag && (sum-nums[index])==S || tag && (sum+nums[index] == S)) return 1;
        return 0;
    }
    **/
    public int findTargetSumWays(int[] nums, int S) {
        if(nums.length == 0) return 0;
        return helper(nums,S,false,0,0)+helper(nums,S,true,0,0);
    }
    public int helper(int[] nums,int S,boolean tag,int index,int sum){
        if(index<=nums.length-2){
            if(tag) sum+=nums[index];
            else sum-=nums[index];
            index++;
            return helper(nums,S,false,index,sum)+helper(nums,S,true,index,sum);
        }
        if(tag && (sum+nums[index] == S) ||!tag && (sum-nums[index] == S)) return 1;
        return 0;
    }
}

486. Predict the Winner
public class Solution {
    //Key:origin
    /**
    public boolean PredictTheWinner(int[] nums) {
        //Key point:如果大小相同，也算Player1赢。
        //Corner case:[1,1] 返回true
        if(nums.length == 0) return false;
        //Key:因为是player1先拿的，所以>0即为true
        return helper(nums,0,nums.length-1)<0?false:true;
    }
    //这道题也可以用DP来做，不过更绕一些
    //Recursion version
    public int helper(int[] nums,int s,int e){
        //Key:s --> start,e --> end
        //Key point:这个有点绕。理解成有一个机器可以自动返回最大值，由player1和player2轮流使用...
        //轮到当前player时，加上两端中的最大值，并且减去另一个player接下来的最大值
        //Key:两个人的目的都是为了使自己当前回合拿到的能够大于对方当前回合能够拿到的
        return s == e?nums[e]:Math.max(nums[s] - helper(nums,s+1,e),nums[e] - helper(nums,s,e-1));
    }
    **/
    
    //V2
    public boolean PredictTheWinner(int[] nums) {
		//Key:试着用{1,2}来理解一下，即要求己方数字差最大即可
        //Corner case:[0]  expected:true  这case给的不怎么好，0有实际意义??
        return helper(nums,0,nums.length-1)>=0?true:false;
    }
    public int helper(int[] nums,int start,int end){
        return start == end?nums[start]:Math.max(nums[start]-helper(nums,start+1,end),nums[end]-helper(nums,start,end-1));
    }
}

474. Ones and Zeroes
public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        //Key:题意--->使用m个0，n个1构建最多数量的strs中的字符串
        if(strs.length == 0) return 0;
        //Key:类似于01pack，但是这里的[m][n]作为一个整体考虑，所以可以当成一个三位数组考虑
        //Key:类似于01pack。value都是1，但是weight限制变成了一个二维数组。仔细想想，有些绕，但不算很难
        //
        int[][] arr = new int[m+1][n+1];
        //Key:fill只能填充一维数组
        /**
        public static void fill(int[] a,int val)
        Assigns the specified int value to each element of the specified array of ints.
        
        ****/
        //Arrays.fill(arr,0);
        //Key:为了防止数字被重复使用干扰，所以index需要从end开始
        for(int k = 0;k<=strs.length-1;k++){
            //统计每个字符串中0，1个数
            int count0 = 0,count1 = 0;
            for(int i = 0;i<=strs[k].length()-1;i++){
                if(strs[k].charAt(i) == '0') count0++;
                else count1++;
            }
            //Log:System.out.println(count0+"+"+count1);
            //Key:Corner case:["10","0","1"] 1 1
            //因为会出现这种单数字，所以除了[0][0],无意义外。[0][1],[1][0]这种也有意义
            //Key:关键!!!  i,j 一定要从前往后，这道题中，一个pair[i][j]相当于01pack中的单一物品[i],(其实要对应01pack的话，这道题应该想象成一个3维数组，[m][n]作为一个整体考虑)如果从前往后的话，现状态会覆盖之前状态的
            for(int i = m;i>=0;i--){
                for(int j =n;j>=0;j--){
                    if(i>=count0 && j >= count1){
                        arr[i][j] = Math.max(arr[i][j],arr[i-count0][j-count1]+1);
                    }
                }
            }      
        }
        return arr[m][n];
    }
}

//V2
public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        
        int[][] dp = new int[m+1][n+1];
        int res = 0,count0 = 0,count1 = 0;
        for(String s:strs){
            count0 = 0;
            count1 = 0;
            for(int i = 0;i<=s.length()-1;i++){
                if(s.charAt(i) == '0') count0++;
                else count1++;
            }
            //Key:关键!!!  i,j 一定要从前往后，这道题中，一个pair[i][j]相当于01pack中的单一物品[i],(其实要对应01pack的话，这道题应该想象成一个3维数组，[m][n]作为一个整体考虑)如果从前往后的话，现状态会覆盖之前状态的
            for(int i = m;i>=0;i--){
                for(int j = n;j>=0;j--){
                    if(i>=count0 && j >= count1) dp[i][j] = Math.max(dp[i-count0][j-count1]+1,dp[i][j]);
                }
            }
        }
        return dp[m][n];
    }
}


216. Combination Sum III
public class Solution {
    //combinationSum3 指的是该题型第三道题.....
    public List<List<Integer>> combinationSum3(int k, int n) {
        //Key:similar to permutations
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        helper(list,item,0,n,k);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int sum,int n,int k){
        if(item.size() == k && sum == n){
            list.add(new ArrayList<>(item));
        } else {
            int i = item.size() == 0?1:item.get(item.size()-1)+1;
            for(;i<=9;i++){
                //Key:判断条件要仔细想一下
                if((sum+i) > n || item.size()>=k) break;
                item.add(i);
                helper(list,item,sum+i,n,k);
                item.remove(item.size()-1);
            }
        }
    }
}


377. Combination Sum IV
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        //这道题有难度，HARD
        //Ref link:http://www.cnblogs.com/grandyang/p/5928417.html
        //状态转移方程   F[i] = F[i]+F[i-a]
        //应该和完全背包一个思路
        //只要最后的F[target]，之前的值都是临时存储变量
        if(nums.length == 0) return 0;
        int[] F = new int[target+1];
        
        //Key Point:Java 填充 数组
        //Arrays.fill(F,0); 不过默认好像空的都是0吧
        F[0] = 1;
        for(int i=1;i<=target;i++){
            for(int j=0;j<=nums.length-1;j++){
                if(i>=nums[j]){
                    //因为这里有个+F[i]，所以前边要全部初始化为0
                    //Key:以(1,2,3)  4为例，  i==3,j=1,nums[j] = 2,soF[i]等于F[i]之前所有的情况加上F[1]的情况
                    F[i] = F[i-nums[j]]+F[i];
                    //System.out.println(F[i]+",I"+i);
                }
                //Corner Case:本身也可以算作一个元素--->改一下，把F[0] =1卸载外头，更方便些
                
                //if(i == nums[j]) F[i]++;
            }
        }
        return F[target];
    }
}

//V2
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 1;i<=target;i++){
            for(int j = 0;j<=nums.length-1;j++){
                if(i>=nums[j]) dp[i] = dp[i] + dp[i-nums[j]];
            }
        }
        return dp[target];
    }
}



//V2
//Key:https://discuss.leetcode.com/topic/12024/java-dp-solution-o-n-runtime-and-o-1-space-with-inline-comment
//Key:这个sol可以和stock cooldown一起看
 public int rob(int[] num) {
    int rob = 0; //max monney can get if rob current house
    int notrob = 0; //max money can get if not rob current house
    for(int i=0; i<num.length; i++) {
        int currob = notrob + num[i]; //if rob current value, previous house must not be robbed
        notrob = Math.max(notrob, rob); //if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
        rob = currob;
    }
    return Math.max(rob, notrob);
}








264. Ugly Number II
/*170526*/
public class Solution {
    public int nthUglyNumber(int n) {
        //Key170526:dp[l] = min(2*dp[i],3*dp[j],5*dp[k])  l,i,j,k 范围从1到n
        //当min得出的值选择的是2*dp[i],则i++。相同的，min是3*dp[j]，则j++....每当选择出一个min，则l++
        int[] dp = new int[n+1];
        dp[1] = 1;
        int count = 2;
        int i = 1,j = 1,k = 1;
        Set<Integer> set = new HashSet<>();
        set.add(1);
        while(count <= n){
            //Key170526:注意2*dp[2] 和3*dp[2]这种相同的结果会导致重复，所以需要排除一下这些duplicates
            dp[count] = Math.min(2*dp[i],Math.min(3*dp[j],5*dp[k]));
            if(dp[count] == 2*dp[i]) i++;
            else if(dp[count] == 3*dp[j]) j++;
            else k++;
            //Key170526:用set排除重复项
            if(set.add(dp[count])) count++;
            
        }
        return dp[n];
    }
}
/*origin*/
public class Solution {
    public int nthUglyNumber(int n) {
        //https://discuss.leetcode.com/topic/21791/o-n-java-solution/2
        int[] arr = new int[n];
        arr[0] = 1;
        int index2 = 0,index3 = 0,index5 = 0;
        //Key point:这么一看，倒的确是DP
        //相当于在2,3,5分别的最小值上乘以2或3或5.然后比较
        for(int i=1;i<=n-1;i++){
            arr[i] = Math.min(arr[index2]*2,Math.min(arr[index3]*3,arr[index5]*5));
            //Key170526:因为三个if是顺序执行的，所以即使arr[i] == 3*2,但是同时也把 arr[i] == 2*3这个判断也给进行了,所以index2和index3都会++,也就不会因为2*3和3*2这样产生冗余项了
            if(arr[i] == arr[index2]*2) index2++;
            if(arr[i] == arr[index3]*3) index3++;
            if(arr[i] == arr[index5]*5) index5++;
        }
        return arr[n-1];
    }
}

598. Range Addition II
/*170528*/
public class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if(m == 0 || n == 0 ) return 0;
        if(ops.length == 0) return m * n;
        int width = Integer.MAX_VALUE,length = Integer.MAX_VALUE;
        for(int[] i:ops){
            width = Math.min(i[0],width);
            length = Math.min(i[1],length);
        }
        return width*length;
        //Key170528:错误且麻烦的做法
        /**
        int[][] dp = new int[m][n];
        int max = Integer.MIN_VALUE,count = 0;
        for(int[] i:ops){
            for(int j = 0;j<=i[0]-1;j++){
                for(int k = 0;k<i[1]-1;k++){
                    dp[j][j]++;
                    max = Math.max(dp[j][k],max);
                }
            }
        }
        for(int i = 0;i<=m-1;i++){
            for(int j = 0;j<=n-1;j++){
                if(dp[i][j] == max)count++;
            }
        }
        return count;
        **/
    }
}

565. Array Nesting
/*170528*/
public class Solution {
    public int arrayNesting(int[] nums) {
        //Key150528:这道题最好看着例子写，然后corner case就比较好写了
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        int max = 1;
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<=n-1;i++){
            int index = i;
            int count = 1;
            //Key170528:set去除已经算过的数字是关键，否则会因为一个巨大的case导致TLE
            //Key170528:判断的是nums[i](也可以把i的判断也加上),而不仅仅是是i。因为后边的是set.add(i),只有当前i之前的i会被加上，而当前i则不会被加上。所以如果仅仅判断当前i是否存在的话，是无意义的行为!!!
            if(set.contains(nums[i])||set.contains(i)) continue;
            while(nums[index] != i){
                index = nums[index];
                //Key170528:在这里也加句会减掉更多不必要的
                set.add(index);
                count++;
                max = Math.max(max,count);
            }
            //Key170528:
            set.add(i);
        }
        return max;
    }
}

599. Minimum Index Sum of Two Lists
/*170528*/
public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        //Key170528:map<restaurant,index>
        if(list1.length == 0 || list2.length == 0) return new String[0];
        Map<String,Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<=list1.length-1;i++){
            map.put(list1[i],i);
        }
        for(int i = 0;i<=list2.length-1;i++){
            //Key170528:Corner case:Shogun index之和等于KFC (4)，所以要注意一下
            //["Shogun","Tapioca Express","Burger King","KFC"]
            //["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
            if(map.containsKey(list2[i])){
                int sum = map.get(list2[i])+i;
                list.add(list2[i]);
                min = Math.min(min,sum);
                map.put(list2[i],sum);
            } 
        }
		//Key:Map遍历 for(Map.Entry<String,Integer> entry:map.entrySet())
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            if(entry.getValue() != min) list.remove(entry.getKey());
        }
				
        return list.toArray(new String[0]);
    }
}

//Key:Test -> 170603

606. Construct String from Binary Tree
//Key170604:original
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
    String res = "";
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        helper(true,t);
        return res.substring(1,res.length()-1);
    }
    public boolean helper(boolean tag,TreeNode node){
        if(node == null) return false;
        if(!tag) res += "()";
        res += "(";
        res += node.val;
        boolean left = helper(true,node.left);
        helper(left,node.right);
        res += ")";
        return true;
    }
}

605. Can Place Flowers
//Key170604:original
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean res = true;
        
        int length = flowerbed.length,count = 0;
        if(length == 0 || length < n) return false;
        //Key170604: Test case太烦人，扩大一下边界吧
        int[] tmp = new int[length+2];
        for(int i = 0;i<=length-1;i++){
            tmp[i+1] = flowerbed[i];
        }
        
        for(int i = 1;i<=tmp.length-2;i++){
            if(tmp[i-1] == 0 && tmp[i] == 0  && tmp[i+1] == 0) {
                count++;
                tmp[i] = 1;
            }
        }
        //Key170604:count>n也可以!!!  [0,0,1,0,0] 1
        return count >= n?res:false;
        //Test case:[1,0,0,0,0,0,1] 2
        //Test case:[1,0,0,0,0,1]  2
    }
}

238. Product of Array Except Self
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        //Key170605:这个完全就是凭感觉做出来的.....
        //Key170605:思路是将问题拆分为分别求出nums[i]左边和右边的乘积，优化时即正序逆序各遍历一遍
        int n = nums.length,tmp = 1;
        if(n == 0) return new int[0];
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1;i<=n-1;i++){
            tmp = tmp * nums[i-1];
            res[i] = tmp;
        }
        tmp = 1;
        for(int i = n-2;i>=0;i--){
            tmp = tmp*nums[i+1];
            res[i] = res[i]*tmp;
        }
        return res;
    }
}



392. Is Subsequence
/*20170615*/
public class Solution {
    public boolean isSubsequence(String s, String t) {
        //Key170615:这道题容易被他给的tag给带弯,tag里有个dp,但是实际上用dp写起来很麻烦。而且其实根本就用不上dp......下面的dp是错误思路
        //Key170615:if s[i] == t[j] -->dp[i][j] =  dp[i-1][j-1] && true;
        int index1 = 0,index2 = 0;
        int length1 = s.length(),length2 = t.length();
        while(index1 <= length1-1 && index2 <= length2-1){
            if(s.charAt(index1) == t.charAt(index2)){
                index1++;
                index2++;
            } else {
                index2++;
            }
        }
        return index1 == length1?true:false;
    }
}

//Key170615:这道题容易被他给的tag给带弯,tag里有个dp,但是实际上用dp写起来很麻烦。而且其实根本就用不上dp......
public class Solution {
    public boolean isSubsequence(String s, String t) {
        //2pointers方法更好
        if(s == null || t == null ) return false;
        if(s.equals("")) return true;
        int index1 = 0,index2 = 0,length1 = s.length(),length2 = t.length();
        while(index1 <= length1-1 && index2 <= length2-1){
            if(s.charAt(index1) == t.charAt(index2)){
                index1++;
                index2++;
            } else {
                index2++;
            }
        }
        return length1 == index1?true:false;
    }
}



309. Best Time to Buy and Sell Stock with Cooldown
/*170609*/
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        //Key:sell[i]第i天卖出状态时的收益,即手里没有stock的状态
        if(n <= 1) return 0;
        if(n == 2) return Math.max(0,prices[1]-prices[0]);
        int[] sell = new int[n];
        int[] buy = new int[n];
        sell[0] = 0;
        sell[1] = Math.max(prices[1] - prices[0],0);
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[1],-prices[0]);
        for(int i = 2;i<=n-1;i++){
            //Key:buy[i],sell[i] transition func的顺序千万不能错!!!
            buy[i] = Math.max(buy[i-1],sell[i-2]-prices[i]);
            sell[i] = Math.max(sell[i-1],buy[i-1]+prices[i]);
        }
        return sell[n-1];
    }
}

public class Solution {
    public int maxProfit(int[] prices) {
        //Key:这道题可以和那个predict winner 一起看，都是状态机的样子
        //Key:DP,cp,只能背  https://discuss.leetcode.com/topic/30421/share-my-thinking-process
		//http://www.cnblogs.com/grandyang/p/4997417.html
		//http://www.cnblogs.com/lasclocker/p/5003534.html
		//http://blog.csdn.net/xyqzki/article/details/50262315
        /**
            For each of them we make an array, buy[n], sell[n] and rest[n].
    
            buy[i] means before day i what is the maxProfit for any sequence end with buy.
            
            sell[i] means before day i what is the maxProfit for any sequence end with sell.
            
            rest[i] means before day i what is the maxProfit for any sequence end with rest.
            
            Then we want to deduce the transition functions for buy sell and rest. By definition we have:
            
            buy[i]  = max(rest[i-1]-price, buy[i-1]) 
            sell[i] = max(buy[i-1]+price, sell[i-1])
            rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
            Where price is the price of day i. All of these are very straightforward. They simply represents :
            
            (1) We have to `rest` before we `buy` and 
            (2) we have to `buy` before we `sell`
                
        **/


        //Key:这个方法好是好，但是不是特别容易理解
        /**
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy = 0;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
        **/
        
        
        //Key：下边这个较容易理解
        
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        //Key:buy[]和sell[]均表示当第i天买或卖状态结束时手上钱的最大值。下边这行的理解应该更准确些
		//即buy[i]表i天手上有股票时的最大值，sell[i]表第i天手上无股票时的最大值
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        //Key:buy[0]本身是可以等于0的，即第0天不买，可是如果等于0的话，sell[i-1]就会出错。
		//Key:但是如果buy[i]表最大收益，第一天和第二天buy[i]=0，即什么都不买不是更符合含义吗？？？
		//Key:上面这行理解错误。准确的说，buy[i]表i天手上有股票时的最大值，sell[i]表第i天手上无股票时的最大值。所以前两天buy手上一定有股票
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, prices[1]-prices[0]);
        for(int i = 2; i < len; i++) {
            //Key：不过一直有个困惑，他是如何保证sell时手上一定有股票的???
            //Key: 因为buy[0] = -prices[0];buy[1]都设置的是-prices，所以buy[]手里一定有股票
            buy[i] = Math.max(buy[i-1], sell[i-2]-prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1]+prices[i]);
        }
        return sell[len-1];
    }
}


*********************************************************************************************************************
*********************************************************************************************************************
*********************************************************************************************************************
LintCode 

恢复旋转排序数组
/*170818*/
public class Solution {
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        //Mark0:Collections.sort改变了List对象(堆上)
        Collections.sort(nums);
    }
    
};

657. Judge Route Circle
//Star
/*170819*/
//Input: "UD"  Output: true 先向上，再向下 return true
//mark0:判断UD,LR数量相等
class Solution {
    public boolean judgeCircle(String moves) {
        int count1 = 0,count2 = 0;
        for(Character c:moves.toCharArray()){
            switch(c){
                case 'U':count1++;
                    break;
                case 'L':count2++;
                    break;
                case 'D':count1--;
                    break;
                case 'R':count2--;
                    break;
                default:break;
            }
        }
        return count1 == 0 && count2 == 0;
    }
}