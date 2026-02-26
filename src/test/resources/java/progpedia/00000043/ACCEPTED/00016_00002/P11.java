import java.util.*;

public class P11{
	public static void main(String[] args){

		Scanner teclado = new Scanner(System.in);
		int nCasos = teclado.nextInt();

		//Criar um livro de 

		encomendas LivroEncomendas[] = new encomendas[nCasos];
		
		for (int i=0; i<nCasos; i++) {
			int a = teclado.nextInt();
			int b = teclado.nextInt();
			LivroEncomendas[i] = new encomendas(a, b); 
		}

		
		encomendasOptimizadas LivroOptimizado[] = new encomendasOptimizadas[nCasos];

		for (int i=0; i<nCasos ; i++) {
			int a =  i+1;
			double b = LivroEncomendas[i].multa / LivroEncomendas[i].duracao;
			LivroOptimizado[i] = new encomendasOptimizadas(a, b);
		}

		//Debug
		// for (int i=0; i<nCasos ; i++) {
		// 	System.out.print("[" + LivroOptimizado[i].nEncomenda + "," + LivroOptimizado[i].racio + "]");
		// }
		// System.out.println();



		//Ordernar Livro de Encomendas
		Arrays.sort(LivroOptimizado, new ComparadorRacio());


		//Debug
		// for (int i=0; i<nCasos ; i++) {
		// 	System.out.print("[" + LivroOptimizado[i].nEncomenda + "," + LivroOptimizado[i].racio + "]");
		// }
		// System.out.println();


		//Resposta ao problema
		for (int i=0; i<nCasos-1 ; i++) {
			System.out.print(LivroOptimizado[i].nEncomenda + " ");
		}
		System.out.println(LivroOptimizado[nCasos-1].nEncomenda);


	}
}


class encomendasOptimizadas{

	int nEncomenda;
	double racio;

		encomendasOptimizadas(int x ,double y){
		    nEncomenda = x;
			racio	   = y;   // (multa / dias)
		}

		
}

class encomendas{
		int duracao;
		double multa;

		encomendas(int x ,double y){
			duracao = x;
			multa	= y;
		}
}

class ComparadorRacio implements Comparator<encomendasOptimizadas>{
	@Override
	public int compare(encomendasOptimizadas a, encomendasOptimizadas b){

		if (a.racio == b.racio)
			return a.nEncomenda < b.nEncomenda ? -1 : a.nEncomenda == b.nEncomenda ? 0: 1;			
		else
			return a.racio > b.racio ? -1 : a.racio == b.racio ? 0: 1;
	}
}