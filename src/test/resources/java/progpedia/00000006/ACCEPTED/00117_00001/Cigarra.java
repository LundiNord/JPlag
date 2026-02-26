import java.util.Scanner;


public class Cigarra {

	public static void main(String[] args) {
		
		int v[] = new int [30];
		int valor;
		
		Scanner in = new Scanner(System.in);
		
		valor=in.nextInt();
		int i;
		for(i=0; valor!=0; i++){
			v[i]=valor;
			
			for(int j=i; j!=0 && i!=0; j--){
				if(v[j-1]==valor)
					i=j-1;
			}
			valor=in.nextInt();
		}

		for(int n=0; n<=i-1; n++)
			System.out.println(v[n]);
		
	}

}
