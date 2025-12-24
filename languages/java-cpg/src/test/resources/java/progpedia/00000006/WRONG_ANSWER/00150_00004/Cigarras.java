import java.util.ArrayList;
import java.util.Scanner;


public class Cigarras {

	/**
	 * @param args
	 */
	static ArrayList<Integer> caminho = new ArrayList<Integer>();
	static ArrayList<Integer> caminhofinal = new ArrayList<Integer>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int nome = sc.nextInt();

		while (nome!=0){
			caminho.add(nome);
			nome = sc.nextInt();
		}

		verificacaminho(caminho);
		imprime();


	}
	public static  ArrayList<Integer> verificacaminho(ArrayList<Integer> caminho){

		for(Integer nome : caminho){

			if(caminhofinal.contains(nome)){

				int tamanho = caminhofinal.size()-1;
				int inicio = caminhofinal.indexOf(nome);

				while (inicio<tamanho){
					
					Integer remover = caminhofinal.get(inicio);
					tamanho--;
					caminhofinal.remove(remover);
				}
			}else{
				caminhofinal.add(nome);
			}

		}

		return caminhofinal;
	}

	public static void imprime(){

		for (Integer nome : caminhofinal){
			System.out.println(nome);
		}

	}

}
