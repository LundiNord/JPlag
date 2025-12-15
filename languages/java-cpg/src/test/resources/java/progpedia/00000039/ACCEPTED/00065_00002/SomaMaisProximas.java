import java.util.*;

public class SomaMaisProximas {
    private static int[] array;
    private static int[] sums;
    private static int high = 0;
    private static int[] answer = new int[2];

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	readSetInput(in);
	dataProcessing(in);
    }

    private static void readSetInput(Scanner in) {
	int nSet = in.nextInt();
	array = new int[nSet];
	high = ((nSet * (nSet - 1)) / 2) - 1;
	sums = new int[high+1];

	for (int i=0; i<nSet; i++) {
	    array[i] = in.nextInt();
	}
    }

    private static void dataProcessing(Scanner in) {
	sumSet();
	Arrays.sort(sums);
	int nQueries = in.nextInt();
	int query;

	for (int i=0; i<nQueries; i++) {
	    answer[0] = 0;
	    answer[1] = 0;
	    query = in.nextInt();
	    if (query < sums[0]) {
		answer[0] = sums[0];
	    } else if (query > sums[sums.length-1]) {
		answer[0] = sums[sums.length-1];
	    } else {
		bSearch(query);
	    }
	    writeOutput();
	}
    }

    private static void sumSet() {
	int k = 0;

	for (int i=0; i<array.length; i++) {
	    for (int j=i+1; j<array.length; j++) {
		sums[k++] = array[i] + array[j];
	    }
	}
    }

    private static void bSearch(int q) {
	int low = 0;
	int middle;
	int auxHigh = high;

	while (low <= auxHigh) {
	    middle = (auxHigh + low) / 2;

     	    if (q == sums[middle]) {
		answer[0] = sums[middle];
		return;
	    } else if (q < sums[middle]) {
		auxHigh = middle - 1;
	    } else {
		low = middle + 1;
	    }
	}
	if ((q - sums[auxHigh]) < (sums[low] - q)) {
	    answer[0] = sums[auxHigh];
	    return;
	} else if ((q - sums[auxHigh]) == (sums[low] - q)) {
	    answer[0] = sums[auxHigh];
	    answer[1] = sums[low];
	    return;
	}
	answer[0] = sums[low];
    }

    private static void writeOutput() {
	if (answer[1] != 0) {
	    System.out.println(answer[0] + " " + answer[1]);
	    return;
	}
	System.out.println(answer[0]);
    }
}
