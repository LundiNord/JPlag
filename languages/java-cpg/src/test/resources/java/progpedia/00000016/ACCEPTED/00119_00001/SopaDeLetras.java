//package submissao;

import java.util.Scanner;

public class SopaDeLetras {
	public static void main (String[] args){
		Scanner scan = new Scanner(System.in);
		
		String palavra1 = scan.nextLine();
		String palavra2 = scan.nextLine();
		
		for(int i = 0; i<palavra1.length();i++){
			if(palavra1.charAt(i) != palavra2.charAt(i)){
				System.out.println(palavra1.charAt(i)+""+palavra2.charAt(i));
				return;
			}
		}
		System.out.println("Nenhum");
	}

}
