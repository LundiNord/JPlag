import java.util.Scanner;

public class InfraEstrutura {
	static Scanner ler=new Scanner(System.in);

	
	public static void main(String[] args) {
		int nnos=ler.nextInt();
		
		int caminhos=0;

		int lmin=ler.nextInt();
		int lmax=ler.nextInt();
		int cmin=ler.nextInt();
		int cmax=ler.nextInt();
		int hmin=ler.nextInt();
		//dados da viagem
		int origem=ler.nextInt();;
		int destino=ler.nextInt();
		
		int comeca=ler.nextInt();
		while(comeca!=-1){
			int acaba=ler.nextInt();
			//dados para cada caminho
			int maxl=ler.nextInt();
			int maxc=ler.nextInt();
			int maxh=ler.nextInt();
			if(maxl>=lmin && maxc>=cmin && maxh>=hmin)
				caminhos++;
			comeca=ler.nextInt();
			
		}
		ler.close();
		System.out.println(caminhos);
	}
}
