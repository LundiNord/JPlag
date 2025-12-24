import java.util.*;

class Cigarras {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int k, localCurrente, i = 0;
	int[] local = new int[30];
	
	localCurrente = in.nextInt();

	while (localCurrente != 0) {
	    local[i] = localCurrente;
	    for(k=i-1 ; k>=0 && local[k]!=local[i] ; k--) ;
	    if(k != -1) i = k;
	    i++;
	    localCurrente = in.nextInt();
	}
	for(k=0 ; k<i ; k++) System.out.println(local[k]);
    }
}