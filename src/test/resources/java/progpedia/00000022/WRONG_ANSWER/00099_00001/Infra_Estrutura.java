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
		
		int soma=0;
		int casook=0;
		
		while(extremoa!=-1){
			
			extremob=in.nextInt();
			testel=in.nextInt();
			testec=in.nextInt();
			testea=in.nextInt();
			
			if((extremoa<= origem && extremob>=destino) || (extremoa<= origem && extremob>origem) || (extremoa> destino && extremob>=destino)){
				soma++;
				//System.out.println("entrou aqui extremo");
			}
			if(testel <= maxl && testel >= minl){
				soma++;
				//System.out.println("entrou aqui l");
			}
			if(testec <= maxc && testec >= minc){
				soma++;
				//System.out.println("entrou aqui c");
			}
			if(testea >= mina){
				soma++;
				//System.out.println("entrou aqui a");
			}
			if(soma==4)
				casook++;
			
			soma=0;
			
			//System.out.println("numero de casos = " + casook);
			
			extremoa=in.nextInt();
		}
		
		System.out.println(casook*2);
	}

}
