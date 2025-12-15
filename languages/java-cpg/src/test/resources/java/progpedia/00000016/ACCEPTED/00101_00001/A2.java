import java.util.Scanner;



class A2 {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String p1 = in.next();
		String p2 = in.next();
		int size = Math.min(p1.length(), p2.length());
		int i;
		for (i = 0; i < size; i++) {
			if(p1.charAt(i)!=p2.charAt(i)){
				System.out.println(p1.charAt(i) + "" + p2.charAt(i));
				break;
			}
		}
		if(i==size && p1.charAt(i-1)==p2.charAt(i-1)) System.out.println("Nenhum");
	}
}