import java.util.*;

class sapato implements Comparable<sapato>{

    int duracao;
    int multa;
    int indice;

    sapato(int x,int y,int i){
	duracao = x;
	multa = y;
	indice = i;
    }

     public int compareTo(sapato t){
	if(multa/(float)duracao > t.multa/(float)t.duracao) return -1;
	if(multa/(float)duracao < t.multa/(float)t.duracao) return +1;
	else{
	    if(indice < t.indice) return -1;
	    if(indice > t.indice) return +1;
	    else return -1;
	    }
    }

}

class prob10{

    public static void main(String args[]){
	Scanner in = new Scanner(System.in);

	int N = in.nextInt();

	sapato encomendas[] = new sapato[N];
	
	for(int i=0;i<N;i++)
	    encomendas[i] = new sapato(in.nextInt(),in.nextInt(),i+1);

	Arrays.sort(encomendas);  

	for(int i=0;i<N;i++)
	    if(i+1 != N)System.out.print(encomendas[i].indice + " ");
	        else System.out.print(encomendas[i].indice);
	System.out.println();

    }

}
