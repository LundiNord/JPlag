import java.util.*;

class e1
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	ArrayList<Rocha> list = new ArrayList<Rocha>();

	int layers  = input.nextInt();
	int def = input.nextInt();
	
	for (int i=0;i<def;i++) {
	    Rocha r = new Rocha((layers-input.nextInt()),input.nextInt()-1);
	    list.add(r);
	}
	System.out.println(solved(cTable(layers,list),layers));
    }
    
    public static Boolean[][]  cTable (int layers, ArrayList<Rocha> list) {
	Boolean flag = false;
	Boolean[][] p = new Boolean[1000][1000];
	
	for (int i=0;i<layers;i++) {
	    for (int j=0;j<=i;j++) {

		int l=0;
		for (l=0;l<list.size();l++) {
		    if (list.get(l).pos==j && list.get(l).lay==i){
			p[i][j]=true;
			break;
		    }
		}
		if (l==list.size())
		    p[i][j]=false;
		
		flag=false;
	    }
	    for (int j=i+1;j<layers;j++) 
		p[i][j]=true;
	}
	
	return p;
    }

    
    public static long solved (Boolean[][] p, int layers) {
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
		if (i>=0 &&  p[i][j]==false) {
		    if (p[i+1][j]==false)
			cont[i][j] += cont[i+1][j];
		    if (p[i+1][j+1]==false)
			cont[i][j] += cont[i+1][j+1];
		}
	    }
	    
	}
	return (cont[0][0]);
    }
    
}

class Rocha
{
    int lay=0;
    int pos=0;
    
    Rocha(int lay, int pos){
	this.lay=lay;
	this.pos=pos;
    }
}
