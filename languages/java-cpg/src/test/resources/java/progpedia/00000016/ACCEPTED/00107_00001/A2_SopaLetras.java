import java.util.*;

public class A2_SopaLetras {
	public static void main(String[] args) {
		leituraInput();
	}
	
	public static void leituraInput() {
		Scanner in = new Scanner(System.in);
		String word1 = in.nextLine();
		String word2 = in.nextLine();
		wordsCompare(word1 , word2);
	}
	
	public static void wordsCompare(String w1 , String w2) {
		int minLength;
		
		if (w1.length() < w2.length()) minLength = w1.length();
		else minLength = w2.length();
		
		for (int i=0 ; i<minLength ; i++) {
			char c1 = w1.charAt(i), c2 = w2.charAt(i);
			
			if (c1 != c2) {
				System.out.println(c1 + "" + c2);
				break;
			} else if (i == minLength-1) {
				System.out.println("Nenhum");
			}
		}
	}

}
