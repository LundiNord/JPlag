import java.util.*;

class Node {
    int cod;
    int dist;
    int pos;
    boolean fixo;

    Node (int a, int b) {
	cod = a;
	pos = b;
	dist = -1;
	fixo = false;
    }
}

class Arco {
    Node ini;
    Node fin;
    int comp;

    Arco (Node a, Node b, int c) {
	ini = a;
	fin = b;
	comp = c;
    }
}

class encome {

    static int procura (int no, LinkedList <Node> nn, int p) {
	Node k;
	for (int i=0; i<p; i++) {
	    k = nn.get(i);
	    if (k.cod==no) return i;
	}
	return -1;
    }

    static int procv (int codi, Node nn [], int p) {
	for (int i=0; i<p; i++) if (nn[i].cod==codi) return i;
       	return -1;
    }

    static int extracmax (Node ns [], int p) {
	int max = -1;
	int pos = -1;
	for (int i=0; i<p; i++) {
	    if (ns[i].dist>max && !ns[i].fixo) {
		max=ns[i].dist;
		pos=i;
	    }
	}
	return pos;
    }
	    
    public static void main (String args[]) {
	Scanner kb = new Scanner (System.in);
	LinkedList <Arco> larco = new LinkedList <Arco> ();
	LinkedList <Node> lnode = new LinkedList <Node> ();
	int larmin = kb.nextInt();
	int larmax = kb.nextInt();
	int compmin = kb.nextInt();
	int compmax = kb.nextInt();
	int altmin = kb.nextInt();
	int pos = 0;
	int orig = kb.nextInt();
	Node no= new Node (orig,pos);
	lnode.addLast(no);
	pos++;
	int dest = kb.nextInt();
	Node nd = new Node (dest,pos);
	lnode.addLast(nd);
	pos++;
	int ext1 = kb.nextInt();
	while (ext1!=-1) {
	    int ext2 = kb.nextInt();
	    int k1 = procura (ext1, lnode, pos);
	    Node n1;
	    if (k1==-1) {
	        n1 = new Node (ext1, pos);
		lnode.addLast(n1);
		pos++;
	    }
	    else n1 = lnode.get(k1);

	    int k2 = procura (ext2, lnode, pos);
	    Node n2;
	    if (k2==-1) {
		n2 = new Node (ext2, pos);
		lnode.addLast(n2);
		pos++;
	    }
	    else n2 = lnode.get(k2);
	    int larg = kb.nextInt();
	    int comp = kb.nextInt();
	    if (comp>compmax) comp=compmax;
	    int altu = kb.nextInt();
	    if (larmin<=larg && compmin<=comp && altmin<=altu) {
		larco.addLast(new Arco (n1,n2,comp));
	    }
	    ext1 = kb.nextInt();
	}
	if (larco.size()==0) System.out.println(larco.size());
	else {
	    int a = lnode.size();
	    Node vecnode[] = new Node [a];
	    
	    while (!lnode.isEmpty()) {
		Node k = lnode.removeFirst();
		vecnode [k.pos] = k;
		
	    }
	    vecnode[0].dist=0;	
	    
	    int adj[][] = new int [pos][pos];
	    while (!larco.isEmpty()) {
		Arco x = larco.removeFirst();
		adj[x.ini.pos][x.fin.pos]=x.comp;
		adj[x.fin.pos][x.ini.pos]=x.comp;
	    }
	    boolean fal = true;
	    for (int i=0; i<a && fal; i++) {
		int k = extracmax (vecnode,a);
		if (k==-1) fal= false;
		else {
		    //System.out.println("vk "+k);
		    vecnode[k].fixo=true;
		
		    for (int j=0; j<a; j++) {
			if (adj[k][j]>0 && !vecnode[j].fixo) { //relax k j
			    int nova = adj[k][j];
			    if (vecnode[k].dist!=0 && nova>vecnode[k].dist) nova=vecnode[k].dist;
			    if (nova>vecnode[j].dist) vecnode[j].dist=nova;
			}
		    }
		}
	    }	
	    if (!fal) System.out.println (0);
	    else System.out.println(vecnode[1].dist);
	}
    }
}


/*

20 30 25 40 15
4 3
1 6 25 30 50
4 6 30 27 20
6 3 15 35 25
50 7 29 20 35
1 50 30 25 20
3 7 23 25 15
4 50 22 30 15
1 3 20 30 23
7 4 30 40 16
-1

*/