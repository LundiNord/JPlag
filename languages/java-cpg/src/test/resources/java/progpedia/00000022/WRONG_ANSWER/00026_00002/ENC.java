import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class ENC {

	/**
	 * @param args
	 */
	private HashMap<Integer, ArrayList<Integer>> hm;
	private int [][] largura;
	private int [][] comprimento;
	private int [][] altura;
	private int larg_min ;
	private int larg_max ;
	private int comp_min ;
	private int comp_max ;
	private int alt_min ;
	private int partida;
	private int fim;
	private int cam=0;
	private ArrayList<Integer> vis = new ArrayList<Integer>();
	
	public ENC(HashMap<Integer, ArrayList<Integer>> HM, int [][] l , int [][] c, int [][] a,
			int lmin, int lmax,int cmin, int cmax, int alt, int p, int f){
		hm = HM;
		largura = l;
		comprimento = c;
		altura = a;
		larg_min = lmin;
		larg_max = lmax;
		comp_min = cmin;
		comp_max = cmax;
		alt_min = alt;
		partida = p;
		fim = f; 		
		
	}
	
	public void  caminhos(int vertice){		
		vis.add(vertice);
		for (int i : hm.get(vertice)){
			if (i==fim){
				if(largura[vertice][i]>=larg_min && largura[vertice][i]<=larg_max && 
					comprimento[vertice][i]>=comp_min && comprimento[vertice][i]<=comp_max &&
					altura[vertice][i]>=alt_min){
					cam +=1;
				}
				//return 0;
			}
			else {
				if (!vis.contains(i)){
					if(largura[vertice][i]>=larg_min && largura[vertice][i]<=larg_max && 
							comprimento[vertice][i]>=comp_min && comprimento[vertice][i]<=comp_max &&
							altura[vertice][i]>=alt_min){
					caminhos(i);
					}
				}
			}
		}			
		
	}
	
	public void res(){
		System.out.println(cam);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> vertices = new ArrayList<Integer>(); 
		Scanner scan = new Scanner(System.in);
		
		String []sss = scan.nextLine().split(" ");
		int locais = Integer.parseInt(sss[0]);
		int [][] largura = new int [locais+1][locais+1];
		int [][] comprimento = new int [locais+1][locais+1];
		int [][] altura = new int [locais+1][locais+1];
		
		String str [] = scan.nextLine().split(" ");
		int larg_min = Integer.parseInt(str[0]);
		int larg_max = Integer.parseInt(str[1]);
		int comp_min = Integer.parseInt(str[2]);
		int comp_max = Integer.parseInt(str[3]);
		int alt_min = Integer.parseInt(str[4]);
		
		str = scan.nextLine().split(" ");
		int partida = Integer.parseInt(str[0]);
		int fim = Integer.parseInt(str[0]);	
		
		String str1 = scan.nextLine();
		while (!str1.equals("-1")){
			str=str1.split(" ");
			int pos1 = Integer.parseInt(str[0]);
			int pos2 = Integer.parseInt(str[1]);
			int larg = Integer.parseInt(str[2]);
			int comp = Integer.parseInt(str[3]);
			int alt = Integer.parseInt(str[4]);
			
			altura[pos1][pos2]=alt;
			altura[pos2][pos1]=alt;
			largura[pos1][pos2]=larg;
			largura[pos2][pos1]=larg;
			comprimento[pos1][pos2]=comp;
			comprimento[pos2][pos1]=comp;
			
			if (!hm.containsKey(pos1)){
				hm.put(pos1,new ArrayList<Integer>());
				vertices.add(pos1); 
			}
			hm.get(pos1).add(pos2);
			
			if (!hm.containsKey(pos2)){
				hm.put(pos2,new ArrayList<Integer>());
				vertices.add(pos2);
			}
			hm.get(pos2).add(pos1);          
			str1 = scan.nextLine();			
			
		}
		ENC enc = new ENC(hm, largura,comprimento, altura, larg_min,larg_max,comp_min,comp_max,alt_min,partida,fim); 
		enc.caminhos(partida);
		enc.res();
		
	}

}
