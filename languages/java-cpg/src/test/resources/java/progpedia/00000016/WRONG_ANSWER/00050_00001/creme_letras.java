import java.util.*;
import java.lang.*;

class creme_letras{
    public static char palavra1[]= new char[21];
    public static char palavra2[]=new char[21];
    public static Scanner scan= new Scanner(System.in);
    public static void main(String[] args){
	String line1=scan.nextLine();
	String line2=scan.nextLine();
	int idd=compara(line1,line2);
	if(idd==-1){
	    System.out.println("Nenhum");
	}else{
	    System.out.println( line1.charAt(idd)+""+line2.charAt(idd));
	}
    }


    static public int compara(String line1,String line2){
	int i=0;
        
	while(line1.charAt(i)==line2.charAt(i) && i<line1.length()-1){
	    i++;
	    System.out.println(i);
	    
	}
	if(i<line1.length()) return -1;
	return i;
    }
}
