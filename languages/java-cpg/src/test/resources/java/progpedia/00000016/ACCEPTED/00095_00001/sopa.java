import java.util.Scanner;



public class sopa {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String a=sc.nextLine();
		String b=sc.nextLine();
		a=a.concat(".");
		b=b.concat(".");
		boolean c=false;
		int i=0;
		while((a.charAt(i) != '.')&&(b.charAt(i) != '.'))
		{
			if(a.charAt(i)!=b.charAt(i)){
					System.out.print(a.charAt(i));
					System.out.print(b.charAt(i));
					c=true;
					break;
			}
			i++;
		}
		if(c==false)
		System.out.print("Nenhum");
	}
}
