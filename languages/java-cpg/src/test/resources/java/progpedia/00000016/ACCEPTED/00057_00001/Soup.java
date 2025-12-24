import java.util.*;

class Soup{
    
    public static void main(String [] args){
	Scanner sc = new Scanner(System.in);
	String um = sc.nextLine();
	String dois = sc.nextLine();
	int e = um.length();
	int i;
	for(i=0;i<e;i++){
	    if(um.charAt(i)!=dois.charAt(i)){
		System.out.print(um.charAt(i));
		System.out.println(dois.charAt(i));
		return;
	    }
	}
	System.out.println("Nenhum");
	return;
	

	
    }
}
