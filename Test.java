import java.util.*;
class Test{
	//public static int A = 10;
	/*
		public static void main(String[] s){
			//System.out.println(A+"");
			Solution.printB();
		}
	*/
	
	//java.lang.StackOverflowError
	//递归调用 stack溢出错误，即使没有变量或者传参，递归时也还有一些返回地址，执行上下文等看不见的参数，因此每次栈还是会增加
	//最后测试深度为11417
	public static void StackOverflowTest(int count){
		System.out.println(count);
		StackOverflowTest(++count);
	}
	
	//Mark:OutOfMemoryError的原因有可能不同
	//内存溢出解释为所需要的内存容量超过了可用内存容量，可能较好理解些
	public static void outOfMemory(){
		//Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit	
		//int[] arr = new int[Integer.MAX_VALUE];
		//这是 heap outOFMemory 
		//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		List<int[]> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		while(true){
			//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space  
			//这是因为int[1000]需要的mem足够多。如果仅仅是list.add(new String("abc")),因为String需要的mem很少，所以我等了半天也没报错....
			list.add(new int[1000]);
			
			//list.add(new String("abc")),因为String需要的mem很少，所以我等了半天也没报错....
			//list2.add(new String("abc"));
		}
			
	}
	public static void main(String[] s){
		//StackOverflow test
		//wrong -> 下边的这2行不是stackOverflow
		//int i = 2147483641;
		//int[] arr = new int[2];   i = arr[2];
		
		
		//StackOverflowTest(0);
		outOfMemory();
		
		/*
				
			
			Integer i1 = 128;
			int i2 = 128;
			//true
			//Integer和int比较时，自动拆箱为int
			System.out.println(i1 == i2);
			
			Integer a = Integer.valueOf(3);
			Integer b = Integer.valueOf(3);
			//true
			//因为-128<3<127,所以valueOf内部返回的是同一个IntegerCache
			System.out.println(a == b);
			++a;
			//4 
			//Integer a先被拆为int，自增，再通过自动封箱，返回IntegerCache[4] -> 因为在-128~127内，所以自动返回Cache
			System.out.println(a);
			//false
			System.out.println(a == b);
			//和a++相反
			--a;
			//true 
			System.out.println(a == b);
			
			//直接new 新对象了
			Integer c = new Integer(3);
			Integer d = new Integer(3);
			//false
			//地址不同
			System.out.println(c == d);
			++c;
			++d;
			//true
			//c,d自增后，自动装箱 return Cache
			System.out.println(c == d);
		*/
	}
}
class Solution{
	public static int B = 9;
	public static void printB(){
		System.out.println(B+"");
	}
}