import java.util.Scanner;
class SopaDeLetras{
  
  // campo do objeto
  private String sequencia1;
  private String sequencia2;
  
  SopaDeLetras(){}
  SopaDeLetras(String a, String b){
    sequencia1 = a;
	sequencia2 = b;
  }
  
  // Metodos do objeto
  public void comparar(String seq1, String seq2){                                   
    int i = 0;                                                                      
	while( i < seq1.length() && seq1.charAt(i) == seq2.charAt(i) )                  
	   i++;                                                                       
	 
	if( i == seq1.length() ){                                                       
	  System.out.println("Nenhum");
       //return -1;	  
	}
	else                                                                            
	  System.out.println(seq1.charAt(i) + "" + seq2.charAt(i));                     
	  //return i;
 }
 
  
  public static void main(String[] args){
    
	Scanner a = new Scanner(System.in);                                          
	//System.out.println( "Escreve a primeira sequencia" );
	String seq1 = a.next();                                                     
	
	//Scanner b  = new Scanner(System.in);                                       
	//System.out.println( "Escreve a segunda sequencia" );
	String seq2 = a.next();                                                    
	
	SopaDeLetras sequencias = new SopaDeLetras(seq1, seq2 );                      
	
	sequencias.comparar(seq1, seq2);
	//System.out.println(sequencias.comparar(seq1, seq2));                                                
  }
}
	