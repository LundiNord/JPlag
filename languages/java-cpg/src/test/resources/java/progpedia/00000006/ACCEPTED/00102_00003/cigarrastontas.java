import java.util.Scanner;


public class cigarrastontas {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n= in.nextInt();
        int v[] = new int[30000];
        int v2[] = new int[30000];
        int j=0;
       
        //ler dados vetor v
        while(n!=0) {
                v[j]=n;
                n=in.nextInt();
                j++;
        }
        
        procura(v,v2,j);
        
        //imprimir
        for(int i=0;i<v2.length;i++){
        	if(v2[i]!=0)
        		System.out.println(v2[i]);
        }
	}

	static void procura(int[] v, int[] v2,int j) {                                
	int  i2,i4,i3,tmp;
		//i2 corre v[]
		//i4 corre v2[]
		for (i2=0, i4=0; i2<j; i2++) {
            tmp = i2;
            for (i3=i2+1; i3<j; i3++)
                if (v[i2] == v[i3])
                    tmp = i3;
           v2[i4] = v[tmp];
           i2 = tmp;
           i4++;
		}
	}

}
