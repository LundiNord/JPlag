
import java.util.Scanner;

public class Infraestrutura {


	public static void main(String[] args) {
		Scanner leitura = new Scanner(System.in);
		leitura.nextInt();
		int lminimo = leitura.nextInt();
		leitura.nextInt();
		int cminimo = leitura.nextInt();
		leitura.nextInt();
		int aminima = leitura.nextInt();
		int origem = leitura.nextInt();
		int destino = leitura.nextInt();
		int aux = leitura.nextInt();
		int count = 0;
		while(aux!=-1){
			leitura.nextInt();
			int a = leitura.nextInt();
			int b = leitura.nextInt();
			int c = leitura.nextInt();
			if(a >= lminimo && b >= cminimo && c >= aminima){
				count++;
			}
			aux = leitura.nextInt();
		}
		System.out.println(count);
	}
}
