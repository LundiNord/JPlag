import java.util.Scanner;

public class SopadeLetras {

	public static void compararPalavras(String p1, String p2){
		char a1[] = p1.toCharArray();
		char a2[] = p2.toCharArray();
		int stop=0;
		
		for(int i=0; i<a1.length && stop==0; i++){
			if(a1[i]!=a2[i]){
				System.out.println(a1[i]+""+a2[i]);
				stop=1;
			}
		}
		
		if (stop==0) System.out.println("Nenhum");
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String palavra1 = kb.nextLine();
		String palavra2 = kb.nextLine();
		compararPalavras(palavra1, palavra2);
	}
}
