import java.util.*;
public class circuitPoints
{

	public static void main (String args[])
	{
		Scanner input = new Scanner(System.in);
		int nPoints = input.nextInt();
		boolean visitedPoints[] = new boolean[nPoints];
		Arrays.fill(visitedPoints,false);
		int nLinks = input.nextInt();
		input.nextLine();
		boolean arrayLinks[][] = new boolean[nPoints][nPoints];
		for(int i=0;i<nLinks;i++)
		{
			int point = input.nextInt() - 1;
			int linkTo = input.nextInt() - 1;
			arrayLinks[point][linkTo] = true;
		}
		int count = 0;
		for(int i=0;i<nPoints;i++)
		{
			if(visitedPoints[i] == false)
			{
				count++;
				deepSearch(i,visitedPoints,arrayLinks,nLinks,nPoints);
			}
		}
		System.out.println(count);
	}
	static void deepSearch(int value,boolean visitedPoints[],boolean arrayLinks[][],int nLinks, int nPoints)
	{
		visitedPoints[value] = true;
		for(int i=0;i<nPoints;i++)
		{
			for(int i2=0;i2<nPoints;i2++)
			{
				if(value == i && arrayLinks[i][i2] == true)
				{
					if(visitedPoints[i2] == false)
					{
						deepSearch(i2,visitedPoints,arrayLinks,nLinks,nPoints);
					}
				}
				else if(value == i2 && arrayLinks[i][i2] == true)
				{
					if(visitedPoints[i] == false)
					{
						deepSearch(i,visitedPoints,arrayLinks,nLinks,nPoints);
					}
				}
			}
		}
	}
}