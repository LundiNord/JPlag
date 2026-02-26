import java.io.*;
import java.util.*;

class Segmentos implements Comparable<Segmentos>{
		int inicio;
		int fim;
		
		Segmentos(int i, int f){
			inicio=i;
			fim=f;
		}
	
		@Override
		public int compareTo(Segmentos o){
			if (o.inicio==inicio)
				return 0;
			else if(o.inicio > inicio) 
				return -1;
			else 
				return 1;	
		}
}
public class Prob009{
	
    public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
				
		int m = stdin.nextInt();
		
		int n = stdin.nextInt();
		Segmentos s[] = new Segmentos[n];
		
		for(int i=0; i<n; i++){
			s[i]= new Segmentos(stdin.nextInt(), stdin.nextInt());
		}
	
		Arrays.sort(s);
		
		int end=0, count=0, aux=0;
		while(end < m){
			for(int i=0; i<n; i++){
				//if(s[i].inicio==0){
					//aux=s[i].fim;
				//}
				if(s[i].inicio <= end){
					if(s[i].fim > aux){
						aux=s[i].fim;
					}	
				}
				
			}
			end=aux;
			count++;
		}
		System.out.println(count);
	
	}		
	
}
