import java.util.*;

public class SopaLetras {
	
	private static Scanner in;
	
	public static void main(String args[]){
		
		in = new Scanner(System.in);
		
		String p1 = in.nextLine();
		String p2 = in.nextLine();
		
		for(int i=0; i<p1.length(); i++){
			char aux1 = p1.charAt(i);
			char aux2 = p2.charAt(i);
			if(aux1!=aux2){
				System.out.print(aux1);
				System.out.println(aux2);
				break;
			}
			if(i==(p1.length()-1) && aux1==aux2){
				System.out.println("Nenhum");
			}
		}
	}
}
