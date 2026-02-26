import java.util.*;
import java.io.*;

public class prob6 {
		public static void pesqbin(int[] array, int valor){
		int inicio = 0;
		int fim = array.length-1;
		int dif=Integer.MAX_VALUE;
		int imprime=0;
		int t=0;
		while(inicio <= fim){
			int meio = (inicio+fim)/2;
			int diff=Math.abs(array[meio]-valor);
			if(diff<dif){
				dif=Math.abs(array[meio]-valor);
				imprime=array[meio];
				t=meio;
				}
		
			
			if(valor > array[meio]){
				inicio = meio+1;
			} else {
				fim = meio-1;
			}
		}

		if(imprime==0){
			System.out.println(array[0]);
		}
		else{
			if (dif!=1){
				System.out.println(imprime);
			}
			
			else{
				
				if(t+1<array.length){
					int patria=Math.abs(array[t+1]-valor);
					if(patria==1){
				System.out.println(array[t] + " " + array[t+1]);}}
				else{System.out.println(imprime);}
			}
	}	
}

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int n = in.nextInt();
		int con[] = new int[n];
		for (int i = 0; i < n; i++){
			con[i] = in.nextInt();}
		int somas[] = new int[n*(n-1)/2];
		int p = in.nextInt();
		int que[] = new int[p];
		for (int i = 0; i < p; i++){
			que[i] = in.nextInt();}
		int s=0;
		for(int i=0; i<n;i++){
			for(int j=i+1;j<=n-1;j++){
				somas[s]=con[i]+con[j];
				s++;
			}
		}
		Arrays.sort(somas);

		/* for(int i=0;i<s;i++){
			System.out.print(somas[i] + " ");
		} */

		for(int i=0;i<p;i++){
		pesqbin(somas,que[i]);
		}
	}
}