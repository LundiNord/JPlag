import java.util.*;

class No {
    int pri;
    boolean estado;

    No() {
	pri=-1;
	estado=false;
    }
}


class opt2 {

    static int extmin (No v[], int n) {
	int min = -1;
	int pos = -1;
	for (int i=1; i<=n; i++) {
	    if (v[i].pri!=-1 && !v[i].estado && v[i].pri>min) {
		min = v[i].pri;
		pos = i;
	    }
	}
	return pos;
    }

    public static void main (String args[]) {
	Scanner kb = new Scanner (System.in);
	int nos = kb.nextInt();
	int grelha [][] = new int [nos+1][nos+1];
	for (int i=0; i<=nos; i++) 
	    for (int j=0; j<=nos; j++) grelha[i][j]=0;
	int ligac = kb.nextInt();
	int custo = kb.nextInt();
		
	for (int i=0; i<ligac; i++) {
	    int x = kb.nextInt();
	    int y = kb.nextInt();
	    int ganho = kb.nextInt();
	    grelha[x][y]=ganho;
	    grelha[y][x]=ganho;
	}
	No grupo[] = new No [nos+1];
	for (int i=1; i<=nos; i++) grupo[i] = new No();
	grupo[1].pri=0;
	boolean v = true;
	int tot=0;
	for (int i=1; i<=nos && v; i++) {
	    int max=0;
	    int k = extmin (grupo,nos);
	    //System.out.println("k "+k);
	    if (k==-1) {
		v=false;
		System.out.println("impossivel");
	    }
	    else {
		tot+=grupo[k].pri;
		grupo[k].estado=true;
		for (int j=1; j<=nos; j++) {
		    if (grelha[k][j]>0 && !grupo[j].estado && grelha[k][j]>grupo[j].pri) 
			grupo[j].pri=grelha[k][j];
		}
	    }
	}
	nos--;
	tot = tot - (nos*custo);  
	if (v) System.out.println("rendimento optimo: "+tot);
	
    }
}