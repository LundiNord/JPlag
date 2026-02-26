import java.util.*;

class ProbA {
	static String comparaPalavras(String a, String b, int rev) {
		int l, i;

		l = a.length();
		for (i=0; i < l; i++) {
			if (a.charAt(i) != b.charAt(i) && rev == 0)
				return "" + a.charAt(i) + b.charAt(i);
			else if (a.charAt(i) != b.charAt(i) && rev == 1)
				return "" + b.charAt(i) + a.charAt(i);
			
		}
		
		return "Nenhum";
	}


	public static void main (String [] args) {
		String p1, p2, res;
		Scanner kb = new Scanner(System.in);

		p1 = kb.nextLine();
		p2 = kb.nextLine();

		if (p1.equals(p2) == true)
			System.out.println("Nenhum");
		else {
			if (p1.length() <= p2.length()) {
				res = comparaPalavras(p1, p2, 0);	
			} else {
				res = comparaPalavras(p2, p1, 1);
			}
			System.out.println(res);
		}
	}
}