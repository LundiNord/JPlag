import java.math.*;
import java.util.*;
import java.util.*;
import java.io.*;
import java.util.*;
import java.io.*;
class p3b{
    static int a[];
    static long inv;
    static void mergesort(int v[], int start, int end) {
	//System.out.println(start + " "+end);
	int middle = (start+end)/2;
	if(end>start){
	    mergesort(v, start, middle);
	    mergesort(v, middle+1, end);
	    merge(v, start, middle+1, end);
	}
    }
    static void merge(int v[], int s, int m, int e){
	int tam=e-s+1;
	int j=s,k=m,i=0;
	while(j<m &&k<=e){
	    if(v[j]<=v[k])
		a[i]=v[j++];
	    else{
		inv+=m-j;
		a[i]=v[k++];
	    }
	    i++;
	}
	while(j<m)
	    a[i++]=v[j++];
	while(k<=e)
	    a[i++]=v[k++];
	for(int p=0;p<tam;p++)
	    v[p+s]=a[p];
    }
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	int v[]=new int[N];
	a=new int[N];
	for(int i=0;i<N;i++)
	    v[i]=sc.nextInt();
	inv=0;
	mergesort(v,0,N-1);
	System.out.println(inv);
    }
}

