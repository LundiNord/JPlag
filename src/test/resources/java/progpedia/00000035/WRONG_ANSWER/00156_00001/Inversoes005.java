import java.io.*;
import java.util.*;

class Inversoes005{

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		int cont = 0;
		int N = in.nextInt();
		int v[] = new int[N];
		for(int i=0;i<N;i++)
			v[i]=in.nextInt();

		for(int i=0;i<N;i++){
			for(int j=1;j<N-i;j++){
				if(v[i]>=v[j])
					cont++;
			}

		}

		System.out.println(cont);
	}
}