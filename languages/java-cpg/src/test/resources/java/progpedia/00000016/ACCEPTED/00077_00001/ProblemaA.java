
import java.util.*;

public class ProblemaA {
	
	
	static String procuraLetrasDiferentes (String string1,String string2){
	int comp=string1.length();
	int comp2=string2.length();	
		if(comp > comp2)
			comp = comp2;
		for(int i=0;i<comp;i++){
			if(string1.charAt(i)!=string2.charAt(i))
				return ""+string1.charAt(i)+string2.charAt(i);
		}
	return "";
	}
	
	
	public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
		String a=in.next();
		in.nextLine();
		String b= in.next();
		String res= procuraLetrasDiferentes (a,b);
		if(res.equals(""))
			System.out.println("Nenhum");
		else
			System.out.println(res);
	}

}
