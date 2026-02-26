import java.util.*;


public class Infra_Estrutura {
	
	static int comprimentoMin;
	static int comprimentoMax ;
	static int larguraMin ;
	static int  larguraMax ; 
	static int alturaMin;
	static int origem;
	static int destino;
	
	
	static int lerContarTroco(Scanner in){
		
		int nNo = in.nextInt();
		 comprimentoMin = in.nextInt();
		 comprimentoMax = in.nextInt();
		 larguraMin = in.nextInt();
		 larguraMax = in.nextInt(); 
		 alturaMin = in.nextInt(); 
		 origem = in.nextInt();
		 destino = in.nextInt();
	
		 int cont=0;
		 int extremo1 = in.nextInt();
			while(extremo1!=-1){
				int extremo2 = in.nextInt();
				int comp = in.nextInt();
				int larg = in.nextInt();
				int alt = in.nextInt();
			
				
				if(comp >=  comprimentoMin && larg >= larguraMin  && alt >= alturaMin ){
					cont++;

				}
				
				extremo1 = in.nextInt();	
				
			}
		 return cont;
		 
		
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(lerContarTroco(in));

	}

}
