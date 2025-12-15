import java.util.*;

class Somas{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int nS = in.nextInt();

		int s[] = new int[nS];

		// int temp = 0;

		int soma[] = new int[nS*nS];

		for(int i=0; i<nS; i++){
			s[i] = in.nextInt();
		}

		int k=0;
		for(int i=0; i<nS; i++){
			for(int j=i; j<nS; j++){
				if(i!=j){
					// System.out.println(s[i]+s[j]);
					soma[k] = s[i]+s[j];
					k++;
				}
				
			}
		}

		Arrays.sort(soma);

		// System.out.println(Arrays.toString(soma));

		int nP = in.nextInt();

		for(int i=0; i<nP; i++){
			int p = in.nextInt();
			int min[] = binary(soma, 0, nS*nS - 1, p);

			Arrays.sort(min);

			compare(min, p);

			Arrays.sort(min);

			// if(min[0]!=-1 && min[1]!=-1 && min[0]!=min[1])
			// 	System.out.println(min[0]+" "+min[1]);
			// else if(min[0]==-1)
			// 	System.out.println(min[1]);
			// else{
			// 	System.out.println(min[0]);
			// }


			if(min[0]==-1 || min[0]==min[1] || min[0]==0){
				System.out.println(min[1]);
			}
			else{
				System.out.println(min[0]+" "+min[1]);
			}
				
		}
	}

	/*pensar melhor sobre isto*/


	// public static int[] binary(int array[], int low, int high, int key){
	// 	int difference = Integer.MAX_VALUE;
	// 	int middle;

	// 	int min[]={-1,-1};
	// 	while (low <=high){
	// 		middle = low+(high-low)/2;

	// 		if (Math.abs(array[middle]-key) < difference && array[middle]!=0) {
	// 			difference = Math.abs(array[middle] - key);
	// 			// System.out.println("valor atual" +array[middle] + " chave "+key);
				

	// 			min[0] = array[middle];

	// 			if(middle+1<array.length && Math.abs(array[middle+1]-key)== Math.abs(min[0]-key)){
	// 				min[1] = array[middle+1];
	// 			}
	// 			else if(Math.abs(array[middle-1]-key)== Math.abs(min[0]-key)){
	// 				min[1]=array[middle-1];
	// 			}
	// 			else{
	// 				min[1]=-1;
	// 			}
				

	// 		}
	// 		if (key - array[middle] < 0) {
	// 			high = middle-1;
	// 		}
	// 		else{
	// 			low = middle+1;
	// 		}
	// 	}
	// 	return min;

	// }


	public static int[] binary(int array[], int low, int high, int key){
		int result[]={-1,-1};
		while(low <= high){
			int middle = low + (high-low)/2;

			if(key == array[middle]){
				result[0] = array[middle];
				return result;
			}
			else if(key < array[middle]){
				high = middle -1;

				// System.out.println("high="+high);

				result[1]=array[high];
			}
			else{
				low = middle+1;

				// System.out.println("low ="+low);

				if(low < array.length)
					result[0]=array[low];
			}
		}
		return result;


	}

	public static void compare(int array[], int key){
		int test1 = Math.abs(array[0]-key);
		int test2 = Math.abs(array[1]-key);

		// System.out.println(array[0]+" ola");

		if (test2 < test1 && array[0]!=0){
			array[0]=-1;
		}
		else if(test1 < test2 && array[0]!=0){
			array[1]=-1;
		}
	}
}
