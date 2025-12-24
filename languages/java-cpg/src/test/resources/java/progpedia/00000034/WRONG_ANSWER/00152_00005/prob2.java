import java.util.*;
import java.io.*;

class prob2{
	public static void main(String [] args){
		int v[] = new int[10];
		
		Scanner input = new Scanner(System.in);
		
		for(int i=0; i<10; i++){
			v[i] = input.nextInt();
		}
		
		mergeSort(v);
		
		for(int i=0; i<10; i++){
			System.out.printf(v[i] + " ");
		}
    }
	
	static void mergeSort(int[] A){
        if(A.length>1) {
            int q = A.length/2;

            int leftArray[] = Arrays.copyOfRange(A, 0, q);
            int rightArray[] = Arrays.copyOfRange(A,q,A.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(A,leftArray,rightArray);
        }
    }

    static void merge(int[] a, int[] l, int[] r){
        int totElem = l.length + r.length;
        
        int i = 0, li = 0, ri = 0;
        
        while(i<totElem) {
            if((li<l.length) && (ri<r.length)){
                if(l[li]<r[ri]){
                    a[i] = l[li];
                    i++;
                    li++;
                }
                else{
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            }
            else{
                if(li>=l.length){
                    while(ri<r.length){
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                
                if(ri>=r.length){
                    while(li<l.length){
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
    }
}