import java.util.*;

class ProblemaA2 {

    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	String palavra1,palavra2;
	int i;

	palavra1=sc.next();
	palavra2=sc.next();

 	for(i=0;i<palavra1.length();i++)
	    if(palavra1.charAt(i)!=palavra2.charAt(i)){
		System.out.println(palavra1.charAt(i)+""+palavra2.charAt(i));
		break;
	    }
	if(i==palavra1.length())
	    System.out.println("Nenhum");
    }
}