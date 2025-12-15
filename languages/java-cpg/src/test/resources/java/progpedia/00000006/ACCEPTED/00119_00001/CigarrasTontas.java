import java.util.Scanner;


public class CigarrasTontas {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		int caminho [] = new int [10001];  //locias possiveis
		int inicio = kb.nextInt();         //guarda o primeiro local
		int actual = inicio;               //guarda o lugar actual
		int proximo;                       //guarda o proximo local
		
		do{
			proximo=kb.nextInt();
			caminho[actual]=proximo;
			actual=proximo;           
		}while(proximo!=0);
		
		caminho[proximo]=0;
		int p=inicio;           //variavel auxiliar para escrever caminho 
		
		while(p!=0){
			System.out.println(p);
			p=caminho[p];
		}
	}

}
