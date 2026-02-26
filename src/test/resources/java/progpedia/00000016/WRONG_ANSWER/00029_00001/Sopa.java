
import java.util.*;


public class Sopa 
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		String palavra1 = scanner.nextLine();
		String palavra2 = scanner.nextLine();
		
		int len = palavra1.length();
		int len2 = palavra2.length();
		
		if(len2 < len)
			len = len2;
		
		int pos = -1;
		
		for(int i=0;i<len;i++)
		{
			if(palavra1.charAt(i) != palavra2.charAt(i))
			{
				if(++i != len)
				{
					pos = --i;
				}
				
				break;
			}
		}
		
		if(pos > -1)
			System.out.println(palavra1.charAt(pos) + "" + palavra2.charAt(pos));
		else
			System.out.println("Nenhum");

	}

}
