import java.util.*;

public class sopa_de_letras {
	public static void main(String []Args){
		Scanner input = new Scanner(System.in);

		String str1 = input.next();
		String str2 = input.next();
		
		int j = 0;
		while(j !=  str1.length() && str1.charAt(j) == str2.charAt(j)){
			j++;
		}
		if(j == str1.length())
			System.out.println("Nenhum");
		else
			System.out.println(str1.charAt(j) + "" + "" + str2.charAt(j));
	}
}
