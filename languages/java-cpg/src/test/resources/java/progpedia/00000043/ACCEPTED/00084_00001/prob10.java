import java.util.*;

class prob10
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	ArrayList<Enco> encomendas = new ArrayList<Enco>();
	int n = input.nextInt();
	for (int i=0;i<n;i++) {
	    Enco e = new Enco (input.nextInt(), input.nextInt(), i+1);
	    encomendas.add(e);
	}
	
	Collections.sort(encomendas);
	
	for (int i=0;i<n-1;i++) {
	    System.out.print(encomendas.get(i).ind + " ");
	}
	System.out.println(encomendas.get(encomendas.size()-1).ind);
    }
}


class Enco implements Comparable<Enco>
{
    int d;
    int m; 
    int ind;
    double rel;

    Enco() {
	d=0;
	m=0;
	ind=0;
	rel=0;
    }
    
    Enco (int d, int m, int ind) {
	this.d=d;
	this.m=m;
	this.ind=ind;
	this.rel=(double)m/d;
    }
    
    @Override
	public int compareTo(Enco e) {
	if (this.rel<e.rel)
	    return 1;
	return -1;
    }
}


