import java.util.*;
import java.io.*;

// Recebi uma grande ajuda do Andre Brito na resoluçao deste exercicio

class Pontos implements Comparable<Pontos>{

    public int linha, coluna;

    Pontos(int i, int j){
	linha = i;
	coluna = j;
    }

    public int compareTo(Pontos p){
	if ( linha < p.linha) 
	    return -1;
	if ( linha > p.linha)
	    return +1;
	if( coluna < p.coluna)
	    return -1;
	if (coluna > p.coluna)
	    return +1;
	return 1;
    }

}

public class P012{
    static int npedras;
    static long piramide[];
    static int defeitos;
    static Pontos defeito[];

 


    public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
	npedras = stdin.nextInt();
	defeitos = stdin.nextInt();
	defeito = new Pontos[defeitos];
	for ( int i = 0; i < defeitos; i++)
	    defeito[i] = new Pontos((stdin.nextInt()-1), (stdin.nextInt())-1);
	Arrays.sort(defeito);
	piramide = new long[npedras];


	
	int defeitoss = defeitos;
	defeitos = 0;
	int colunas = 1;
	for(int i = 0; i < npedras; i++){
	    if(defeitos < defeitoss && defeito[defeitos].coluna == i && defeito[defeitos].linha == 0){
		piramide[i] = 0;
		defeitos++;
	    }
	    else
		piramide[i] = 1;
	}


	while(colunas != npedras){
	    for ( int i = 0; i < (npedras - colunas); i++ ){
		if(defeitos < defeitoss && defeito[defeitos].coluna == i && defeito[defeitos].linha == colunas){
		    piramide[i] = 0;
		    defeitos++;
		}
		else
		    piramide[i] += piramide[i+1];
	    }
	    colunas++;	    
	}
	System.out.println(piramide[0]);
    }
    

}
