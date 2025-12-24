import java.io.*;
import java.util.*;

public class moo12{
    static public void main(String Args[]){
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();

	boolean[][]ni = new boolean[n+1][n+1];

	int d = in.nextInt();
	for(int i=0; i<d; i++)
	    ni[in.nextInt()][in.nextInt()] = true;

    }
}
