import java.util.*;

class ProblemaA2 {

    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	LinkedList<String> palavra1=new LinkedList<String>();
	LinkedList<String> palavra2=new LinkedList<String>();
	String letra1,letra2;

	String palavra=sc.next();
	for(int i=0;i<palavra.length();i++)
	    palavra1.addLast(""+palavra.charAt(i));
	palavra=sc.next();
	for(int i=0;i<palavra.length();i++)
	    palavra2.addLast(""+palavra.charAt(i));

	while(palavra1.size()>0){
	    letra1=""+palavra1.removeFirst();
	    letra2=""+palavra2.removeFirst();
	    if(!letra1.equals(letra2)){
		System.out.println(letra1+letra2);
		break;
	    }
	}
	if(palavra1.size()==0 && palavra2.size()>0)
	    System.out.println("Nenhum");
    }
}