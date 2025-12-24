import java.util.*;
class estrutura{
    public static void main (String args[]){
	Scanner teclado = new Scanner(System.in);
	int nlocais = teclado.nextInt();
	int jlmin = teclado.nextInt();
	int jlmax = teclado.nextInt();
	int jcmin = teclado.nextInt();
	int jcmax = teclado.nextInt();
	int jamin= teclado.nextInt();
	int trajecto_de = teclado.nextInt(); int trajecto_para = teclado.nextInt();
	int de, para, lmax, cmax, amax, contador=0;
	de = teclado.nextInt();
	while(de!=-1){
	    para = teclado.nextInt();
	    lmax= teclado.nextInt();
	    cmax = teclado.nextInt();
	    amax = teclado.nextInt();
	    if(jlmin<=lmax && jcmin<=cmax && jamin<=amax)
		contador++;
	    de = teclado.nextInt();
	}
	System.out.println(contador);



    }
}