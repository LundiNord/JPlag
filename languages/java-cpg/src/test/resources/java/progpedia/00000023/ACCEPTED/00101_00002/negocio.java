import java.util.*;

class GNode{
    public int nome= Integer.MIN_VALUE;
    public int peso= Integer.MAX_VALUE;
    public ArrayList<Integer> filhos= new ArrayList<Integer>();
}

class Builder{
    void Dijkstra(GNode[] G, int[][] P, GNode o){
	/*Dijkstra que recebe:
	 *Um grafo;
	 *Uma matriz de pesos;
	 *Um no de inicio;
	 *
	 *Este Dijkstra usa ainda a propriedade "nome" de cada no,
	 *propriedade essa que, neste caso, indica o numero da loja.*/
	o.peso= 0;
	ArrayList<GNode> Resultado= new ArrayList<GNode>();
	ArrayList<GNode> Fila= new ArrayList<GNode>();
	for(int i= 0; i< G.length; i++){
	    Fila.add(G[i]);
	}

	while((Fila.isEmpty())== false){
	    int min= Integer.MAX_VALUE;
	    int index= Integer.MIN_VALUE;
	    //EXTRACT_MIN_VALUE
	    for(int i= 0; i< Fila.size(); i++){
		if(Fila.get(i).peso < min){
		    min= Fila.get(i).peso;
		    index= i;
		}
	    }
	    GNode n= Fila.get(index);
	    Resultado.add(n);
	    for(int i= 0; i< n.filhos.size(); i++){
		GNode v= G[(n.filhos.get(i))-1];
		Relax(n, v, P[(n.nome)-1][(v.nome)-1]);
	    }
	    Fila.remove(index);
	}

	//Print dos resultados
	for(int i= 0; i< Resultado.size(); i++){
	    if(i== (Resultado.size()-1)){
		System.out.print(Resultado.get(i).nome);
		System.out.println();
	    }
	    else{
	    System.out.print(Resultado.get(i).nome + " ");
	    }
	}
    }

    void Relax(GNode NoPai, GNode NoFilho, int PesoDaLigacaoPaiFilho){
	if(NoFilho.peso > (NoPai.peso + PesoDaLigacaoPaiFilho)){
	    NoFilho.peso= (NoPai.peso + PesoDaLigacaoPaiFilho);
	}
    }
}

class negocio{
    public static void main(String args[]){	
	/*Input dos dois primeiros valores,
	 *inicializacao dos nos no grafo,
	 *numero de loja dado a cada no e
	 *preenchimento da matriz com infinito*/
	Scanner stdin= new Scanner(System.in);
	int numerodenos= stdin.nextInt();
	GNode[] Grafo= new GNode[numerodenos];
	int[][] Matrizdepesos= new int[numerodenos][numerodenos];
	for(int i= 0; i< numerodenos; i++){
	    for(int j= 0; j< numerodenos; j++){
		Matrizdepesos[i][j]= Integer.MIN_VALUE;
	    }
	    Matrizdepesos[i][i]= 0;
	    Grafo[i]= new GNode();
	    Grafo[i].nome= i+1;
	}
	int cliente= stdin.nextInt();

	/*O programa diz a cada no qual e o nome dos seus filhos e
	 *coloca os pesos na matriz*/
	boolean ciclo= false;
	while(ciclo== false){
	    int temp= stdin.nextInt();
	    if(temp== -1){
		ciclo= true;
	    }
	    else{
		int temp2= stdin.nextInt();
		int temppeso= stdin.nextInt();
		Matrizdepesos[temp-1][temp2-1]= temppeso;
		Matrizdepesos[temp2-1][temp-1]= temppeso;
		Grafo[temp-1].filhos.add(temp2);
		Grafo[temp2-1].filhos.add(temp);
	    }
	}

	//Dijkstra. O print esta no proprio algoritmo.
	Builder algoritmo= new Builder();
	algoritmo.Dijkstra(Grafo, Matrizdepesos, Grafo[cliente-1]);
    }
}