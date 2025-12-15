//Feito com a ajuda de Artur Ferreira e Francisco Oliveira
import java.util.*;
import java.io.*;

class P007{
    public static void main (String args []){

	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int v[] = new int[n];

	for(int i=0; i<n; i++)
	    v[i] = in.nextInt();	
       
	int np =in.nextInt();
	int p [] = new int[np];

	for(int i=0; i<np; i++)
	    p[i]= in.nextInt();

	int ts= n*n/2 - n/2;
	int somas[] = new int[ts];
	int ps = 0;
	
	for(int i=0; i<n; i++){
	    for(int j=0; j<i && j!=i; j++){
		somas[ps] = v[i] + v[j];
		ps++;
	    }
	}
	Arrays.sort(somas);

	for(int i=0; i<np; i++){
	    System.out.println(bsearch(somas,p[i]));
	}
    }

    public static int bsearch(int somas[], int key){
	int low=0;
	int high = somas.length-1;
	int middle;
	while(somas[low] < somas[high]){
	    middle = low + (high-low)/2;

	    if(key-somas[middle]==somas[middle+1]-key && somas[middle]!=somas[middle+1]){
		System.out.print(somas[middle] + " "); 
		return somas[middle+1];
	    }

	    if(key<somas[middle]){
		high = middle;
	    }

	    else if(key>somas[middle]){
		low = middle+1;
	    }

	    else return somas[middle];
	}   
	if(low > 0 && key-somas[low-1]<somas[low]-key)
	    return somas[low-1];
	return somas[low];
    }

}
			      
