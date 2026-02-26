import java.util.*;
class cob{
    public static void main (String[] args){
	Scanner input = new Scanner(System.in);
	int M = input.nextInt();
	int N = input.nextInt();
	seg[] array = new seg[N];
	for (int i=0;i<N;i++){
	    array[i]=new seg(input.nextInt(),input.nextInt());
	}
	int fim=0;
	int fimp=0;
	int cont=0;
	while (fim<M){
	    for (int i=0;i<N;i++){
		if (array[i].start<=fim && array[i].end>fim && fimp<array[i].end)
		    fimp=array[i].end;
	    }
	    fim=fimp;
	    cont++;
	}
	System.out.println(cont);
    }
}

class seg {
    int start,end;
    seg (int s, int e){
	start=s;
	end=e;
    }
}
