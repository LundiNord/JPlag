import java.util.Scanner;


public class SopaDeLetras {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String palavra_1 = "";
		String palavra_2 = "";
		//int i = 0;
		boolean terminado = false;
		
		palavra_1 = in.nextLine();
		palavra_2 = in.nextLine();
		
		for(int i=0; i<min(palavra_1.length(), palavra_2.length()); i++){
			if(palavra_1.charAt(i)!=palavra_2.charAt(i)){
				char l1 = palavra_1.charAt(i);
				char l2 = palavra_2.charAt(i);
				
				System.out.println(l1 +""+ l2);
				terminado = true;
				break;
			}
		}
		if (terminado != true) System.out.println("Nenhum");
	}

	private static int min(int length, int length2) {
		// TODO Auto-generated method stub
		if (length > length2) return length2;
		else return length;
		
		
	}

}
