import java.util.*;

public class Prob005 {
    static int n; //Quantidade de números da minha sequência
    static int v[];   // Guardar a sequência
    static int conta;
    static int soma;
    
    //---------------------------------------------------------------------
    
    /*
    static void solve1(){
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		if(v[i] > v[j]){
		    conta = conta + 1;
		}
	    }
	}
       	System.out.println(conta);
    }
    */

    //----------------------------------------------------------------------

    static void solve2(){
	
	mergesort(v,0,n-1);
	
	System.out.println(conta);
    }
    
    static int mergesort(int v[], int start, int end) {
	int middle;
	if (start<end) {   // Parar quando tamanho do array < 2
	    middle = (start+end)/2;  // Calcular ponto medio
	    conta = 0;
	    conta = conta + mergesort(v, start, middle);   // Ordenar metade esquerda
	    conta = conta + mergesort(v, middle+1, end);   // Ordenar metade direita
	    conta = conta + merge(v, start, middle, end);  // Combinar duas metades ordenadas
	}
	return conta;
    }
    
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[end+1];
	int conta=0;
	
	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	
	while (p1<=middle && p2<=end) { // Enquanto de para comparar
	    if (v[p1] <= v[p2]){
		aux[i++] = v[p1++];  // Escolher o menor e adicionar
	    }
	    else{
		aux[i++] = v[p2++];	    
		conta = conta + (middle-p1+1);
	    }
	}
	
	while (p1<=middle)
	    aux[i++] = v[p1++];  // Adicionar o que resta
	
	while (p2<=end)
	    aux[i++] = v[p2++];	
	
	for (i=start; i<=end; i++)
	    if(aux[i] < v[i]){
		v[i] = aux[i];  // Copiar array aux[] para v[]
	    }
		
	return conta;
    }
    
    //----------------------------------------------------------------------
    
    static void readInput(){
	Scanner stdin = new Scanner(System.in);
	
	n = stdin.nextInt(); //Quantidade de números da minha sequência
	
	v = new int[n]; // Alocar memoria para a sequência

	for(int i=0;i<n;i++){
	    v[i] = stdin.nextInt();
	}
	
    }
    //------------------------------------------------------------------
    
    public static void main(String args[]) {
	
	readInput();
	
	//solve1();
	
	solve2();
    }

}
