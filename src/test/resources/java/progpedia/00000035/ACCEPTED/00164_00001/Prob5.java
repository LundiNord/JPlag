import java.util.*;

class Prob5
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int n = input.nextInt();
	int[] v = new int[n];
	for (int i=0;i<n;i++) {
	    v[i]=input.nextInt();
	}
	System.out.println(getInversions(v,n));
    }
    public static int getInversions(int[] v, int n) {
	int count=0;
	for (int i=0;i<n;i++) {
	    for (int j=i+1;j<n;j++) {
		if (v[i]>v[j])
		    count++;
	    }
	}
	return count;
    }
}
