import java.util.*;

class Prob16{
    static int k=0;

    public static void main(String [] args){
	Scanner in = new Scanner (System.in);
	int numVertices = in.nextInt();
	int numArestas = in.nextInt();
	boolean matrizExisteAresta [][] = new boolean [numVertices+1][numVertices+1];
	for(int i=0;i<numArestas;i++){
	    int x = in.nextInt();
	    int y = in.nextInt();
	    matrizExisteAresta [x][y] = matrizExisteAresta [y][x] = true;
	}
	boolean v [] = new boolean [numVertices+1];
	for(int i=1;i<=numVertices;i++){
	    if(!v[i]){
		k++; 
		contaComponentes(matrizExisteAresta,v,i,numVertices);
	    }
	}
	System.out.println(k);
    }
    public static void contaComponentes(boolean matrizExisteAresta[][],boolean v[], int i,int numVertices){
		v[i]=true;
		for(int j=1;j<=numVertices;j++){
		    if(!v[j] && matrizExisteAresta[i][j]==true)
			contaComponentes(matrizExisteAresta,v,j,numVertices);
	    }
    }
		    
}