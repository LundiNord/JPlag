import java.util.*;

class Sopa_Letras {
	public static void main (String args[]){
		Scanner in = new Scanner (System.in);
		
	
		
		String pal = in.nextLine();
		
		//System.out.println(pal);
		
		char pal1[] = new char[pal.length()];
		pal1 = pal.toCharArray();
		
		pal = in.nextLine();
		char pal2[] = new char[pal.length()];
		pal2 = pal.toCharArray();
		boolean igual = true;
		
		for(int i=0; i<pal1.length; i++){
			if(pal1[i]!=pal2[i]){
				System.out.print(pal1[i] );
				System.out.println(pal2[i]);
				igual=false;
				break;
			}
		}
		
		if(igual==true)
			System.out.println("Nenhum");
		
		
		
	}

}
