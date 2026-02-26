import java.util.*;
//import java.io.*;

class matriz {
    int a;
    int b;
    int v;
    matriz(int pri,int sec,int peso){
	a = pri;
	b = sec;
	v = peso;
    }
}

class no {
    int rank;
    int p;
    int tag;
    no(int v, int n,int id) {
	rank=v;
	p= n;
	tag = id;
    }
}

class krusk {

    int p;
    int r;
    int na;
    int cnt;
    int custos;
    matriz o[];
    no nos[];
    krusk(matriz m [],int tnos,int tlig,int zz){
	p = tnos;
	r = tlig;
	na = 0;
	cnt=0;
	custos=zz;
	nos= new no[tnos];
	o = m;
    }

    void principal () {
	
	int ii=0;

	//quicksort(o,0,r-1);
	
	for (int k=0;k<r;k++) {
	    
	    if ((findset(o[k].a)) != (findset(o[k].b))){
		ii++;
		cnt += o[k].v;
		union(o[k].a,o[k].b);
	    }
	       
	}
	if (ii == (p-1)){
	    System.out.println("rendimento optimo:" + (cnt+custos));}
	else {System.out.println("impossivel");
	
	}
    }

    void makeset(int x) {	

	no n;
	n = new no(0,x,x);
	nos[na] = n;
	na++;
    }

    int findset(int x) {
	
	int ind = find (x);
	if (x != nos[ind].p){
	    nos[ind].p = findset(nos[ind].p);
	}
	return nos[ind].p;
    }
    
    int find(int x){
	
	for(int i=0;i<p;i++){
	    if (nos[i] != null){
		if (nos[i].tag == x){
		    return i;	
		}
	    }
	}
	makeset(x);
	return na-1;
    }
    
    void union (int x,int y){

	link(findset(x),findset(y));
    }

    void link(int x,int y){

	int h = find(x);
	int g = find(y);

	if (nos[h].rank > nos[g].rank){
	    nos[g].p = nos[h].tag;
	}
	else {
	    nos[h].p = nos[g].tag;
	    if (nos[h].rank == nos[g].rank){
		nos[g].rank += 1;
	    }
	}
    }
    /*
    void quicksort (matriz f[],int pp,int rr){

	if (pp < rr){
	    int q = part(f,pp,rr);
	    quicksort(f,pp,q-1);
	    quicksort(f,q+1,rr);
	}
    }

    int part(matriz f[],int pp,int rr){

	int z = f[rr].v;

	int i = pp-1;
	
	for (int j=pp;j<=rr-1;j++){
	    if (f[j].v <= z){
		i++;
		matriz xx = f[i];
		f[i] = f[j];
		f[j] = xx;
	    }
	}
	matriz xx = f[i+1];
	f[i+1] = f[rr];
	f[rr] = xx;
   
	return i+1;

    }
    */
}

class opticamin {

    public static void main(String args[]) {

	Scanner in = new Scanner(System.in);
	
	int x = in.nextInt();
	int y = in.nextInt();
	int z = in.nextInt();

	matriz m[] = new matriz[y];;
	
	for (int i=0;i<y;i++) {
	
	    int a = in.nextInt();
	    int b = in.nextInt();
	    int v = in.nextInt();
	    matriz n = new matriz(a-1,b-1,v);
	    m[i] = n;
	}
	
	krusk k = new krusk(m,x,y,z);
	k.principal();
	
    }
}	
	