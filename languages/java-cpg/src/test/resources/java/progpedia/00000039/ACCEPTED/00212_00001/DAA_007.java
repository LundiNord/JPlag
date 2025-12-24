import java.io.*;
import java.util.*;
 
class DAA_007 {
   
 // Pesquisa binária interativa
  
    static int binarysearch(int [] v, int low, int high, int val){
	int middle = 0;

	//Base Binary Search
	//------------------------------------------------------------
	while(low < high){
	    middle = low + (high - low)/2;

	    if(val == v[middle])
		return middle;
	    else if(val < v[middle])
		high = middle;
	    else 
		low = middle + 1;
	}
	//-------------------------------------------------------------
	//como não encontrou o valor pedido no ciclo anterior não retornou nada. 
	//O low passou a ser a soma mais próxima do lado direito (o que não significa que seja a mais próxima).
	//Teremos que comparar com a soma do lado esquerdo.	
	middle = low;

	if(middle > 0){
	    if(val - v[middle-1] < v[middle] - val) 
//se (val - soma do lado esquerdo) for menor que (soma do lado direito - val) significa que o meu val esta mais perto da soma do lado esquerdo (middle - 1)
		return middle-1;

	    if(val - v[middle-1] == v[middle] - val){//no caso de a diferença dos dois lados ser igual, ou seja retorna as duas somas
		System.out.print(v[middle-1] + " " );
		return middle;
	    }
	    if(val - v[middle-1] > v[middle] - val)
//se (val - soma do lado esquerdo) for maior que (soma do lado direito - val) significa que o meu val esta mais perto da soma do lado direito (middle)
		return middle;
	}
	return middle; //para o caso do middle ser o v[0], ou seja esta será a primeira soma e portanto a soma mais próxima 
    }



    public static void main(String []args){

	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt(); //tamanho da sequencia dos números

	int seq[] = new int[n];

	int fact = 0; 

	//leitura da sequencia dos nrs para somar
	for(int i = 0; i < n; i++){
	    seq[i] = stdin.nextInt();
	    fact+=i; //faz as permutaçoes para saber quantas somas possiveis existem
	}

	// faz as sucessivas somas
	int v[] = new int[fact];
	int x = 0;
	for(int i = 0; i < n; i++){
	    for(int j = 0; j < i ; j++){
		v[x] = seq[i]+seq[j];
		x++;
	    }
	}
	Arrays.sort(v); //para o teste nao sera preciso porque ja vai estar ordenado

	int nperguntas = stdin.nextInt(); // numero de perguntas

	// pesquisa a soma mais proxima.
	for(int i = 0; i < nperguntas; i++){
	    int p = stdin.nextInt(); //leitura das perguntas
	    int res = binarysearch(v, 0, fact-1, p); //pesquisa propriamente dita.
		System.out.println(v[res]);
	}
    }
}

//????? VER SE PODEMOS TIRAR ESTE ULTIMO ELSE
