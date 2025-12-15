
//com ajuda de catarina ribeiro
import java.io.*;
import java.util.*;

public class Prob3 {

	static int n;
	static int seq[]; 
	
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		seq = new int[n];
		for(int i = 0; i < n; i++){
			seq[i] = stdin.nextInt();
		}
		
		Arrays.sort(seq);
		
		for(int i = 0; i < n; i++){
			if(i < n - 1)
				System.out.print(seq[i] + " ");
			else 
				System.out.println(seq[i]);
		}
		
	}
	
}
