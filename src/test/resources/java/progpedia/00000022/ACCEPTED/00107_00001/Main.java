import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int nLocais = scan.nextInt();
		
		int lMin = scan.nextInt();
		int lMax = scan.nextInt();
		int cMin = scan.nextInt();
		int cMax = scan.nextInt();
		int aMin = scan.nextInt();
		
		int origem = scan.nextInt();
		int destino = scan.nextInt();
		
//		Grafo locaisGrafo = new Grafo(nLocais);
		int contagem = 0;
		
		int local1 = scan.nextInt();
		while(local1 != -1){
			int local2 = scan.nextInt();
			int lMaxArco = scan.nextInt();
			int cMaxArco = scan.nextInt();
			int alturaArco = scan.nextInt();
//			locaisGrafo.insert_new_arc(local1, local2, lMaxArco, cMaxArco, alturaArco);
			
			if(lMaxArco >= lMin && cMaxArco >= cMin && alturaArco >= aMin) contagem++;
			
			local1 = scan.nextInt();
		}
		
		System.out.println(contagem);
	}

}
