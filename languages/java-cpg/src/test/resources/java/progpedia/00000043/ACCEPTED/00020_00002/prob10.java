import java.util.*;

class obj implements Comparable<obj>{
	int numero;
	float ratio;
	
	public obj(int n, float r){
		numero = n;
		ratio = r;
	}
	
	//@Override
    public int compareTo(obj n) {
		if(ratio == n.ratio)
			if(numero < n.numero)
				return -1;
			else
				return 1;
				
		else if (ratio > n.ratio)
				return -1;
			else 
				return 1;
	}
}

public class prob10 {
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		obj enco[] = new obj[n];
		
		for(int i=0; i<n; i++){
			int num = i+1;
			float dur = in.nextInt();
			float multa = in.nextInt();
			float ratio = multa/dur;
			
			enco[i] = new obj(num, ratio);
		}
		
		Arrays.sort(enco);
		/*
		for(int i=0; i<n; i++){
			System.out.print(enco[i].numero + " ");
		}
		System.out.println();
		*/
		
		for(int i=0; i<n-1; i++){
			if(i != n)
				System.out.print(enco[i].numero + " ");
		}
		System.out.print(enco[n-1].numero);
		
		System.out.println();
        
	}
}