import java.util.Scanner;


public class SopaLetras {
	public static void main(String []args){
		int count=0;
		Scanner in = new Scanner(System.in);
		String a= in.nextLine();
		String b=in.nextLine();
		int length=Min(a.length(),b.length());
		for(int i=0;i<length;i++){
			if((a.charAt(i) != b.charAt(i))){
				System.out.print(a.charAt(i));
				System.out.print(b.charAt(i));
				count++;
				break;
			}
		}
		if(count==0){
			System.out.print("Nenhum");
		}
	}

	private static int Min(int a, int b) {
		if(a>b){
			return b;
		}else{
			return a;
		}
	}

	
}
