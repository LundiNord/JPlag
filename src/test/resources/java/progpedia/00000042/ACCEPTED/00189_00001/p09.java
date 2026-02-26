import java.util.*;
import java.io.*;

//Exercicio feito em conjunto pelos alunos
// Carlos Bernardes up201303743
// Célio Rodrigues up201303171

class p09{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int intervalo = in.nextInt();
        int n = in.nextInt(); 

        segmento[] segs = new segmento[n];

        for (int i = 0; i < n; i++) 
            segs[i] = new segmento(in.nextInt(), in.nextInt());
	
		Arrays.sort(segs);

		//for(int i=0; i < segs.length; i++)
		//	System.out.println(segs[i].left+","+segs[i].right);
	
		int fim = 0;
        int fim_ate_agr = 0;
        int count = 0;

        // A ideia passa por alocar por ordem ascendente de left(i) e em caso de empate
		//escolher o maior; tentar cobrir o maximo possível com um segmento
        
        while ( fim < intervalo) {

            for (int i = 0; i < n; i++) {
                if (segs[i].left <= fim) { // se "couber" dentro do intervalo então 
                    if ( segs[i].right > fim_ate_agr )
                        fim_ate_agr = segs[i].right;
                }
            }
            
            fim = fim_ate_agr;
            count++;
        }

        System.out.println(count);
	}
}

class segmento implements Comparable<segmento>{
	int left, right;

	segmento(int left, int right){
		this.left=left;
		this.right=right;
	}
	
	@Override
	public int compareTo(segmento seg){
		if( left < seg.left)
			return -1;
		else
			return 1;
	}
}