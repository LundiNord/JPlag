import java.util.*;
import java.io.*;

class Cobertura009 {

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		Segmento[] v = new Segmento[n];
		for(int i=0;i<n;i++){
			v[i] = new Segmento(in.nextInt(), in.nextInt());
		}
		Arrays.sort(v);
		int contador= 0;
		int fin = 0;
		int pos = 0;
		while(fin < m){
			int end = fin;
			while(pos < n && v[pos].x <= fin ){
				if(v[pos].y > end){
					end = v[pos].y;
				}
				pos++;
			}
			fin = end;
			contador++;
		}
		System.out.println(contador);


	}
		

	
}


class Segmento implements Comparable<Segmento>{

	int x;
	int y;

	Segmento(int a, int b){
		x = a;
		y = b;
	}
	public int compareTo(Segmento a){
		return this.x - a.x;
	}
}


