import java.io.*;
import java.util.*;

class ordnu{
    public static void main(String args[]){
	int n , num , max = 0 , min = Integer.MAX_VALUE;
	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	int v[] = new int[n];
	for(int i = 0; i < n ; i++){
	    v[i] = stdin.nextInt();
	    if(v[i] > max)
		max = v[i];
	    else if(v[i] < min)
		min = v[i];
	}
	int conta[] = new int[1000000];

	for(int i = 0; i < n; i++)
	    conta[v[i]]++;
	int i = 0;

	for(int j = min; j < 1000000 ; j++){
	    while(conta[j] > 0){
		v[i] = j;
		conta[j]--;
		i++;
	    }
	}
	for(int j = 0; j < n ; j++)
	    if(j < n -1)
		System.out.print(v[j] + " ");
	    else
		System.out.print(v[j]);
	System.out.println();

    }





}
