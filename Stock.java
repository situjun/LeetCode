public class Stock{
	public static void main(String[] args){
		int[] stock = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		int[] F = new int[stock.length];
		int max = F[0] = stock[0];
		for(int i=1;i<stock.length-1;i++){
			F[i] = Math.max(stock[i],F[i-1]+stock[i]);
			max = Math.max(max,F[i]);
		}
		System.out.println("Maximum Profit:"+max);
	}
	
	
}