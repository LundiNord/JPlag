import java.util.*;
import java.io.*;

class Encomenda implements Comparable<Encomenda>{
    int dias;
    int multa;
    int ordem;

    Encomenda(int dias, int multa, int ordem){
	this.dias = dias;
	this.multa = multa;
	this.ordem = ordem;
    }

    @Override
    public int compareTo(Encomenda x){
	float ratioA = this.multa/this.dias;
	float ratioB = x.multa/x.dias;
	if(ratioA>ratioB){
	    return -1;
	}
	else if(ratioA<ratioB){
	    return 1;
	}
	else{
	    if(this.ordem<x.ordem) return -1;
	    else if(this.ordem>x.ordem) return 1;
	    else return 0;
	}
    }
}

class Sapateiro{
    public static void main(String[] args){
	Scanner hello = new Scanner(System.in);
	int N = hello.nextInt();
	Encomenda[] array = new Encomenda[N];
	for(int i=0; i<N; i++){
	    Encomenda novo = new Encomenda(hello.nextInt(), hello.nextInt(), i+1);
	    array[i] = novo;
	}
       	Arrays.sort(array);
	for(int i=0;i<N;i++){
	    System.out.print(array[i].ordem);
	    if(i!=N-1) System.out.print(" ");
	}
	System.out.println();
    }
}
