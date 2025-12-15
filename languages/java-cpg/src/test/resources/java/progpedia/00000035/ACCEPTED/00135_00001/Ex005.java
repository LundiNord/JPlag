import java.util.*;

public class Ex005{
    public static void  main(String args[]){
	Scanner in = new Scanner(System.in);
	int n;
	n= in.nextInt();
	int v[]= new int[n];

	for(int i=0;i<n;i++){
	    v[i]=in.nextInt();
	    // System.out.println(v[i]);
	}
	
	System.out.println(solucao(v));
    }

    static int solucao(int v[]){
	int contador=0;
	for(int i=0;i<v.length;i++){
	    for(int j=i+1; j<v.length; j++){
		if(v[i]>v[j])
		    contador+=1;
	    }
	}
	return contador;
    }
} 
