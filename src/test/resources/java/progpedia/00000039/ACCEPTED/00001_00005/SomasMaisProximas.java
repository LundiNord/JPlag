import java.util.*;
import java.io.*;

public class SomasMaisProximas {
	
	public static int valorCondicional = 0;
	public static int pesquisar(int[] v, int key){
		valorCondicional = 0;
		int middle, end = v.length, start = 0;
		if(key < v[start]){
			return v[start];
		}
		else if(key >= v[end-1]){
			return v[end-1];
		}
		while(start <= end){
			middle = (start+end)/2;
			if(key == v[middle]){
				return v[middle];
			}
			else if(key < v[middle] && key > v[middle-1]){
				if(v[middle]-key < key-v[middle-1]){
					return v[middle];
				}
				else if(v[middle]-key > key-v[middle-1]){
					return v[middle-1];
				}
				else {
					valorCondicional = v[middle];
					return v[middle-1];
				}
			}
			else if(key < v[middle]){
				end = middle-1;
			}
			else {
				start = middle+1;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int nPerguntas = Integer.parseInt(br.readLine());
		String perguntas[] = new String[nPerguntas];
		perguntas = br.readLine().split(" ");
		int nQueries = Integer.parseInt(br.readLine());
		String queries[] = new String[nQueries];
		queries = br.readLine().split(" ");
		int somas[] = new int[nPerguntas*((nPerguntas-1))/2];
		int n = 0;
		for(int i = 0; i < nPerguntas-1; i++){
			for(int j = i+1; j < nPerguntas; j++){
				somas[n++] = Integer.parseInt(perguntas[i]) + Integer.parseInt(perguntas[j]);
			}
		}
		Arrays.sort(somas);
		for(int i = 0; i < nQueries; i++){
			int k = pesquisar(somas, Integer.parseInt(queries[i]));			
			if(valorCondicional != 0){
				System.out.println(k + " " + valorCondicional);
			}
			else {
				System.out.println(k);
			}
		}
	}
}