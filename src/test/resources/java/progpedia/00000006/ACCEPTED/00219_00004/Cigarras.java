import java.util.Scanner;


public class Cigarras {

	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		int[] locais = new int [30];
		locais[0]=in.nextInt();
		int ultimo=0;
		int lido = in.nextInt();
		
		while(lido!=0){
			int i;
			for(i=0; i<ultimo && lido!=locais[i]; i++){}
			if(lido==locais[i]){
				ultimo=i;
			}
			else{
				ultimo++;
				locais[ultimo]=lido;
			}
			lido=in.nextInt();
		}
			
		for(int c=0; c<=ultimo; c++){
			System.out.println(locais[c]);
		}
	}
		
}

