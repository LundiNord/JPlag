import java.util.Scanner;

public class SopaLetras {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String nome =  new String();
		String nome2 = new String();
		
		nome=in.nextLine();
		nome2=in.nextLine();
		int index2=0;
		int comp=0,aux=0;
		
		if(nome.length() > nome2.length())
			comp=nome2.length();
		else
			comp=nome.length();
		
		for(int i=0;i<comp;i++){
			if(nome.charAt(i)!=nome2.charAt(i)){
				index2=i;
				aux=1;
				break;
			}
			
			aux=0;
			continue;
		}
		
		if(aux==1)
			System.out.printf("%c%c\n",nome.charAt(index2),nome2.charAt(index2));
		else
			System.out.println("Nenhum");
	}
}