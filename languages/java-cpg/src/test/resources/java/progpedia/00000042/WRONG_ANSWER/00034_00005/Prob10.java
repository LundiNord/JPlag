import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;



public class Prob10 {
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		int M,N;
		int[][] dists;
		int cnt=0;
		
		M = in.nextInt();
		N = in.nextInt();
		
		dists = new int[N][2];
		
		for(int i=0; i<N; i++){
			int ini, fim;
			ini = in.nextInt();
			fim = in.nextInt();
			
			dists[i][0] = ini;
			dists[i][1] = fim;
		}
		
		Arrays.sort(dists, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {

				int t1 = o1[0];
				int t2 = o2[0];
				int r1 = o1[1];
				int r2 = o2[1];
				
				if (t1 < t2) {
					return -1;
				} else if (t1 > t2) {
					return 1;
				}
				else if(t1 == t2){
					if (r1 > r2) {
						return -1;
					} else if (r1 < r2) {
						return 1;
					}
				}

				return 0;
			}
		});
		
	/*	for(int i=0; i < N; i++){
			System.out.println(dists[i][0]+ " " +dists[i][1]);
		}
		*/
		System.out.println(finddist(N,M,dists));
		
		
	}
	
	public static int finddist(int N, int M, int[][] dists){
		int line=dists[0][1];
		int cnt=1;
		int index=1;
		
		while(line<M && index<N){
			if(dists[index][0] == line){
				if(dists[index][1] > line){
					line = dists[index][1];		
					cnt++;
					//System.out.println("l:"+dists[index][0]+"c:"+dists[index][1]);
				}
			}
			index++;
		}
		
		
		return cnt;
	}
	

}
