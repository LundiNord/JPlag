import java.util.Arrays;
import java.util.Scanner;

public class File {

    private static int BinarySearchIndex(int[] soma ,int key, int high) //double preciso?
    {

	int low = 0;
	int mid=0;

	if (high < 0)
	    throw new IllegalArgumentException("The array cannot be empty");

	while (low < high) {
	    mid = low + (high-low) / 2;
	    if (key == soma[mid])
		return mid;
	    else if (key < soma[mid])
		high=mid;
	    else
		low=mid+1;
	}
	mid=low;	    
	if(mid > 0){
	    if(key - soma[mid-1] < soma[mid] - key)
		return mid-1;
	    if(key - soma[mid-1] == soma[mid] - key){
		System.out.print(soma[mid-1] + " " );
		return mid;
	    }
	    if(soma[mid]-key < key - soma[mid-1])
		return mid;
	}
	return mid;    	  
	//		//se nao fosse necessario repeats bastava return a[high]; e nao era necessario passar ncombos
	//				for(int i1 =0; i1<ncombos;i1++) {
	//					if(a[high]==(key-(a[high]-key) return key-(a[high]-key)a[high]);
	//				}
		
		      
    }



    //		int lo = 0;
    //		int hi = a.length - 1;
    //
    //		int index = 0;
    //		while (hi > lo )
    //		{
    //			index = (lo + hi) / 2;
    //			double atIndex = a[index];
    //			if (key > atIndex)
    //			{
    //				lo = index + 1;
    //			}
    //			else
    //			{
    //				hi = index;
    //			}
    //		}
    ////		return index;
    //	}


    //	public static int[] collapse(int[] toSum) {
    //	    int[] sumPairs = new int[toSum.length / 2 + toSum.length % 2]; 
    //	    for (int i = 0, j = 0; i < toSum.length - 1; i+=2, j++) {
    //	        sumPairs[j] = toSum[i] + toSum[i + 1];
    //	    }
    //	    if (toSum.length % 2 == 1) {
    //	        sumPairs[sumPairs.length - 1] = toSum[toSum.length - 1];
    //	    }
    //	    return sumPairs;
    //	}  

    public static void main(String[] args) {

	Scanner in = new Scanner (System.in);
	int n = in.nextInt();
	int v[] = new int[n];

	int ctr=0;                            //
	for (int a=0;a<n;a++) {         
	    v[a]=in.nextInt();
	    ctr +=a;                          //
	}
	int aux=0;	
	int p =in.nextInt();
	int v2[] = new int[p];

	for (int b=0;b<p;b++)
	    v2[b]=in.nextInt();
		
	int ncombos = (n*(n-1))/2;
	int[] somas = new int[ctr];

	
	int temp = 0;

	for (int i = 0; i < n; i++) { 
	    for (int j = 0; j < i; j++) { 
		somas[temp] = v[i] + v[j];
		temp++;
	    }

	}

			
		
		
	//detectar simetricos


	//		int q = 0;
	//		while(true) {
	//			
	//			if(somas[q]==0) break;
	//			
	//			System.out.println(somas[q]);
	//			
	//			q++;
	//		}
	//
	//		int [] somanovos = new int[q];
	//		
	//		for(int i = 0; i<q; i++) {
	//			somanovos[i] = somas[i];
	//		}
	//		
	//		//		for (int i=0;i<n-1;i++)
	//		//		{
	//		//			for (int j=i+1;j<n-1;j++)
	//		//			{
	//		//				somas[aux]=v[i]+v[j];
	//		//				aux++;
	//		//			}	
	//		//		}
	//		


	Arrays.sort(somas);	 

	int abc;

	for (int i=0;i<p;i++)
	    {
		int key=v2[i];
		//			System.out.println("a soma mais proxima de " +key + " " + BinarySearchIndex(somas,key));
		abc = (BinarySearchIndex(somas,key,ctr-1));
		if(abc != -1){
		    System.out.println(somas[abc]);
		} else {
		    System.out.println(abc);
		}
		
	    }	
	in.close();
	//for (int t=0;t<(n*(n-1))/2;t++)
	//System.out.println(somas[t]);
    }




}


