import java.io.*;
import java.util.*;



class Segmento implements Comparable<Segmento>{
	int inicio, fim;

	Segmento(int inicio, int fim){
    	this.inicio = inicio;
    	this.fim = fim;
	}
 

public int compareTo(Segmento a){
        
	return this.inicio - a.inicio;
}

}

public class Cobertura{

public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
    int m = stdin.nextInt();
    int n = stdin.nextInt();
    
    Segmento s[] = new Segmento[n];
    for (int i=0; i<n; i++){
    	s[i] = new Segmento (stdin.nextInt(), stdin.nextInt());
    }
    

    Arrays.sort(s);
   

	int end = 0;
	int soma = 0;
	int posicao = 0;
	int best = 0;

	while (end < m){
       
          
          for(int i=0; i<n; i++){
              if(s[i].inicio <= end)
              	if(s[i].fim > best){
              		posicao = i;
              		best = s[i].fim;
              	}
            }
	        end = s[posicao].fim;
	        soma++;

	}
    System.out.println(soma);

}
}
