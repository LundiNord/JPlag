import java.util.*;

class ProblemaA{

    public static void main(String[] args){

	Scanner sc=new Scanner(System.in);
	String p1=sc.next();
	String p2=sc.next();

	int i=0;
	while(i<p1.length()){
	    if(p1.charAt(i)!=p2.charAt(i))
		break;
	    i++;
	}

	if(i<p1.length())
	    System.out.printf("%c%c\n",p1.charAt(i),p2.charAt(i));
	else
	    System.out.println("Nenhum");
    }
}