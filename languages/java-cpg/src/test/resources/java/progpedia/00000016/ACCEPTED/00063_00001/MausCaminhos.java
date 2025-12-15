import java.util.Scanner;


public class MausCaminhos {

	public static void main(String[] args) {
		Scanner leitura = new Scanner (System.in);
		String primeira = leitura.next();
		leitura.nextLine();
		String segunda = leitura.next();
		int tamanhoProcura = Math.min(primeira.length(), segunda.length());
		int flag = 0;
		for(int i = 0; i<tamanhoProcura;i++){
			if(primeira.charAt(i)!=segunda.charAt(i) && flag==0){
				System.out.printf("%c%c",primeira.charAt(i), segunda.charAt(i));
				flag = 1;
			}
		}
		if(flag==0)
			System.out.println("Nenhum");

	}

}
