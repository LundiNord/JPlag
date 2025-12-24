import java.util.*;
import java.io.*;

public class prob15 {
	static int n;
	static int l;
	static boolean lig[][];
	static boolean visit[];
	static int cont;

	public static void main (String args[]){
		Scanner stdin = new Scanner(System.in);
	
		n = stdin.nextInt();
		l = stdin.nextInt();
		lig = new boolean[n+1][n+1];
		visit = new boolean [n+1];
		cont = 0;
	
		for (int i=0; i<l;i++){
			int x = stdin.nextInt();
			int y = stdin.nextInt();
			lig[x][y] = lig[y][x] = true;
		}
	
		for (int i=1;i<=n; i++){
			if (visit[i] != true)
				cont++;
				dfs(i);
		}
		System.out.println(cont);
	}
	static void dfs(int v){
		visit[v] = true;
		for (int i=1; i<=n; i++)
			if (lig [v][i] && visit[i] != true)
				dfs(i);
	}	
}

//feito em conjunto com Nuno Duraes.
