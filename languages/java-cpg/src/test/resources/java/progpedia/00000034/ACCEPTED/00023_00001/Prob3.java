import java.util.*;

class Prob3
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int n = input.nextInt();
	int[] list = new int[n];
		
	for (int i=0;i<n;i++) {
	    list[i]=input.nextInt();
	}
	
	Arrays.sort(list);
       
	for (int value : list) {
	    System.out.print(value + " ");
	}
	System.out.println();
    }
}
