import java.util.Scanner;
public class CoinChange {
	static final int TROCOS[]={-1,50,25,10,5,1};
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		while(in.hasNextInt())
			System.out.println( contaTrocos( in.nextInt() ) );
	}
	private static int contaTrocos(int x) {
		if(x==0)
			return 1;
		
		int res=0;
		for (int i=1; i<=4; i++) {
			int coin=TROCOS[i];
			for(int j=1; j<=x/coin;j++)
				res+=possibilidades(x-j*coin, i+1);
		}
		return res+1;
	}
	private static int possibilidades(int x, int c) {
		if(c<5)
			return x/TROCOS[c]+possibilidades(x,c+1);
		return 1;
	}
}
