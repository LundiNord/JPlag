import java.io.*;
import java.util.*;

public class moo06{
    static int[] soma;

    public static int bSearch(int[] list, int low, int high, int key){
	if(key<list[low]){
  	    System.out.println(list[low]);
	    return 0;
	}
	if(key>list[high]){
	    System.out.println(list[high]);
	    return 0;
	}
	
	
	
	while(high>low){
	    int mid = low + (high-low)/2;
	    
	    if(key==list[mid]){ 
		System.out.println(list[mid]);
		return 0;
	    }
	    
	    else if(key<list[mid])
		high = mid;
	    
	    else if(key>list[mid])
		low = mid+1;
	}
	
	int difh = soma[low] - key;
	int difl = key - soma[low-1];

	if(difh==difl){
	    System.out.println(soma[low-1] + " " + soma[low]);
	    return 0;
	}

	else if(difh>difl){
	    System.out.println(soma[low-1]);
	    return 0;
	}
	
	else{
	    System.out.println(soma[low]);
	    return 0;
	}
    }

    
    
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt();
	int[] s = new int[n];


	for(int i=0; i<n; i++){
	    s[i] = in.nextInt();
	}

	soma = new int[n*(n-1)/2];

	int l = 0;
	
	for(int i=0; i<n; i++){
	    for(int j=i+1; j<n; j++){
		soma[l]=s[i]+s[j];
		l++;
	    }
	}
	
	Arrays.sort(soma);

     	         /*for(int i=0; i<l; i++){
		   System.out.print(soma[i] + " ");
		   }
		   
		   System.out.println();*/
	
	int p = in.nextInt();
	
	for(int i=0; i<p; i++){
	    int pi=in.nextInt();
	    bSearch(soma, 0, l-1, pi);
	}
    }
}
