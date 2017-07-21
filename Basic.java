import java.util.*;
class Basic{
	public static void main(String[] s){
		//new Basic().mergeSort();
		//new Basic().binarySearch();
		new Basic().medianSearch();
	}
	public void mergeSort(){
		int[] nums = {3,1,4,5,5,2,5,6,66,33,22,1,21,13,14};
		int[] tmp = new int[nums.length];
		new MergeSort().sort(nums,tmp,0,nums.length-1);
		for(int i:nums) System.out.print(i+",");
	}
	public void binarySearch(){
		int[] nums = {4,2,5,2,5,2,2,1,88,4,22,45,23,121,9,65};
		Arrays.sort(nums);
		System.out.println(new BinarySearch().search(nums,45));
	}
	/*170714*/
	public void medianSearch(){
		int[] nums1 = {1,2};
		int[] nums2 = {3,4};
		MedianSearch obj = new MedianSearch();
		System.out.println(obj.search(nums1,nums2));
	}
}
class BinarySearch{
	public int search(int[] nums,int target){
		return helper(nums,target,0,nums.length-1);
	}
	public int helper(int[] nums,int target,int low,int high){
		if(low <= high){
			int mid = (low+high)/2;
			if(nums[mid] == target) return mid;
			if(nums[mid] < target) return helper(nums,target,mid+1,high);
			else return helper(nums,target,low,mid-1);
		}
		return -1;
	}
}
/*170711*/
//mark1:只有static 方法调用non-static方法才需要new Class(),non-static 方法调用non-static 方法不需要new Class()
class MergeSort{
	public void MergeSort(){}
	public void sort(int[] nums,int[] tmp,int low,int high){
		if(low < high){
			int mid = (low+high)/2;
			sort(nums,tmp,low,mid);
			sort(nums,tmp,mid+1,high);
			merge(nums,tmp,low,mid,high);
		}
	} 
	public void merge(int[] nums,int[] tmp,int low,int mid,int high){
		int index1 = low,index2 = mid+1,index3 = low;
		while(index1 <= mid && index2 <= high){
			if(nums[index1] <= nums[index2]){
				tmp[index3++] = nums[index1++];
			} else {
				tmp[index3++] = nums[index2++];
			}
		}
		while(index1 <= mid) tmp[index3++] = nums[index1++];
		while(index2 <= high) tmp[index3++] = nums[index2++];
		while(low <= high){
			nums[low] = tmp[low++];
		}
	}
}

/*170714*/
class MedianSearch{
	public double search(int[] nums1,int[] nums2){
		//mark-0.9:记住left,right分别奇偶数量时的中间index,所以一定要记得(n1+n2+1)除以2
		int n1 = nums1.length,n2 = nums2.length,left = (n1+n2+1)/2,right = (n1+n2+2)/2;
		//mark-0.99:因为return double,so 记得是除以2.0，而不是2
		//case:[1,2][3,4] 除以2时，返回的是2.0,明显结果是不对的
		//return (findKth(nums1,0,nums2,0,left)+findKth(nums1,0,nums2,0,right))/2;
		return (findKth(nums1,0,nums2,0,left)+findKth(nums1,0,nums2,0,right))/2.0;
	}
	//mark0:是第K小而不是第k大
	public int findKth(int[] nums1,int aStart,int[] nums2,int bStart,int k){
		int n1 = nums1.length,n2 = nums2.length;
		//mark1:空数组排除
		//mark1.5:k-1别忘记加上aStart
		//if(aStart >= n1) return nums2[k-1];
		if(aStart >= n1) return nums2[bStart+k-1];
		if(bStart >= n2) return nums1[aStart+k-1];		
		//mark1.6:不是nums1[0]，而是nums1[aStart]
		//if(k == 1) return Math.min(nums1[0],nums2[0]);
		if(k == 1) return Math.min(nums1[aStart],nums2[bStart]);
		//mark2
		int midIndA = aStart+k/2-1,midIndB = bStart+k/2-1;
		//mark3:排除空数组干扰
		int midValA = midIndA >= n1?Integer.MAX_VALUE:nums1[midIndA];
		int midValB = midIndB >= n2?Integer.MAX_VALUE:nums2[midIndB];
		//mark4:==归哪边无所谓
		if(midValA < midValB) 
			//mark5:记住midIndA要+1
			return findKth(nums1,midIndA+1,nums2,bStart,k-k/2);
		else
			return findKth(nums1,aStart,nums2,midIndB+1,k-k/2);
	}
}

