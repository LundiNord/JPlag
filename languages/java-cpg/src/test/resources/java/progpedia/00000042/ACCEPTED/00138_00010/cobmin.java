import java.util.*;
class segmento implements Comparable<segmento>{
	int l;
	int r;
	int visitado = 0;
	segmento(int a, int b){
		l=a;
		r=b;
	}
	
	@Override
	public int compareTo(segmento m){
		if(l<m.l) return -1;
		else if(l>m.l) return +1;
		else {
			if(r>m.r) return -1;
			else return +1;
		}
		
	}
}


class cobmin {
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		int m=stdin.nextInt();			//tamanho do segmento que queremos descobrir
		int n=stdin.nextInt();		//quantidade de segmentos a considerar
		segmento[] seg = new segmento[n];
		for(int i=0; i<n; i++) seg[i]= new segmento(stdin.nextInt(),stdin.nextInt());
		Arrays.sort(seg); 
		//for(int i=0; i<n; i++) System.out.println(seg[i].l + " " + seg[i].r);
		int end=0;
		int contador=0;
		int i=0;
	
		while(end<m){
			if( seg[i].l<=end) {
				int j=i;
				i++;
				while(i<n && seg[i].l<=end){
					if(seg[j].l==seg[i].l && i+1<n) i++;
					else if(seg[i].r>seg[j].r) {j=i;}
					else i++;
				}
				end = seg[j].r; 
				contador++;
			}
			else  i++;
		}
		
		System.out.println(contador);
	}
}
