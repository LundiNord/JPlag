import java.io.*;
import java.util.*;

class Prob10{
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	int m = stdin.nextInt();
	int number_segments = stdin.nextInt();
	//System.out.println("number_segments = " + number_segments);
	Ponto[] pontos = new Ponto[number_segments];
	for(int i = 0; i < number_segments; i++){
	    pontos[i] = new Ponto(stdin.nextInt(), stdin.nextInt());
	}
	Arrays.sort(pontos); // implementado na classe Ponto
	int fim = 0;
	int pos = 0;
	int segments_needed = 0;
	while(fim < m){
	    int later_end = fim;
	    pos=0;
	    while (pos<number_segments && pontos[pos].x <= fim) {
		if(pontos[pos].y > later_end){
		    later_end = pontos[pos].y;
		}
		pos++;
	    }
	    fim = later_end;
	    segments_needed++;
	}
	System.out.println(segments_needed);
    }
}

class Ponto implements Comparable<Ponto> {
    int x;
    int y;

    Ponto(int a, int b){
	x = a;
	y = b;
    }
    // Definir como comparar dois elementos da classe Ponto
    // compareTo e uma funcao que compara objecto com outro objecto "a"
    // Esta funcao deve devolver:
    //  - numero < 0 se objecto for menor que objecto "n"
    //  - numero > 0 se objecto for maior que objecto "n"
    //  - zero, se objecto for igual ao objecto "n"
    @Override
    public int compareTo(Ponto a){
	return this.x - a.x;
    }
	
}
