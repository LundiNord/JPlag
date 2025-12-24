import java.util.*;

class Prob122
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	ArrayList<Rock> list = new ArrayList<Rock>();

	int layers  = input.nextInt();
	int defects = input.nextInt();
	
	for (int i=0;i<defects;i++) {
	    Rock r = new Rock((layers-input.nextInt()),input.nextInt()-1);
	    list.add(r);
	}
	System.out.println(solve(createTable(layers,list),layers));
    }
    
    public static Boolean[][]  createTable (int layers, ArrayList<Rock> list) {
	Boolean flag = false;
	Boolean[][] p = new Boolean[1000][1000];
	
	for (int i=0;i<layers;i++) {
	    for (int j=0;j<=i;j++) {

		int l=0;
		for (l=0;l<list.size();l++) {
		    if (list.get(l).pos==j && list.get(l).layer==i){
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

    
    public static int solve (Boolean[][] p, int layers) {
	int count[][]  = new int[1000][1000];
	for (int i=0;i<=layers;i++) {
	    for (int j=0;j<=layers;j++) {
		count[i][j]=0;
	    }
	}
	for (int j=0;j<=layers;j++) {
	    count[layers-1][j]=1;
	}
	for (int i=layers-2;i>=0;i--) {
	    for (int j=0;j<layers;j++) {
		if (i>=0 &&  p[i][j]==false) {
		    if (p[i+1][j]==false)
			count[i][j] += count[i+1][j];
		    if (p[i+1][j+1]==false)
			count[i][j] += count[i+1][j+1];
		}
	    }
	    
	}
	return (count[0][0]);
    }
    
}

class Rock
{
    int layer=0;
    int pos=0;
    
    Rock(int layer, int pos){
	this.layer=layer;
	this.pos=pos;
    }
}
