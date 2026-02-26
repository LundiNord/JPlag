import java.util.*;
class cigarras {
	public static void main(String[] args)
	{
		Scanner stdin = new Scanner (System.in);
		ArrayList<Integer> trajecto = new ArrayList<Integer>();
		int temp = stdin.nextInt();
		while (temp!=0)
		{
			if (trajecto.contains(temp))
			{
				while (trajecto.contains(temp))
				{
					trajecto.remove(trajecto.size()-1);
					
				}
				trajecto.add(temp);
			}
			else 
			{
				trajecto.add(temp);
			}
			temp = stdin.nextInt();
		}
		int n =0;
		while (trajecto.size()!=0)
		{
			temp = trajecto.remove(0);
			System.out.println(temp);
			
		}
	}

}
