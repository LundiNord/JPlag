import java.util.Scanner;


class Prob005{
	static int Nnum; //Quantidade de Numeros
	static int numeros[]; //Array de Numeros
	
	
	public static void main(String args[]){
		
		Scanner stdin = new Scanner(System.in);
		
		//Le quantidade de numeros
		Nnum = stdin.nextInt();
		//Da tamanhos
		numeros = new int[Nnum];
		
		//Ciclo para ler sequencia de numeros
		for(int i=0; i< Nnum; i++){
			numeros[i] = stdin.nextInt(); 
		}
		
		int contador=0;
		for(int i =0; i<Nnum; i++){
			for(int j =i+1; j<Nnum; j++){
				if(numeros[i] > numeros[j])
					contador++;
			}
		}
		System.out.println(contador);
		
	}
	
	
	
	
	
	
	
	
	
	
}