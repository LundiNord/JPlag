import java.util.Scanner;
import java.util.Arrays;

public class somas {
    public static int bsearch (int [] somas, int low, int high, int val){
	while (high>low){
	    int middle = low + (high-low)/2;
	    if (somas[middle]>=val){
		high = middle;
	    }
	    else
		low = middle+1;
	}
	return low;
    }
    public static void solve (int [] somas, int res, int val){
	if (res!=0)
	    if (Math.abs(somas[res]-val)==Math.abs(somas[res-1]-val))
		System.out.print (somas[res-1] + " " + somas[res]);
	    else if (Math.abs(somas[res]-val)<Math.abs(somas[res-1]-val))
		System.out.print (somas[res]);
	    else
		System.out.print (somas[res-1]);
	else
	    System.out.print (somas[res]);
    }
    public static int [] somas (int [] v){
	int size = ((v.length*v.length)-v.length)/2;
	int [] somas = new int [size];
	int k = 0;
	for (int i = 0; i < v.length; i++){
	    for (int j=i+1; j < v.length; j++){
		somas [k] = v[i]+v[j];
		k++;
	    }
	}
	Arrays.sort(somas);
	return somas;
    }
    public static void main (String [] args){
	Scanner stdin = new Scanner (System.in);
	int n = stdin.nextInt();
	int [] v = new int [n];
	for (int i = 0; i < n; i++)
	    v[i] = stdin.nextInt();
	int q = stdin.nextInt();
	int [] quest = new int [q];
	int [] sum = somas(v);
	for (int j = 0; j < q; j++){
	    quest [j] = stdin.nextInt();
	    int res = bsearch (sum,0,sum.length-1,quest[j]);
	    solve (sum, res, quest[j]);  
	    System.out.println();
	}
    }
}
