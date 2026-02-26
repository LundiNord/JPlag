/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package problems;
import java.util.*;
/**
 *
 * @author Hugo
 */
public class ProblemA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int locais[]=new int[30], i=0, aux;
        
        while((aux=in.nextInt())!=0){
            locais[i]=aux;
            for(int k=0; k<i; k++){
                if (locais[k]==aux){
                    i=k;
                    break;
                }
            }
            i++;
        }        
        for(int j=0; j<i; j++){
            System.out.println(locais[j]);
        }
    }
}
