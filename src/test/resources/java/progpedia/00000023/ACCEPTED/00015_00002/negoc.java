import java.util.*;

class negoc {

    static int extracmin (int a[][], int n, int m) {
	int min = m;
	int posmin=0;
	for (int i=1; i<=n; i++) {
	    if (a[0][i]<min && a[i][0]!=-1) {
		min=a[0][i];
		posmin=i;
	    }
	}
	return posmin;
    }

    public static void main (String args[]) {
	Scanner kb = new Scanner (System.in);
	int soma = 0;
	int regi = kb.nextInt();
	int adj [][] = new int [regi+1][regi+1];
	int start = kb.nextInt();
	int v1 = kb.nextInt();
	while (v1!=-1) {
	    int v2 = kb.nextInt();
	    int dis = kb.nextInt();
	    soma+=dis;
	    adj[v1][v2]=dis;
	    adj[v2][v1]=dis;
	    v1=kb.nextInt();

	}
	for (int i=1; i<=regi; i++) adj[0][i]=soma;
	adj[0][start]=0;
	int soluc[] = new int [regi];
	int p=0;
	for (int i=0; i<regi; i++) {
	    int k = extracmin (adj,regi,soma);
	    adj [k][0] = -1;
	    soluc[p]=k;
	    p++;
	    for (int j=1; j<=regi; j++) {
		if (adj[k][j]>0 && adj [j][0]!=-1) {                       //relax k j
		    int nova = adj[0][k] + adj[k][j];
		    if (nova < adj[0][j]) adj[0][j] = nova;

		}
	    }
	}

	for (int i=0; i<regi; i++) System.out.print(soluc[i]+" ");
	System.out.println();

	
    }

}
/*
9 7
1 2 32
1 3 14
1 4 15
2 5 35
3 2 20
5 3 22
3 4 25
5 6 24
2 6 40
7 2 40
7 8 33
6 7 15
6 8 25
9 6 22
9 8 21
5 9 17
5 4 33
9 4 45
-1

*/