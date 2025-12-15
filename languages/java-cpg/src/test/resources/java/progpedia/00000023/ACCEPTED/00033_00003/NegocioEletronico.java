import java.util.*;

class Regiao{
	LinkedList<Ligacao> ligacoes;
	int distancia;
	boolean visitado;
	int nome;
	Regiao(int nome){
		ligacoes = new LinkedList<Ligacao>();
		distancia = 999999999;
		visitado = false;
		this.nome = nome;
	}
}

class Ligacao{
	int regiao;
	int distancia;
	Ligacao(int regiao, int distancia){
		this.regiao = regiao;
		this.distancia = distancia;
	}
}

public class NegocioEletronico {
	static Regiao[] sort(Regiao[] regioes){
		Regiao[] res = new Regiao[regioes.length];
		res[0] = regioes[0];
		int i, j;
		Regiao k, m;
		for(i=1; i<regioes.length; i++){
			k = regioes[i];
			for(j=0; j<res.length && res[j]!=null && k.distancia>=res[j].distancia; j++);
			while(j<res.length){
				m = res[j];
				res[j] = k;
				k = m;
				j++;
			}
		}
		return res;
	}
	
	static int getMin(Regiao[] regioes){
		int i=0, min=-1;
		for(; i<regioes.length; i++)
			if(!regioes[i].visitado){
				min=i;
				break;
			}
		for(; i<regioes.length; i++)
			if(!regioes[i].visitado && regioes[i].distancia<regioes[min].distancia)
				min = i;
		return min;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int d = in.nextInt()-1;
		Regiao[] regioes = new Regiao[n];
		for(int i=0; i<n; regioes[i]=new Regiao(i), i++);
		int x, y, dis;
		while(true){
			x = in.nextInt()-1;
			if(x==-2) break;
			y = in.nextInt()-1;
			dis = in.nextInt();
			regioes[x].ligacoes.add(new Ligacao(y, dis));
			regioes[y].ligacoes.add(new Ligacao(x, dis));
		}
		int nVisitados = 1;
		regioes[d].visitado = true;
		regioes[d].distancia = 0;
		for(Ligacao l : regioes[d].ligacoes)
			regioes[l.regiao].distancia = l.distancia;
		int i;
		while(nVisitados<n){
			i = getMin(regioes);
			if(i<0)
				break;
			regioes[i].visitado = true;
			for(Ligacao l : regioes[i].ligacoes)
				if(regioes[l.regiao].distancia>regioes[i].distancia+l.distancia)
					regioes[l.regiao].distancia = regioes[i].distancia+l.distancia;
		}
        regioes = sort(regioes);
		for(i=0; i<regioes.length; i++){
			System.out.print(regioes[i].nome+1);
            if(i!=regioes.length-1)
                System.out.print(' ');
            else
                System.out.print('\n');
        }
	}
}
