import java.util.Scanner;


public class Prob5 {

	public static void main (String args  []){

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int v [] = new int [n];
		for (int i =0 ; i< n ; i++ ){
			v[i]= in.nextInt();
		}
		int count =0;
		int aux =0;
		for(int j=0;j<n-1;j++){
			for(int k=j+1;k<n;k++){			
				if(v[k]<v[j]){
					aux=v[k];
					v[k]=v[j];
					v[j]=aux;
					count ++;	
				}
			}
		}
	
		System.out.println(count); 
		
		
	}
}
