import java.io.*;
import java.util.*;

public class prob06 {

	public static int binarySearch(int low, int high, int q, int v[]){	
		int mid=0;
		
		while(low<high){

			mid=low+(high-low)/2;

			if(q==v[mid]){			return mid;}
			else if(q < v[mid]){	high=mid;	}
			else 					low=mid+1;
		}


		mid=low;
		if(mid>0){
			if(q-v[mid-1] < v[mid]-q){return mid-1;} 
			else if(q-v[mid-1] == v[mid]-q){System.out.print(v[mid-1] + " "); return mid;}
			else if(q-v[mid-1] > v[mid]-q){return mid;}
		}
		return mid;
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
		int a = binarySearch(0, temp-1, questions[i], sums);
		if(a==-1) System.out.println(a);
		else System.out.println(sums[a]);

	}

    }

}