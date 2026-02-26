//Rute Santos e Joana Valente
import java.util.*;
class Prob9{
	public static void main(String args[]){
		Scanner input=new Scanner(System.in);
		int m=input.nextInt();
		int n=input.nextInt();
		Seg v[]=new Seg[n];
		for(int i=0;i<n;i++)
			v[i]=new Seg(input.nextInt(),input.nextInt());
		Arrays.sort(v);
        int fim=0,best=0,res=0;
        for(int i=0;i<n;i++){
        	if(fim>=m)
        		break;
        	for(int j=i;j<n;j++){
        		if(v[j].esq<=fim && v[j].dir>fim){
        			if(best<v[j].dir)
        				best=v[j].dir;
        		}
        	}
        	res++;
        	fim=best;
        }
        System.out.println(res);
	}
}
class Seg implements Comparable<Seg>{
    public int dir,esq,tamanho;
    Seg(int e,int d){
        esq=e;
        dir=d;
        tamanho=d-e;
    }
    @Override
    public int compareTo(Seg s){
        if(esq<s.esq) 
			return -1;
        else if(esq>s.esq) 
			return 1;
        else if(dir<s.dir) 
			return -1;
        else if(dir>s.dir) 
			return 1;
        return 0;
    }
}