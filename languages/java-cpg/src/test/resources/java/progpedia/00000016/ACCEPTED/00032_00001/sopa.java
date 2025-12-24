import java.util.Scanner;

class sopa{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String um = sc.next();
		String dois = sc.next();
		int i,len;

		if(um.length() < dois.length())
			len = um.length();
		else
			len = dois.length();

		for(i=0;i<len;i++){
			if(um.charAt(i) != dois.charAt(i)){
				System.out.printf("%c%c\n", um.charAt(i), dois.charAt(i));
				return;
			}
		}

		System.out.println("Nenhum");
	}
}