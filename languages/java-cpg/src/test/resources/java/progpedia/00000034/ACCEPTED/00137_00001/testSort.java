import java.io.*;
import java.util.*;

public class testSort { 
    public static void main(String args[]) {
		
		int n;
		Scanner input = new Scanner(System.in);
		
		n = input.nextInt();
		int []vec = new int[n];
		for (int i=0; i<n; i++)
			vec[i] = input.nextInt();
	
		Arrays.sort(vec);
	
		for (int i=0; i<n; i++){
			if(i>0)
				System.out.print(" ");
			System.out.print(vec[i]);
		}
		System.out.println();
    }
}
