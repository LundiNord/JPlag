import java.util.*;

class cigarras {
    public static void main(String args[]) {
	int n=0;
	int local;
	int [] v=new int[30];
	Scanner in = new Scanner(System.in);
	local=in.nextInt();
	while(local != 0) {
	    n=trata(local,v,n);
	    local=in.nextInt();
	}
	
	escreve(v,n);
    }
	
    public static int trata(int l, int a[],int j){
	int i=0;
	while(a[i]!=l && i<j) 
	    i++;
	a[i]=l;
	return (i+1);
    }
	
    public static void escreve(int a[],int k) {
	for(int i=0;i<k;i++)
	    System.out.println(a[i]);
    }
}

	
