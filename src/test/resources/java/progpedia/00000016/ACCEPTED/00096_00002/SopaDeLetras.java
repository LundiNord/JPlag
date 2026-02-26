import java.util.Scanner;

public class SopaDeLetras {

	public static void main(String[] args) {
		Scanner ler=new Scanner(System.in);
		
		String string1=ler.next();
		String string2=ler.next();
		System.out.println(avalia_resultado(string1,string2));
	
		ler.close();
	}
	
	public static String avalia_resultado(String s1,String s2){
		
		String maior=get_lower(s1,s2);
		for(int i=0;i<maior.length();i++)
			if(s1.charAt(i) !=(s2.charAt(i)))
				return(s1.charAt(i)+""+s2.charAt(i));
		
		return "Nenhum";
	}

	public static String get_lower(String s1,String s2){
		if(s1.length()<s2.length())
			return s1;
		return s2;
	}
}
