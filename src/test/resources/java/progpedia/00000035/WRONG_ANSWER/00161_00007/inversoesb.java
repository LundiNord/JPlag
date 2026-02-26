import java.util.*;

public class inversoesb {
	public static int[] v;
	public static int n;
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		v = new int[n];
		for(int i=0; i<n; i++)
			v[i]=stdin.nextInt();
		
		System.out.println(mergesort(v,0,n-1));
	}
	
	public static int mergesort(int v[], int start, int end){
		int middle;
		int qi=0;
		if(start<end){
			middle=(start+end)/2;				//middle = start + (end-start)/2
			qi+=mergesort(v, start, middle);
			qi+=mergesort(v, middle+1, end);
			qi+=merge(v, start, middle, end);
		}
		return qi;
	}
	
	public static int merge(int v[], int start, int middle, int end){
		int i, p1, p2,qi;
		qi=0;
		int aux[] = new int[end+1];
		
		p1=start;
		p2=middle+1;
		i = start;
		while (p1<=middle && p2<=end) {
			if(v[p1] <= v[p2]) aux[i++] = v[p1++];
			else {aux[i++] = v[p2++]; qi++;} 
		} 
		
		while(p1<=middle) {aux[i++] = v[p1++]; qi++;}
		
		while(p2<=end) {aux[i++] = v[p2++]; qi--;}
		
		for(i=start; i<end; i++) v[i] = aux[i];
		return qi;
	}

}