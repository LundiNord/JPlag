import java.util.*;
class Prob12{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int k = in.nextInt();
	long piramide[][] = new long[n+1][n+1];
	for(int i=0;i<k;i++){
	    int x = in.nextInt();
	    int y = in.nextInt();
	    piramide[x][y]=(-1);
	}
	for(int i=1;i<=n;i++){
	    if(piramide[1][i]==(-1)){piramide[1][i]=0;}
	    else piramide[1][i]=1;
	}

	for(int i=2;i<=n;i++){
	    for(int j=1;j<=1+n-i;j++){
		if(piramide[i][j]==-1) piramide[i][j]=0;
		else piramide[i][j]=piramide[i-1][j]+piramide[i-1][j+1];
	    }
	}
	System.out.println(piramide[n][1]);
    }
}
