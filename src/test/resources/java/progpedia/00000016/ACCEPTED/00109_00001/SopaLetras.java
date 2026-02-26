import java.util.Scanner;

public class SopaLetras {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String p1, p2;

		p1 = input.nextLine();
		p2 = input.nextLine();
		
		if(p1.equals(p2) || p1.startsWith(p2) || p2.startsWith(p1))
			System.out.println("Nenhum");
		
		int length = Math.min(p1.length(), p2.length());
		
		for (int i = 0; i < length; i++) {
				if(p1.charAt(i) != p2.charAt(i)){
					System.out.println(p1.charAt(i)+""+p2.charAt(i));
					break;
				}
		}
	}
}
