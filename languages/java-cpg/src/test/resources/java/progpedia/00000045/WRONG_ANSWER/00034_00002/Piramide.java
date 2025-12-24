import java.util.*;

class Piramide{
	public static int verifica(int[][] v, int x, int y, int n){
			if ((x == n-1))
				return 1;
			else if (v[x+1][y]!=-1 && v[x+1][y+1]!=-1 && v[x+1][y]!=0 && v[x+1][y+1]!=0){
				if (x+1 != n-1){
					v[x+1][y] = -1;
					v[x+1][y+1] = -1;}
				return verifica(v, x+1, y, n) + verifica(v, x+1, y+1, n);}
			else if (v[x+1][y]!= -1 && v[x+1][y]!=0){
				if (x+1 != n-1)
					v[x+1][y] = -1;
				return verifica(v, x+1, y, n);}
			else if (v[x+1][y+1]!= -1 && v[x+1][y+1]!=0){
				if (x+1 != n-1)
					v[x+1][y+1] = -1;
				return verifica(v, x+1, y+1, n);}
			return 0;
	}

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int d = input.nextInt();
		int[][] v = new int[n][n];
		for (int i = 0; i < n; i++){
			for (int j = 0; j <= i; j++){
					v[i][j] = 1;
			}
		}
			
		for (int h = 0; h < d; h++){
			int x = input.nextInt();
			int y = input.nextInt();
			v[n-x][y-1] = 0;
		}
		for (int i = 0; i < n; i++){
			for (int j = 0; j < i+1; j++){
				System.out.print(v[i][j]);
				
			}
			System.out.println();
		}
		int c = verifica(v,0,0,n);
		System.out.print(c);
	}
}
