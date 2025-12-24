
import java.util.*;

public class ContagemOrdem {
		
	static int contagem = 0;
	
	public static int mergeSort(int [] a){
		
		if(a.length<=1)
			return a.length;
		
		//dividir array
		int[] first = new int[a.length/2];
		int[] second = new int[a.length - first.length];
		
		//copiar valores do vector a
		
		System.arraycopy(a, 0, first, 0, first.length);
		System.arraycopy(a, first.length, second, 0, second.length);
		
		//recursao do metodo
		contagem+=mergeSort(first);
		contagem+=mergeSort(second);
		
		//merge para resultado final
		contagem += merge(first, second, a);
		return contagem;
	}
	
	public static int merge(int[] first, int second[], int [] result){
		int a = 0; //posicao do vector first
		int b = 0; //posicao do vector second
		int i=0;   //posicao do vector end
		
		int end =0;
		
		while(a<first.length && b<second.length){
			if(first[a]<second[b]){
				end++;
				a++;
			}
			else{
				end++;
				b++;
			}
			i++;
		}
		
		System.arraycopy(first, a, result, i, first.length-a);
		System.arraycopy(second, b, result, i, second.length-b);
		
		return end;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int tam = in.nextInt();
		int[] aven = new int[tam];
		for(int i=0; i<tam; i++){
			aven[i] = in.nextInt();
		}
		contagem = mergeSort(aven);
		System.out.println(contagem);
		
		System.out.println();
	}
}
