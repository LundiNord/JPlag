//package cenas;

import java.util.ArrayList;
import java.util.Scanner;

public class Cigarra {
	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		int valor = in.nextInt();
		ArrayList<Integer> caminho = new ArrayList<Integer>();
		while(valor!=0){
			for(int i=0; i<caminho.size();i++){
				if (valor == caminho.get(i)){
					while(i<caminho.size()){
						caminho.remove(i);
					}
					break;
				}
			}
			caminho.add(valor);
			valor=in.nextInt();
		}
		for (int s : caminho){
			System.out.println(s);
		}
	}
	
}
