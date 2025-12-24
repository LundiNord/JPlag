import java.util.*;
public class pyramidClimb
{

	public static void main (String args[])
	{
		Scanner input = new Scanner(System.in); // Declaration of input scanner.
		int layers = input.nextInt(); // Number of layers.
		int nbadStones = input.nextInt(); // Number of bad stones.
		input.nextLine();
		int badStones[][] = new int[layers][layers]; // Array of lines to be read.
		for(int i=0;i<layers;i++) // All stones in the pyramid are good (not bad).
		{
			for(int i2=0;i2<layers-i;i2++)
			{
				badStones[i][i2] = 0;
			}
		}
		for(int i=0;i<nbadStones;i++) // Some stones in the pyramid are bad.
		{
			int layer = input.nextInt();
			int position = input.nextInt();
			input.nextLine();
			badStones[layer-1][position-1] = 1;
		}
		int memArray[][] = new int[layers][layers];
		for(int i=0;i<layers;i++) // Preparing the memory array.
		{
			for(int i2=0;i2<layers-i;i2++)
			{
				memArray[i][i2] = -1; // Signals non calculation.
			}
		}
		int numberPaths;
		if(badStones[layers-1][0] == 0)
		{
			numberPaths = countPaths(badStones,(layers-1),0,memArray);
		}
		else
		{
			numberPaths = 0;
		}
		System.out.println(numberPaths);
	}
	static int countPaths(int badStones[][],int layer,int position,int memArray[][])
	{
		if(memArray[layer][position]!=-1) return memArray[layer][position];
		int path = 0;
		if(layer == 0) return 1;
		else
		{
			if(badStones[layer-1][position] == 0) // If stone is good.

			{ 
				path += countPaths(badStones,layer-1,position,memArray);
			}
			if(badStones[layer-1][position+1] == 0) // If stone is good.
			{

				path += countPaths(badStones,layer-1,position+1,memArray);
			}
		}
		memArray[layer][position] = path;
		//System.out.println(layer + " " + position + " " + memArray[layer][position]);
		return path;
	}
}