import java.util.*;

public class P10{
	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		int area   = in.nextInt();
		int nCasos = in.nextInt();


		pontos linhas[] = new pontos[nCasos];
	
		for (int i=0; i<nCasos; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			linhas[i] = new pontos(a, b); 
		}

		Arrays.sort(linhas, new ComparadorInicio());

		// Escolher pontos

		int count=0,end =0,max=0;
		for (int i=0; i<nCasos; i++) {
			if (linhas[i].inicio <= end && linhas[i].fim > max) {
				max=linhas[i].fim;
			}
			else if(linhas[i].inicio>end){
				count++;
				end = max;
				i--;
			}
		}
		if (linhas[nCasos-1].inicio == end) {
			count++;
		}
		
		System.out.println(count);
	}
}


class pontos{
		int inicio, fim;

		pontos(int x ,int y){
			inicio = x;
			fim	   = y;
		}
}

class ComparadorInicio implements Comparator<pontos>{
	@Override
	public int compare(pontos a, pontos b){
		
		if (a.inicio == b.inicio)
			return a.fim < b.fim ? -1 : a.fim == b.fim ? 0: 1;
		else
			return a.inicio < b.inicio ? -1 : a.inicio == b.inicio ? 0: 1;
	}
}