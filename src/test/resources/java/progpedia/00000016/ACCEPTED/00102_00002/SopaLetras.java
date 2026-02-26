



import java.util.Scanner;

public class SopaLetras {

	/**
	 * @param args
	 */

	public static String word;
	public static String word2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in=new Scanner(System.in);



		word=in.next();
		word2=in.next();

		char[] cArray=word.toCharArray();
		char[] cArray2=word2.toCharArray();



		if(cArray.length<=cArray2.length){
			for(int i=0;i<cArray.length;i++){
				if(cArray[i]!=cArray2[i]){
					System.out.print(cArray[i]);
					System.out.print(cArray2[i]);
					System.exit(0);
				}
			}
		}else{
			for(int i=0;i<cArray2.length;i++){
				if(cArray[i]!=cArray2[i]){

					System.out.print(cArray[i]);
					System.out.print(cArray2[i]);
					System.exit(0);
				}
			}

		}
		System.out.println("Nenhum");

	}


}







