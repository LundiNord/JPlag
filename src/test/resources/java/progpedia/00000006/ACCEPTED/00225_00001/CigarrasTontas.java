import java.util.*;

class CigarrasTontas{
    public static void main (String[] args){
	ArrayList<Integer> locais = new ArrayList<Integer>();
	int nlocais = 0;
	Scanner stdin = new Scanner(System.in);
	int novoLocal = stdin.nextInt();
	while(novoLocal != 0){
	    for(int i = 0; i < nlocais; i++){
		if(locais.get(i) == novoLocal)
		    nlocais = i;
	    }
	    locais.add(nlocais, novoLocal);
	    nlocais++;
	    novoLocal = stdin.nextInt();
	}
	for(int i = 0; i < nlocais; i++){
	    System.out.println(locais.get(i));
	}
	
    }
}

