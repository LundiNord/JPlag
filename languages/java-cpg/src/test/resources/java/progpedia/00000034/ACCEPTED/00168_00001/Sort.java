

import java.util.*;

public class Sort {
	
	static Scanner sc;
	static int n;
	
    public static void main(String args[]) {

	sc = new Scanner(System.in);
	n = sc.nextInt();
	int v[] = new int[n];
	
	for(int i=0;i<n;i++){
		v[i]=sc.nextInt();
	}
  	
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(v);
	
	for (int i=0; i<n; i++)
	    System.out.print(v[i] + " ");
    }
}
