import java.util.*;

class Lig{
	No no;
	int l;
	Lig(No no, int l){
		this.no = no;
		this.l = l;
	}
}

class No{
	int dist;
	boolean naArvore;
	No pai;
	LinkedList<Lig> ligs;
	No(){
		dist = -1;
		naArvore = false;
		pai = null;
		ligs = new LinkedList<Lig>();
	}
}

public class OpticaMinimalista_Prim{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int L = in.nextInt();
		int C = in.nextInt();
		
		if(N>0){	
			No[] nos = new No[N];
			for(int i=0; i<N; nos[i]=new No(), i++);
			
			for(int i=0; i<L; i++){
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				int l = in.nextInt();
				nos[a].ligs.add(new Lig(nos[b], l));
				nos[b].ligs.add(new Lig(nos[a], l));
			}
			
			int peso = 0;
			int tamArv = 1;
			nos[0].naArvore = true;
			for(Lig lig : nos[0].ligs)
				if(!lig.no.naArvore){
					lig.no.pai = nos[0];
					lig.no.dist = lig.l;
				}
			while(tamArv<N){
				int mDist = -1;
				No no = null;
				for(No noi : nos)
					if(!noi.naArvore && noi.dist>mDist){
						mDist = noi.dist;
						no = noi;
					}
				if(mDist==-1){
					tamArv = -1;
					break;
				}
				no.naArvore = true;
				tamArv++;
				peso += no.dist;
				for(Lig lig : no.ligs)
					if(!lig.no.naArvore && lig.no.dist<lig.l){
						lig.no.pai = no;
						lig.no.dist = lig.l;
					}
			}
			if(tamArv==-1)
				System.out.println("impossivel");
			else
				System.out.println(peso-((N-1)*C));
		}else
			System.out.println(0);
	}
}