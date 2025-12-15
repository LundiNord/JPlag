import java.util.*;

class sopaletras{
	
	private static Scanner in;

	public static void main (String[] args){
		
		in = new Scanner (System.in);
		
		String palavra1 = new String();
		String palavra2 = new String();
		
		palavra1 = in.nextLine();
		palavra2 = in.nextLine();
		
		int j=0;
		int comp;
		
		if (palavra1.length() > palavra2.length()){
			comp = palavra2.length();
		}
		
		else{
			comp = palavra1.length();
		}
		
		
		while(j< comp){
			
			if (palavra1.charAt(j) != palavra2.charAt(j)){
				end(palavra1, palavra2, j);
				break;
			}
		j++;
		}
		
		System.out.println("Nenhum.\n");
		
		
	}

	private static void end(String palavra1, String palavra2, int j) {
		System.out.printf("%c%c\n", palavra1.charAt(j), palavra2.charAt(j));
	}

}