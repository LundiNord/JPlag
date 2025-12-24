

import java.util.ArrayList;
import java.util.Scanner;

public class SopaLetras {
	public static void main(String args[]){
		boolean terminou=false;
		Scanner in= new Scanner(System.in);
		String word1=in.nextLine();
		String result="Nenhum";
		String word2=in.nextLine();
			
				int size=min(word1,word2);
				for(int i=0;i<size && !terminou;i++){
					if(word1.charAt(i)!=word2.charAt(i)){
						terminou=true;
						result=String.valueOf(word1.charAt(i))+String.valueOf(word2.charAt(i));
					}
				}
		System.out.println(result);
		
		
	}
	
	public static int min(String a,String b){
		if(a.length()<b.length()){
			return a.length();
		}
		return b.length();
	}
}
