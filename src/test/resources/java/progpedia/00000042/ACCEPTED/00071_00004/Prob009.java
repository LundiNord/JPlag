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
	int m = in.nextInt();
	int n=in.nextInt();
	Ati v[]=new Ati[n];
	for(int i=0;i<n;i++)
	    v[i]=new Ati(in.nextInt(), in.nextInt());
	Arrays.sort(v);
	int i=0,j=0;
	int s=1; 
	while(i<n){
	    int temp = v[i].fim;
	    j=i+1; 
	    if(v[i].fim >= m || j >= n) 
		break;
	    int max=0, pos_temp = 0;
	    while(j < n && v[j].ini <= temp){
		if(v[j].fim > max){
		    pos_temp = j;
		    max = v[j].fim;
		}
		j++;
	    }	
	    i = pos_temp; 
	    s++;
	    //System.out.printf("%d  -> %d %d\n", s, v[i].ini, v[i].fim);
	}
	System.out.println(s);
    }
}


