import java.util.*;

public class Sopa{
    public static int min(int l1, int l2){
	if(l1<=l2) return l1;
	return l2;
    }

    public static void main(String args[]){
	Scanner in = new Scanner (System.in);
	boolean diferente = false;
	char l1='a', l2='a';
	String line1 = in.nextLine();
	String line2 = in.nextLine();
	int size = min(line1.length(),line2.length());
	for(int i = 0; i<size && !diferente; i++){
	    if(line1.charAt(i)!=line2.charAt(i)){ diferente=true; l1 = (char)line1.charAt(i); l2 = (char)line2.charAt(i);}
	}
	if(!diferente) System.out.println("Nenhum");
	else{System.out.print((char)l1); System.out.println((char)l2);}
    }
}