import java.util.*;
public class piramide {
	public static void main (String argv[]){
		Scanner in = new Scanner(System.in);
		int altura = in.nextInt();
		int roten = in.nextInt();
		int[][] piramide = new int[altura+1][altura+1];

		for(int i=1; i<altura+1; i++){
			piramide[1][i]=1;
		}

		for(int i=0; i<roten; i++){
			piramide[in.nextInt()][in.nextInt()]=-1;
		}
		piramide[altura][1]=1;
		for(int i=2; i<altura+1; i++){
			for(int e=1; e<altura-i+2; e++){
				if(piramide[i][e]!=-1){
					if(piramide[i-1][e] != -1 && piramide[i-1][e+1]!=-1){
						piramide[i][e]=piramide[i-1][e]+piramide[i-1][e+1];
					}
					if(piramide[i-1][e] == -1 && piramide[i-1][e+1]!=-1){
						piramide[i][e]=piramide[i-1][e+1];
					}
					if(piramide[i-1][e] != -1 && piramide[i-1][e+1]==-1){
						piramide[i][e]=piramide[i-1][e];
					}
				}
			}
		}

		System.out.println(piramide[altura][1]);
	}
}
