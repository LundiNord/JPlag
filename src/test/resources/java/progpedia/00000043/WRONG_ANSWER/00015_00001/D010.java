import java.io.*;
import java.util.*;

public class D010{
    static int n;
    static float tempo;
    static float multa;
    static float values[];
    static int print[];
    
    public static void lerInput(){
	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	values  = new float[n];
	print(= new int[n];)
	for ( int i = 0; i < n; i++){
	    tempo = stdin.nextInt();
	    multa = stdin.nextInt();
	    solve(tempo, multa,i);
	}
    }
    public static void solve(float temp, float mul, int pos){
	float rating =(float) mul / temp;
	int i = 0;
	float val;
	int posi = 0;

	while(i < n-1 && values[i] != 0){
	    if(rating > values[i]){
		val = values[i];
		values[i] = rating;
		rating = val;
		posi = print[i];
		print[i] = pos+1;
	    }
	    i++;
	}

	values[i] = rating;

	if ( mul/temp == rating)
	    print[i] = pos+1;
	else
	    print[i] = posi;
    }

    public static void main(String args[]){
	lerInput();
	System.out.print(print[0]);
	for (int i = 1; i < n; i++){
	    System.out.print(" " + print[i]);
	}
	System.out.println();
    }
}
