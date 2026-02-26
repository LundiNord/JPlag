import java.util.Scanner;
import java.util.Vector;


class vertice{
	public vertice(){}
	public vertice(int org,int dest,int nLarg,int nComp,int nAlt){
		 origem=org;
		 destino=dest;
		 larg=nLarg;
		 comp=nComp; 
		 alt=nAlt;
	}
	int origem;
	int destino;
	int larg;
	int comp; 
	int alt;
	
	public int getOrigem(){return origem;}
	public int getDestino(){return destino;}
	public int getLarg(){return larg;}
	public int getComp(){return comp;}
	public int getAlt(){return alt;}
	
}

class ex2{
	public static void main(String args[]){
		Scanner input=new Scanner(System.in);
		int nTrocos= input.nextInt();
		input.nextLine();
		//caracteristicas
		int largMin = input.nextInt();
		int largMax = input.nextInt();
		int compMin = input.nextInt();
		int compMax = input.nextInt();
		int altMin = input.nextInt();
		input.nextLine();
		//fim caracteristicas
		
		//origem e destino
		int origem=input.nextInt();
		int destino=input.nextInt();
		input.nextLine();
		
		//caminhos
		int nextValue;
		Vector<vertice> vertices=new Vector<vertice>();
		while((nextValue=input.nextInt())!=-1){
			int org=nextValue;
			int dest=input.nextInt();
			int larg=input.nextInt();
			int comp=input.nextInt();
			int alt=input.nextInt();
			vertice temp=new vertice(org,dest,larg,comp,alt);
			vertices.add(temp);
		}
		work2(largMin,largMax,compMin,compMax,altMin,vertices);
	}
	
	public static void work2(int largMin,int largMax,int compMin,int compMax,int altMin,Vector<vertice> vertices){
		int nvertices=0;
		for(int a=0;a<vertices.size();a++){
			if(testing(largMin,largMax,compMin,compMax,altMin,vertices.elementAt(a))){
				nvertices++;
			}
		}
		System.out.println(nvertices);
	}
	
	public static boolean testing(int largMin,int largMax,int compMin,int compMax,int altMin,vertice derp){
		int larg=derp.getLarg();
		int comp=derp.getComp();
		int alt=derp.getAlt();
		if(largMin<=larg && compMin<=comp && altMin<=alt){
			return true;
		}
		return false;
	}

}