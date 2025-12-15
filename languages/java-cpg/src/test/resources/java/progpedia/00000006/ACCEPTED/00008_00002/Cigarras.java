
import java.util.ArrayList;
import java.util.Scanner;


class Cigarras {
	static ArrayList<Integer> caminho = new ArrayList<Integer>();
	static ArrayList<Integer> caminhofinal = new ArrayList<Integer>();
	
	
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		int sitio = stdin.nextInt();
		
		while (sitio!=0){
			caminho.add(sitio);
			sitio = stdin.nextInt();
		}
		melhorCaminho(caminho);
		imprime();
	}	
	
	public static ArrayList<Integer> melhorCaminho(ArrayList<Integer> caminho){
		
		for(Integer sitio : caminho){
			
			if(caminhofinal.contains(sitio)){
				
				
				
				while ((caminhofinal.get(caminhofinal.indexOf(sitio))) != (caminhofinal.get(caminhofinal.size()-1))){
					
					caminhofinal.remove(caminhofinal.size()-1);
					
				}
				
			}
			else{
				caminhofinal.add(sitio);
			}
		}
		return caminhofinal;
		
	}
	public static void imprime(){
		for(Integer sitio:caminhofinal){
			System.out.println(sitio);
		}
	}
}
