import java.util.Scanner;

public class sopa {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String a=sc.nextLine();
		String b=sc.nextLine();
		boolean c=false;
		int len = Math.min(a.length(),b.length());
		for(int i=0;i<len;i++){
			if(a.charAt(i)!=b.charAt(i)){
					System.out.print(a.charAt(i));
					System.out.print(b.charAt(i));
					System.out.print("\n");
					c=true;
			}
		}
		if(c==false)
		System.out.print("Nenhum\n");
	}
}
