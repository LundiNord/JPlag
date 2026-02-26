import java.util.*;



public class Sopa {

	


	static void compara (String pal1, String pal2){
				
		int j=0;
		while (j<pal1.length() && pal1.charAt(j)==pal2.charAt(j))
			j++;

		if (j==pal1.length())
			System.out.println("Nenhum");
		else {
			System.out.println(""+pal1.charAt(j)+pal2.charAt(j));
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		String lisA = in.nextLine();
		
		String lisB = in.nextLine();
		
		compara(lisA, lisB);
		

	}

}
