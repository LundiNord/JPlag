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
			if(min[1]!=-1 && min[0]!=min[1])
				System.out.println(min[0]+" "+min[1]);
			else
				System.out.println(min[0]);
				
		}
	}

	/*pensar melhor sobre isto*/


	public static int[] binary(int array[], int low, int high, int key){
		int difference = Integer.MAX_VALUE;
		int middle;

		int min[]={-1,-1};
		while (low <=high){
			middle = low+(high-low)/2;

			if (Math.abs(array[middle]-key) < difference && array[middle]!=0) {
				difference = Math.abs(array[middle] - key);
				
				min[0] = array[middle];
				if(middle+1<array.length && Math.abs(array[middle+1]-key)== difference){
					min[1] = array[middle+1];
				}

			}
			if (key - array[middle] < 0) {
				high = middle-1;
			}
			else{
				low = middle+1;
			}
		}
		return min;

	}
}
