class Test{
	//public static int A = 10;
	/*
		public static void main(String[] s){
			//System.out.println(A+"");
			Solution.printB();
		}
	*/
	public static void main(String[] s){
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
	}
}
class Solution{
	public static int B = 9;
	public static void printB(){
		System.out.println(B+"");
	}
}