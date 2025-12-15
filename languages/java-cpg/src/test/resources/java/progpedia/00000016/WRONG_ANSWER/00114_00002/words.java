import java.util.Scanner;

class words{
	public static void main(String args[]){
		Scanner input=new Scanner(System.in);
		String palavra1=input.next();
		input.nextLine();
		String palavra2=input.next();
		int derp=0;
		
		int tamanho=min(palavra1.length(),palavra2.length());
		
		for(int a=0;a<tamanho;a++){
			
			if(palavra1.charAt(a)==(palavra2.charAt(a))){
				continue;
			}
			
			else{
				System.out.println(palavra1.charAt(a)+" "+palavra2.charAt(a));
				derp=1;
				break;
			}
		}
		if(derp==0){
			System.out.println("Nenhum");
		}
	}

	private static int min(int length, int length2) {
		if (length>length2){return length2;}
		return length;
	}
}