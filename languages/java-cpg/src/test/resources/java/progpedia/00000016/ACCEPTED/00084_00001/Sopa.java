import java. util.*;
public class Sopa {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String palavra1 = teclado.next();
		String palavra2 = teclado.next();
		int i=0;
		while(i<palavra1.length() && (palavra1.charAt(i) == palavra2.charAt(i)))
			i++;
		if(i==palavra1.length())
			System.out.println("Nenhum");
		else
			System.out.println(palavra1.charAt(i)+""+palavra2.charAt(i));

	}

}
