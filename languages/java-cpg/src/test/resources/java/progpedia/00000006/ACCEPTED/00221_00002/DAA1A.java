import java.util.*;

class DAA1A{
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		int v[]=new int[10000];
		int inicio = stdin.nextInt();
		int corrente = inicio;
		int novo;
		while(corrente!=0){
			novo = stdin.nextInt();
			v[corrente]=novo;
			corrente=novo;
		}
		System.out.println(inicio);
		while(v[inicio]!=0){
			System.out.println(v[inicio]);
			inicio=v[inicio];
		}
	}
}
