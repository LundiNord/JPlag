import java.util.*;

class SopaDeLetras
{
    public static void lookingPairOfLetters(String s1, String s2)
    {
	int k;
	
	if (s1.length() < s2.length())
	    k = s1.length();
	else 
	    k = s2.length();
	
	for (int i = 0; i < k; i++)
	    {
		char c1 = s1.charAt(i);
		char c2 = s2.charAt(i);
		
		if (c1 != c2)
		    {
			System.out.printf("%c%c\n", c1, c2);
			break;
		    }
		
		if (i == k-1)
		    System.out.println("Nenhum");
	    }
    }
    
    public static void main(String[] args)
    {
	Scanner in = new Scanner(System.in);

	String s1 = in.nextLine();
	String s2 = in.nextLine();

	lookingPairOfLetters(s1, s2);
    }
}