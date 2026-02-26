import java.util.*;

class prog{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		String linha1, linha2;
		linha1=input.next();
		linha2=input.next();
		int i;
		
		for(i=0; i<linha1.length(); i++)
			if(linha1.charAt(i)!=linha2.charAt(i)){
				System.out.println(linha1.charAt(i)+""+linha2.charAt(i));
				System.exit(0);
			}
		System.out.println("Nenhum");
	}
}

