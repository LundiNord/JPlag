import java.util.*;

class Sopa {
	public static void main(String args[]){
		int flag=0;
		Scanner ler = new Scanner(System.in);
		String pri = ler.next();
		String sec = ler.next();
		ler.close();
		for(int i=0;i<pri.length()&& i<sec.length();i++){
			if(pri.charAt(i)!=sec.charAt(i)){
			System.out.println(String.format("%c%c",pri.charAt(i),sec.charAt(i)));
		flag=1;
		break;
			}
		}
		if(flag==0)
		System.out.println(String.format("Nenhum"));
	
	}
}
