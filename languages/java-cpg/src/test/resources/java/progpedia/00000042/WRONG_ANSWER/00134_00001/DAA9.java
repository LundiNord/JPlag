import java.util.*;

class Seg implements Comparable<Seg>{
    public int l,r;
    Seg(int l, int r){
	this.l=l;
	this.r=r;
    }
    @Override
    public int compareTo(Seg c){
	if(l<c.l) return -1;
	if(l>c.l) return +1;
	return 0;
    }
}

class DAA9{
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);
	int m = in.nextInt();
	int n = in.nextInt();
	int contador = 0;
	int max = 0;
	int rec = -999999999;
	Seg v[] = new Seg[n];
	
	for(int i=0; i<n; i++)
	    v[i]=new Seg(in.nextInt(),in.nextInt());
	
	Arrays.sort(v);
	
	Seg res = new Seg(0,0);

	for(int i=0; i<n; i++){
	    if(v[i].l==rec)
		contador--;
	    /*System.out.println(v[i].l+" "+v[i].r+" ");*/
	    if(v[i].l<=res.r){
		if(max<v[i].r){
		    max=v[i].r;
		    res.r=max;
		    rec=v[i].l;
		    contador++;
		}
	    }
	}
	System.out.println(contador);
    }
}
