import java.io.*;
import java.util.*;

class DAAtres{
    public static void main(String args[]) {
	Scanner input=new Scanner(System.in);
	int x=input.nextInt();
	int vec[]=new int[x];
	for(int i =0;i<x;i++)
	    vec[i]=input.nextInt();
	int sol=findMaxSubArray(vec);
	System.out.println(sol);
    }


    public static int findMaxSubArray(int[] vec){

        int cumulativeSum= vec[0];
        int maxSum = cumulativeSum;
	
        for (int currentIndex = 1; currentIndex < vec.length; currentIndex++) {

            int x = vec[currentIndex];

	    if (cumulativeSum<0)
            	cumulativeSum=0;
            
            if(cumulativeSum+x >maxSum) {
                maxSum = cumulativeSum +x;
	    }

	    cumulativeSum+=x;
	    
        }

        return maxSum;

    }

}
