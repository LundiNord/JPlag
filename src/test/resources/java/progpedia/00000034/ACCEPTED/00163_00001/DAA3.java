import java.io.*;
import java.util.*;

class DAA3{
    public static void main(String args[]) {
    Scanner scan=new Scanner(System.in);
    int size=scan.nextInt();
    int i;
    int v[]=new int[size];
    for(i=0; i<size; i++)
    	v[i]=scan.nextInt();

	Arrays.sort(v);

	for(i=0; i<size; i++)
	    System.out.print(v[i] + " ");
	System.out.println();

    }
}