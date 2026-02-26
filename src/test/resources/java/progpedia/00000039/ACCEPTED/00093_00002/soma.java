import java.io.*;
import java.util.*;

class soma{

    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int v[] = new int[n];
	int k=n*n/2 - n/2;
	int ck=0;
	//System.out.println(k);
	int somas[] = new int[k];
	for(int i=0;i<n;i++)
	    v[i]=in.nextInt();
	for(int i=0;i<n;i++){
	    for(int j=0;j<i && j!=i;j++){
		somas[ck]=v[i]+v[j];
		//System.out.print(somas[ck]+ " ");
		ck++;;
	    }
	    //System.out.println();
	}
	int np = in.nextInt();
	int p[] = new int[np];
	for(int i=0;i<np;i++)
	    p[i]=in.nextInt();
	Arrays.sort(somas);
	//for(int i=0;i<k;i++)
	    //  System.out.println(somas[i]);
	for(int i=0;i<np;i++)
	    System.out.println(bsearch(somas,p[i]));
    }

    public static int bsearch(int somas[],int x){
	int low = 0;
	int high = somas.length-1;
	int mid=0;
	while(somas[low] < somas[high]){
	    mid = low + (high-low)/2;
	    if(x-somas[mid]==somas[mid+1]-x && somas[mid]!=somas[mid+1]){
		System.out.print(somas[mid] + " "); 
		return somas[mid+1];
	    }
	    if(x<somas[mid]){
		high = mid;
	    }
	    else if(x>somas[mid]){
		low = mid+1;
	    }
	    else return somas[mid];
	}   
	if(low > 0 && x-somas[low-1]<somas[low]-x)
	    return somas[low-1];
	return somas[low];
    }
    
}
