import java.util.*;

public class Cigarras {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);

		int locais[] = new int[30];
		int ultimo=0; // ultimo local de passagem
		
		int pos = stdin.nextInt();  //le o posicao
		
		while (pos!=0){
			locais[ultimo]=pos;
			pos = stdin.nextInt();
			int aux=0;	
				
				for(int i=0; i<=ultimo; i++){
					if(pos==locais[i])
						ultimo=i;
					else if(aux==0){
						ultimo++;
						aux=1;
					}
				}
				
				
		}
		
		for (int i=0; i<ultimo; i++)
			System.out.println(locais[i]);
			
		
		
	}//main

}//class
