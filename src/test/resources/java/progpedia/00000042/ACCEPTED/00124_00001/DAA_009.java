//-------------------------------------------------------------------------
//DAA_009 - Bruna Madeira em conjunto com Federica Murillo 
//+ ajuda dos esclarecimentos das dúvidas dos nossos colegas no piazza :)
//-------------------------------------------------------------------------

import java.io.*;
import java.util.*;

//classe para guardar o segmento
class Segmento implements Comparable<Segmento> {
    public int start;
    public int end;

    Segmento(int s, int e){
	start=s;
	end=e;
    }

    //definir como comparar elementos da classe Segmento
    @Override
	public int compareTo(Segmento s){

	//ordenar os segmentos por ordem crescente de início 
	//em caso de empate ordenar pelo fim

	if (start < s.start || (start == s.start && end > s.end)) 
	    return -1;
	return 1;
    }

}


public class DAA_009 {
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);

	int m = stdin.nextInt(); //ler tamanho de segmento que queremos cobrir
	int n = stdin.nextInt(); //quantidade de segmentos a considerar

	Segmento s[] = new Segmento[n];
	
	for(int i=0;i<n; i++)
	    s[i] = new Segmento(stdin.nextInt(), stdin.nextInt());
	
	//chamada ao sort padrao da Linguagem Java
	//usa o comparador padrao do tipo do array
	Arrays.sort(s);
	
	int fim = 0;
	int conta = 0;
	int atual = 0;

	while(fim<m){
	    for(int i=0; i<n; i++){

		if(s[i].start <= fim){
		    if(s[i].end > atual){
			atual = s[i].end;
		    }
		}
	    }
	    fim = atual;
	    conta++;
        }

	System.out.println(conta);
    }
}
