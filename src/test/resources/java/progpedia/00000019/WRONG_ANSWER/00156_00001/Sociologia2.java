import java.util.*;

class Hnode {
	int id;
	int color;
	int visitado;
	int arvore;
	Hnode pai;
	
	Hnode(int i) {
		id = i;
		color = 0;
		visitado = 0;
		pai = null;
		arvore = 0;
	}
	
	int DFS(Hnode[] NodeList, int[][] listAdj, int time) {
		if( color == 0) {
			time = DFSvisit(NodeList, listAdj, time, id);
		}
		return time;
	}
	
	
	int DFSvisit(Hnode[] NodeList, int[][] listAdj, int time, int no) {
		NodeList[no].visitado = time;
		NodeList[no].color = 1;
		time++;
		for(int i = 0; i < NodeList.length; i++) {
			if(listAdj[no][i] == 1 && NodeList[i].color == 0)
				time = DFSvisit(NodeList, listAdj, time, i);
		}
		NodeList[no].color = 2;
		time++;
		
		return time;
	}
	
	int DFSinvertida(Hnode[] NodeList, int[][] listaAdjInvertida, int time) {
		if(color == 0) 
			time = DFSinvertidaVisit(NodeList, listaAdjInvertida, time, id); 
		time++;
		return time;
		
	}
	
	int DFSinvertidaVisit(Hnode[] NodeList, int[][] listaAdjInvertida, int time, int no) {
		NodeList[no].arvore = time;
		NodeList[no].color = 1;
		for(int i = 0; i < NodeList.length; i++) {
			if(listaAdjInvertida[no][i] == 1 && NodeList[i].color == 0)
				time = DFSvisit(NodeList, listaAdjInvertida, time, i);
		}
		NodeList[no].color = 2;
		return time;
	}

}

public class Sociologia2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		int ncenarios = in.nextInt();
		
		for(int l = 0; l < ncenarios; l++) {
			int npessoas = in.nextInt();
			Hnode[] NodeList = new Hnode[npessoas];
			int[][] listaAdj = new int[npessoas][npessoas];
			int[][] listaAdjInvertida = new int[npessoas][npessoas];
			for(int k = 0; k < npessoas; k++) {
				int proprio = in.nextInt();
				NodeList[k] = new Hnode(proprio-1);
				int namigos = in.nextInt();
				for(int s = 0; s < namigos; s++) {
					int amigo = in.nextInt();
					listaAdj[proprio-1][amigo-1] = 1;
					listaAdjInvertida[amigo-1][proprio-1] = 1;
					
				}
			}
			
			for(int u = 0; u<npessoas-1; u++) {
				for(int y = u+1; y < npessoas; y++) {
					if(NodeList[u].id > NodeList[y].id) {
						Hnode temp = NodeList[u];
						NodeList[u] = NodeList[y];
						NodeList[y] = temp;
					}
				}
			}
			int time = 1;
			for(int t = 0; t < npessoas; t++) {
				time = NodeList[t].DFS(NodeList, listaAdj, time);
			}
			for(int u = 0; u<npessoas-1; u++) {
				for(int y = u+1; y < npessoas; y++) {
					if(NodeList[u].visitado < NodeList[y].visitado) {
						Hnode temp = NodeList[u];
						NodeList[u] = NodeList[y];
						NodeList[y] = temp;
					if(NodeList[u].color > 0) {
						NodeList[u].color = 0;
					}
					}
				}
			}
			time = 1;
			for(int f = 0; f< npessoas; f++) {
				time = NodeList[f].DFSinvertida(NodeList, listaAdjInvertida, time); 
			}
			int count = 0;
			int nGrupos = 0;
			int nSozinhos = 0;
			for(int i = 0; i < npessoas; i++) {
				count = 0;
				for(int j = 0; j < npessoas; j++) {
					if(NodeList[j].arvore == i) {
						count++;
					}
				}
				if(count > 3) {
					nGrupos++;
				}
				else {
					nSozinhos = nSozinhos + count;
				}
			}
			System.out.println("Caso #" + (l+1));
			System.out.println(nGrupos + " " + nSozinhos);
			
			/*
			Imprimir as Matrizes das listas de Adjacencia
			
			System.out.println();
			for(int i = 0; i < npessoas; i++) {
			System.out.println();
				for(int j = 0; j < npessoas; j++) {	
					System.out.printf(listaAdj[i][j] + " ");
				}
			}

			System.out.println();
			System.out.println("Invertida");
			for(int i = 0; i < npessoas; i++) {
				System.out.println();
				for(int j = 0; j < npessoas; j++) {
					System.out.printf(listaAdjInvertida[i][j] + " ");
				}
			}
			*/
		}
	}
}
