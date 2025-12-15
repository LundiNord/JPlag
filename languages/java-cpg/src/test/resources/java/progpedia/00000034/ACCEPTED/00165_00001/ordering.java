import java.util.Arrays;
import java.util.Scanner;

public class ordering {

    public static void main (String args[]) {
	int n,i=0;
	Scanner input = new Scanner (System.in);

	n = input.nextInt();
	int[] toSort = new int[n];
	while (i<n){
	    toSort[i] = input.nextInt();
	    i++;
	}
	Arrays.sort(toSort);
	for (i=0; i<n-1; i++) System.out.print(toSort[i] + " ");
	System.out.println(toSort[n-1]);
    }
}
