import java.util.Scanner;


public class Main {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String word1= in.nextLine();
		String word2=in.nextLine();
		
		System.out.println(cenas(word1,word2));
		
	}

	private static String cenas(String word1, String word2) {
		int min=minimo(word1,word2);
		for(int i=0;i<min;i++){
			if(word1.charAt(i)!=word2.charAt(i)){
				return(word1.charAt(i)+""+word2.charAt(i));
			}
		}
		return "Nenhum";
	}

	private static int minimo(String word1, String word2) {
		if(word1.length()>word2.length()){
			return word2.length();
		}
		return word1.length();
	}
}
