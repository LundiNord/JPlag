import java.util.*;
class CigarrasTontas {
	public static void main(String args[]){
		Scanner stdin= new Scanner (System.in);
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int aux;
		aux=stdin.nextInt();
		while(aux!=0){
			lista.add(aux);
			
			aux=stdin.nextInt();
		}
		
		for(int i=0; i<lista.size();i++){
			for(int j=i+1; j<lista.size();j++){
				if(lista.get(i).equals(lista.get(j)))
					i=j;
			}
			
			System.out.println(lista.get(i));
		}
	}
}
