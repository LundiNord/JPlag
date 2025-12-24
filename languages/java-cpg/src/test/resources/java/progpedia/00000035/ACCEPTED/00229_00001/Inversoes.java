import java.io.*;
import java.util.*;

class Inversoes{
    public static void main(String []args){
	Scanner in= new Scanner(System.in);
	int n=in.nextInt();
	int []v= new int [n];
	int conta=0;
	
	for(int i=0;i<n;i++)
	    v[i]=in.nextInt();
	conta=mergesort(v,0,n-1);
	System.out.println(conta);
    }
    static int mergesort(int v[], int low, int high) {
	int mid=0;
	int conta= 0;
	if (low<high) {                 
	    mid = (high+low)/2;        
	    conta+= mergesort(v, low, mid);   
	    conta+= mergesort(v, mid+1, high);  
	    conta+= merge(v, low, mid, high);  
	}
	return conta;
	
    }
     static int merge(int v[], int low, int mid, int high) {
	 int pivot1=low;    
	 int pivot2=mid+1; 
	 int temp[] = new int[high+1];
	 int i = low;
	 int conta=0;
	 while (pivot1<=mid && pivot2<=high) {
	     
	     if (v[pivot1] <= v[pivot2])
		 temp[i++] = v[pivot1++];  
	     else {
		 temp[i++] = v[pivot2++];
		 conta += mid - pivot1 + 1;
	     }

	 }
	 while (pivot1<=mid)
	     temp[i++] = v[pivot1++];
	 while (pivot2<=high)
	     temp[i++] = v[pivot2++];
	 
	 for (i=low; i<=high; i++) 
	     v[i] = temp[i];
	 
	 return conta;
     }
    
}


//neste exercicio tive a ajuda do rui carvalho, principalmente onde tinha de colocar o contador e em algumas condiçoes no mergesort.
