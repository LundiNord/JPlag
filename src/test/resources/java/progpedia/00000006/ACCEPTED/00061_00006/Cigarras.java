import java.util.*;
import java.lang.*;

class Cigarras {
    static Scanner in = new Scanner(System.in);
    public static void main(String args[]) {
	int local,n,i;
	int locais[];
	
	locais = new int[30];
	locais[0] = in.nextInt();
	local = in.nextInt();
	n = 1;
	while (local != 0) {
	    for (i = n; i>0 && local != locais[i-1]; i--);
	    if (i == 0) {
		locais[n] = local;
		n++;
	    }
	    else
		n = i;
	    local = in.nextInt();
	}
	for (i = 0; i<n; i++) 
	    System.out.printf("%d\n", locais[i]);
    }
}