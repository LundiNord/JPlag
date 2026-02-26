import java.util.Scanner;
public class cigarras {
	public static void main(String[] args) {
		
		Scanner stdin  = new Scanner (System.in);
		int sigarra[] = new int [10001];
		int n=stdin.nextInt();
		int j = 1;
		while (n!=0)
		{
			if(sigarra[n]==0)
			{
				sigarra[n]=j;
			}
			else{
				int apaga=sigarra[n];
				for(int i =1; i<10001;i++)
				{
					if(sigarra[i]>apaga)
					{
						sigarra[i]=0;
						
					}

				}
			j--;
			}
			n=stdin.nextInt();
			//System.out.println(n);
			j++;
		}
	//System.out.println(j-1);
		
		for(int z= 1; z<j ;z++)
		{
			for(int i =1; i<10001;i++)
			{
				if(sigarra[i]==z)
					System.out.println(i);	


			}
		}
	}
}

