import java.util.*;

class daa12{
    
    public static int calcula(boolean m[][], int n){
	int i;
	int j;
	int aux[][] = new int[n+1][n+1];
	for(i=0;i<=n;i++)
	    for(j=0;j<=n;j++)
		aux[i][j] = 0;
	if(!m[n][0]) return 0;
	aux[n][0] = 1;
	for(i=n; i>0; i--)
	    for(j=0; j<=n-i; j++){
		if(m[i][j]){
		if(m[i-1][j])
		    aux[i-1][j]+=aux[i][j];
		else
		    aux[i-1][j] = 0;
		if(m[i-1][j+1])
		    aux[i-1][j+1]+=aux[i][j];
		else
		    aux[i-1][j+1] = 0;
		}
	    }
	
	int sum=0;
	for(i=0; i<=n; i++)
	    sum+= aux[0][i];
	
	return sum;	
    }
    
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int x=0;
	int y=0;
	int size = in.nextInt();
	boolean m[][] = new boolean[size][size];
	for(int i=0; i<size; i++)
	    for(int j=0; j<size-i; j++)
		m[i][j] = true;
	
	int falhas = in.nextInt();
	
	for(int i=0; i<falhas; i++){
		x=in.nextInt();
		y=in.nextInt();
		m[x-1][y-1] = false;
	}

	int save = calcula(m, size-1);
	System.out.println(save);
    }
}
