/*

XXXX 0 
XXX- 1
XX-- 2
X--- 3

 */

import java.io.*;
import java.util.*;

class Pirmaides {
    static int n,d,c[],p[];
 
    static long calcular(){
	long count[][];
	
	int C=n;	
	count = new long[C][C];

	for(int j=0;j<C;j++) {
	    if (pedra(0,j)) count[0][j] = 0;
	    else count[0][j] = 1;
	}
	for(int i=1;i<C;i++){
	    for(int j=0;j<C-i;j++){
		if (pedra(i,j)) count[i][j] = 0;
		else count[i][j] = count[i-1][j] + count[i-1][j+1];
	    }
	}

	/*	for(int i=0;i<C;i++){
	    for(int j=0;j<C-i;j++)
		System.out.print(count[i][j] + " ");
	    System.out.println();
	    }*/

	
	return count[C-1][0];
    }
    
    static boolean pedra(int i,int j){
	for(int x=0;x<d;x++)
	    if(i+1==c[x] && j+1==p[x]) return true;
	return false;
    }


    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);

	n = stdin.nextInt();
	d = stdin.nextInt();

	c = new int[d];
	p = new int[d];
	
	for(int i=0;i<d;i++){
	    c[i]=stdin.nextInt();
	    p[i]=stdin.nextInt();
	}

	System.out.println(calcular());
    }
}
