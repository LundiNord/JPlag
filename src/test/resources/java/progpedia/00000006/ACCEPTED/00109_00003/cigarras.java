import java.util.*;
import java.io.*;

public class cigarras{

    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int n = 0;
	int i = 0;
	int m = 0;
	int v[]= new int[30];

	while((v[i]=in.nextInt())!=0){
	    for(int j =0;j<i; j++)
		if(v[j]==v[i]) i=j;
	    i++;
	}

	while(v[n]!=0){
	    System.out.println(v[n]);
	    n++;
	}
    }
}

