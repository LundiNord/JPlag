import java.util.Scanner;

class sopaletras {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		lexOrder(in);
	}
	
	static void lexOrder(Scanner in) {
		String s1 = in.next();
		String s2 = in.next();
		
		for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
			if(s1.charAt(i)!=s2.charAt(i)) {
				System.out.println(s1.charAt(i)+""+s2.charAt(i));
				return;
			}
		}
		
		System.out.println("Nenhum");
	}
}