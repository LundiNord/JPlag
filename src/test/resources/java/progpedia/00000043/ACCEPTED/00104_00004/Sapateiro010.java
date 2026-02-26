import java.util.*;
import java.io.*;

class Sapateiro010{

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Sapatos v[] = new Sapatos[n];
		for(int i=0; i<n; i++){
			int duracao = in.nextInt();
			v[i] = new Sapatos(i+1,in.nextFloat()/duracao);
		}
		Arrays.sort(v);
		for(int i=0; i<n-1; i++){
			System.out.println(v[i].pos + " ");
		}
		System.out.println(v[n-1].pos);

	}	
}


class Sapatos implements Comparable<Sapatos>{

	int pos;
	float racio;

	Sapatos(int a, float racio){
		this.pos = a;
		this.racio = racio;
	}

	public int compareTo(Sapatos a){

		if(this.racio == a.racio){
			if(this.pos < a.pos)
				return -1;
			else
				return 1;
		}
		else{
			if(a.racio > this.racio)
				return 1;
			else
				return -1;
		}
	}
}