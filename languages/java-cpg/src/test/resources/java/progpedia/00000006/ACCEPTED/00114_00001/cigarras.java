import java.*;
import java.util.Scanner;

class cigarras {
	public static void main(String args[]){
		int i = 0;
		int num;
		int v[] = new int[30];
		Scanner scan = new Scanner(System.in);
		num = scan.nextInt();
		while(num != 0){
			v[i]=num;
			i=proximo(i,v);
			num = scan.nextInt();
		}
		for(int j=0;j<=i-1;j++){
			System.out.println(v[j]);
		}
	}
	
	static int proximo(int n, int v[]){
		int i=0;
		while(v[i]!=v[n]){
			i++;
		}
		return i+1;
	}
}
