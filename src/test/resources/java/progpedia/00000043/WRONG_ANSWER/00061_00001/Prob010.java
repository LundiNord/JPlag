import java.io.*;
import java.util.*;
class Sap implements Comparable<Sap>{
    public int dur;
    public int mul;
    public float tot;
    public int ind;
    Sap(int d, int m, int k){
	dur=d;
	mul=m;
	tot=((float)m)/d;
	ind=k;
    }
    @Override
    public int compareTo(Sap t){
	if(tot > t.tot) return -1;
	if(tot < t.tot) return +1;
	if(dur > t.dur) return +1;
	if(dur < t.dur) return -1;
	if(ind > t.ind) return -1;
	if(ind < t.ind) return +1;
	/*if(mul < t.mul) return -1;
	  if(mul > t.mul) return +1;*/
	return 0;
    }
}
public class Prob010{
    public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	Sap v[]=new Sap[n];
	for(int i=0;i<n;i++)
	    v[i]=new Sap(in.nextInt(), in.nextInt(),i+1);
	Arrays.sort(v);
	for(int i=0;i<n;i++){
	    //System.out.printf("%d -> %f\n",v[i].ind, v[i].tot);

	    System.out.print(v[i].ind + " ");
	    
	}
	System.out.println("");
	}
}
