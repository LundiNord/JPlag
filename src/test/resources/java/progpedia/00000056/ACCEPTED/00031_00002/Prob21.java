import java.util.*;


class Node implements Comparable<Node>{
	double selecionado;
	int anterior, proximo; 

	Node(double selecionado, int anterior, int proximo){
		this.selecionado = selecionado;
		this.anterior = anterior;
		this.proximo = proximo;
	}

	public int compareTo(Node outra) {
		return Double.compare(this.selecionado, outra.selecionado);
	}
}

public class Prob21{
	static List<Node> lista = new ArrayList<Node>();
	static int posicao[], resultado[];

	static void distancia(int nSardas, double pX[], double pY[]){
		double dist;
		
		for (int i=0; i<=nSardas; i++){
			for(int j=i+1; j<nSardas; j++){
							
				dist = Math.sqrt((pX[i] - pX[j])*(pX[i] - pX[j]) + (pY[i] - pY[j])*(pY[i] - pY[j]));

				lista.add(new Node(dist, i, j));
				lista.add(new Node(dist, j, i));
			}
		}
	}

	static double resolve(){
		double total = 0;
		Collections.sort(lista);
		
		for(int i=0; i<lista.size(); i++){
			Node node = lista.get(i);
			
			if(!igual(node.anterior, node.proximo)){
				Junc(node.anterior, node.proximo);
				total += node.selecionado;
			}
		}

		return total;
	}

	static int proc(int i){
		if(i!=posicao[i]) {
			posicao[i] = proc(posicao[i]);
		}
		return posicao[i];
	}

	static boolean igual(int i, int j){
		return proc(i) == proc(j);

	}

	static void Junc(int i, int j){
		int x = proc(i);
		int y = proc(j);


		if(resultado[x] > resultado[y])
			posicao[y] = x;
	
		else{
			posicao[x] = y;
			if(resultado[x] == resultado[y])
				resultado[y]++;
		}
	}

	public static void main(String args[]) {
		Scanner inp = new Scanner(System.in);

		int nSardas = inp.nextInt();
		
		double pX[] = new double[nSardas]; 
		double pY[] = new double[nSardas]; 

		for(int i=0; i<nSardas; i++){
			pX[i] = Double.parseDouble(inp.next());
			pY[i] = Double.parseDouble(inp.next());
		}
		
		distancia(nSardas, pX, pY);

		posicao = new int[nSardas];
		resultado = new int[nSardas];
		
		for(int i=0; i<nSardas; i++){
			posicao[i] = i;
			resultado[i] = 0;
		}
		
		System.out.printf("%.2f\n", resolve());
	}
}
