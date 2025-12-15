import java.io.*;
import java.util.*;

class Proximas{
    public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
	
	int N = stdin.nextInt();
	int n[] = new int[N]; // n = numeros
	for(int i=0 ; i<N ; i++)	    
	    n[i] = stdin.nextInt();

	int P = stdin.nextInt();
	int p[] = new int[P]; // p = perguntas
	for(int i=0 ; i<P ; i++)
	    p[i] = stdin.nextInt();

	//guardar todas as somas de pares no array s (somas)
	int pos = 0; // posições no array s
	int size = N*(N-1)/2 ;
	int somas[] = new int[size];
	for(int i=0 ; i<N ; i++)
	    for(int j=i+1 ; j<N ; j++){
		somas[pos] = n[i] + n[j];
		pos++;
	    }
       
	Arrays.sort(somas);
	//System.out.println(Arrays.toString(somas));
	for(int i=0 ; i<P ; i++)
	    bsearch(somas, 0, size-1, p[i]);
	
    }
    public static void bsearch(int[] somas, int low, int high, int value){
	
	int middle=0;
	int flag = 1;
	int h1 = high; //"apontador" do tamanho do array
	
	while(low <= high){
	    middle = low + (high-low)  / 2;
	    if(value == somas[middle]){
		System.out.println(somas[middle]);
		flag = 0;
		break;
	    }
	    else if(value < somas[middle])
		high = middle - 1;
	    else
		low = middle + 1;
	}	
	if(flag == 1){
	    if(middle == 0){      // [middle, ...]
		
		int n1 = Math.abs(somas[middle]-value);
		int n2 = Math.abs(somas[middle+1]-value);
		if(n1 == n2) System.out.println(somas[middle] + " " + somas[middle+1]);
		else if(n1 < n2) System.out.println(somas[middle]);
		else System.out.println(somas[middle+1]);
	    }
	    else
		if(middle == h1){ // [..., middle]
		    
		    int n1 = Math.abs(somas[middle]-value);
		    int n2 = Math.abs(somas[middle-1]-value);
		    if(n1 == n2) System.out.println(somas[middle] + " " + somas[middle-1]);
		    else if(n1 < n2) System.out.println(somas[middle]);
		    else System.out.println(somas[middle-1]);
		}
		else{             // [..., middle, ...]
		    
		    int n1 = Math.abs(somas[middle-1]-value); 
		    int n2 = Math.abs(somas[middle]-value);
		    int n3 = Math.abs(somas[middle+1]-value);
		    if(n1 < n3 && n1 < n2) System.out.println(somas[middle-1]);
		    if(n2 < n3 && n2 < n1) System.out.println(somas[middle]);
		    if(n3 < n1 && n3 < n2) System.out.println(somas[middle+1]);
		    if(n1 < n3 && n1 == n2) System.out.println(somas[middle-1] +" " + somas[middle]);
		    if(n1 < n2 && n1 == n3) System.out.println(somas[middle] +" " + somas[middle+1]);
		    if(n2 < n1 && n2 == n3) System.out.println(somas[middle-1] +" " + somas[middle]);
		}
	}
    }
}
