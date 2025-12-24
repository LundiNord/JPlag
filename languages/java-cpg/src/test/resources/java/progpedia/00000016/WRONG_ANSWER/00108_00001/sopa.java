import java.util.*;


public class sopa {
	public static void main(String args[]){
	
	
	Scanner in = new Scanner(System.in);
	int aux=0;
	String a = in.nextLine();
	String b = in.nextLine();
	
	if(a.length() > b.length()){
		aux=b.length();
	}
	
	for(int i=0;i<aux;i++){
		if(a.charAt(i) != b.charAt(i)){
			System.out.print(a.charAt(i));
			System.out.print(b.charAt(i));
			System.exit(0);
		}
		}
	System.out.println("Nenhum");
	

}

}

