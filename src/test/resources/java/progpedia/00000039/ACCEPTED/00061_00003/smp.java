import java.io.*;
import java.util.*;


public class smp{

static int bs(int k, int somas[],int low, int high){
    
    while(low < high){

    int mid= low + (high-low)/2;
    if(somas[mid]==k)
	return mid;
    else if(somas[mid]>k)
	return bs(k,somas,low,mid);
    else
	return bs(k,somas,mid+1,high);
    

    }
    return low;

}
    public static void main(String args []){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	int s[] = new int[n];
	for(int i=0;i<n;i++)
	    s[i]=stdin.nextInt();
	int p = stdin.nextInt();
	int pi[] = new int[p];
	for(int j=0;j<p;j++)
	    pi[j]= stdin.nextInt();

	int nmaximo = n*(n-1)/2;
	int somas[] = new int[nmaximo];
	int pos=0;
	for(int i=0;i<n;i++)
	    for(int j=i+1;j<n;j++){
		somas[pos]=s[i]+s[j];
		pos++;
	    }
	Arrays.sort(somas);
	for(int i=0;i<p;i++){
	    
	 
	    int x = bs(pi[i],somas,0,nmaximo-1);


	    if(x==0)
		System.out.println(somas[x]);
	    else if(Math.abs(somas[x]-pi[i])==Math.abs(somas[x-1]-pi[i])){
		if(somas[x]==somas[x-1])
		    System.out.println(somas[x]);
		else
		    System.out.println(somas[x-1] + " " + somas[x]);
	    }
	    else if(Math.abs(somas[x]-pi[i]) > Math.abs(somas[x-1]-pi[i]))
		System.out.println(somas[x-1]);
	    else
		System.out.println(somas[x]);


	}

   }
}
