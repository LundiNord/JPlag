import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Prob5 {
    public static void main (String args[]){
	int v[];
	int n, cont;
	
	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	v = new int [n];
	for(int i=0; i<n; i++){
	    v[i]=stdin.nextInt();
	}
	cont=0;
	for(int i=0; i<n-1; i++)
	    for(int j=i+1; j<n-1; j++){
		if(v[i]>v[j])
		    cont++;
	    }
	System.out.println(cont);
	
    }
}
