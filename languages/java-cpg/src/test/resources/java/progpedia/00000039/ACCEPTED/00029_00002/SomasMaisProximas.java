import java.util.Arrays;
import java.util.Scanner;


public class SomasMaisProximas {

	private static int[] somas;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdin = new Scanner(System.in);
		
		int n_valores = stdin.nextInt();
		int soma = (n_valores * (n_valores-1))/2;
		int[] valores = new int[n_valores];
		somas = new int[soma];
		int incr = 0;
		
		for(int i=0; i<n_valores; i++){
			valores[i]=stdin.nextInt();
		}
		
		for(int i=0; i<n_valores; i++){
			for(int j=i+1; j<n_valores;j++){
				somas[incr]=valores[i]+valores[j];
				incr++;
			}
		}
		
		Arrays.sort(somas);
		
		int n_casos = stdin.nextInt();
		int valor;
		int a;
		for(int i=0; i<n_casos; i++){
			valor = stdin.nextInt();
			
			a=binarySearch(0, incr-1, valor);
			if(a!=0)
				System.out.println(a);
		}	
		
	}

	private static int binarySearch(int i, int j, int valor) {
		// TODO Auto-generated method stub
		if((j-i) == 1){
			int diff1 = Math.abs(somas[i]-valor);
			int diff2 = Math.abs(somas[j]-valor);
			if(diff1 < diff2)
				return somas[i];
			
			else if(diff1==diff2){
				System.out.println(somas[i]+" "+somas[j]);
				return 0;
			}
			
			return somas[j];
		}
		
		int mid = (i+j) / 2;
		if(valor<=somas[mid])
			return binarySearch(i, mid, valor);
		else
			return binarySearch(mid, j, valor);
	}

}
