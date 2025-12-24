import java.util.*;

class Segmento{
	int inicio;
	int fim;

	Segmento(int i, int f){
		inicio = i;
		fim = f;
	}
}

public class CoberturaMinima{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int caminho = sc.nextInt();
		int nCasos = sc.nextInt();
		Segmento[] segmentos = new Segmento[nCasos];
		for(int i = 0; i < nCasos; i++){
			segmentos[i] = new Segmento(sc.nextInt(), sc.nextInt());
		}
		Arrays.sort(segmentos, new Comparator<Segmento>(){
			public int compare(Segmento s1, Segmento s2){
				if(s1.inicio > s2.inicio){
					return 1;
				}
				else if(s1.inicio == s2.inicio){
					return 0;
				}
				else {
					return -1;
				}
			}
		});
		int end = 0;
		int i = 0;
		int count = 0;
		int tempEnd = 0;
		while(i < segmentos.length && end < caminho){
			while(i < segmentos.length && segmentos[i].inicio <= end){
				if(segmentos[i].fim > tempEnd){
					//System.out.print(segmentos[i].inicio+" ");
					tempEnd = segmentos[i].fim;
				}
				i++;
			}
			end = tempEnd;
			count++;
		}
		System.out.println(count);
	}
}