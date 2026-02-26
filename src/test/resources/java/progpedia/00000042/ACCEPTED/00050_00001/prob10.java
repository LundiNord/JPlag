import java.util.*;
import java.io.*;


class segmentos{
    int inicio, fim;

    segmentos ( int a, int b){
	inicio = a;
	fim = b;
    }
}

class prob10 {
    public static void main (String args[]){
	int m;
	int n;
	Scanner input= new Scanner(System.in);
	m= input.nextInt();
	input.nextLine();
	n=input.nextInt();
	input.nextLine();
	segmentos a[]= new segmentos[n];
	for(int i=0;i<n;i++){
	    int a2 = input.nextInt();
	   
	    int b = input.nextInt();
	    input.nextLine();
	    a[i] = new segmentos(a2,b);
	}
	Arrays.sort(a, new Comparator<segmentos>() {
		public int compare(segmentos s1, segmentos s2) {
		    if (Math.abs(s1.inicio - s2.inicio) > 0) return s1.inicio - s2.inicio ;
		    return s1.fim - s2.inicio;
		}
	    });
	/*	for (int i = 0; i < n; i++) {
	    System.out.println(a[i].inicio + " " + a[i].fim);
	    }*/
	int end =0;
	int cont=0;
	while(end <m){
	    int max=0;
	    for (int i =0;i<n;i++){
		if(a[i].inicio <= end)
		    if (a[i].fim > max)
			max=a[i].fim;
	    }
	    cont++;
	    end=max;
	}
	System.out.println(cont);
    }
}
 
 



