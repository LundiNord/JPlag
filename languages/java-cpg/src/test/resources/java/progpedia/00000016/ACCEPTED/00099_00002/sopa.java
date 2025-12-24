import java.lang.*;
import java.util.*;
 
class sopa {
    static void comparaString(String l1, String l2) {
	    int len;
	    if(l1.length()<l2.length())
		len=l1.length();
	    else
		len=l2.length();
	    for(int i=0;i<len;i++){
		if(l1.charAt(i)!=l2.charAt(i)){
		    System.out.printf("%c%c\n",l1.charAt(i),l2.charAt(i));
		    return;
		}
	    }
	    System.out.println("Nenhum\n");
    }
 
 
    public static void main (String [] args) {
	Scanner inp = new Scanner(System.in);
	String linha1,linha2;
	linha1=inp.nextLine();
	//	inp.nextLine();
	linha2=inp.nextLine();
	comparaString(linha1,linha2);
    }
}

