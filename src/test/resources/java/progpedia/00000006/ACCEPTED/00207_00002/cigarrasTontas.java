import java.util.Scanner;

public class cigarrasTontas {
	static int[] locais = new int[30];
	static Scanner leitura = new Scanner(System.in);
	static int lugar;
	
	static void lerInput(){
		int inteiro = leitura.nextInt();
		lugar = 0;
		while(inteiro!=0){
			for(int i = 0; i<lugar; i++){
				if(locais[i]==inteiro)
					lugar = i;
			}
			locais[lugar]=inteiro;
			lugar++;
			inteiro=leitura.nextInt();
		}
	}
	
	static void imprimirResultado(){
		for(int i = 0; i<lugar; i++){
			System.out.println(locais[i]);
		}
	}
	
	public static void main(String[] args) {
		lerInput();
		imprimirResultado();
	}

}
