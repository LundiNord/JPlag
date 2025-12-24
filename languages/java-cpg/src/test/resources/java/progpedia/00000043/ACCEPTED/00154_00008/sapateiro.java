
import java.util.*;

public class sapateiro {
	
	
	
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		
		int size=input.nextInt();
		int vetor[][]=new int[size][2];
		float aux[]=new float[size];
		for(int i=0;i<size;i++){
			for(int j=0;j<2;j++)
				vetor[i][j]=input.nextInt();
			aux[i]=(float)vetor[i][1]/vetor[i][0];
		}
		float maior=-1;
		int index=0,tam=size,ya=0;
		while(tam!=0){
			maior=0;
			ya=0;
			for(int i=0;i<size;i++){
				if(maior==aux[i] && ya==0 && aux[index]!=aux[i]){
					index=i;
					ya++;
				}
				else if(maior<aux[i] && aux[i]!=-1){
					maior=aux[i];
					index=i;
				}
				
			}
//*****************************Print***********************
			if(aux[index]!=-1){
				aux[index]=-1;
				tam--;
				if(tam!=0)
					System.out.print(index+1+" ");
				else
					System.out.print(index+1);
			}
			else
				tam--;
			
		}System.out.println();
		
		
	}//End of main
}





