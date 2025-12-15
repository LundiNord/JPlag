import java.util.*;
import java.io.*;

class inversoesn2{
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	int v[] = new int[n];
	for(int i = 0 ;i < n; i++)
	    v[i] = stdin.nextInt();
	int cont = 0;
	for(int i  = 0; i < n-1; i ++){
	    if(v[i]>v[i+1]){
		
		int aux = v[i];
		v[i] = v[i+1];
		v[i+1]=aux;
		cont++;
	    }
	}
	for(int i  = 0; i < n-1; i ++){
	    if(v[i]>v[i+1]){
		
		int aux = v[i];
		v[i] = v[i+1];
		v[i+1]=aux;
		cont++;
	    }
	}
	System.out.println(cont);




    }
}
