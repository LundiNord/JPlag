import java.util.*;

class Piramides
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	ArrayList<Pedra> list = new ArrayList<Pedra>();

	int layers  = input.nextInt();
	int def = input.nextInt();
	
	for (int i=0;i<def;i++) {
	    Pedra p = new Pedra((layers-input.nextInt()),input.nextInt()-1);
	    list.add(p);
	}
	System.out.println(solved(cTable(layers,list),layers));
    }
    
    public static Boolean[][]  cTable (int layers, ArrayList<Pedra> list) {
	Boolean flag = false;
	Boolean[][] a = new Boolean[1000][1000];
	
	for (int i=0;i<layers;i++) {
	    for (int j=0;j<=i;j++) {

		int l=0;
		for (l=0;l<list.size();l++) {
		    if (list.get(l).pos==j && list.get(l).lay==i){
			a[i][j]=true;
			break;
		    }
		}
		if (l==list.size())
		    a[i][j]=false;
		
		flag=false;
	    }
	    for (int j=i+1;j<layers;j++) 
		a[i][j]=true;
	}
	
	return a;
    }

    
    public static long solved (Boolean[][] a, int layers) {
	long cont[][]  = new long[1000][1000];
	for (int i=0;i<=layers;i++) {
	    for (int j=0;j<=layers;j++) {
		cont[i][j]=0;
	    }
	}
	for (int j=0;j<=layers;j++) {
	    cont[layers-1][j]=1;
	}
	for (int i=layers-2;i>=0;i--) {
	    for (int j=0;j<layers;j++) {
		if (i>=0 &&  a[i][j]==false) {
		    if (a[i+1][j]==false)
			cont[i][j] += cont[i+1][j];
		    if (a[i+1][j+1]==false)
			cont[i][j] += cont[i+1][j+1];
		}
	    }
	    
	}
	return (cont[0][0]);
    }
    
}

class Pedra
{
    int lay=0;
    int pos=0;
    
    Pedra(int lay, int pos){
	this.lay=lay;
	this.pos=pos;
    }
}

