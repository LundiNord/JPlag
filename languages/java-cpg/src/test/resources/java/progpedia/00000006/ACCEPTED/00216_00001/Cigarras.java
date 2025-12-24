import java.util.*;

class Cigarras{
    static Scanner in = new Scanner(System.in);
    static int vetor[] = new int [10000];
    static int origem;
    
    static void leitura(){
	origem = in.nextInt();
	int destino = in.nextInt();
	int guarda = destino;
	vetor[origem]=destino;
	while(destino!=0){
	    destino = in.nextInt();
	    vetor[guarda] = destino;
	    guarda = destino;
	}
    }
    
    static void escrita(){
	System.out.println(origem);
	int prox = vetor[origem];
	while(prox!=0){
	    System.out.println(prox);
	    prox = vetor[prox];
	}
    } 

    public static void main(String args[]){
	leitura();
	escrita();
    }

   
}