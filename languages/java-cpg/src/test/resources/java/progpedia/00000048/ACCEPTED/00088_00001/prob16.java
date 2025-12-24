import java.util.*;
class prob16{
	// dfs recebe:
		// a matriz de adjacencias 
		//o vetor que diz que pontos ja foram visitados
		//o ponto onde fazemos dfs
		//o numero de pontos de contacto
	public static void dfs(boolean pontos[][], boolean visitados[], int i, int n){
		// assinala o ponto onde fazemos dfs como visitado
		visitados[i] = true;
		for(int j=1; j<=n; j++){
			if(visitados[j] == false && pontos[i][j] == true){
				dfs(pontos,visitados,j,n);
			}
		}
	}
	
	public static void main(String Args[]){
		Scanner in = new Scanner (System.in);
		// pontos de contacto
		int n = in.nextInt();
		//ligacoes
		int l = in.nextInt();
		//matriz booleana de adjacencias
		boolean lig[][]=new boolean[n+1][n+1];
		//inicializar matriz a false
		for (int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				lig[i][j]= false;
		//ler os pares e preencher matriz
		for(int t=0;t<l;t++){
			int a = in.nextInt();
			int b = in.nextInt();
			lig[a][b]=lig[b][a] = true;
		}
		boolean visitados[]= new boolean[n+1];
		int contador = 0;
		// contar componentes conexas
		for(int k=1;k<=n;k++){
			if(visitados[k]== false){
				contador++;
				dfs(lig,visitados,k,n);	
			}
		}
		System.out.println(contador);
	
	}
}