import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class Soma {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int n, num[], nQ, Qnum[], somas[];

		n = in.nextInt();
		num = new int[n];
		
		for(int i=0; i < n; i++)
			num[i] = in.nextInt();
			
		nQ = in.nextInt();
		Qnum = new int[nQ];
		
		for(int i = 0; i < nQ; i++)
			Qnum[i] = in.nextInt();
		
		somas = new int[(n*(n-1))/2];
		int count = 0;
		for(int i = 0; i < n-1; i++)
			for(int j = i+1; j < n; j++){
				somas[count] = (num[i]+num[j]);
				count++;
			}
		
		Arrays.sort(somas);
		
		for(int i = 0; i < nQ; i++)
			binary(somas, 0, somas.length - 1, Qnum[i]);
		

	}

	private static void binary(int[] somas, int low, int high, int key) {
		
		int dif, min = Integer.MAX_VALUE, vMid;
		LinkedList<Integer> answer = new LinkedList<Integer>();
		
		while(low <= high){
			
			int middle = low + ((high-low)/2);
			
			vMid = somas[middle];
			dif = key - vMid;
			if(dif == 0) {
				System.out.println(key);
				return;
			}
			
			if(dif < 0) high = middle-1;
			else low = middle+1;
			
			dif = Math.abs(dif);
			
			if(dif == min){
				if(answer.getFirst() < vMid)
					answer.addLast(vMid);
				else
					answer.addFirst(vMid);
			}
			else if(dif < min){
				answer.clear();
				answer.add(vMid);
				min = dif;
			}
		}
		
		if(answer.size()>1)
			System.out.println(answer.getFirst() + " " + answer.getLast());
		else
			System.out.println(answer.getFirst());
		
	}

}
