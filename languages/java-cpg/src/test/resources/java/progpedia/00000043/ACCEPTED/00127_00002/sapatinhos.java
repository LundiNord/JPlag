import java.util.*;
class sapatinhos{
    public static void main (String args[]){
	Scanner input = new Scanner(System.in);
	int N = input.nextInt();
	sapato array[] = new sapato[N];
	for (int i=0; i<N; i++){
	    array[i] = new sapato (input.nextInt(),input.nextInt(),i+1);
	}
	Arrays.sort(array);
	for (int i=0; i<N-1; i++)
	    System.out.print(array[i].pos + " ");
	System.out.println(array[N-1].pos);
    }
}

class sapato implements Comparable<sapato> {
    int tempo,multa,pos;
    double ratio;
    sapato (int tempo, int multa, int pos){
	this.tempo = tempo;
	this.multa = multa;
	this.pos = pos;
	ratio = (double)tempo/multa;
    }

    public int compareTo(sapato s2){
	if (this.ratio > s2.ratio)
	    return 1;
	if (this.ratio < s2.ratio)
	    return -1;
	return 0;
    }
}
