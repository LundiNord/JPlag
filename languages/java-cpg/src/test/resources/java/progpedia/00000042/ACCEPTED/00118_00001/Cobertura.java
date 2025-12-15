import java.util.*;

class Segmento implements Comparable<Segmento>{
    public int left,right,tamanho;

    Segmento(int l,int r){
        left = l;
        right = r;
        tamanho = r-l;
    }

    @Override
    public int compareTo(Segmento s){
        if(left < s.left) return -1;
        else if(left > s.left) return +1;
        else if(right < s.right) return -1;
        else if(right > s.right) return +1;
        return 0;
    }
}

class Cobertura{
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);

		int m = input.nextInt();
		int n = input.nextInt();
		Segmento vetor[] = new Segmento[n];

		for(int i=0;i<n;i++)
			vetor[i] = new Segmento(input.nextInt(),input.nextInt());

		Arrays.sort(vetor);

		/*for(int i=0;i<n;i++){
           System.out.println(vetor[i].left + " " + vetor[i].right);
        }*/

        int end=0,resultado=0,best=0;
        for(int i=0;i<n;i++){
        	if(end >= m)
        		break;
        	for(int j=i;j<n;j++){
        		if(vetor[j].left <= end && vetor[j].right > end){
        			if(best < vetor[j].right)
        				best = vetor[j].right;
        		}
        	}
        	resultado++;
        	end = best;
        }
        System.out.println(resultado);

        
	}
}