import java.util.Scanner;
import java.util.Arrays;

public class somas {
    
	static int bsearch(int [] sum, int low, int high, int key){
		int middle = 0;
		while(low < high){
			middle = low + (high - low)/2;
			if(key == sum[middle])
				return middle;
			else if(key < sum[middle])
				high = middle;
			else low = middle + 1;
		}
		
		middle = low;
		if(middle > 0){
			if(key - sum[middle-1] < sum[middle] - key)
				return middle-1;
			if(key - sum[middle-1] == sum[middle] - key){
				System.out.print(sum[middle-1] + " " );
				return middle;
			}
			if(sum[middle]-key < key - sum[middle-1])
				return middle;
		}
		return middle;
		
	}

    public static void main (String args[]){
	int i=0,j,y=0;
	int sumsize = 0;
	Scanner input = new Scanner(System.in);
	
	int  size = input.nextInt();
	int[] set = new int[size];
	
	while (i<size) {
	    set[i] = input.nextInt(); 
	    sumsize+=i;
	    i++;
	}
        
        
	int[] sums = new int[sumsize];
	for (i=0; i<size; i++){
	    for (j=0; j<i; j++){
	        sums[y] = set[i]+set[j];
	        y++;
	    }
	}
	Arrays.sort(sums);

	int     qsize = input.nextInt();
	int[] queries = new int[qsize];
	i=0;
	while (i<qsize){
	    queries[i] = input.nextInt();
	    i++;
	}

	for(int k: queries){ 
	    int mas = bsearch(sums, 0, sumsize-1, k);
	    if(mas!=-1)
		System.out.println(sums[mas]);
	    else{
		System.out.println(mas);
	    }
	}
    }
}


