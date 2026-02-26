import java.io.*;
import java.util.*;

class prob16{

    public static boolean visitados[] = new boolean[101];
    public static boolean redes[][] = new boolean[101][101]; //matriz que contem as ligacoes
    public static int n;

    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	
	n = in.nextInt(); //numero de vertices
	int l = in.nextInt(); //numero de ligacoes

	//	Arrays.fill(redes,false);

	for(int i = 0; i < l; i++){
	    int no1 = in.nextInt();
	    int no2 = in.nextInt();

	    redes[no1][no2] = true;
	    redes[no2][no1] = true;
	}

	int contador = 0;
	//	Arrays.fill(visitados,false); //marca todos os nos como nao visitados

	for(int i = 1; i < n+1; i++)
	    for(int j = 1; j < n+1; j++){
		if(!visitados[i] && redes[i][j]){
		    contador++;
		    dfs(i);
		}
	    }
	
	System.out.println(contador);
    }

    public static void dfs(int v){
	visitados[v] = true;
	
	for(int i = 1; i < n+1; i++){
	    if(!visitados[i] && redes[i][v])
		dfs(i);
	}
    }
}
