import java.util.*;
public class Rareorder {
	
	static String compara(String l1, String l2){
		int j=0;
		while(j<l1.length() && l1.charAt(j)==l2.charAt(j)){
			j++;
		}
		
		if(j==l1.length())
			return "Nenhum";
		
		else 
			return "" + l1.charAt(j)+l2.charAt(j);
	}
	
	
	public static void main(String[] args){
	Scanner moo = new Scanner(System.in);

	String palavra1=moo.nextLine();
	String palavra2=moo.nextLine();
	
	
	
	System.out.println(compara(palavra1, palavra2));
	
	
}
}
