import java.util.*;

class Local {
	int id;
	LinkedList<Destino> destinos;
	int cMax;
	
	boolean visitado;
	Local(int id){
		this.id = id;
		destinos = new LinkedList<Destino>();
		cMax = -1;
		//pai = null;
		visitado = false;
	}
}

class Destino {
	Local local;
	int cMax;
	Destino(Local destino, int cMax){
		this.local = destino;
		this.cMax = cMax;
	}
}

class Enc{
	static int nDestinos;
	static LinkedList<Local> locais;
	static Local getLocalByID(int id){
		for(Local l : locais)
			if(l.id==id)
				return l;
		nDestinos++;
		Local l = new Local(id);
		locais.add(l);
		return l;
		
	}

	static Local getMax(){
		Local max = new Local(0);
		for(Local l : locais)
			if(!l.visitado && l.cMax>max.cMax)
				max = l;
		return max;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int lMin = in.nextInt();
		int lMax = in.nextInt();
		int cMin = in.nextInt();
		int cMax = in.nextInt();
		int aMin = in.nextInt();
		int origem = in.nextInt();
		int destino = in.nextInt();
		int x, y, l, c, a;
		nDestinos = 0;
		Local lX, lY, lOrigem=null, lDestino=null;
		locais = new LinkedList<Local>();
		x = in.nextInt();
		while(x!=-1){
			
			y = in.nextInt();
			l = in.nextInt();
			c = in.nextInt();
			a = in.nextInt();
			if(l>lMin || c>cMin || a>aMin){
				if(c>cMax){
					c=cMax;
					//System.out.print(c);
				}
				lX = getLocalByID(x);
				lY = getLocalByID(y);
				//System.out.println(lX.id);
				//System.out.println(lY.id);
				if(x==origem){
					lOrigem = lX;
					//System.out.println(lOrigem.id);
				}
				else if(x==destino)
					lDestino = lX;
				if(y==origem)
					lOrigem = lY;
				else if(y==destino)
					lDestino = lY;
				lX.destinos.add(new Destino(lY, c));
				lY.destinos.add(new Destino(lX, c));
			}
			x = in.nextInt();
		}
		//System.out.println(nDestinos);
		if(lOrigem==null){
			System.out.println(0);
			return;
		}
		int nVisitados=1;
		lOrigem.cMax = cMax;
		lOrigem.visitado = true;
		for(Destino d : lOrigem.destinos){
			d.local.cMax = d.cMax;
			//d.local.pai = lOrigem;
		}
		while(nVisitados<nDestinos){
			lX = getMax();
			//System.out.println(lX.id);
			if(lX.cMax<=0)
				break;
			nVisitados++;
			lX.visitado = true;
			for(Destino d : lX.destinos){
				if(d.local.cMax<d.cMax){
					d.local.cMax = Math.min(d.cMax, lX.cMax);
					//d.local.pai = lX;
				}
			}
		}
		if(lDestino.cMax>0)
			System.out.println(lDestino.cMax);
		else
			System.out.println(0);
	}

}
