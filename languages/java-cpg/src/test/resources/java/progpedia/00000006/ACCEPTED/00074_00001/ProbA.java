import java.util.Scanner;

class ProbA {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int i = 1;
		int[] path = new int[30];
		
		path[0] = sc.nextInt();
		path[1] = sc.nextInt();
		
		while(path[i] != 0){
			for(int c=0;c<i;c++){
				if(path[c]==path[i]){
					i=c;
					break;
				}
			}
			path[++i] = sc.nextInt();
		}
		
		for(int c=0;c<i;c++)
			System.out.println(path[c]);
		
	}
}