import java.io.*;
import java.util.*;


class Prob10{
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	Par[] ratio = new Par[n];
	for(int i = 0; i < n; i ++){
	    int tempo = stdin.nextInt();
	    ratio[i] =  new Par(i + 1,stdin.nextFloat()/tempo);
	}
	//System.out.print("ratio[] = [");
	//for(int i = 0; i < n; i ++){
	//  System.out.print("(" + ratio[i].pos + "," + ratio[i].ratio +") | ");
	//}
	//System.out.println("]");
	Arrays.sort(ratio);

	for(int i = 0; i < n-1; i++){
	    System.out.print(ratio[i].pos + " ");
	}
	System.out.println(ratio[(n-1)].pos);
    
    }
}


class Par implements Comparable<Par> {
    int pos;
    float ratio;

    Par(int a, float ratio){
	this.pos = a;
	this.ratio = ratio;
    }

    // Definir como comparar dois elementos da classe Team
    // compareTo e uma funcao que compara um Par com outro Par "p"
    // Esta funcao deve devolver:
    //  - numero < 0 se objecto for menor que objecto "p"
    //  - numero > 0 se objecto for maior que objecto "p"
    //  - zero, se objecto for igual ao objecto "p" -> NAO APLICAVEL
    @Override
    public int compareTo(Par  p) {
	//System.out.println("Started");
	if(this.ratio == p.ratio)
	    return this.pos - p.pos;
	else
	    return (int) (p.ratio - this.ratio);
    }
}

