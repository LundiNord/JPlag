import java.util.*;
public class closeSum {

	
	
	public static class MyMergeSort{
		 private int[] array;
		 private int[] tempMergArr;
		 private int length;
		    
		public void sortArr(int inputArr[]) {
	        this.array = inputArr;
	        this.length = inputArr.length;
	        this.tempMergArr = new int[length];
	        mergeSort(0, length - 1);
	    }
		
		private void mergeSort(int low, int high) {
	         
	        if (low < high) {
	            int middle = low + (high - low) / 2;
	            
	            mergeSort(low, middle);
	            mergeSort(middle + 1, high);
	            
	            merge(low, middle, high);
	        }
	    }
		
		 private void merge(int low, int middle, int high) {
			 
		        for (int i = low; i <= high; i++) {
		            tempMergArr[i] = array[i];
		        }
		        int i = low;
		        int j = middle + 1;
		        int k = low;
		        while (i <= middle && j <= high) {
		            if (tempMergArr[i] <= tempMergArr[j]) {
		                array[k] = tempMergArr[i];
		                i++;
		            } else {
		                array[k] = tempMergArr[j];
		                j++;
		            }
		            k++;
		        }
		        while (i <= middle) {
		            array[k] = tempMergArr[i];
		            k++;
		            i++;
		        }
		 
		    }
	}
	
	public static int bsearch(int v[], int low, int high, int key){
		int middle=0;
		while(low<=high){
			middle=low+((high-low)/2);
			if(key==v[middle]) return v[middle];
			else if(key<v[middle]) high=middle-1;
			else low=middle+1;
			if(low==high) return v[low];
		}
		return -1;
	}
	
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int numbers[] = new int[n];
		int sum[]=new int[n*n];
		
		for(int i=0;i<n;i++){
			numbers[i]=sc.nextInt();
		}
		
		int aux=0;
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				sum[aux]=numbers[i]+numbers[j];
				aux++;
			}
		}
	
		//for(int i=0;i<sum.length;i++) if(sum[i]!=0) System.out.println(sum[i] +" ");
		
		MyMergeSort mms = new MyMergeSort();
		mms.sortArr(sum);
				
		
		int p=sc.nextInt();
		int questions[]= new int[p];
		
		for(int i=0;i<p;i++) questions[i]=sc.nextInt();
		sc.close();
		
		int z;
		for(int i=0;i<p;i++){
			z=bsearch(sum,0,sum.length-1,questions[i]);
			System.out.println(z + "\n");
		}
//		for(int i=0;i<sum.length;i++) if(sum[i]!=0) System.out.println(sum[i] +" ");
	}
	
}
