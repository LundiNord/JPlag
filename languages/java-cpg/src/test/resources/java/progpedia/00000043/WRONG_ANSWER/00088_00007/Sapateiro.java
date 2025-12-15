/**
 *
 * @author Luis Graça up201304954
 */
import java.util.*;

class encomenda implements Comparable <encomenda> {
    int dias;
    int multa;
    float ratio;
    int index;
    
    public encomenda (int dias, int multa, float ratio, int index) {
        this.dias = dias;
        this.multa = multa;
        this.ratio = ratio;
        this.index = index;
    }
    
    @Override
    
    public int compareTo (encomenda enc) {
        if (ratio < enc.ratio) return -1;
	if (ratio > enc.ratio) return +1;
        if (ratio == enc.ratio) {
            if (enc.multa == enc.dias) {
                Integer numA = multa;
                Integer numB = enc.multa;
                return numA.toString().compareTo( numB.toString() );
            }
            else {
                if (dias < enc.dias) return -1;
                if (dias > enc.dias) return +1;
            }
        }
	return 0;
    }
}

public class Sapateiro {
    public static void main (String [] args){
        Scanner stdin = new Scanner (System.in);
        int n = stdin.nextInt();
        encomenda v[] = new encomenda[n];
        for (int i = 0; i < n; i++) {
            int d = stdin.nextInt();
            int m = stdin.nextInt();
            float r = (float)d / m;
            int index = i+1;
            v[i] = new encomenda(d, m, r, index);
        }
        Arrays.sort(v);
        for (int i = 0; i < n-1; i++) {
            System.out.print(v[i].index + " ");
        }
        System.out.println(v[n-1].index);
        
    }
    
}
