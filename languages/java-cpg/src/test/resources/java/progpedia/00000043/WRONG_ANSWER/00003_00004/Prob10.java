import java.util.*;

class Prob10
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	ArrayList<Encomenda> encomendas = new ArrayList<Encomenda>();
	int n = input.nextInt();
	for (int i=0;i<n;i++) {
	    Encomenda e = new Encomenda(input.nextInt(), input.nextInt(), i+1);
	    encomendas.add(e);
	}
	Collections.sort(encomendas);
	
	for (int i=0;i<n-1;i++) {
	    System.out.print(encomendas.get(i).index + " ");
	}
	System.out.println(encomendas.get(encomendas.size()-1).index);
    }
}


class Encomenda implements Comparable<Encomenda>
{
    int d; //Duracao
    int m; //Multa
    int index;
    int relacao;

    Encomenda() {
	d=0;
	m=0;
	index=0;
	relacao=0;
    }

    Encomenda (int d, int m, int index) {
	this.d=d;
	this.m=m;
	this.index=index;
	this.relacao=m/d;
    }
    
    @Override
	public int compareTo(Encomenda e) {
	if (relacao<=e.relacao)
	    return 1;
	return -1;
    }
}
