import java.util.*;
import java.util.Arrays;


class somasmaisproximas{
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int n    = in.nextInt();
		int v[]= new int [n];
	
		for (int i = 0;i<n; i++){
			v[i] = in.nextInt();
		}
	
		int p = in.nextInt();
		int x;
		int z;
		int fact = 0;
		int k=0;

		for (int i=0;i<n;i++){
			fact = fact + i;
		}
		
		
		int somas[] = new int[fact];
		
		
		
	
		for (int i=0;i<n;i++){
			for (int j=i+1;j<n;j++){
				somas[k++] = v[i]+v[j];
			}
		}
		
		Arrays.sort(somas);
		
		for (int j = 0;j<p;j++){
			x = in.nextInt();
			z = pesquisabinaria(somas,0,fact-1,x);
			
			if (z!=-1)
				System.out.println(somas[z]);
			else
				System.out.println(z);	
			
		}
	}
	

	static int pesquisabinaria(int [] v, int low, int high, int key){
		
int middle=0;		
		//System.out.println(low+" "+high+":"+ key + " middle=" + v[middle]);
		while(low<=high){
			middle = low + (high - low)/2;
			//System.out.println(low+" "+high+"   key = "+ key + " middle " + middle + " = "  + v[middle]);
			if(key == v[middle]){
				//System.out.println(middle);
				return middle;
			}
			else if(key < v[middle]){
				high = middle-1;
				//pesquisabinaria(v,low,high,key);
			}
			else{ 
				low = middle+1;
				//pesquisabinaria(v,low,high,key);
			}	
		}
		if ( middle > 0 ) {
			if (key-v[middle-1]<v[middle]-key)
				return middle-1;
			if ( key-v[middle-1] == v[middle]-key ) {
				System.out.print(v[middle-1]+ " " );
				return middle;
			}
		}
			return middle;
	}
}
