import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String palavra1 = scan.next();
		String palavra2 = scan.next();
		
		int i = 0;
		int test = 0;
		while(i < palavra1.length() && i < palavra2.length()){
			if(palavra1.charAt(i) != palavra2.charAt(i)){
				System.out.println((new StringBuilder().append(palavra1.charAt(i)).append(palavra2.charAt(i))).toString());
				test = 1;
				break;
			}
			i++;
		}
		if(test == 0)System.out.println("Nenhum");
	}

}
