import java.io.*;
import java.util.*;

class Ati implements Comparable<Ati>{
	public int ini;
	public int fim;
	public int tam;
	Ati(int i, int f){
	    ini=i;
	    fim=f;
	    tam=f-i;
	}
	@Override
	public int compareTo(Ati t){
	    if(ini < t.ini) return -1;
	    if(ini > t.ini) return +1;
	    if(tam > t.tam) return -1;
	    if(tam < t.tam) return +1;
	    return 0;
	}
}
public class Prob009{
    public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	int k=0,s=0;
	int m = in.nextInt();
	int n=in.nextInt();
	Ati v[]=new Ati[n];
	for(int i=0;i<n;i++)
	    v[i]=new Ati(in.nextInt(), in.nextInt());
	Arrays.sort(v);
	int i=0, j=0;
	/*for(i=0;i<n;i++)
	  System.out.printf("%d %d\n",v[i].ini, v[i].fim);*/

	s++;
	k=v[0].fim;
	//System.out.printf("%d %d\n",v[0].ini, v[0].fim);
	while(i<n){
	    
	    //System.out.printf("%d %d\n",v[i].ini, v[i].fim);
	    if(v[i].fim >= m){
		//System.out.println(i);
		break;
	    }
	    k=v[i].fim;
	    int max=0;
	    j=i+1;	    
	    int max_j=j;
	    while(j<n && v[j].ini <= k){
		if(v[j].tam >= max) {
		    max=v[j].tam;
		    max_j=j;
		    // System.out.printf("%d  / %d %d\n",s,v[j].ini, v[j].fim);
			    
		}
		j++;
	    }
	   
	    i = max_j;
	    //System.out.printf("%d %d\n",v[i].ini, v[i].fim);
	
	    k=v[i].fim;
	    s++;
	}
	System.out.println(s);
	//System.out.println(k);
    }
}


