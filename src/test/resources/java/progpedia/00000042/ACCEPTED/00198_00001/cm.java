import java.io.*;
import java.util.*;

class cm{
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	int m = stdin.nextInt();
	int number_segments = stdin.nextInt();
	
	Ponto[] pontos = new Ponto[number_segments];
	for(int i = 0; i < number_segments; i++){
	    pontos[i] = new Ponto(stdin.nextInt(), stdin.nextInt());
	}
	Arrays.sort(pontos); 
	int fim = 0;
	int pos = 0;
	int segments_needed = 0;
	while(fim < m){
	    int later_end = fim;
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
 
    @Override
    public int compareTo(Ponto a){
	return this.x - a.x;
    }
	
}
