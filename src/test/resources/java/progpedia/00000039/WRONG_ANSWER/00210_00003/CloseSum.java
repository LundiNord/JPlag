import java.io.*;
import java.util.*;
import java.lang.Math;

public class CloseSum{
    static int n; //nº de numeros
    static int q; // nº de perguntas;
	static int[] num;
	static int[] quest;
	static int closestsum;
	static int dif;
	static int bestdif = 9999999;
	static int sum;
	static int question;
	static int[] answer;
	static int a;
	static int sum2;

    static int binarySum(int l,int h){

		if(l > h) return 0;
		if(l == h) return 0;
		
		sum = num[l] + num[h];
	    dif = Math.abs(sum-question);
		
		if(dif == 0){
			answer = new int[n];
			a = 1;
			answer[0] = question;
			return 0;
		}
			
		else if(dif < bestdif){
			answer = new int[n];
			a = 1;
			bestdif = dif;
			answer[0] = sum;
		}
		
		else if(dif == bestdif){
			sum2 = sum;
			//System.out.print(sum2 + "|" + a);
			answer[a] = sum2;
			a++;
		}
		
		if(sum > question) return binarySum(l,h-1);		
		else if(sum < question) return binarySum(l+1,h);
		
		return 0;
	}
	

    public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		
		n = stdin.nextInt();
		num = new int[n];
		for(int i = 0; i < n; i++)
		    num[i] = stdin.nextInt();
		
		q = stdin.nextInt();
		quest = new int[q];
		for(int j = 0; j < q; j++)
		    quest[j] = stdin.nextInt();
		
		int zero = 0;
		Arrays.sort(num);
		for(int x = 0; x < q; x++){
			question = quest[x];
			zero = binarySum(0, n-1);
			if(a == 0) System.out.print(answer[0]);
			
			else {for(int f = 0; f < a; f++)
				System.out.print(answer[f] + " ");
		    }
			
			System.out.println("");
			answer = new int[n];
		}
	}
}
