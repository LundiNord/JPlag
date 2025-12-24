import java.util.*;


public class sopas {

	
	public static int testa(String a , String b){
			int i=0;
			
			while(i<a.length() && a.charAt(i)==b.charAt(i))
				i++;
			
			if(i==a.length())
				return -1;
			
			return i ;
		
		
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		
		String s1,s2;
	
		s1=in.nextLine();
		s2=in.nextLine();
		
		//System.out.println(testa(s1,s2));
		int aux= testa(s1,s2);
		if(aux==-1)
			System.out.println("Nenhum");
		else{
			System.out.println(s1.charAt(aux)+""+s2.charAt(aux));
		}
			
		
	}

}
