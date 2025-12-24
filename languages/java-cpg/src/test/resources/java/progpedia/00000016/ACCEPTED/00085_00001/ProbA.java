import java.util.*;

public class ProbA {
	
	public static int ComparaPalavra(String p1, String p2){
		for(int i=0; i<p1.length();i++){
			if(p1.charAt(i)!=p2.charAt(i)){
				System.out.print(p1.charAt(i));
				System.out.println(p2.charAt(i));
				return 1;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
			Scanner kb = new Scanner(System.in);
			
			String palavra1 = kb.next();
			//kb.next(); //paragrafo do input
			String palavra2 = kb.next();
			
			int res= ComparaPalavra(palavra1,palavra2);
			if(res==0) System.out.println("Nenhum");

	}

}
