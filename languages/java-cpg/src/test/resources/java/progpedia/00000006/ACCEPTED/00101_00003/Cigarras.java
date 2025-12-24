import java.util.*;
public class Cigarras {
	public static void main(String[] args) {
		Scanner kb = new Scanner (System.in);
		int caminho[]=new int[30];
		int v=kb.nextInt();
		int n=0;
		boolean repetido;
		while(v!=0){
			int aux = -1;
			repetido=false;
			for(int i=0;i<n;i++){
				if(repetido==true)
					caminho[i]=0;
				else
					if(caminho[i]==v){
						repetido=true;
						aux=i+1;
					}
			}
			if(repetido==false){
				caminho[n]=v;
				n++;
			}
			if(aux!=-1)
				n=aux;
			v=kb.nextInt();
		}
		for(int i=0;i<30;i++){
			if(caminho[i]!=0)
				System.out.println(caminho[i]);
		}
	}

}
