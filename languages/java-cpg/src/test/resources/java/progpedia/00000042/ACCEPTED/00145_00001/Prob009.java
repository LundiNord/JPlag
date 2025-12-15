import java.util.*;
	
class linha implements Comparable <linha>{
    int li;
    int ri;
    
    public linha (int li, int ri) {
        this.li = li;
        this.ri = ri;
        
    }
    
    @Override
    public int compareTo(linha seg) {
	if (li < seg.li) return -1;
	if (li > seg.li) return +1;
        if (li == seg.li) {
            if (ri > seg.ri) return -1;
            if (ri < seg.ri) return +1;
        }
	return 0;
    }
}


class Prob009{
	
	static int numM;

	static int solve(linha vet[], int numN, int fim) {
        int count = 0;
        int i = 0; 
        int bigR = 0;
        while (fim < numM && i < numN) {
            if (vet[i].li <= fim){
                count++;
                fim = vet[i].ri;
                int j = i+1;
                while (j < numN && vet[j].li <= fim) {
                    if (vet[j].ri > bigR) {
                        bigR = vet[j].ri;
                        i = j;
                    }
                    j++;
                }
                
            }
            bigR = 0;
            
        }
        return count;
    }
	
	public static void main(String args []){
		Scanner stdin = new Scanner(System.in);
		
		numM = stdin.nextInt();

		int numN = stdin.nextInt();
		
		linha vet[] = new linha[numN];
		
		for(int i=0;i<numN;i++){
			int li = stdin.nextInt();
			int ri = stdin.nextInt();
			vet[i] = new linha(li,ri);
		}
		
		Arrays.sort(vet);
		
		int sol = solve(vet, numN, 0);
		System.out.println(sol);
		
		
		
	}
	
	
	
	
	
	
}