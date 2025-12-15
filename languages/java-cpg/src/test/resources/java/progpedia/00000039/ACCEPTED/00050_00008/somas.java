import java.util.*;
import java.lang.Math.*;
class somas{
    static public int fim=0;
    static public void main (String[] args){
	Scanner input = new Scanner (System.in);
	
	int n = input.nextInt();
	int[] array = new int[n];
	for (int i=0; i<n; i++)
	    array[i]=input.nextInt();
	for (int i=1; i<n; i++){
	    fim+=i;
	}
	fim+=2;
	int soma[] = new int[fim];

	soma[0] = -2000000;
	soma[1] = 2000000;
	
	int c = 2;
	for (int i=0; i<n; i++){
	    for (int j=i+1; j<n; j++){
		soma[c++] = array[i] + array[j];
	    }
	}

	
	Arrays.sort(soma);

	/*for (int i=0; i<fim; i++)
	    System.out.print(soma[i] + " ");
	    System.out.println();	    */
	
	int p = input.nextInt();
	for (int i=0; i<p; i++)
	    pesq(soma,input.nextInt(),0,fim-1);
    }
	
    static public void pesq(int[] soma, int per, int start, int end){

	if (start<end){
	    int middle = (start + end) / 2;
	    if (soma[middle] >= per) pesq(soma,per,start,middle);
	    else pesq (soma,per,middle+1,end);
	} else {
	    if (per-soma[start-1] == soma[start]-per)
		System.out.println(soma[start-1] + " " + soma[start]);
	    else if (per-soma[start-1] < soma[start]-per)
		System.out.println(soma[start-1]);
	    else
		System.out.println(soma[start]);
	}
    }

}
