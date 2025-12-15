import java.util.*;

class Infra_Estrutura {
	public static void main (String args[]){
		Scanner in = new Scanner (System.in);
		
		int ntrocos=in.nextInt();
		
		
		int minl=in.nextInt();
		int maxl=in.nextInt();
		
		int minc=in.nextInt();
		int maxc=in.nextInt();
		
		int mina=in.nextInt();
		
		int origem=in.nextInt();
		int destino=in.nextInt();
		
	
			
		int extremoa, extremob, testel, testec, testea;
		
		extremoa=in.nextInt();
		
		
		int casook=0;
		
		
			while(extremoa!=-1){
				
				extremob=in.nextInt();
				testel=in.nextInt();
				testec=in.nextInt();
				testea=in.nextInt();
				
				
				if( testel >= minl && testec >= minc && testea >= mina ){
					casook++;
					//System.out.println("entrou aqui l");
				}
		
				//System.out.println("numero de casos = " + casook);

				extremoa=in.nextInt();
			}
		

		System.out.println(casook);

	}
}
