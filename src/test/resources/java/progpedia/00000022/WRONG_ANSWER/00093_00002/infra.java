import java.util.*;

public class infra {
	
	public static class Globals{
		 static int largMin, largMax, compMin, compMax, altMin, origem, destino;
	}
	
	public static class Grafo{
		No[] nos;
		
		Grafo(int n){
			this.nos = new No[n];
			for(int i=0; i<n; i++)
				this.nos[i] = new No();
		}
		
		No getNo(int n){
			return this.nos[n-1];
		}
	}
	
	public static class No{
			LinkedList <Arco> adjs;

			
			No(){
				this.adjs = new LinkedList <Arco>();

			}
			
			void addAdj(Arco adj){
				this.adjs.add(adj);
			}
			
	}
	
	public static class Arco{
		int larg, comp, alt, fim;
		
		Arco(int larg, int comp, int alt, int fim){
			this.larg = larg;
			this.comp = comp;
			this.alt = alt;
			this.fim = fim;
		}
		
		int getLarg(){
			return this.larg;
		}
		
		int getComp(){
			return this.comp;
		}
		
		int getAlt(){
			return this.alt;
		}
		
		int getFim(){
			return this.fim;
		}
	}
	
	public static int verificar(int larg, int comp, int alt){
		int cont = 0;
		if (larg<=Globals.largMax && larg>=Globals.largMin)
			if (comp<=Globals.compMax && comp>=Globals.compMin)
				if (alt >= Globals.altMin)
					cont++;
		return cont*2;
	}
	
	public static void main (String args[]) {
		Scanner ler = new Scanner(System.in);
		
		int N = ler.nextInt();
		int contador = 0;
		
		Grafo grafo = new Grafo(N);
		
		Globals.largMin = ler.nextInt();
		Globals.largMax = ler.nextInt();
		Globals.compMin = ler.nextInt();
		Globals.compMax = ler.nextInt();
		Globals.altMin = ler.nextInt();
		Globals.origem = ler.nextInt();
		Globals.destino = ler.nextInt();
		
		int ext1 = ler.nextInt();
		
		while(ext1 != -1){
			int ext2 = ler.nextInt();
			int larg = ler.nextInt();
			int comp = ler.nextInt();
			int alt = ler.nextInt();
			
			Arco arco = new Arco(larg, comp, alt, ext2);
			grafo.getNo(ext1).addAdj(arco);
			
			int ext = verificar(larg, comp, alt);
			contador += ext; 
			
			arco = new Arco(larg, comp, alt, ext1);
			grafo.getNo(ext2).addAdj(arco);
			
			ext1 = ler.nextInt();
		}
		
		System.out.println(""+contador);
	}
}

