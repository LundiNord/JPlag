import java.util.Scanner;


class Cigarras{
	public static void main(String[] args) {
		Scanner kb= new Scanner(System.in);
		int n,i,valor;
		int v[] = new int[30];
		valor=kb.nextInt();
		v[0]=valor;
		n=1;
		while(valor!=0){
			i=0;
			while(i<n && v[i]!=valor){
				i++;
				if(i<n)
					n=i+1;
				else{
					v[n]=valor;
					n=n+1;
				}	
			}
			valor=kb.nextInt();
		}
		for (int j = 0; j < n; j++) {
			System.out.println(v[j]);
		}
	}
}