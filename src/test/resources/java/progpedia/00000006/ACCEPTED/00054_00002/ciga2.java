import java.util.*;

class ciga2 {
    public static void main (String args[]) {
	Scanner kb = new Scanner (System.in);
	int pos = 0;
	int perc [] = new int [30];
	int loc = kb.nextInt();
	while (loc!=0) {
	    perc[pos] = loc;
	    for (int i=0; i<pos; i++) {
		if (perc[i]==loc) pos=i;
	    }
	    pos++;
	    loc = kb.nextInt();
	}
	for (int k=0; k<pos; k++) System.out.println(perc[k]);


    }


}
