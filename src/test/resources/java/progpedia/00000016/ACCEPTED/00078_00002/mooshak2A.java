import java.util.Scanner;

class mooshak2A {
	
	public static void main(String Args[]) {
		Scanner in = new Scanner(System.in);
		
		String s = in.nextLine();
		String s2 = in.nextLine();
		int i=0;
		while ((i<s.length())&&(s.charAt(i)==s2.charAt(i))) {
			i++;
		}
		if(s.length()==i) System.out.println("Nenhum");
		else System.out.println(s.charAt(i)+""+s2.charAt(i));
	}
}