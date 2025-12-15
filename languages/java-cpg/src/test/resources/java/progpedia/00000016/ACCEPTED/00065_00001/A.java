import java.util.Scanner;


public class A {

	public static String diferenca (String a, String b) {
		
		for (int i = 0 ; a.charAt(i)!='\n' && b.charAt(i)!='\n' ; i++) 
			if (a.charAt(i)!=b.charAt(i))
				return ""+a.charAt(i) + b.charAt(i);
		return "Nenhum";
	}
	public static void main(String[] args) {
		
		String p1, p2;
		
		Scanner stdin = new Scanner(System.in);
		p1 = stdin.nextLine()+'\n';
		p2 = stdin.nextLine()+'\n';
		
		System.out.println(diferenca(p1,p2));
		
	}

}
