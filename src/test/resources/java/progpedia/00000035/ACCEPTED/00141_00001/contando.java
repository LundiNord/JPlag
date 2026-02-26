import java.util.*;


public class contando{
    static int n;
    static int list[];
    static int cont;




    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	int list[]= new int[n];
	for(int i=0; i<n; i++)
	    {
		list[i]= stdin.nextInt();
	    }
	
	cont=0;

	for(int i=0; i<n; i++){
	    for(int j=i+1;j<n; j++){
		if(list[i] > list[j])
		    {
			cont++;
		    }
	    }
	}
	System.out.println(cont);
    }
}
