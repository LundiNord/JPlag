import java.io.*;
import java.util.*;

class Piramid{
    public static void main(String[] args) {
	Scanner stdin = new Scanner(System.in);
	int camadas = stdin.nextInt();
	long[][] piramide = new long[camadas][camadas];
	int pedras = stdin.nextInt();
	for(int i = 0; i < camadas; i++) 
	    for(int j = 0; j < camadas; j++)  
		piramide[i][j] = 1;
	for(int i = 0; i < pedras; i++)
	    piramide[stdin.nextInt() - 1][stdin.nextInt() - 1] = 0;
	for(int i = 1; i < camadas; i++) {
	    for(int j = 0; j < camadas - i; j++) {
		if(piramide[i][j] == 0)
		    piramide[i][j] = 0;
		else
		    piramide[i][j] = piramide[i - 1][j] + piramide[i - 1][j + 1];
	    }
	}
	System.out.println(piramide[camadas - 1][0]);
    }
}
