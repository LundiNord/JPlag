
import java.util.Scanner;

public class Sopa {
	public static void main(String[] args) {
		String str1,str2;

		Scanner in = new Scanner(System.in);
		str1 = in.nextLine();
		str2 = in.nextLine();
		if(str1.length()>str2.length()){
			str1.substring(0, str2.length());
		}
		else str2=str2.substring(0,str1.length());
		char[] char1 = str1.toCharArray();
		char[] char2 = str2.toCharArray();
		if(str1.equals(str2)) System.out.println("Nenhum");
		else{
			for (int i = 0; i < char1.length; i++) {
				if(char1[i]!=char2[i]){
					System.out.printf("%c%c\n",char1[i],char2[i]);
					i=char1.length;
				}
			}
		}
	}
}

