import java.util.*;

class Par implements Comparable<Par>{
    public int l,r,index;
    float m;
    Par(int l, int batatas,int i){
	index=i;
	this.l = l;
	r = batatas;
	m = (float)l/batatas;
    }
    @Override
    public int compareTo(Par f){
	if(m<f.m) return -1;
	if(m>f.m) return +1;
	return 0;
    }
}
class DAA10{
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int contador = 0;
	int v[] = new int[n*2];
	float v2[] = new float[n];
	Par p[] = new Par[n];

	    
	for(int i=0; i<n; i++)
	    p[i]=new Par(in.nextInt(),in.nextInt(),i+1);

	Arrays.sort(p);

	for(int i=0; i<n; i++)
	    System.out.println(p[i].index);
	
    }
}
