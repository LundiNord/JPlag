import java.util.ArrayList;
import java.util.Scanner;

class Cigarras {
    public static void main(String [] args) {
	ArrayList<String> source = new ArrayList<String>();
	Scanner stdin = new Scanner(System.in);	
	
	while(stdin.hasNext()) {
	    String line = stdin.next();

	    for(int i = 0; i < source.size(); i++) {
		if(line.equals(source.get(i))) {
		    for (int j = source.size(); j>i; j--) {
			source.remove(i);
		    }
		}
	    }

	    source.add(line);
	}

	for (String s : source) {
	    if(!s.equals("0"))
	    System.out.println(s);
	}
    }
}