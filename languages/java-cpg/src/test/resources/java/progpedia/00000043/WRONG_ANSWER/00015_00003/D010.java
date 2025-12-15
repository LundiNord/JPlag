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
    public static void solve(float time, float coima, int pos){
	float ratio = time / coima;
	int i = 0;
	float temp = 0;
	int temppos = 0;

	while(i < n && values[i] != 0){
	    if(ratio < values[i]){
		temp = values[i];
		values[i] = ratio;
		ratio = temp;
		temppos = print[i];
		print[i] = pos;
		pos = temppos;	
	    }
	    else if( ratio == values[i] && print[i] > pos){
		temp = values[i];
		values[i] = ratio;
		ratio = temp;
		temppos = print[i];
		print[i] = pos;
		pos = temppos;	
	    }
	    i++;
	}

	values[i] = ratio;
	print[i] = pos;
    }

    public static void main(String args[]){
	lerInput();
	System.out.print(print[0]+1);
	for (int i = 1; i < n; i++){
	    System.out.print(" " + (print[i]+1));
	}
	System.out.println();
	for (int i = 0; i < n; i++)
	    System.out.print(values[i]);
	System.out.println();
    }
}
