/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author pedropetz
 */
public class CigarrasTontas {

    /**
     * @param args the command line arguments
     */
    public static LinkedList <Integer> eliminaDesvios(LinkedList <Integer>rota,int inicio, int fim){
        
        for(int i=fim;i>inicio;i--){
            rota.remove(i);
        }
        
        return rota;
    }
    
    public static void imprimeRota(LinkedList <Integer>rota){
		int i;
        for(i=0;i<rota.size()-1;i++){
            System.out.println(rota.get(i));
        }
		System.out.println(rota.get(i));
    }
    
    public static void rotaFinal(LinkedList <Integer>rota){
        int aux=0;
        
        for(int i=0;i<rota.size();i++){
            aux=rota.lastIndexOf(rota.get(i));
            if(aux!=-1 && aux!=i){
                rota=eliminaDesvios(rota,i,aux);
            }
            
        }
        
        imprimeRota(rota);
    }
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        LinkedList <Integer>rota = new LinkedList<Integer>();
        
        int aux=Integer.parseInt(scan.next());
        
        for(int i=0;aux!=0;i++){
            rota.add(aux);
            aux=Integer.parseInt(scan.next());
        }
        
        rotaFinal(rota);
    }
}

/**
 * 1600
 * 15
 * 2315
 * 15
 * 1315
 * 0
 */