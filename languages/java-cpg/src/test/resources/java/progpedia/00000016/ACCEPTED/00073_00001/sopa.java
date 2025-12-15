import java.util.Scanner;

public class sopa {
	
		public static Scanner in=new Scanner(System.in);
		
		public static String equals(String a, String b){
			
			int min=a.length();
			if(a.length()>b.length()) 
				min=b.length();
			for(int i=0;i<min;i++)
				if(a.charAt(i)!=b.charAt(i))
					return a.charAt(i)+""+b.charAt(i); 
			return "Nenhum";
		}

		public static void main(String[] args) {
		
			System.out.println(equals(in.nextLine(),in.nextLine()));

	}

}
