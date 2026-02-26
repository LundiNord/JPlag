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
	int soma[] = new int[fim];
	int c = 0;
	for (int i=0; i<n; i++){
	    for (int j=i+1; j<n; j++){
		soma[c++] = array[i] + array[j];
	    }
	}
	Arrays.sort(soma);
	int p = input.nextInt();
	for (int i=0; i<p; i++)
	    System.out.println(pesq(soma,input.nextInt(),0,fim-1));
    }
	
    static public int pesq(int[] soma, int per, int start, int end){
	//System.out.println("pesq " + start + " " + end);
	if (start<end){
	    int middle = (start + end) / 2;
	    if (soma[middle] == per)
		return per;
	    if (soma[middle] > per)
		return pesq(soma,per,start,middle-1);
	    else return pesq (soma,per,middle+1,end);
	}
	if (soma[start] == per)
	    return per;
	int min  = Math.abs (soma[start] - per);
	int min1 = -1;
	int min2 = -1;
		
	if (start>0)
	    min1 = Math.abs (soma[start-1] - per);
	if (end<fim-1)
	    min2 = Math.abs (soma[start+1] - per);
		
	if ((min < min1 || min1 == -1) && (min < min2 || min2 == -1))
	    return soma[start];
	if (min1 > -1 && min1 < min && (min1 < min2 || min2 == -1))
	    return soma[start-1];
	if (min2 > -1 && min2 < min && (min2 < min1 || min1 == -1))
	    return soma[start+1];
	if (min == min1){
	    System.out.print(soma[start-1] + " ");
	    return soma[start];
	}
	if (min == min2){
	    System.out.print(soma[start] + " ");
	    return soma[start+1];
	}
	return -1;
    }
}
