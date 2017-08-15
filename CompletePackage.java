class CompletePackage{
	public static void main(String[] args){	
		int[] val = {5,1,5,4,2};
		int[] weight = {4,2,4,5,3};
		
		int length= weight.length;
		int limit = 20;
		int F[] = new int[limit];
		
		for(int i= 0;i<=limit-1;i++){
			F[i] = 0;
			for(int j =0;j<=length-1;j++){
				if(i-weight[j]>=0){
					F[i] = Math.max(F[i],F[i-weight[j]]+val[j]); 
				}
			}
		}
		System.out.println("The max value is "+F[limit-1]);
		System.out.println("Entire maxtrix is:");
		for(int i =0;i<=limit-1;i++){
			System.out.print(i+"\t");
		}
		System.out.print("\n");
		for(int i:F){
			System.out.print(i+"\t");
		}
	}
}