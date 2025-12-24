import java.util.*;

class Piramides{

    static int N, D;
    static int[][] soltas;
    static int[][] visitadas;
 

    static boolean isVisited(int linha, int pos){

	if(visitadas[linha][pos] == 1){
	    return true;
	}

	return false;
    }

    static boolean isLoose(int linha, int pos){

	for(int i=0; i<D; i++){
	    if(soltas[i][0] == linha && soltas[i][1] == pos){
		//	System.out.println("Loose: "+soltas[i][0] + " "+ soltas[i][1]);
		return true;
	    }
	}
	return false;
    }
    
    static int whiteRabbit(int linha, int posicao){

	//como apenas quero 1 ou 0 caso seja possivel
	//multiplica-se os returns;
	//nao usar (0,0)

	int contador=0;
	
	if(isLoose(linha, posicao)){
		return 0;
	}
	
	if(!isVisited(linha, posicao)){
	    visitadas[linha][posicao] = 1;
	 
	    
	    if(linha == 1){
		return 1;
	    }
	    
	    //se nao foi visitado
	    if(posicao>1 && posicao<N){
		contador *= whiteRabbit(linha-1, posicao-1)*whiteRabbit(linha-1, posicao);
	    }
	    if(posicao==1){
	        contador += whiteRabbit(linha-1, posicao);
	    }
	    if(posicao==N){
		contador += whiteRabbit(linha-1, posicao-1);
	    }

	    return contador;
	}
	    
    
	
	return visitadas[linha][posicao];
    }

    public static void main(String args[]){

	Scanner input = new Scanner(System.in);
	N = input.nextInt(); //layer #1
	if(N == 1){
	    System.out.println(1);
	    System.exit(0);
	}
	D = input.nextInt(); //nr of loose stones
        soltas = new int[D][2];
	visitadas = new int[N+1][N+1];
	
	for(int i=0; i<D; i++){
	    soltas[i][0] = input.nextInt(); // camada 1 <= C <= N
	    soltas[i][1] = input.nextInt(); // posiçao 1 <= P <= N-(C-1)
	}
	
	//para cada pedra da base
	int contador=0;
	for(int i=1; i<N+1; i++){
	    
	    if(i>1 && i<N){
		contador += whiteRabbit(N-1, i-1) + whiteRabbit(N-1, i);
	    }
	    else if(i==1){
		contador += whiteRabbit(N-1, i);
	    }
	    else if(i==N){
		contador += whiteRabbit(N-1, i-1);
	    }
	}

	System.out.println(contador);
    }


}
