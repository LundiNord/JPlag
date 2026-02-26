import java.util.*;

public class ProblemaC {
	public static void main (String agrs[]){
		Scanner in = new Scanner(System.in);
		int c [] = new int[30];
		int i=0; int aux;
		
		while((aux=in.nextInt())!=0){
				c[i]=aux;
				for(int j=0; j<i; j++){
			     if(c[j]==aux){
				     i=j;
			     	}
			     }
			i++;
			
		}
		for(int j=0; j<i; j++){
			System.out.println(c[j]);
		}
		
	}
}
