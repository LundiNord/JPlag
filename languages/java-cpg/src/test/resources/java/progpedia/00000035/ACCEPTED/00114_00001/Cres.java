import java.util.*;

public class Cres{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int n=in.nextInt();
	int v[]=new int[n];
	for(int k=0; k<n; k++)
	    v[k]=in.nextInt();
	int contador=0;
	for(int i=0; i<=n-1; i++){
	    for(int j=i+1; j<=n-1; j++){
		if(v[i]>v[j])
		    contador=contador+1;
	    }
	}
	System.out.println(contador);
	
    }
}
