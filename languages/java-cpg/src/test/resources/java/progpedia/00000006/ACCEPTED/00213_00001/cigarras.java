import java.util.Scanner;


public class cigarras {
	
	public static void main(String[] args) {
		
		int[] caminho;
		caminho = new int[10000];
		
		Scanner in = new Scanner(System.in);
		
		int inicio = in.nextInt();
		int corr = inicio; 
		int novo_valor;
		
		do{
			novo_valor = in.nextInt();
			caminho[corr] = novo_valor;
			corr = novo_valor;
		}while( corr != 0);
		
		while(inicio != 0){
			System.out.println(inicio);
			inicio = caminho[inicio];
		}
	
		/*
		System.out.println(size);
		for(i = 0; i< size -1; i++){
			System.out.printf("%d ", v[i]);
		}*/
		
		
		
	}
}
