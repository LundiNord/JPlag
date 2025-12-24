import java.io.*;
import java.util.*;

public class  aula203{
    public static void main(String args[]) {
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	int[] v=new int[n];
	for(int i=0;i<n;i++){
	    v[i]=in.nextInt();
	}
	Arrays.sort(v);
	for(int i=0;i<n;i++){
	    if(i<n-1)
		System.out.print(v[i] + " ");
	    else
		System.out.println(v[i]);
	}
    }
}

