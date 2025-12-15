import java.util.*;





public class mooshak1 {
	
	
	static int maiorlucro(int v[],int range){
		
		 int maxStartIndex=0;
	        int maxEndIndex=0;
	        int maxSum = Integer.MIN_VALUE; 
	 
	        int cumulativeSum= 0;
	        int maxStartIndexUntilNow=0;
	        		
	        for (int currentIndex = 0; currentIndex < range; currentIndex++) {
	        	
	            int eachArrayItem = v[currentIndex];
	            
	            cumulativeSum+=eachArrayItem;
	 
	            if(cumulativeSum>maxSum){
	                maxSum = cumulativeSum;
	                maxStartIndex=maxStartIndexUntilNow;
	                maxEndIndex = currentIndex;
	            }
	            if (cumulativeSum<0){
	            	maxStartIndexUntilNow=currentIndex+1;
	            	cumulativeSum=0;
	            }
	        }
	 return maxSum;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String args[]){
	
		
	Scanner in = new Scanner(System.in);
	int v[]=new int [1000000];
	int range=in.nextInt();
	for(int i=0;i<range;i++)
	{
		v[i]=in.nextInt();
	}
	
	int res=maiorlucro(v,range);
	
	
	System.out.println(res);
	
	
	
	}
}

