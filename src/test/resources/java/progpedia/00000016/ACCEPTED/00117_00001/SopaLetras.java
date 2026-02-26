import java.util.*;

class SopaLetras{
	static Scanner stdin=new Scanner(System.in);

	static int teste(String a, String b){
		int i;
		for(i=0; i<Math.min(a.length(), b.length()); i++){
			if(Character.getNumericValue(a.charAt(i))!=Character.getNumericValue(b.charAt(i))){
				return i;
			}
		}
		return -1;
	}

	public static void main(String args[]){
		String a=stdin.nextLine();
		String b=stdin.nextLine();
		int x=teste(a, b);
		if (x==-1){
			System.out.println("Nenhum");
		}
		else{
			System.out.print(a.charAt(x));
			System.out.println(b.charAt(x));
		}
	}
}
