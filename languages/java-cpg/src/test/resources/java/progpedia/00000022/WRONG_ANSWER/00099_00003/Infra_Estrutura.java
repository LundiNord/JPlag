import java.util.*;

import java.util.LinkedList;

class Arco {
    int no_final;
    int valor;
    
    Arco(int fim, int v){
    	no_final = fim;
    	valor = v;
    }

    int extremo_final() {
    	return no_final;
    }

    int valor_arco() {
    	return valor;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
    	adjs = new LinkedList<Arco>();
    }
}


class Grafo {
    No verts[];
    int nvs, narcos;
			
    public Grafo(int n) {
    	nvs = n;
    	narcos = 0;
    	verts  = new No[n+1];
		for (int i = 0 ; i <= n ; i++)
	    verts[i] = new No();
        // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
    }
    
    public int num_vertices(){
    	return nvs;
    }

    public int num_arcos(){
    	return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
    	return verts[i].adjs;
    }
    
    public void insert_new_arc(int i, int j, int valor_ij){
    	verts[i].adjs.addFirst(new Arco(j,valor_ij));
        	narcos++;
    }

    public Arco find_arc(int i, int j){
    	for (Arco adj: adjs_no(i))
    		if (adj.extremo_final() == j) return adj;
    	return null;
    }
}

class Infra_Estrutura {
	public static void main (String args[]){
		Scanner in = new Scanner (System.in);
		
		int ntrocos=in.nextInt();
		Grafo g = new Grafo(ntrocos);
		
		int minl=in.nextInt();
		int maxl=in.nextInt();
		
		int minc=in.nextInt();
		int maxc=in.nextInt();
		
		int mina=in.nextInt();
		
		int origem=in.nextInt();
		int destino=in.nextInt();
		
		int help;
			
		int extremoa, extremob, testel, testec, testea;
		
		extremoa=in.nextInt();
		
		int soma=0;
		int casook=0;
		
		if(origem < destino){
			while(extremoa!=-1){
				
				extremob=in.nextInt();
				testel=in.nextInt();
				testec=in.nextInt();
				testea=in.nextInt();
				
				if(extremoa > extremob){
					help=extremoa;
					extremoa=extremob;
					extremob=help;
				}
				
				if((extremoa<= origem && extremob>=destino) || (extremoa<= origem && extremob>=origem) || (extremoa< destino && extremob>=destino)){
					soma++;
					//System.out.println("entrou aqui extremo");
				}

				if(testel <= maxl && testel >= minl){
					soma++;
					//System.out.println("entrou aqui l");
				}
				if(testec <= maxc && testec >= minc){
					soma++;
					//System.out.println("entrou aqui c");
				}
				if(testea >= mina){
					soma++;
					//System.out.println("entrou aqui a");
				}
				if(soma==4)
					casook++;
			
				soma=0;

				//System.out.println("numero de casos = " + casook);

				extremoa=in.nextInt();
			}
		}
		
		if(origem > destino){
			while(extremoa!=-1){
				
				extremob=in.nextInt();
				testel=in.nextInt();
				testec=in.nextInt();
				testea=in.nextInt();
				
				if(extremoa < extremob){
					help=extremoa;
					extremoa=extremob;
					extremob=help;
				}
				
				if(extremoa>= origem && extremob<=destino){
					soma++;
					//System.out.println("entrou aqui extremo 1");
				}
				
				if(extremoa>= origem && extremob<=origem) {
					soma++;
					//System.out.println("entrou aqui extremo 2");
				}
				
				if(extremoa>= destino && extremob<=destino){
					soma++;
					//System.out.println("entrou aqui extremo 3");
				}

				if(testel <= maxl && testel >= minl){
					soma++;
					//System.out.println("entrou aqui l");
				}
				if(testec <= maxc && testec >= minc){
					soma++;
					//System.out.println("entrou aqui c");
				}
				if(testea >= mina){
					soma++;
					//System.out.println("entrou aqui a");
				}
				if(soma==4)
					casook++;
			
				soma=0;

				//System.out.println("numero de casos = " + casook);

				extremoa=in.nextInt();
			}
		}
		
		System.out.println(casook*2);

	}
}
