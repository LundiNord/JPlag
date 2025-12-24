import java.util.*;

class Encomenda implements Comparable<Encomenda>{
    int x, ratio;
    Encomenda(int a,int b){
	x=a;
	ratio=b;
    }
    @Override
    public int compareTo(Encomenda a){
	if(this.ratio<a.ratio)
	    return 1;
	else if(this.ratio>a.ratio)
	    return -1;
	else{
	    if(this.x>a.x)
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
	    encomendas[i]=new Encomenda(i+1,in.nextInt()/duraçao);
	}
	Arrays.sort(encomendas);

	for(int i=0;i<encomendas.length;i++){
	    System.out.println(encomendas[i].x);
	}
    }
		
}
