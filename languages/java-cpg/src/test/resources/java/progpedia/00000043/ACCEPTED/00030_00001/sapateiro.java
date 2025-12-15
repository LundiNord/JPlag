import java.util.*;
import java.io.*;

class Tarefas implements Comparable<Tarefas> {
    public int duracao;
    public int multa;
    public double racio;
    public int indice;

    Tarefas (int x, int y, double z, int i){
	duracao = x;
	multa = y;
	racio = x/(float)y;
	indice = i;
    }
    public int compareTo (Tarefas tar){
	if (racio < tar.racio) return -1;
	else if (racio > tar.racio) return +1;
	else{
	    if (indice < tar.indice) return -1;
	    else if (indice > tar.indice) return +1;
	    else return 0;
	}
	    
    }
}
public class sapateiro{
    public static void main (String [] args){
	Scanner stdin = new Scanner (System.in);
	int n = stdin.nextInt();
	Tarefas tar [] = new Tarefas [n];
	for (int i = 0; i < n; i++)
	    tar [i] = new Tarefas (stdin.nextInt(), stdin.nextInt (), 0.00, i+1);
	Arrays.sort(tar);
	System.out.print (tar[0].indice);
	for (int i = 1; i < n; i++)
	    System.out.print (" " + tar[i].indice);
	System.out.println ();
    }
}
