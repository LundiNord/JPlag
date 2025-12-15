import java.util.Scanner;


public class Cigarras {
	public static void main(String[] args){
		
		Scanner moo = new Scanner(System.in);
		int caminho[]=new int [30];
		int index=0;
		
		int local=moo.nextInt();
		
		
		while(local !=0){
			for(int i=0; i<index; i++)
				if(caminho[i]==local)
					index=i;
			
			caminho[index]=local;
			local=moo.nextInt();
			index++;
		}

		for(int k=0; k<index;k++)
			System.out.println(caminho[k]);
		
		
		
		
}
}