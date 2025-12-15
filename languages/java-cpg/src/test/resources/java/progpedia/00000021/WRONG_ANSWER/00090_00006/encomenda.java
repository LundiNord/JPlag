import java.util.*;

//Classe No
class GNode{
    public int nome= Integer.MIN_VALUE;
    public int comprimentoNo= Integer.MIN_VALUE;
    public ArrayList<Jaula> ligacoes= new ArrayList<Jaula>();
}

//Classe Jaula, que guarda as informacoes de cada ligacao
class Jaula{
    public int largura= 0;
    public int comprimento= 0;
    public int altura= 0;
    public int ligacao= Integer.MIN_VALUE;
   
    Jaula(int l, int c, int a, int f){
	largura= l;
	comprimento= c;
	altura= a;
	ligacao= f;
    }
}

//Classe Medicoes, que guarda os limites da jaula dados pelo input
class Medicoes{
    public int largmin;
    public int largmax;
    public int compmin;
    public int compmax;
    public int altmin;

    Medicoes(int lm, int lma, int cm, int cma, int am){
	largmin= lm;
	largmax= lma;
	compmin= cm;
	compmax= cma;
	altmin= am;
    }
}


//Classe Builder, que contem o Dijkstra e o Relax
class Builder{
    void Dijkstra(ArrayList<GNode> G, GNode o, GNode f, Medicoes medis){
	/*O Dijkstra recebe, como input:
	 *Um Grafo;
	 *Um no de partida;
	 *Um no de chegada;
	 *Medidas maximas da jaula.
	 *
	 *O que este Dijkstra tem de especial e que, antes de comecar um Relax,
	 *ele verifica se a ligacao que se vai tratar no Relax esta dentro
	 *dos limites de tamanho de jaula impostos pelo input.*/
	o.comprimentoNo= 0;
	ArrayList<GNode> Resultado= new ArrayList<GNode>();
	ArrayList<GNode> Fila= new ArrayList<GNode>();
	for(int i= 0; i< G.size(); i++){
	    Fila.add(G.get(i));
	}
	while((Fila.isEmpty())== false){
	    int max= Integer.MIN_VALUE;
	    int index= Integer.MIN_VALUE;
	    //EXTRACT_MAX_VALUE
	    for(int i= 0; i< Fila.size(); i++){
		if(Fila.get(i).comprimentoNo >= max){
		    max= Fila.get(i).comprimentoNo;
		    index= i;
		}
	    }
	    GNode n= Fila.get(index);
	    GNode v= new GNode();
	    for(int i= 0; i< n.ligacoes.size(); i++){
		//Busca do no filho no grafo
		Jaula jaulatemp= n.ligacoes.get(i);
		for(int j= 0; j< G.size(); j++){
		    if(G.get(j).nome== jaulatemp.ligacao){
			v= G.get(j);
		    }
		}
		/*Verificacao do tamanho da jaula e, no caso do no pai ser a raiz,
		 *o programa, aqui, iguala o comprimento desse no ao comprimento da ligacao
		 *que se esta a tratar. Se nao fizer isto aqui, no relax tenho que fazer um caso
		 *especifico para a raiz, coisa que nao cai bem comigo. :(*/
		boolean boolflag= false;
		for(int j= 0; j< Fila.size(); j++){
		    if(Fila.get(j).nome== v.nome){
			boolflag= true;
		    }
		}
		if(boolflag== true){
		    if(jaulatemp.largura <= medis.largmax && jaulatemp.largura >= medis.largmin){
			if(jaulatemp.comprimento <= medis.compmax && jaulatemp.comprimento >= medis.compmin){
			    if(jaulatemp.altura >= medis.altmin){
				if(n.nome== o.nome){
				    n.comprimentoNo= jaulatemp.comprimento;
				}
				Relax(n, v, jaulatemp);
			    }
			}
		    }
		}
	    }
	    if(n.comprimentoNo!= Integer.MIN_VALUE){
		Resultado.add(n);
	    }
	    Fila.remove(index);
	}

	/*Print do resultado:
	 *Primeiro, verifica se o no de chegada esta dentro do espaco de resultados ou nao:
	 *Se o no de chegada esta dentro do espaco de resultados, imprime o comprimento desse no;
	 *Se nao esta dentro do espaco de resultados, imprime zero.*/
	boolean inResultado= false;
	GNode noResultado= new GNode();
	for(int i= 0; i< Resultado.size(); i++){
	    if(Resultado.get(i).nome== f.nome){
		inResultado= true;
		noResultado= Resultado.get(i);
	    }
	}
	if(inResultado== true){
	    System.out.println(noResultado.comprimentoNo);
	}
	else if(inResultado== false){
	    System.out.println("0");
	}
    }

    void Relax(GNode NoPai, GNode NoFilho, Jaula PesoDaLigacaoPaiFilho){
	/*Este Relax faz duas verificacoes:
	 *Se o comprimento do no pai e menor ou igual ao comprimento da ligacao ^ se e menor que o comprimento do no filho.*/
	if(NoPai.comprimentoNo <= PesoDaLigacaoPaiFilho.comprimento && NoPai.comprimentoNo >= NoFilho.comprimentoNo){
	    NoFilho.comprimentoNo= NoPai.comprimentoNo;
	}
    }
}

class encomenda{
    public static void main(String args[]){	
	//Passagem de todo o input para uma string
	Scanner input= new Scanner(System.in);
	StringBuilder strinput= new StringBuilder();
	ArrayList<Integer> listadenomes= new ArrayList<Integer>();
	String entrada= "";
	while(entrada.equals("-1")== false){
	    entrada= input.nextLine();
	    strinput.append(entrada + "\n");
	}
	input.close();
	entrada= strinput.deleteCharAt((strinput.length())-1).toString();

	//Utilizando a string com todo o input, descubro o numero de nos do grafo
	Scanner scannomes= new Scanner(entrada);
	scannomes.nextLine();
	scannomes.nextLine();
	boolean ciclo= false;
	while(ciclo== false){
	    int nometemp1= scannomes.nextInt();
	    if(nometemp1== -1){
		ciclo= true;
	    }
	    else{
		int nometemp2= scannomes.nextInt();
		if(listadenomes.contains(nometemp1)== false){
		    listadenomes.add(nometemp1);
		}
		if(listadenomes.contains(nometemp2)== false){
		    listadenomes.add(nometemp2);
		}
		scannomes.nextLine();
	    }
	}
	scannomes.close();

	//Passo agora a inicializar os nos e a construir o grafo 
	int numerodenos= listadenomes.size();
	Scanner stdin= new Scanner(entrada);
	int larguramin= stdin.nextInt();
	int larguramax= stdin.nextInt();
	int comprimentomin= stdin.nextInt();
	int comprimentomax= stdin.nextInt();
	int alturamin= stdin.nextInt();
	Medicoes medidas= new Medicoes(larguramin, larguramax, comprimentomin, comprimentomax, alturamin);
	int partida= stdin.nextInt();
	int chegada= stdin.nextInt();
	ArrayList<GNode> Grafo= new ArrayList<GNode>();
	for(int i= 0; i< numerodenos; i++){
	    GNode novono= new GNode();
	    novono.nome= listadenomes.get(i);
	    Grafo.add(novono);
	}
	ciclo= false;
	while(ciclo== false){
	    int temppartida= stdin.nextInt();
	    if(temppartida== -1){
		ciclo= true;
	    }
	    else{
		int tempchegada= stdin.nextInt();
		int templargura= stdin.nextInt();
		int tempcomprimento= stdin.nextInt();
		int tempaltura= stdin.nextInt();
		Jaula jaula= new Jaula(templargura, tempcomprimento, tempaltura, tempchegada);
		Jaula jaula2= new Jaula(templargura, tempcomprimento, tempaltura, temppartida);
		for(int i= 0; i< numerodenos; i++){
		    if(Grafo.get(i).nome== temppartida){
			Grafo.get(i).ligacoes.add(jaula);
		    }
		    if(Grafo.get(i).nome== tempchegada){
			Grafo.get(i).ligacoes.add(jaula2);
		    }
		}
	    }
	}
	stdin.close();

	//Retiro o no de partida e o no de chegada e envio-os para o Dijkstra. O print esta no proprio algoritmo.
	Builder algoritmo= new Builder();
	GNode nodepartida= new GNode();
	GNode nodechegada= new GNode();
	for(int i= 0; i< Grafo.size(); i++){
	    if(Grafo.get(i).nome== partida){
		nodepartida= Grafo.get(i);
	    }
	    if(Grafo.get(i).nome== chegada){
		nodechegada= Grafo.get(i);
	    }
	}
	algoritmo.Dijkstra(Grafo, nodepartida, nodechegada, medidas);
    }
}