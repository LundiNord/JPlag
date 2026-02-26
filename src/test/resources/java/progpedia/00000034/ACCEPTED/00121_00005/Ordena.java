import java.util.*;

class Ordena{


    public static void main(String args[]){

	Scanner input = new Scanner(System.in);
        int n = input.nextInt();	
        long[] list = new long[n];
	for(int i=0; i<n; i++){
	    list[i] = input.nextInt();
	}

	Arrays.sort(list);

	for(int i=0; i<n; i++){
	    if(i == n-1){
		System.out.print(list[i]);
	    }else{
		System.out.print(list[i]+" ");
	    }
	}
	System.out.println();
	
    }



}
