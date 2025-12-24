import java.util.*;

public class sopa {

	public static int testa(String a, String b){
		int i=0;
		
	while(i<a.length() && a.charAt(i)==b.charAt(i))
		 i++;
		 
	if(i==a.length())
		return -1;
		
	return i;
		
		
	}
	
	
	
	
	public static void main(String args[]){
		
		Scanner in = new Scanner(System.in);
		
		String s1, s2;
		int result;
		s1=in.nextLine();
		s2=in.nextLine();
		
	result=testa(s1,s2);
		if(result==-1)
			System.out.println("Nenhum");
		else
			System.out.println(""+s1.charAt(result)+s2.charAt(result));
		
		
	}
	
}
