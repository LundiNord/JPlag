import java.util.*;

class Encomenda implements Comparable<Encomenda>{
    int x;
    double ratio;
    Encomenda(int a,double b){
	x=a;
	ratio=b;
    }
    public int compareTo(Encomenda a){
	if(this.ratio < a.ratio)
	    return 1;
	else if(this.ratio >a.ratio) 
	    return -1;
	else {
	    if (this.x > a.x) 
		return 1;
	    else 
		return -1;
	}
    }
}

class prob10{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	Encomenda[] encomendas=new Encomenda[N];
	for(int i=0;i<N;i++){
	    int duraçao=in.nextInt();
	    encomendas[i]=new Encomenda(i+1,in.nextDouble()/duraçao);
	}
	Arrays.sort(encomendas);
	boolean first=true;
	for(int i=0;i<encomendas.length;i++){
	    if(first){
		System.out.print(encomendas[i].x);
		first=false;
	    }
	    else
		System.out.print(" "+encomendas[i].x);
	}
	System.out.println();
    }
}
