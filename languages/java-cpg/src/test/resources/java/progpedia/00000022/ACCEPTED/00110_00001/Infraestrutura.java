//package treinoTestesPraticos.ano0809;

import java.util.Scanner;

public class Infraestrutura {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int nLocais = in.nextInt();
		int larguraMin = in.nextInt();
		int larguraMax = in.nextInt();
		int comprimentoMin = in.nextInt();
		int comprimentoMax = in.nextInt();
		int alturaMin = in.nextInt();
		
		int origem = in.nextInt();
		int destino = in.nextInt();
		
		int count = 0;
		
		while (in.nextInt() != -1){
			int no_f = in.nextInt();
			int lMax = in.nextInt();
			int cMax = in.nextInt();
			int aMax = in.nextInt();
			
			if (lMax >= larguraMin && cMax >= comprimentoMin && aMax >= alturaMin)
				count ++;
		}
		
		System.out.println(count);
	}

}
