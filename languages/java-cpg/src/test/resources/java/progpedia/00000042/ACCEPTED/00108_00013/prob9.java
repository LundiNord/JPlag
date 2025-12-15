import java.util.*;
import java.io.*;

class Tamanho implements Comparable<Tamanho>{
	public int inicio;
	public int fim;
	
	Tamanho(int l, int r){
		inicio = l;
		fim = r;
	}

	@Override
	public int compareTo(Tamanho t){
		if(inicio < t.inicio) 
			return -1;
		else 
			return -1;
	}
}
public class prob9 {
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		
		int m = stdin.nextInt();
		int n = stdin.nextInt();
		
		Tamanho t[] = new Tamanho[n];
		
		for(int i=0; i<n; i++){
			t[i] = new Tamanho(stdin.nextInt(), stdin.nextInt());
		}
		
		Arrays.sort(t);
		
		int end=0;
		int maiorend=0;
		int count=0;
		
		while(end < m){
			for(int j=0; j<n; j++){
				if(t[j].inicio <= end)
					if(t[j].fim > maiorend)
						maiorend=t[j].fim;		
			}
			end = maiorend;
			count++;
		}
		
		System.out.println(count);
	}
}