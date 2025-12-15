import java.util.*;
import java.io.*;
class encomendas{
    int nr;
    int di;
    int mu;
    float me;
    encomendas(int nr, int di, int mu){
	this.nr=nr;
	this.di=di;
	this.mu=mu;
	me=(float)di/mu;
    }
}
class prob11{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	encomendas S[]=new encomendas[N];
	for(int i=0;i<N;i++)
	    S[i]=new encomendas(i+1,sc.nextInt(),sc.nextInt());
	Arrays.sort(S, new Comparator<encomendas>(){
		public int compare(encomendas a, encomendas b){
		    if(a.me<b.me)
			return -1;
		    if(a.me>b.me)
			return 1;
		    else if(a.nr<b.nr)
			return -1;
		    return 1;
		}});
	System.out.print(S[0].nr);
	for(int k=1;k<N;k++)
	    System.out.print(" "+S[k].nr);
	System.out.println();
    }

}
