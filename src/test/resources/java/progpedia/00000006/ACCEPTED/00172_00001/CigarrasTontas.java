import java.util.*;

public class CigarrasTontas {
	public static void main(String args[])
    {
		int					nPath		= -1;
		int					nPathBegin	= -1;
		ArrayList<Integer>	alPath		= null;
		Scanner 			myScan		= new Scanner(System.in);
		
		alPath = new ArrayList<Integer>();	
		
		nPathBegin = myScan.nextInt();
		alPath.add(nPathBegin);
		
		while (nPath != 0)
		{
			nPath = myScan.nextInt();
			
			if (alPath.indexOf(nPath) != -1)
			{
				alPath = RemovePaths(alPath, alPath.indexOf(nPath), alPath.size() - 1);
			}
			if(nPath != 0)
			{
				alPath.add(nPath);
			}
		}
		
		for(int i = 0; i < alPath.size(); i++)
		{
			System.out.println(alPath.get(i));
		}
		
    }
	
	public static ArrayList<Integer> RemovePaths (ArrayList<Integer> alPath, int nPathBegin, int nPathEnd)
	{
		for(int i = nPathBegin; i <= nPathEnd;)
		{	
			nPathEnd--;
			alPath.remove(i);
		}
		
		return alPath;
	}
}
