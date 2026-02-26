import java.io.*;
import java.util.*;

class CigarrasTontas{
    public static void main(String args[]){
        Scanner in= new Scanner(System.in);
        int locais[] = new int[30];
        int i;
        int j=0;
        while((i=in.nextInt())!=0){
            locais[j]=i;
            for(int k=0; k<j; k++){
                if(locais[k]==locais[j]){//se o local ja tiver sido visitado entao 
                   // int temp=j;
                    j=k;
                   // k=temp;
                }
            }
            j++;
        }
        for(int k=0; k<j; k++){
            System.out.println(locais[k]);
        }
    }
} 
            
                    
            
        
