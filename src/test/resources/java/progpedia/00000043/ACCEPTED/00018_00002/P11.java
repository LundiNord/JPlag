import java.util.*;

public class P11{
	public static void main(String[] args){

		Scanner teclado = new Scanner(System.in);
		int nCasos = teclado.nextInt();



		encomendas livroEmcomendas[] = new encomendas[nCasos];
		
		for (int i=0; i<nCasos; i++) {
			int a = teclado.nextInt();
			int b = teclado.nextInt();
			livroEmcomendas[i] = new encomendas(a, b); 
		}

		
		encomendasOptimizadas livroOtmizada[] = new encomendasOptimizadas[nCasos];
		for (int i=0; i<nCasos ; i++) {
			int    a =  i+1;
			double b =  livroEmcomendas[i].multa / livroEmcomendas[i].duracao;
			livroOtmizada[i] = new encomendasOptimizadas(a, b);
		}
		//System.out.println("------"+ Arrays.toString(livroOtmizada) + "-------");
		Arrays.sort(livroOtmizada, new ComparadorRacio());
		//System.out.println("------" + Arrays.toString(livroOtmizada) + "-------");

		for (int i=0; i<nCasos-1 ; i++ ) {
			System.out.print(livroOtmizada[i].nEncomenda + " "); // menos o ultimo para poder tirar o espaco no final da imprecao do output
		}
		System.out.println(livroOtmizada[nCasos-1].nEncomenda);//ultimo posicao do vetor
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