import java.util.*;

public class C3_InfraEstrutura {
	public static void main(String[] args) {
		readInput();	
	}

	public static void readInput() {
		Scanner in = new Scanner(System.in);
		
		int nPlaces   = in.nextInt();
		int minWidth  = in.nextInt();
		int maxWidth  = in.nextInt();
		int minLength = in.nextInt();
		int maxLength = in.nextInt();
		int minHeight = in.nextInt();
		
		routesWhereItPasses(minWidth , minLength , minHeight , in);
	}
	
	public static void routesWhereItPasses(int width , int length , int height , Scanner in) {
		int start    = in.nextInt();
		int end      = in.nextInt();
		
		int extreme1 , extreme2 , cageWidth , cageLength , cageHeight , count = 0;
		while ((extreme1 = in.nextInt()) != -1) {
			extreme2   = in.nextInt();
			cageWidth  = in.nextInt();
			cageLength = in.nextInt();
			cageHeight = in.nextInt();
			
			if ((cageWidth >= width) && (cageLength >= length) && (cageHeight >= height)) { count++; }
		}
		
		System.out.println(count);
	}
}
