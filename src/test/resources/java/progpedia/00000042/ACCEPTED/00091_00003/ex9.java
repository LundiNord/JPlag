import java.util.*;
import java.io.*;

class ex9{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int intervalo = in.nextInt();
        int n = in.nextInt(); 
        segmento[] segs = new segmento[n];
        for (int i = 0; i < n; i++) 
            segs[i] = new segmento(in.nextInt(), in.nextInt());
	Arrays.sort(segs);
	int fim = 0;
        int fim_ate_agr = 0;
        int count = 0;
        while ( fim<intervalo) {
            for (int i=0;i<n;i++){
                if (segs[i].left <= fim){
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

//Exercicio feito com a ajuda de:
// Carlos Bernardes
// Célio Rodrigues