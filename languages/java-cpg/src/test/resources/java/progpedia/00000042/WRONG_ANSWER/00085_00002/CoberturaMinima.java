import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Ponto{
	int p1;
	int p2;
	
	public Ponto(int p1, int p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	
}

public class CoberturaMinima {
	static Scanner stdin = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = stdin.nextInt();
		int n = stdin.nextInt();
		Ponto[] pontos = new Ponto[n];
		int in;
		int fi;
		
		for(int i=0; i<n; i++){
			in = stdin.nextInt();
			fi = stdin.nextInt();
			pontos[i] = new Ponto(in, fi);
		}
		
		Arrays.sort(pontos, new Comparator<Ponto>() {

			@Override
			public int compare(Ponto o1, Ponto o2) {
				// TODO Auto-generated method stub
				int c = 0;
				if (o1.p1 < o2.p1)
					c = -1;
				if (o1.p1 > o2.p1)
					c = 1;
				return c;
			}
		});	
	
		int end = 0;
		int count = 0;
		int i = 0;
		while(end<m && i<n){
			if(pontos[i].p1>=end){
				end = pontos[i].p2;
				count++;
			}
			i++;
		}
		
		if(end>m)
			count++;
		
		
		System.out.println(count);
		
	}

}
