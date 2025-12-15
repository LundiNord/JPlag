//Com ajuda do Tiago Francisco

import java.io.*;
import java.util.*;


class P10{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int N=in.nextInt();
	Par_de_sapatos encomenda[]=new Par_de_sapatos[N];
	for(int i=0;i<N;i++){
	    int tempo=in.nextInt();
	    encomenda[i] =  new Par_de_sapatos(i+1,in.nextFloat()/tempo);
	}
	Arrays.sort(encomenda);
	for(int j=0;j<N-1;j++){
	    System.out.print(encomenda[j].posicao + " ");
	}
	System.out.println(encomenda[N-1].posicao);
    }
}


class Par_de_sapatos implements Comparable<Par_de_sapatos> {
    int posicao;
    float encomenda;
    
    Par_de_sapatos(int posicao, float encomenda){
	this.posicao=posicao;
	this.encomenda=encomenda;
    }
    
    // Definir como comparar dois elementos da classe Team
    // compareTo e uma funcao que compara um Par com outro Par "p"
    // Esta funcao deve devolver:
    //  - numero < 0 se objecto for menor que objecto "p"
    //  - numero > 0 se objecto for maior que objecto "p"
    //  - zero, se objecto for igual ao objecto "p" -> NAO APLICAVEL
    @Override
    
	public int compareTo(Par_de_sapatos  e){

	if(this.encomenda==e.encomenda){
	    if(this.posicao<e.posicao)
		return -1;
	    else return 1;
	}	else {
	    if (e.encomenda>this.encomenda)
		return 1;
	    else return -1;
	}
    }
}
