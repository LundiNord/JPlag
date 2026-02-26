import java.util.*;
public class Rareorder {
	public static void main(String[] args){
	Scanner moo = new Scanner(System.in);
	String palavra1=moo.nextLine();
	String palavra2=moo.nextLine();
	int j=0;
	while(j<palavra1.length() && palavra1.charAt(j)==palavra2.charAt(j)){
//		if(palavra1.charAt(i)!=palavra2.charAt(i)){
//			System.out.print(palavra1.charAt(i));
//			System.out.println(palavra2.charAt(i));
//			break;
			
	//	}
		j++;
	}
	if(j==palavra1.length())
		System.out.println("Nenhum");
	else{
		System.out.print(palavra1.charAt(j));
		System.out.println(palavra2.charAt(j));
	}
}
}
