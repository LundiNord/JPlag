
import java.util.*;

public class sapateiro {
	
	
	
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		
		int size=input.nextInt();
		int vetor[][]=new int[size][2];
		int aux[]=new int[size];
		for(int i=0;i<size;i++){
			for(int j=0;j<2;j++)
				vetor[i][j]=input.nextInt();
			aux[i]=Math.abs(vetor[i][0]-vetor[i][1]);
		}
		int maior=-1;
		int index=0,tam=size,ya=0;
		while(tam!=0){
			maior=0;
			ya=0;
			for(int i=0;i<size;i++){
				if(maior==aux[i] && ya==0){
					index=i;
					ya++;
				}
				else if(maior<aux[i] && aux[i]!=-1){
					maior=aux[i];
					index=i;
				}
				
			}
			if(aux[index]!=-1){
				aux[index]=-1;
				tam--;
				System.out.print(index+1+" ");
			}
			else
				tam--;
			
		}System.out.println();
		
		
	}//End of main
}





