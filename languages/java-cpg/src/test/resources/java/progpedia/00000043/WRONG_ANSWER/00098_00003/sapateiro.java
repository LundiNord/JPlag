import java.util.*;

class encomenda implements Comparable<encomenda> {
	int d;	//duração
	int m; //multa
	float racio;
	int no;
	encomenda(int a, int b, int c){
		d=a;
		m=b;
		racio = b/a;
		no = c;
	}
	
	public int compareTo(encomenda n){
		if(racio>n.racio) return -1;
		else if(racio<n.racio) return 1;
		else {
			if(no>n.no) return -1;
			else return 1;
		}
	}
}

class sapateiro {
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt(); //numero de encomendas a processar
		encomenda[] e = new encomenda[n];
		for(int i=0; i<n; i++) e[i]=new encomenda(stdin.nextInt(),stdin.nextInt(),i+1);
		Arrays.sort(e);
		for(int i=0; i<n-1; i++) System.out.print(e[i].no+" ");
		System.out.println(e[n-1].no);
	}
}