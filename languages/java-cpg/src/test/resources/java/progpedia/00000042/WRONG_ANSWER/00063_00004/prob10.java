import java.io.*;
import java.util.*;

class Comparador implements Comparator<int[]> {
    private int index_to_compare;
    
    public Comparador(int index) {
	index_to_compare = index;
    }
    
    public int compare(int[] elem1, int[] elem2) {
	return compare(elem1, elem2, index_to_compare); // recursividade para tratar diferentes colunas
    }
    
    public int compare(int[] elem1, int elem2[], int index) {
	if (elem1.length == index) // verifica se ja procurou em todas as colunas
	    return 0; // elementos iguais
	
	if (elem1[index] == elem2[index]) // verifica se os elementos nos indices "index" sao iguais
	    return compare(elem1, elem2, index + 1); // verifica na coluna seguinte
	
	return elem1[index] - elem2[index]; // retorna a diference entre os elementos
    }
}

class prob10{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);

	int m = in.nextInt();
	int n = in.nextInt();
	int retas[][] = new int[n][2];

	for(int i = 0; i < n; i++){
	    retas[i][0] = in.nextInt();
	    retas[i][1] = in.nextInt();
	}

	//ordenar retas por ordem crescente da primeira coluna
	Comparador c = new Comparador(0);
	Arrays.sort(retas,c);
	
	//processar na lista todos os segmentos que tem inicio pelo menos em end e deste escolher o que termina depois
	//atualizar o end e repetir estes dois passos ate end >= m
	int current = 0;
	int qmin = 0;

	for(int i = 0; (i < n) && (current < m); i++){
            if(retas[i][0] <= current && retas[i][1] > current){
                qmin++;
                current = encontra(retas,current,i,n);
            }            
            else if(retas[i][0] > current){
                qmin = 0;
                break;
            }
        }
        
        if(current < m)
            qmin = 0;
        
        System.out.println(qmin);
    }
    
    public static int encontra(int retas[][], int c, int i, int n){
        int max = 0;
        while(i < n){
            if(retas[i][0] <= c && retas[i][1] > c && retas[i][1] >= max)
                max = retas[i][1];
            else
                break;
            i++;
        }
        return max;
    }
}