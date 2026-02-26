import java.util.*;
public class letras1 {

	public static void main(String args[]){
		Scanner ler = new Scanner (System.in);
		int flag=1;
		String one = ler.nextLine();
		String two = ler.nextLine();
		
		for(int x=0;x<one.length()&& x<two.length() && !one.equals(two);x++){
			if(one.charAt(x)!= two.charAt(x)){
				char one_c =one.charAt(x);
				char two_c = two.charAt(x);
			System.out.println(one_c +""+ two_c);
			flag=0;
			break;
			}
		}
	if(flag==1){
		System.out.println("Nenhum");
	}

	}
}
