import java.util.*;
import java.io.*;

public class moo06{
    static int bSearch(int[] list, int low, int high, int key){
	int mid = low + (high-low)/2;

	if(key<list[low]){ 
	    System.out.println(list[low]);
	    return 0;
	}
	if(key>list[high]){
	    System.out.println(list[high]);
	    return 0;
	}
	while(high>low){
	    //	    System.out.println(low + ": " + list[low] + "   " + high + ": " + list[high]);

	    
	    mid = low + (high-low)/2;    
	    if (key==list[mid]){
		System.out.println(list[mid]);
		return 0;
	    }
	    
	    else if (key < list[mid]){
		high = mid;
	    }
	    
	    else if (key > list[mid]){
		low = mid+1;
	    }
	}
	
	int difh = list[low] - key;
	int difl = key - list[low-1];

	if (difh == difl){ 
	    System.out.println(list[low-1] + " " + list[low]);
	    return 0;
	}
	else if (difh < difl){
	    System.out.println(list[low]);
	    return 0;
	}
	
	else{
	    System.out.println(list[low-1]);
	    return 0;
	}
	
    }
    public static void main(String args[]){
	
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt();

	int s[] = new int[n];
	for(int i=0; i<n; i++){
	    s[i] = in.nextInt();
	}
	
	
	
	
	/*todas as somas*/
	
	int l=0;
	int soma[]=new int[n*(n-1)/2];
	for(int i=0 ; i<n; i++){
	    for(int j=i+1;j<n;j++){
		soma[l]=s[i]+s[j];
		l++;
	    }
	    
	}
	
	Arrays.sort(soma);
	
	int p = in.nextInt();
	
	for(int j=0; j<p; j++){
	    
	    int pi = in.nextInt();
	    bSearch(soma, 0, l-1, pi);

	}
    }
}
