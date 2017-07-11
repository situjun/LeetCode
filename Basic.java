import java.util.*;
class Basic{
	public static void main(String[] s){
		//new Basic().mergeSort();
		new Basic().binarySearch();
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