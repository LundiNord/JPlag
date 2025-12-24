import java.io.*;
import java.util.*;

public class ContInv {

	static int n;
	static int seq[]; 
	
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		seq = new int[n];
		for(int i = 0; i < n; i++){
			seq[i] = stdin.nextInt();
		}
		int total=0;
		for(int i = 0; i < n; i++){
			for(int j=i; j<n; j++){
				if (seq[i]<seq[j])
					total++;
			}
		}
		
		System.out.print(total-1);
		/*Arrays.sort(seq);
		
		for(int i = 0; i < n; i++){
			if(i < n - 1)
				System.out.print(seq[i] + " ");
			else 
				System.out.println(seq[i]);
		}*/
		
	}
	
}
