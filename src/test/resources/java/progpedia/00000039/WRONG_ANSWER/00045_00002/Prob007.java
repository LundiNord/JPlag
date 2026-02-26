import java.util.*;

public class Prob007 {
    static int n; //Quantidade de números da minha sequência
    static int v[]; //sequência de números
    static int k;  //nºtotal de combinações entre n e cada par
    static int p;  //nº de perguntas
    static int val[]; //valores de cada pergunta
    static int soma[]; //soma dos pares da sequência
    static int aux[];  //copia de soma[]
    //variáveis do bsearch
    static int low;
    static int high;
    static int key;
    static int middle;

    //--------------------------------------------------

    static void sum(int v[]){
	int conta=0;
	int a=0;
	soma =new int[n*n];

	for(int i=0;i<n-1;i++){ // soma pares
	    for(int j=i+1;j<n;j++){
		soma[conta]=v[i]+v[j];
		conta++;		    
	    }
	}

	aux =new int[conta];//int[conta] -> conta é o Nº de combinações possivel
	
	for(int i=0;i<conta;i++) // copiar array soma para aux
	    aux[i]=soma[i];
       	
	Arrays.sort(aux);

	for(int i=0;i<p;i++) //imprime resultado final
	    System.out.println(bsearch(aux,0,conta-1,val[i]));
    }
    
    //----------------------------------------------------------------------

    static int bsearch(int v[], int low, int high, int key){
	while(low < high){
	    middle = low + (high-low)/2;
	    if(key == v[middle]){        
		return v[middle];
	    }	  
	    else if(key < v[middle]){        
		high=middle;       
	    }
	    else if(key > v[middle]){
		low= middle+1;
	    }
	}
	
	if(low-1<0)  //resposta está no início do array
	    return v[0];
	else if(Math.abs(key-v[low]) == Math.abs(key-v[low-1])){ //caso em que as distâncias à resposta são iguais
	    System.out.print(v[low-1] + " ");
	    return v[low];
	}
	else
	    return v[low-1];
	
    }

    //----------------------------------------------------------------------<<<
    
     static void readInput(){
	Scanner stdin = new Scanner(System.in);
	
	n = stdin.nextInt(); //Quantidade de números da minha sequência

	v = new int[n]; // Alocar memoria para a sequência

	//k= n*(n-1)/2;  // nº total de combinações entre n e cada par

	for(int i=0;i<n;i++)
	    v[i]=stdin.nextInt();
	
	p = stdin.nextInt(); // nº de casos a procurar

	val = new int[p];  // alocar memória para cada caso

	for(int j=0;j<p;j++)
	    val[j] = stdin.nextInt();  //casos individuais a procurar	    

	Arrays.sort(v); //ordena v

     }
    
    //------------------------------------------------------------------
    
    public static void main(String args[]) {
	
	readInput();
	
	sum(v);
	
    }

}

