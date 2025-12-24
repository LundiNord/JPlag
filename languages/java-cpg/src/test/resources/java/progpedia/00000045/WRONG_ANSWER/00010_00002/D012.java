import java.io.*;
import java.util.*;


class Pontos implements Comparable<Pontos>{
    public int linha, coluna;

    Pontos(int i, int j){
	linha = i;
	coluna = j;
    }

    public int compareTo(Pontos p){
	if( coluna < p.coluna)
	    return +1;
	if (coluna > p.coluna)
	    return -1;
	if ( linha < p.linha) 
	    return -1;
	if ( linha > p.linha)
	    return +1;
	return 1;
    }

}

public class D012{
    static int npedras;
    static int array[];
    static int defeitos;
    static Pontos defeito[];

    public static void  lerInput(){
	Scanner stdin = new Scanner(System.in);
	npedras = stdin.nextInt();
	defeitos = stdin.nextInt();
	defeito = new Pontos[defeitos];
	for ( int i = 0; i < defeitos; i++)
	    defeito[i] = new Pontos((stdin.nextInt())-1, (stdin.nextInt())-1);
	Arrays.sort(defeito);
	array = new int[npedras];
    }
    public static void solve(){
	int defeitoss = defeitos;
	defeitos = 0;
	int colunas = 1;
	for(int i = 0; i < npedras; i++){
	    if(defeitos < defeitoss && defeito[defeitos].coluna == i && defeito[defeitos].linha == 0){
		array[i] = 0;
		defeitos++;
	    }
	    else
		array[i] = 1;
	}
	while(colunas != npedras){
	    for ( int i = 0; i < (npedras - colunas); i++ )
		if(defeitos < defeitoss && defeito[defeitos].coluna == i && defeito[defeitos].linha == colunas){
		    array[i] = 0;
		    defeitos++;
		}
		else
		    array[i] += array[i+1];
	    colunas++;
	    
	}
	System.out.println(array[0]);
    }

    public static void main(String args[]){
	lerInput();
	solve();
    }

}
