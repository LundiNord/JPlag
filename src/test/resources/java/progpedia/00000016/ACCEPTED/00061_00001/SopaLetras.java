import java.io.*;
import java.util.*;
public class SopaLetras
{
	public static int verifica(String a, String b)
	{
		for(int i = 0; i<a.length(); i++)
		{
			if(a.charAt(i)!= b.charAt(i))
				return i;
		}
		return -1;
		
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		int i = verifica(a,b);
		if(i == -1)
			System.out.println("Nenhum");
		else
			System.out.println(""+ a.charAt(i) + b.charAt(i));
	
	}

}
