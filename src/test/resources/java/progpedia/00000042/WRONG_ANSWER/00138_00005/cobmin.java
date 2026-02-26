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
		int startant=-1;
		while(end<m){
			if(seg[i].l!=startant && seg[i].r>end ) {end = seg[i].r;	startant = seg[i].l; contador++;}

			i++;
		}
		
		System.out.println(contador);
	}
}
