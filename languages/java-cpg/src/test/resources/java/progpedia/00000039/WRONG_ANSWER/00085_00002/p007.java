import java.io.*;
import java.util.*;

class p007{
    public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	int s[]= new int[n];
	int sum[]= new int[n*n];
	for(int i=0;i<n;i++)
	    s[i]=in.nextInt();
	int k=0;
	for(int i=0;i<n;i++){
	    for(int j=0;j<n;j++){
		if(s[i]!=s[j]){
		    sum[k]=s[i]+s[j];
		    k++;
		}
	    }
	}
	Arrays.sort(sum);
	int[] somas=clear(sum);
	int p=in.nextInt();
	for(int i=0;i<p;i++){
	    prox(in.nextInt(), somas);
	}
    }
    public static void prox(int p, int[] sum){
	int low=0;
	int high=sum.length-1;
	int mid=0;
	while(high-low>1){
	    mid=low+(high-low)/2;
	    if(sum[mid]>p)
		low=mid;
	    else
		high=mid;
	}
	if(p-sum[low]==sum[high]-p && sum[low]!=p && sum[high]!=p)
	    System.out.println(sum[low]+" "+sum[high]);
	else{
	    if(p-sum[low]<sum[high])
		System.out.println(sum[high]);
	    else
		System.out.println(sum[low]);
	}
    }
    public static int [] clear(int [] v){
	List<Integer> l=new ArrayList<Integer>();
	int c=0;
	for(int i=0;i<v.length;i++){
	    if(v[i]!=0){
		l.add(v[i]);
		c++;
	    }
	}
	int array[]=new int[c];
	for(int i=0;i<c;i++)
	    array[i]=(Integer)l.get(i);
	return array;
    }
}

