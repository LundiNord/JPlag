import java.io.*;
import java.util.*;

public class prob02{
    public static void main(String args[]) {
		Scanner in = new Scanner (System.in);
		int n;
		n = in.nextInt();
		int v[] = new int [n];
	
		for (int i=0;i<n;i++){
			v[i] = in.nextInt();
		}
		
		if (n==1){
			System.out.println(v[0]);
		}
		int max_total=-1;
		int max_actual=0;
		
	
		for (int i=0;i<n;i++){
			max_actual = max_actual + v[i];
			if (max_actual < 0){
				max_actual = 0;
			}
			if(max_actual > max_total){
				max_total = max_actual;
			}
		}
		if (n!=1)
			System.out.println(max_total);
	}
}
