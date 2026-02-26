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
	  
        for (int i = 0; i < n - 1; i++)  
        {  
            int troca = i;  
            for (int j = i + 1; j < n; j++){  
                if (seq[j] < seq[troca]){  
                    troca= j; 
					total++;
                }  
            }  
            int peq = seq[troca];   
            seq[troca] = seq[i];  
			total++;
            seq[i] = peq;  
        }  
		
		System.out.println(total-2);
		/*for(int i = 0; i < n; i++)
			System.out.print(seq[i] + " ");
		/*Arrays.sort(seq);
		
		for(int i = 0; i < n; i++){
			if(i < n - 1)
				System.out.print(seq[i] + " ");
			else 
				System.out.println(seq[i]);
		}*/
		
	}
	
}
