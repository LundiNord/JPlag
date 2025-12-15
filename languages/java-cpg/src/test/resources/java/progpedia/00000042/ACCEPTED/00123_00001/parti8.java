import java.util.*;
public class parti8 {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		parti8[] todas = new parti8[n];

		for(int i=0; i<n; i++){
			parti8 bino = new parti8(in.nextInt(),in.nextInt());
			todas[i] = bino;
		}
		int lastb=0;
		int progresso=0; int parti8u=0;
		int max=0; int index = 0;

		while(progresso<m){
			for(int i=0; i<n; i++){
				if(todas[i].begin<=lastb && todas[i].end>max){
					max=todas[i].end;
					index=i;
				}
			}
			progresso=max;
			lastb=todas[index].end;
			parti8u++;
		}

		System.out.println(parti8u);

	}

	private int begin;
	private int end;
	public parti8(int begin, int end){
		this.begin = begin;
		this.end = end;

	}
}


