import java.util.*;

public class Sociologia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n_cenarios, n_alunos, control;
		
		
		Scanner stdin = new Scanner(System.in);
		
		n_cenarios = stdin.nextInt();
		stdin.nextLine();
		control=1;
		while(n_cenarios >= control){
			n_alunos = stdin.nextInt();
			stdin.nextLine();
			Grafo g = new Grafo(n_alunos);
			
			
			while(n_alunos>0){
				ArrayList<Integer> temp_Adj = new ArrayList<Integer>();
				ArrayList<Integer> temp_Pre = new ArrayList<Integer>();
				int aluno = stdin.nextInt();
				int amigos = stdin.nextInt();
				while(amigos>0){
					int amigo = stdin.nextInt();
					temp_Adj.add(amigo);
					
					if(!g.listaPre.containsKey(amigo)){
						temp_Pre.add(aluno);
						g.listaPre.put(amigo, temp_Pre);
					}
					else
						g.listaPre.get(amigo).add(aluno);
						
					amigos--;
				}
				
				g.listaAdj.put(aluno, temp_Adj);
				System.out.println("Aluno: " + aluno + " tem " + temp_Adj.size() + " amigos");
				n_alunos--;
			}
			
			System.out.println("Caso: " + control);
			control++;
		}
		
	}

}

class Grafo{
	
	int vertices;
	HashMap<Integer, ArrayList<Integer>> listaAdj;
	HashMap<Integer, ArrayList<Integer>> listaPre;
	int[] color;
	
	Grafo(int v){
		vertices = v;
		listaAdj = new HashMap<Integer, ArrayList<Integer>>();
		listaPre = new HashMap<Integer, ArrayList<Integer>>();
		color = new int[v];
	}
	
	void DFS(){
		for(int u : listaAdj.keySet()){
			if(color[u-1]==0)
				DFS(u);
		}
	}
	
	void DFS(int u){
		color[u-1]=1;
		
		for(int v : listaAdj.get(u)){
			
		}
	}
	
}

