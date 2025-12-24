import java.util.*;
import java.io.*;

class Intervalo implements Comparable<Intervalo> {
    public int inicio;
    public int fim;
    public int tamanho;
    Intervalo (int x, int y, int z){
	inicio = x;
	fim = y;
	tamanho = y-x;
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
	int end = 0;
	int conta = 0;
	int escolha = 0;
	while (end < cobre){
	    //System.out.printf("end = %d\n", end);
	    for (int j=0; j<n; j++)
		if (in[j].inicio <= end && in[j].fim > in[escolha].fim)
		    escolha = j;
	    end = in[escolha].fim;
	    conta ++; 
	}
	return conta;
    }
    public static void main (String [] args){
	Scanner stdin = new Scanner (System.in);
	int cobre = stdin.nextInt();
	int n = stdin.nextInt();
	Intervalo in [] = new Intervalo [n]; 
	for (int i=0; i < n; i++)
	    in[i]=new Intervalo (stdin.nextInt(), stdin.nextInt(),0);
	Arrays.sort(in);
	/*for (int i = 0; i < n; i++)
	  System.out.println (in[i].inicio + " " + in[i].fim);*/
	System.out.println (solve(in,cobre, n-1));
    }
}
