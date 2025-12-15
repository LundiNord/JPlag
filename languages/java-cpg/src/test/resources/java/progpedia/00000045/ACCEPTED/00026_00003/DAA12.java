import java.util.*;
class Pedra{
    public int c,p;
    Pedra(int c,int p){
	this.c=c;
	this.p=p;
    }
}

class DAA12{
    
    static public long sol(int a, int b, long[][]v, long[][]v2){
	long contador=0;
	if(v[a][b]==0)
	    return 0;
	if(a==0)
	    return 1;
	if(v2[a][b]!=-1)
	    return v2[a][b];
	else{
	    contador=contador+sol(a-1,b,v,v2);
	    contador=contador+sol(a-1,b+1,v,v2);
	}
	v2[a][b]=contador;
	return contador;
    }
    /*static public int cal(int L, int C, int[][]v){
	for(int i=L; L>0; L--){
	    for(int j=C; C>0; C--){
		if(i<L && v[i+1][j]!=0)
		    v[i][j]=v[i][j]+v[i+1][j];
		if(j<C && v[i+1][j-1]!=0)
		    v[i][j]=v[i][j]+v[i+1][j-1];
	    }
	}
	return 0;
	}*/
    
    static public void main(String Args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	long [][]m = new long [n][n];
	long [][]m2= new long [n][n];

	for(int i=0; i<n; i++)
	    for(int j=0; j<n; j++)
		m2[i][j]=-1;
	for(int i=0; i<n; i++)
	    for(int j=0; j<n; j++)
		m[i][j]=1;
	
	for(int i=0; i<n; i++)
	    for(int j=n-i; j<n; j++)
		m[i][j]=0;
	
	int d = in.nextInt();
	Pedra []p = new Pedra[d];
	for(int i=0; i<d; i++){
	    p[i]=new Pedra(in.nextInt(),in.nextInt());
	    m[p[i].c-1][p[i].p-1]=0;
	}
	
	/*for(int i=0; i<n; i++){
	    System.out.println();
	    for(int j=0; j<n; j++){
		System.out.print(m[i][j]+" ");
	    }
	}

	    
	for(int i=0; i<n; i++){
     	    for(int j=0; j<n; j++){
		if(i<n-2){
		    m[i][j]=m[i][j]+m[i+1][j];
		    System.out.println("--------------");
		}
		if(j>0 && i<n-2 && m[i+1][j-1]!=0){
		    m[i][j]=m[i][j]+m[i+1][j-1];
		    System.out.println("...............");
		}
	    }
	    }*/


	System.out.println(sol(n-1,0,m,m2));

	
    }      
}

