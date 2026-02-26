import java.util.Scanner;


public class cigarras {

	public static int analisa_caminho(int key, int valor, int[] locais){
		int i = 0;
		boolean isset = false;
		while ( i<=key) {
			if (locais[i]==valor) { //se exite
				key = i; //guarda a key
				isset=true;
			}
		i++;		
		}
		if (!isset) {
			locais[key+1] = valor;
			key++;
		}
	return key;
	}
	
	
	public static void escreve_caminho(int key, int[] locais){
		int c = 0;
		while (locais[c]!=0 && c<=key){
			System.out.println(locais[c]);
			c++;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final int[] locais = new int[30];
		
		Scanner n = new Scanner(System.in);
		int valor = n.nextInt();
		int key = 0;
		if (valor!=0){
		locais[0] = valor;
		key = 1;
		}
		
		while (valor != 0) {
			key = analisa_caminho(key, valor, locais);	
			valor=n.nextInt();
		}
		escreve_caminho(key, locais);

	}

}
