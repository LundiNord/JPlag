import java.util.*;

class Arcos{
	int no_final;
	int valor;

	Arcos(int fim, int v){
		no_final = fim;
		valor=v;
	}

	int extremo_final(){
		return no_final;
	}
	 int valor_arco(){
		 return valor;
	 }
}

class No{
	LinkedList<Arcos> adjs;
	No(){
		adjs = new LinkedList<Arcos>();
	}

}

class Grafo{
	No verts[];
	int nvs, narcos;

	public Grafo(int n){
		nvs=n;
		narcos=0;
		verts = new No[n+1]; // para vertices numerados de 1 a n , posicao 0 nao vai ser utilizada
		for(int i=0; i<=n;i++){
			verts[i] = new No();
		}
	}
	 public int num_vertices(){
		 return nvs;
	 }

	 public int num_arcos(){
		 return narcos;
	 }

	 public LinkedList<Arcos> adjs_no(int i){
		 return verts[i].adjs;
	 }

	 public void insert_new_arc(int i, int j, int valor_ij){
		 verts[i].adjs.addFirst(new Arcos(j, valor_ij));
	 }

	 public Arcos find_arc(int i, int j){
		 for (Arcos adj : adjs_no(i))
			 if(adj.extremo_final()==j) return adj;
		 return null;
	 }
}


public class Redes {

	public static void main(String Args[]){
		@SuppressWarnings("resource")
		Scanner in= new Scanner(System.in);
        int n= in.nextInt();
        int l = in.nextInt();
        Grafo g = new Grafo(n+1);
        int[] visitados= new int[n+1];
        int[] visitados_Copy = new int[n+1];
        int count=0;
        for(int i=0;i<l;i++){
                int t1=in.nextInt();
                int t2=in.nextInt();
                g.insert_new_arc(t1,t2, 1);
                g.insert_new_arc(t2,t1, 1);
        }
        for(int i=1;i<=n;i++){
                if(visitados[i]==0){

                        visitados=dfs(i,g,visitados);
                        count++;
                }

                if(visitados_Copy[i]==0){
                	int[] tamanho=dfs(i,g,visitados);
                }

        }
        System.out.println(count);

}

private static int[] dfs(int i, Grafo g,int[]visitados) {
        for(Arcos a : g.adjs_no(i)){
                if(visitados[a.no_final]==0){
                        visitados[a.no_final]=1;
                        dfs(a.no_final,g,visitados);
                }
        }
        return visitados;

}
}
