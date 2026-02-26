
//Feito em conjunto com Francisco Pimenta na segunda aula pratica 23/10

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {

	int a,b;
	
	Scanner input = new Scanner(System.in);

	a = input.nextInt();

	int v[] = new int[a];

	for(int i=0; i<a; i++)
	    {
		v[i] = input.nextInt();
	    }

	Arrays.sort(v);
	
	for (int i=0; i<a; i++)
	    {
		if(i+1==a)
		 System.out.print(v[i]);
		else
		    {

	    System.out.print(v[i] + " ");
	    
		    }
		    }
	System.out.println();
    }
}
