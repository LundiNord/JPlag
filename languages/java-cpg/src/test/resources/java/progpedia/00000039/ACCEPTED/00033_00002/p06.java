import java.util.*;
import java.io.*;

public class p06 {
//Global var
static int[] sums;

	static void sumList(int [] list ){
		int pos = 0;
		for(int n = 0; n<list.length;n++){
			for(int k = n+1; k<list.length; k++){
				sums[pos]= list[n]+list[k];
				pos++;
			}
		}
		Arrays.sort(sums);
	
	}

	static void binarySearch(int low, int high, int number){

        while(low < high) {
        	//System.out.println(low + " " + high);
            int middle = low + (high-low)/ 2;

            if(sums[middle] < number) {
            low = middle + 1;
            }
            if(sums[middle] > number) {
            high = middle ;
            }
            if(sums[middle] == number) {
            	System.out.println(number);
            	return;
            }
        }

        int h = sums.length-1;
      	if(number > sums[h]){
      		System.out.println(sums[h]);
      		return;
      	}

        int v1 = Math.abs(sums[low]-number);
        int v2 = Integer.MAX_VALUE;
        if (low>0) 
        	v2 = Math.abs(sums[low-1]-number);

        //System.out.println("------");
        if(v1==v2){
        	//System.out.println("enter");
        	System.out.println(sums[low-1] + " " + sums[low]);
        }
        else{
        	if(v1<v2){
        	System.out.println(sums[low]);}
        	else{
        	System.out.println(sums[low-1]);}
        }
	}

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int[] list = new int [n];
		for(int i = 0; i<n ; i++)
			 list[i] = input.nextInt();

		int nq = input.nextInt();
		int[] quest = new int[nq];
		for(int i = 0; i<nq ; i++)
			quest[i] = input.nextInt();

		sums = new int[(n*(n-1))/2];
		sumList(list);
		for(int i = 0; i<quest.length;i++){
			binarySearch(0,sums.length,quest[i]);
	}
  }

}