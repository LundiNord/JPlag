import java.util.*;
import java.io.*;
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
	// int label;
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
class NegocioElectronico {
	// retirarMelhor() - metodo que ira devolver o no cujo distancia e a menor
	public static int retirarMelhor() {
		// menor - contem posicao do melhor, neste caso inicia como 0
		int menor = 0;
		int n = estimativa.length;
		for(int i=1;i<n;i++) {
			if(!retirado[i] && estimativa[menor] > estimativa[i]) {
				menor = i;
			}
		}
		return menor;
	}
	// retirado - vector que para casa no indica se ja foi ou nao usado
	public static boolean[] retirado;
	// estimativa - contem para cada o custo desde o no inicial ate certo no
	public static int[] estimativa;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// nRegioes - numero de nos
		int nRegioes = in.nextInt();
		Grafo g = new Grafo(nRegioes);
		// iniciar retirado com (nRegioes + 1) posicoes todas a false
		retirado = new boolean[nRegioes+1];
		Arrays.fill(retirado, false);
		// iniciar estimativa com (nRegioes + 1) posicoes todas a MAX_VALUE
		estimativa = new int[nRegioes+1];
		Arrays.fill(estimativa, Integer.MAX_VALUE);
		// regiaoInicial - no inicial
		int regiaoInicial = in.nextInt();
		while(true) {
			int regiao1 = in.nextInt();
			if(regiao1 == -1) break;
			int regiao2 = in.nextInt();
			int distancia = in.nextInt();
			g.insert_new_arc(regiao1, regiao2, distancia);
			g.insert_new_arc(regiao2, regiao1, distancia);
		}
		// retirados - numero que no que ja foram retidados
		int retirados = nRegioes;
		
		// Algoritmo de Dijkstra
		// distancia a regiaoInicial é zero, visto ser onde começamos 
		estimativa[regiaoInicial] = 0;
		while(retirados != 0) {
			// obter no cuja distancia até o mesmo e a menor, no caso
			// da primeira iteracao, esse no ira ser o regiaoInicial
			int n = retirarMelhor();
			retirado[n] = true;
			// decrementar o numero de nos ainda nao retirados
			retirados--;
			if(retirados==0) System.out.print(n);
			else System.out.print(n + " ");
			for(Arco arco : g.verts[n].adjs) {
				int novo = estimativa[n] + arco.valor;
				if(estimativa[arco.no_final] > novo) estimativa[arco.no_final] = novo;
			}
		}
		System.out.println();
	}
}
