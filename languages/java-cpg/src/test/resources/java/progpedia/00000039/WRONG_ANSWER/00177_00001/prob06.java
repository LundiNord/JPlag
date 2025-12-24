import java.io.*;
import java.util.*;

public class prob06 {

	public static int dif1=99999;
	public static int dif2=99999;

	public static int best1=-1;
	public static int best2=-1;

	public static int a = 0;

	public static void binarySearch(int low, int high, int mid, int q, int v[]){

		
		
		best1=v[low];
		dif1= q-v[low];
		while(low<high && mid!=low && mid!=high){

			//System.out.println("Performing a search in " + low + " " + high + " " + mid + " for value " + q + " current mid is " + v[mid]);	

			a = Math.abs(q-v[mid]);

			if(dif1>a){	dif1=a; best1=v[mid];	}
			else if(dif1==a && v[mid] != best2){ dif2=a; best2=v[mid];}
			//System.out.println(v[mid] + " " + q);

			if(v[mid]==q){		best1=v[mid]; dif1=0; break;	}
			else if(v[mid]<q){	low=mid; mid=(high+low)/2;	}
			else if(v[mid]>q){ 			high=mid; mid=(high+low)/2;	}	

			if((high-1)==low) {break; }
		}



		if(best2==-1){
			System.out.println(best1);
		}
		else{
			if(best1<best2) System.out.println(best1 + " " + best2);
			else System.out.println(best2 + " " + best1);
		}
	}


 
    public static void main(String args[]) {

	Scanner kb = new Scanner(System.in);

	int x = 0;
	
	int n = kb.nextInt();

	int [] numbers = new int [n];

	for (int i=0; i<n; i++){
		numbers[i]=kb.nextInt();
	}

	int m = kb.nextInt();

	int [] questions = new int [m];

	for (int i=0; i<m; i++){
		questions[i]=kb.nextInt();
	}

	int temp = ((n*n)-n)/2;

	int [] sums = new int [temp];

	for(int i=0; i<n-1; i++){
		for(int j=i+1; j<n; j++){
			sums[x]=numbers[i]+numbers[j];
			x++;
		}
	}

	Arrays.sort(sums);

	// Imprimir
	/*for (int i=0; i<temp; i++) {
	    System.out.println(sums[i]);
	}*/


	for(int i=0; i<m; i++){
		binarySearch(0, temp, temp/2, questions[i], sums);
	}

    }

}