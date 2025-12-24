import java.util.*;

class SopaDeLetras {
    public static void main(String args[]){
        Scanner stdin = new Scanner(System.in);
        String linha1 = stdin.next();
	String linha2 = stdin.next();
	analisa(linha1, linha2);
    }
    
    static void analisa(String linha1, String linha2){
	    int j = 0;
	    boolean founddif = false;
	    while(j < linha1.length() && j < linha2.length()){
		if(linha1.charAt(j) == linha2.charAt(j))
		    j++;
		else{
		    founddif = true;
		    break;
		}
	    }
	    if(founddif == false){
		System.out.println("Nenhum");
	    }
	    else{
		System.out.printf("%c%c\n", linha1.charAt(j), linha2.charAt(j));
	    }
    }
}
	
               