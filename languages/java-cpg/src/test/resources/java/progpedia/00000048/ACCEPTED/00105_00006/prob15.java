import java.util.*;
import java.io.*;

public class prob15 {
    static int n;                 
    static boolean prox[][]; 
    static boolean v[];  

    public static void main(String args[]) {
    	Scanner stdin = new Scanner(System.in);
	
    	n = stdin.nextInt();
    	int l = stdin.nextInt();
    	
    	prox= new boolean[n+1][n+1];
    	v = new boolean[n+1];	
    	
    	for (int i=0; i<l; i++) {
    		int x = stdin.nextInt();
    		int y = stdin.nextInt();
    		prox[x][y] = prox[y][x] = true;
    	}
    	
    	int count =0;
    	for(int i = 1; i<=n; i++){
    		if (!v[i]) {
    			circuito(i);
    			count++;
    		}
    	}
    	
    	if(l == 0){
    		count = n;
    	}
    	System.out.println(count);
    }
        
    static void circuito(int a) {
        v[a] = true;
    	for (int i=1; i<=n; i++){
    		if (prox[a][i] && !v[i])
    			circuito(i);
     	}
    }
}