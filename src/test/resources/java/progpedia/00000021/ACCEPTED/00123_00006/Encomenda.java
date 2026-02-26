import java.util.*;

class Local {
	int id;
	LinkedList<Destino> destinos;
	int cMax;
	//Local pai; esta variavel e usada para caso seja necessario reconstruir o caminho.
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

public class Encomenda {
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
		Local max = new Local(-1);
		for(Local l : locais)
			if(!l.visitado && l.cMax>max.cMax)
				max = l;
		return max;
	}
	public static void main(String[] args) {
		//input e montagem do grafo
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
		while(true){
			x = in.nextInt();
			if(x == -1)
				break;
			y = in.nextInt();
			l = in.nextInt();
			c = in.nextInt();
			a = in.nextInt();
			if(l<lMin || c<cMin || a<aMin)
				continue;
			if(c>cMax)
				c=cMax;
			lX = getLocalByID(x);
			if(x==origem)
				lOrigem = lX;
			else if(x==destino)
				lDestino = lX;
			lY = getLocalByID(y);
			if(y==origem)
				lOrigem = lY;
			else if(y==destino)
				lDestino = lY;
			lX.destinos.add(new Destino(lY, c));
			lY.destinos.add(new Destino(lX, c));
		}
		//resulocao do problema
		if(lOrigem==null || lDestino==null){
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
		// for(Local abc : locais)	System.out.println(abc.id+" "+abc.cMax+" "+abc.destinos);
		while(nVisitados<nDestinos){
			lX = getMax();
			//System.out.println(lX.id);
			if(lX.cMax<=0)
				break;
			nVisitados++;
			lX.visitado = true;
			for(Destino d : lX.destinos){
				if(d.local.cMax<Math.min(d.cMax, lX.cMax)){
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