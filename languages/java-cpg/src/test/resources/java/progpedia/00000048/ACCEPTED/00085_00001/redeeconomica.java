import java.util.Scanner;
import java.util.LinkedList;

class Arco {
	int no_inicial;
    int no_final;
 
    Arco(int inicio,int fim){
    	no_inicial = inicio;
    	no_final = fim;
    }

    int extremo_final() {
    	return no_final;
    }
    
    int extremo_inicial(){
    	return no_inicial;
    }
}

class No {

    LinkedList<Integer> adjs;
    boolean visitado;

    No() {
    	adjs = new LinkedList<Integer>();
    }
}

class Grafo {
    No verts[];
    int nvs, narcos;    
			
    public Grafo(int n) {
    	nvs = n;
    	narcos = 0;
    	verts  = new No[n+1];
    	
    	for (int i = 0 ; i <= n ; i++){
    		verts[i] = new No();
    	}
        	
   }
    
    	public int num_vertices(){
    		return nvs;
    	}

    	public int num_arcos(){
    		return narcos;
    	}

    	public LinkedList<Integer> adjs_no(int i) {
    		return verts[i].adjs;
    	}
    
    	public void insert_new_arc(int i, int j){
    		verts[i].adjs.addFirst(j);
    		narcos++;
    	}

   
		public void dfs(int i) {
			verts[i].visitado=true;
			//System.out.println(i);
			for(int w : adjs_no(i)){
				 
				if(verts[w].visitado==false)
					dfs(w);
			}
			
		}
}

public class redeeconomica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		int l = scan.nextInt();
		
		Grafo g = new Grafo(n);

		for(int i=0;i<l;i++){
			int inicio=scan.nextInt();
			int fim = scan.nextInt();
			g.insert_new_arc(inicio, fim);
			g.insert_new_arc(fim, inicio);
			
		}
		
		componentes(g);
		
	}
	
	public static void componentes(Grafo g){
		int contador=0;
		//feito em cima
		for(int i=1;i<=g.num_vertices();i++){
			g.verts[i].visitado=false;
		}
		
		for(int i=1;i<=g.num_vertices();i++){
			if(!g.verts[i].visitado) {
				//System.out.println("first" + i);
				contador++;
				g.dfs(i);
			}
		}
		
		System.out.println(contador);
			
		
	}
}