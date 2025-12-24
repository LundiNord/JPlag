import java.util.Scanner;

public class CigarrasTontas {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		int[] locs=new int[30];
		int size=0;
		
		int n,i;
		boolean find=false;
		
		while(true) {
			n=in.nextInt();
			if(n==0)
				break;
			find=false;
			for(i=0;i<size;i++) {
				if(n==locs[i])
					find=true;
			}
			if(find) {
				size=i-1;
			}
			else {
				locs[size]=n;
				size++;
			}
		}
		for(i=0;i<size;i++)
			System.out.println(locs[i]);
	}
}
