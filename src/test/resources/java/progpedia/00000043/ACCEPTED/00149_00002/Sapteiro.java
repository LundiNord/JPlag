import java.io.*;
import java.util.*;

class encomenda implements Comparable<encomenda>{
    int dias, multa, indice;
    double racio;

    encomenda(int dias, int multa, int indice){
	this.dias=dias;
	this.multa=multa;
	this.indice=indice;
	racio=(double)dias/(double)multa;
    }

    @Override
    public int compareTo(encomenda e){
	if (racio < e.racio) return -1;
	if (racio > e.racio) return +1;
	if (indice < e.indice) return -1;
	if (indice > e.indice) return +1;
	return 0;
    }
}

public class Sapteiro{
    public static void main(String args[]){
	Scanner input = new Scanner(System.in);
	int n = input.nextInt();
	encomenda e[] = new encomenda[n];
	for(int i=0; i<n; i++)
	    e[i]= new encomenda(input.nextInt(), input.nextInt(), i+1);
	Arrays.sort(e);
	for(int i=0; i<n-1; i++)
	    System.out.print(e[i].indice + " ");
	System.out.println(e[n-1].indice);
    }
}
