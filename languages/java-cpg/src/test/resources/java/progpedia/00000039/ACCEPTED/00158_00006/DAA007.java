//Ao resolver este problema, sucedeu que na maioria dos casos, durante a pesquisa binária, a aproximação era feita apenas pelo limite superior, ou seja: faltava-me implementar o cálculo das distâncias, quando a query se encontrava entre dois valores, no intervalo de somas[] sobre o qual era feita a pesquisa binária. Para obter um resultado correto e assim resolver essa parte do problema, obtive ajuda do meu colega Pedro Ramalho, que me explicou o porquê de ser necessária calcular essa mesma diferença entre a query e os limites superior e inferior, para assim poder apresentar o valor/valores corretos.

import java.io.*;
import java.util.*;

class DAA007 {
    
    static int n, k=2, l=0; // Combinacoes de 'n', 'k' a 'k'
    static int comb[], s[], somas[];   // Guardar a combinacao em si

    //Modifiquei a função de combinações, para poder já colocar em cada posição do array somas[], a soma dos valores resultantes de cada combinação 2-a-2 de valores do array[]
    // Funcao recursiva para colocar numero na posicao 'pos',
    // sabendo que nesta posicao podem vir numeros a partir de 'first'
    static void goComb(int pos, int first) {
	
	// Chegamos ao fim da combinacao, vamos imprimi-la
	if (pos == k) {
        int sum = 0;
	    for (int i=0; i<k; i++) {
        sum += s[comb[i]];
        }
        somas[l] = sum;
        l++;
	}
  
	// Vamos a meio da combinacao
	// Gerar numero nao usado para colocar nesta posicao
	else {
	    for (int i=first; i<n; i++) {
		comb[pos] = i;
		goComb(pos+1, i+1);
	    }
	}
    }
    
    //Função para calcular coefificente binomial
    static int binom(int n, int k) {
        
        if (k>n-k) k=n-k;
 
        int b=1;
        
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        
        return b;
    }
    
    static int bsearch(int v[], int low, int high, int key){
      
      int dlow, dhigh;

      while(low <= high){
          
        int mid = low + (high-low)/2;
          
        //Caso se encontre a quary/key em somas[], então podemos impri-la.
        if(key == v[mid]) {
            System.out.printf("%d\n",somas[mid]);
            return 0;
        }
          
        //Caso isso não aconteça, então ela está entre dois valores ou existe uma aproximação por limite superior e/ou inferior entre esses dois valores, que são respetivamente à direita e esquerda da metade do array somas[] ou de uma qualquer sub-divisão do mesmo.
        else if(key>v[mid] && key<v[mid+1]) {
            
            //Como não se encontrou exatamente o valor da query, então vamos calcular a diferença, face ao limite superior e inferior em que esta possa estar.
            dlow = key-v[mid];
            dhigh = v[mid+1]-key;
              
            //Se o limite inferior for mais próximo da query/key, então este é escolhido como a melhor aproximação em somas[].
            if(dlow < dhigh) {
                System.out.printf("%d\n",v[mid]);
                return 0;
            }
              
            //Se o limite inferior e o superior forem aproximações igualmente boas da query/key, então estes são ambos contabilizados e impressos.
            else if(dlow==dhigh) {
                System.out.printf("%d %d\n",v[mid],v[mid+1]);
                return 0;
            }
            
            //Se o limite inferior for mais próximo da query/key, então este é escolhido como a melhor aproximação em somas[].
            else {
                System.out.printf("%d\n",v[mid+1]);
                return 0;
            }
        }
        
        //Pode acontecer que a query ainda não tenha sido encontrada no intervalo que estamos a analisar, então:
        else {
        
        //Se a query/key está à esquerda da metade, então vai-se agora procurar em somas[] no intervalo [0;mid-1]
        if (key < v[mid]) high=mid-1;
        
        //Caso contrário, a query está à esquerda da metade, assim procura-se em somas[] no intervalo [mid+1;high]
        else low = mid+1;
        
        }
      }
        
      return -1;
}
    
    public static void main (String args[]) {
        
        //Data parsing
        Scanner in = new Scanner(System.in);
        
        n = in.nextInt();
        
        s = new int [n];
        
        for (int i=0; i<n; i++) s[i] = in.nextInt();
        
        int p = in.nextInt();
        
        int q[] = new int [p];
        
        for (int i=0; i<p; i++) q[i] = in.nextInt();
        
        //Sabemos que o número de pares (tamanho de somas[]) é igual ao coeficiente binomial (n k), logo implementei uma função para o calcular em cima.
        int npares = binom(n,k);
        
        somas = new int[npares];

        // Alocar memoria para criar combinacoes
        comb = new int[n];

        // Usei o código para gerar combinações dado em outra aula prática, para ao mesmo tempo ir contruindo o array com a soma dos pares.
        goComb(0, 0);
        
        //Algoritmo sort padrão do java a atuar sobre o array somas[]
        Arrays.sort(somas);
        
        //Podemos tirar as seguintes conclusões, sabendo que somas[] está agora organizado por ordem crescente.
        
        for (int i=0; i<p; i++) {
            
            //Caso a query seja menor que todos os valores do array, então a melhor aproximação é a que está no primeiro valor.
            if(q[i]<somas[0]) System.out.printf("%d\n",somas[0]);
            //Análogamente, se existe uma query maior que todos os valores do array, então a melhor aproximação é o último valor de somas, ou seja: o mais alto.
            if(q[i]>somas[npares-1]) System.out.printf("%d\n",somas[npares-1]);
            //Caso contrário, temos que fazer pesquisa binária, visto que a query se encaixa no intervalo de valores em somas[].
            else bsearch(somas, 0, npares-1, q[i]);
        }
        
    }
}
