import java.util.*;


public class prob16 {
	
	
	static void dfs(int no,int n, int redes[][],int aux[]){
		aux[no]=1;

		for(int i=1;i<=n;i++){
			if(redes[i][no]==1 && aux[i]==0) 
				dfs(i,n,redes,aux);
		}

	}
		
	
	
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int l = in.nextInt();
		int redes[][] = new int [n+1][n+1];
		int aux[] = new int[n+1];
		int soma=0;
		
		
		Arrays.fill(aux,0);

		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++)
				redes[i][j]=0;
		}
		
		for(int i=0;i<l;i++){
			int k1=in.nextInt();
			int k2=in.nextInt();
			redes[k1][k2]=1;
			redes[k2][k1]=1;
		}
	
		
		for(int i=1;i<=n;i++){ 
			if(aux[i]==0) soma++;
			dfs(i,n,redes,aux);
		}
		System.out.println(soma);
	}
}
