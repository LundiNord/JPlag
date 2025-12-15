/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Marina
 */
import java.util.*;

// Classe para guardar um nome
class Encomenda implements Comparable<Encomenda> {
    public int dias;
    public int multa;
    public float ratio;
    public int index;
    
    Encomenda(int dias, int multa, int index, float ratio) {
	      
        this.dias = dias;
	this.multa = multa;
        this.ratio = ratio;
        this.index = index;
        
        
    }
    
    // Definir como comparar dois elementos da classe Person
    // compareTo e uma funcao que compara objecto com outro objecto "p"
    // Esta funcao deve devolver:
    //  - numero < 0 se objecto for menor que objecto "p"
    //  - numero > 0 se objecto for maior que objecto "p"
    //  - zero, se objecto for igual ao objecto "p"
    @Override
    public int compareTo(Encomenda E) {
	if (ratio < E.ratio) return -1;
	if (ratio > E.ratio) return +1;
	if(E.multa == E.dias)
        {
            if(index <E.index) return -1;
            if(index > E.index) return +1;
        }
        
    return 0;
    }
}

public class sapateiroestudo {
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);

	int N = stdin.nextInt();	//numero de encomedas
        int dias;
        int multa;
        int index;
        float ratio;
	Encomenda v[] = new Encomenda[N];
        
	for (int i = 0; i<N; i++) {
            dias = stdin.nextInt();
            multa = stdin.nextInt();
            ratio = ((float)dias / multa);
            index = i+1;
	    v[i] = new Encomenda(dias,multa, index, ratio);
        }
	// Chamada ao sort padrao da linguagem Java
	// Usa o comparador padrao do tipo do array
	Arrays.sort(v);
	
	for (int i = 0; i<N-1; i++){
	    System.out.print(v[i].index + " " );
        }
        System.out.println(v[N-1].index);
}
}

