import java.util.*;
public class cigarras {
	public static void main(String [] args){
		Scanner teclado = new Scanner(System.in);
		int locais[] = new int[31];
		int nlido = teclado.nextInt();
		int cursor=0;
		while(nlido!=0){
			locais[cursor]=nlido; cursor++;
			for(int i=0; i<cursor; i++){
				if(locais[i]==nlido){
					cursor=i+1;
				}
			
				
			
			}
			nlido= teclado.nextInt();	
		}
		for(int i=0; i<cursor; i++)
			System.out.println(locais[i]);
	}
}
