import java.util.*;
import java.io.*;

class Intervalo implements Comparable<Intervalo> {
    public int inicio;
    public int fim;
    Intervalo (int x, int y){
	inicio = x;
	fim = y;
    }
    public int compareTo (Intervalo in){
	if (inicio < in.inicio) return -1;
	else if (inicio > in.inicio) return +1;
	else{
	    if (fim < in.fim) return +1;
	    else if (fim > in.fim) return -1;
	    else return 0;
	}
    }
}
public class minima {
    public static int solve (Intervalo [] in, int cobre, int n){
	int end = in[0].fim;
	int conta = 1;
	for (int i = 0; i < n && end != cobre; i++){
	    if (in[i].inicio==end && in[i].fim<=cobre){
		conta ++;
		end = in[i].fim;
	    }
	}
	return conta;
    }
    public static void main (String [] args){
	Scanner stdin = new Scanner (System.in);
	int cobre = stdin.nextInt();
	int n = stdin.nextInt();
	Intervalo in [] = new Intervalo [n]; 
	for (int i=0; i < n; i++)
	    in[i]=new Intervalo (stdin.nextInt(), stdin.nextInt());
	Arrays.sort(in);
	/*for (int i = 0; i < n; i++)
	  System.out.println (in[i].inicio + " " + in[i].fim);*/
	System.out.println (solve(in,cobre, n));
    }
}
