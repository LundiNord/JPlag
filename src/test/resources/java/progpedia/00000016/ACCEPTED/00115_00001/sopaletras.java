
import java.util.Scanner;

public class sopaletras {

	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);

		String word1=in.next();
		String word2=in.next();

		char[] cArray  = word1.toCharArray();
		char[] cArray_ = word2.toCharArray();

		if(cArray.length <= cArray_.length){
			for(int i=0; i<cArray.length ;i++){
				if(cArray[i] != cArray_[i]){
					System.out.print(cArray[i]);
					System.out.println(cArray_[i]);
					System.exit(0);
				}
			}
		}else{
			for(int i=0; i<cArray_.length ;i++){
				if(cArray[i] != cArray_[i]){
					System.out.print(cArray[i]);
					System.out.println(cArray_[i]);
					System.exit(0);
				}
			}
		}
		System.out.println("Nenhum");
	}

}