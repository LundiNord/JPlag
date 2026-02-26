import java.util.*;
public class Rareorder {
	public static void main(String[] args){
	Scanner moo = new Scanner(System.in);
	String palavra1=moo.nextLine();
	String palavra2=moo.nextLine();
	int j=0;
	for(int i=0; i<palavra1.length() && i<palavra2.length(); i++){
		if(palavra1.charAt(i)!=palavra2.charAt(i)){
			System.out.print(palavra1.charAt(i));
			System.out.println(palavra2.charAt(i));
			break;
			
		}
		j=i;
	}
	if(j==palavra1.length()-1 || j==palavra2.length()-1)
		System.out.println("Nenhum");
	
}
}
