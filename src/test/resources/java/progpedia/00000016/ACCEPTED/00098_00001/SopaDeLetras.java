import java.util.*;

public class SopaDeLetras {
	
	static int tamanhoString(String a) {
		return a.length();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b =in.next();
		
		
		char[] aa = a.toCharArray();
		char[] bb = b.toCharArray();
		
		
		if (b.length()>a.length()) {
			int cenas=0;
			for (int i=0;i<=a.length()-1;i++) {
				if (aa[i]!=bb[i]) {
					cenas=1;
					System.out.print(aa[i]);
					System.out.print(bb[i]);
					break;
				}
			}
			
			if (cenas==0) {
				System.out.println("Nenhum");
			}
		}
			
		
		if (a.length()>=b.length()) {
			int cenas2=0;
			for (int i=0;i<=b.length()-1;i++) {
				
				if (aa[i]!=bb[i]) {
					cenas2=1;
					System.out.print(aa[i]);
					System.out.print(bb[i]);
					break;
				}				
			}
		
				
			if (cenas2==0) {
				System.out.println("Nenhum");
			}
		}
			
			
	}
		
		
}
