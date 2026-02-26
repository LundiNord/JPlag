import java.util.*;

class tro {
    int ini;
    int fin;
    int comp;

    tro (int a, int b, int c) {
	ini = a;
	fin = b;
	comp = c;
    }
}

class Node {
    
    boolean [] verif;
    int comprim;
    int posi;
    int bloc;
    Node next;

    Node (int p, int c, int t) {
	verif = new boolean [t];
	posi=p;
	comprim=c;
	bloc=0;
	next=null;
    }
}

class fila {

    int size;
    Node first;
    Node last;

    fila () {
	size = 0;
	first=last=null;
    }

    void addlast (Node x) {

	if (size==0) {
	    first = last = x;
	}
	else if (size==1) {
	    
	    first.next = x;
	    last = x;
	}
	else {
	    last.next=x;
	    last=x;
	}
	size++;
    }

    Node remove () {

	Node tmp = first;
	first=first.next;
	size--;
	return tmp;
    }
   
}

class animal {

    static int calcula (tro [] tt, int tam, int in, int fi) {
	System.out.println ("ta "+tam+" inic "+in+" fini "+fi);
	Node k;
	fila fil = new fila();
	int oldpos=0, newpos=0, res=0;
	for (int i=0; i<tam; i++) {
	    if (tt[i].ini==in || tt[i].fin==in) {
		if (tt[i].ini==in) {
		    oldpos=tt[i].ini;
		    newpos=tt[i].fin;
		}
		else {
		    oldpos=tt[i].fin;
		    newpos=tt[i].ini;
		}
		System.out.println ("old "+oldpos);
		System.out.println ("nevo "+newpos);
		System.out.println ("comp "+tt[i].comp);
		k = new Node (newpos,tt[i].comp,tam);
		for (int x=0; x<tam; x++) {
		    if (tt[x].ini==oldpos || tt[x].fin==oldpos) {
			k.verif[x]=true;
			System.out.print ("ii "+x);
			k.bloc++;
		    }
		}
		System.out.println ("kbloc "+k.bloc);
		if (newpos==fi && k.comprim>res) {
		    System.out.println ("kc "+k.comprim+" "+ res);
		    res = k.comprim;
		}
		else if (newpos!=fi && k.bloc<tam) {
		    fil.addlast(k);
		    System.out.println ("poe "+k.posi);
		}
	    }
	}
	while (fil.size>0) {
	    Node prov = fil.remove();
	    System.out.println ("tira "+prov.posi+" comp "+prov.comprim);
	    int lar = prov.comprim;
	    boolean mark [] = (boolean [])prov.verif.clone();
	    	    
	    int ini2 = prov.posi;
	    for (int i=0; i<tam; i++) {
		
		if (!prov.verif[i] && (tt[i].ini==ini2 || tt[i].fin==ini2)) {
		    if (tt[i].ini==ini2) {
			oldpos=tt[i].ini;
			newpos=tt[i].fin;
		    }
		    else if (tt[i].fin==ini2){
			oldpos=tt[i].fin;
			newpos=tt[i].ini;
		    }
		    System.out.println ("old "+oldpos);
		    System.out.println ("nevo "+newpos);
		
		    k=new Node (newpos,lar,tam);
		    for (int x=0; x<tam; x++) k.verif[x] = mark[x];
		    if (k.comprim>tt[i].comp) k.comprim=tt[i].comp;
		    for (int x=0; x<tam; x++) {
			if (!k.verif[x] && (tt[x].ini==oldpos || tt[x].fin==oldpos)) {
			k.verif[x]=true;
			System.out.print ("ii "+x);
			k.bloc++;
			}
		    }
		    for (int h=0; h<tam; h++) System.out.print (k.verif[h]+" ");
		    if (newpos==fi) {
			if (k.comprim>res) {
			    System.out.println ("final kc "+k.comprim+" "+ res);
			    res = k.comprim;
			}
			else System.out.println ("kc nao entra"+k.comprim);
		    }
		    if (newpos!=fi && k.bloc<tam) {
			fil.addlast(k);
			System.out.println ("kbloc "+k.bloc);
			System.out.println ("poe "+k.posi+" "+k.comprim);
		    }
		}
	    }
	}
	return res;
    }

    public static void main (String args[]) {
	Scanner kb = new Scanner (System.in);
	tro [] lis = new tro [50];
	int i=0;
	int larmin = kb.nextInt();
	int larmax = kb.nextInt();
	int compmin = kb.nextInt();
	int compmax = kb.nextInt();
	int altmin = kb.nextInt();

	int orig = kb.nextInt();
	int dest = kb.nextInt();

	int ext1 = kb.nextInt();
	while (ext1!=-1) {
	    int ext2 = kb.nextInt();
	    int larg = kb.nextInt();
	    int comp = kb.nextInt();
	    int altu = kb.nextInt();
	    if (larmin<=larg && compmin<=comp && altmin<=altu) {
		lis[i] = new tro (ext1,ext2,comp);
		i++;
	    }
	    ext1 = kb.nextInt();
	}
	
	System.out.println(calcula (lis,i,orig,dest));

    }

}
