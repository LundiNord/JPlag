import java.util.LinkedList;
import java.util.Scanner;


public class ordenar {

	public static int analisa (String d,String e){
		for(int i= 0 ; d.charAt(i)!='\n' || d.charAt(i)!='\n'; i++)
		{
		 if (d.charAt(i)!=e.charAt(i))
			{
			System.out.println(d.charAt(i)+ "" +e.charAt(i));
			return -1;
			}
		
		}
		System.out.println("Nenhum");
		
		return -1;
	}
	public static void main(String[] args) {
		
	Scanner stdin = new Scanner(System.in);
	String d = stdin.nextLine()+'\n';
	String e = stdin.nextLine()+'\n';
	int a = analisa(d,e);
	
	
	
		
	
	}
	

}
