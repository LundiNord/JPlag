import java.util.Scanner;
public class Sopa_de_letras {
	
	public static int min (int a, int b){
		return a>b ? b : a;
	}
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner (System.in);
		
		String s1 = kb.next();
		String s2 = kb.next();
		
		char c1 = '0';
		char c2 = '0';
		boolean condition = true;
		
		for (int i=0; i<min(s1.length(),s2.length()); ++i){
			if (s1.charAt(i) == s2.charAt(i))
				continue;
			else {
				c1 = s1.charAt(i);
				c2 = s2.charAt(i);
				condition = false;
				break;
			}
		}
		
		if (condition)
		System.out.println("Nenhum");
		else
			System.out.println(c1 + "" + c2);
		
		kb.close();
		
		
	}

}
