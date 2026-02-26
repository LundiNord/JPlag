/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author pedropetz
 */
public class sopaDeLetras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        String priPalavra=(String) scan.next();
        String segPalavra=(String) scan.next();
        
        difere(priPalavra,segPalavra);
    }

    private static void difere(String priPal, String segPal) {
        boolean diff=false;
        for(int i=0;i<priPal.length()&&i<segPal.length();i++){
            if(priPal.charAt(i)!=segPal.charAt(i)){
                System.out.println(priPal.charAt(i)+ "" +segPal.charAt(i));
                i=30;
                diff=true;
            }
        }
        if(!diff){
            System.out.println("Nenhum");
        }
    }
}
