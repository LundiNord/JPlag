import java.io.*;
import java.util.*;

class prob6{
	public static void main(String []args){
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt(); 
		int [] s = new int[n];
		int fact =  0;
		
		for(int i = 0; i < n; i++){
			s[i] = stdin.nextInt();
			fact+=i;
		}
		
		int [] sum = new int[fact];
		int x = 0;
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < i ; j++){
				sum[x] = s[i]+s[j]; 
				x++;
			}
		}
		
		Arrays.sort(sum);
		
		int p = stdin.nextInt(); 
		int k;
		
		for(int i = 0; i < p; i++){
			x = stdin.nextInt();
			k = pesqbin(sum, 0, fact-1, x);
			if(k!= -1)
				System.out.println(sum[k]);
			else 
				System.out.println(k);
		}
	}
	static int pesqbin (int [] sum, int low, int high, int key){
		int middle = 0;
		while(low < high){
			middle = low + (high - low)/2;
			if(key == sum[middle])
				return middle;
			else if(key < sum[middle])
				high = middle;
			else low = middle + 1;
		}
		
		middle = low;
		if(middle > 0){
			if(key - sum[middle-1] < sum[middle] - key)
				return middle-1;
			if(key - sum[middle-1] == sum[middle] - key){
				System.out.print(sum[middle-1] + " " );
				return middle;
			}
			if(sum[middle]-key < key - sum[middle-1])
				return middle;
		}
		return middle;
		
	}
	
}
	
