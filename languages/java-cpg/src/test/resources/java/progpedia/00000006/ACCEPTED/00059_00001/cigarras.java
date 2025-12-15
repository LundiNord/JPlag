import java.util.Scanner;
class cigarras{
	public static void main(String[] args){
		int vec[]=new int[30];
		Scanner in = new Scanner(System.in);
		int local;
		int i=0;
		for(;;){
			local=in.nextInt();
			vec[i]=local;
			if(local==0)
				break;
			for(int ii=0;ii<i;++ii){
				if(vec[ii]==vec[i]){
					i=ii;
				}
			}
			++i;
		}
		for(int iii=0;iii<i;++iii){
			System.out.println(vec[iii]);
		}
	}
}
