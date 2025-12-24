import java.util.*;

class Piramides{

    static int N, D;
    static int[][] soltas;
    static long[][] visitadas;
 

    static boolean isVisited(int linha, int pos){

	if(visitadas[linha][pos]>1){
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
    
    static long whiteRabbit(int linha, int posicao){

	//como apenas quero 1 ou 0 caso seja possivel
	//multiplica-se os returns;
	//nao usar (0,0)

	
	if(isLoose(linha, posicao)){
	    return 0;
	}
	    
	if(linha == N){
	    //System.out.println("end of piramid");
	    return 1;
	}

	if(!isVisited(linha,posicao)){
	    //se nao foi visitado
	    if(posicao>1 && posicao<N-linha+1){

		long one = whiteRabbit(linha+1, posicao-1);
		long two = whiteRabbit(linha+1, posicao);
		visitadas[linha][posicao] = one+two;
	       //System.out.println("Returning one+two "+(one+two)+" line/pos "+linha+ " "+posicao);
		return one+two;
	    }
	    if(posicao==1){
		long three = whiteRabbit(linha+1, posicao);
		visitadas[linha][posicao] = three;
	//	System.out.println("Returning three "+three+" line/pos "+linha+ " "+posicao);
		return three;
	    }
	    if(posicao==N-linha+1){
		long four = whiteRabbit(linha+1, posicao-1);
		visitadas[linha][posicao] = four;
//		System.out.println("Returning four "+four+" line/pos "+linha+ " "+posicao);
		return four;
	    }

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
	visitadas = new long[N+1][N+1];
	
	for(int i=0; i<D; i++){
	    soltas[i][0] = input.nextInt(); // camada 1 <= C <= N
	    soltas[i][1] = input.nextInt(); // posiçao 1 <= P <= N-(C-1)
	}
	
	//para cada pedra da base
	long contador=0;
	for(int i=1; i<N+1; i++){
	    
	    if(i>1 && i<N){
		if(!isLoose(1, i)){
		    long one = whiteRabbit(2, i-1);
		    long two = whiteRabbit(2, i);
		    contador += one+two;
		}
	//	System.out.println(contador+"count"+i);
	    }
	    else if(i==1){
		if(!isLoose(1, i)){
		    long three = whiteRabbit(2, i);
		    contador += three;
		}
	//	System.out.println(contador+"counta");
	    }
	    else if(i==N){
		if(!isLoose(1, i)){
		    long four = whiteRabbit(2, i-1);
		    contador += four;
		}
	//	System.out.println(contador+"countb");
	    }
	}

	System.out.println(contador);
    }


}
