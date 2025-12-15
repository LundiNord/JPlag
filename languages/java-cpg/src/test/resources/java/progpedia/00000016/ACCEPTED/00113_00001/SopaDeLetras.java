//package treinoTestesPraticos.ano0910;

import java.util.Scanner;

public class SopaDeLetras {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String word1 = in.next();
		String word2 = in.next();
		boolean flag= false;
		
		int size = Math.min(word1.length(), word2.length());
		
		for(int i = 0; i<size; i++){
			if (word1.charAt(i) != word2.charAt(i)){
				System.out.print(word1.charAt(i));
				System.out.println(word2.charAt(i));
				flag = true;
				break;
			}
		}
		if (flag == false)
			System.out.println("Nenhum");
	}

}
