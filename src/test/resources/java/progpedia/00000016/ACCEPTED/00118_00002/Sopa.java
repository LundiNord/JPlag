import java.util.Scanner;

public class Sopa{
	public static void main(String[] args){
		Scanner stdin = new Scanner(System.in);
		String a= stdin.next();
		String b= stdin.next();
		int x= Math.min(a.length(), b.length());
		for (int y=0; y<x; y++){
			if (a.charAt(y)!=b.charAt(y)){
				System.out.print(a.charAt(y));  
				System.out.println(b.charAt(y));
				return;
			}
		}
		System.out.println("Nenhum");
		
	}
}