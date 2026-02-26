import java.util.*;
import java.io.*;
class SopaLetras {
	public static int minLength(String a, String b) {
		if(a.length() < b.length()) return a.length();
		return b.length();
	}
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String linha1 = in.nextLine();
		String linha2 = in.nextLine();
		boolean none=true;
		char a='a', b='a';
		int size = minLength(linha1, linha2);
		for(int i=0;i<size; i++) {
			a = linha1.charAt(i);
			b = linha2.charAt(i);
			if(a != b) {
				none = false;
				break;
			}
		}
		if(none == true) System.out.println("Nenhum");
		else System.out.println(a + "" +b);
	}
}
