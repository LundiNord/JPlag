import java.util.*;
import java.io.*;

class sopa{

	public static void main(String args[]){
	
		Scanner kb = new Scanner (System.in);
		
		String a = kb.next();
		String b = kb.next();
		
		int length = a.length();
		int ctrl = 0;
		
		for(int i=0; i<length; i++){
			if(a.charAt(i) != b.charAt(i)){
				System.out.print(a.charAt(i)+""+b.charAt(i));
				ctrl = 1;
				break;
			}
		}
		if(ctrl==0){
			System.out.print("Nenhum");
		}
		
		
	}
}