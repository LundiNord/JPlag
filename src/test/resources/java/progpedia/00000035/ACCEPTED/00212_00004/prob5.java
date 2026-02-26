import java.util.*;

public class prob5{
	/* public static void main(String args[]){
	/*contador   0
	Para i   0 ate n 􀀀 1 fazer
	Para j   i + 1 ate n 􀀀 1 fazer
	Se v[i] > v[j] ent~ao
	contador   contador + 1
	escrever(contador) 
	Scanner input=new Scanner(System.in);
	int n=input.nextInt();
	int v[]=new int[n];
	for(int i=0;i<n;i++){
		v[i]=input.nextInt();
	}
	int contador=0;
	for(int i=0;i<n;i++){
		for(int j=i+1;j<n;j++){
			if(v[i]>v[j]){
				contador=contador+1;
			}
		}
	}
	System.out.println(contador);
	}*/

// Exemplo de implementacao do mergesort
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------
  // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[end+1];
	int conta=0;

	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
	    if (v[p1] <= v[p2]) aux[i++] = v[p1++];  // Escolher o menor e adicionar
	    else{                
	    	aux[i++] = v[p2++];
	    	conta=conta+middle-p1+1;}
	}
	while (p1<=middle) aux[i++] = v[p1++];     // Adicionar o que resta
	while (p2<=end)    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
    return conta;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
	int middle;
	int contador=0;
	if (start<end) {                 // Parar quando tamanho do array < 2
	    middle = (start+end)/2;        // Calcular ponto medio
	    contador += mergesort(v, start, middle);
		contador += mergesort(v, middle+1, end);
		contador += merge(v, start, middle, end);

	}
	return contador;
    }
 
    public static void main(String args[]) {
    Scanner stdin = new Scanner(System.in);
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	// Ordenar
	int vito=mergesort(v, 0, n-1);
	System.out.println(vito);
    }
}

