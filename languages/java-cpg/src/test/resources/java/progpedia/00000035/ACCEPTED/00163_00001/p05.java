 
import java.io.*;
import java.util.*;
 
public class p05 {
 
    public static int mergesort(int []  v){
     
	int mid=v.length/2 , count=0;
     
	if( v.length < 2)
	    return count;
     
	int l[]=new int [mid];
	int r[] = new int [v.length-mid];
     
	for(int i=0; i<= mid-1; i++)     // antes for(int i=0; i<mid-1; i++)
	    l[i]=v[i];
 
	for(int i=mid; i<=v.length-1; i++)
	    r[i-mid]=v[i];
     
	count=mergesort(l);
	count+=mergesort(r);
	count+=mergesort(l,r,v); 
         
	return count;
    }
 
    public static int mergesort(int [] l, int [] r, int [] v){
	int i=0, j=0, k=0, mid = l.length, count =0;
 
	while(i< l.length && j< r.length){
         
	    if(l[i] <= r[j]){
		v[k]=l[i];
		i++;
	    }
	    else{
		v[k]=r[j];
		j++;
		count=count+(mid-i); // sabemos que as 2 metades dos arrays left e right estao ordenadas, logo se existir algum 
		//l[i] > r[j] entao necessariamente todos os valores a partir do index i em l serao maiores logo podemos contar 
		// as inversoes desta forma 
	    }
	    k++;
	}
 
	while(i < l.length){
	    v[k]=l[i];
	    i++;
	    k++;
	}
 
	while(j< r.length){
	    v[k]=r[j];
	    j++;
	    k++;
	}
	return count;
    }
    public static void main(String args []){
	Scanner in= new Scanner(System.in);
 
	int n= in.nextInt();
	int v [] = new int [n];
 
	for ( int i=0; i < n; i++)
	    v[i]= in.nextInt();
 
	System.out.println(mergesort(v));
 
    }
}
