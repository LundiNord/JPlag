import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Segment{
	int start;
	int end;
	
	public Segment(int start, int end){
		this.start=start;
		this.end=end;
	}
	
}

public class coberturaminima10 {
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		int seg, n;
		seg = stdin.nextInt();
		n = stdin.nextInt();
		Segment vec[] = new Segment[n];
		for(int i=0;i<n;i++){
			vec[i] = new Segment(stdin.nextInt(), stdin.nextInt());
		}
		
		//ordenação start
		Arrays.sort(vec, new Comparator<Segment>() {
			public int compare(Segment p1, Segment p2) {
				if (p1.start < p2.start) {
					return -1;
				} else
					return 1;

			}
		});
		int end =0;
		int max=0;
		int total=0;
		while(end < seg){
			for(int i=0;i<n;i++){
				if(vec[i].start <= end){
					int aux=vec[i].end;
					if(max < aux)
						max=aux;
				}
			}
			
			if(max > end)
			{
				end=max;
				total++;
			}
		}
		
		System.out.println(total);
	}
}
