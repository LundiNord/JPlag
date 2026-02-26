import java.util.Scanner;

public class Enco{

    public static void main(String [] args){

	Scanner myScan = new Scanner(System.in);
	int n = myScan.nextInt();
	int minLargura = myScan.nextInt();
	int maxLargura = myScan.nextInt();
	int minComprimento = myScan.nextInt();
	int maxComprimento = myScan.nextInt();
	int minAltura = myScan.nextInt();

	int origem = myScan.nextInt();
	int destino = myScan.nextInt();
	int maxPath = 0;
	int matriz [][] = new int [2000][2000];
	int or, dest, mLarg, mComp, mAlt, count=0;
	

	while(true){
	    
	    or = myScan.nextInt();
	 
	    if (or == -1)
		break;
	    dest = myScan.nextInt();
	    mLarg = myScan.nextInt();
	    mComp = myScan.nextInt();
	    mAlt = myScan.nextInt();
	    
	    if(mLarg >= minLargura && mComp >= minComprimento && mAlt >= minAltura){
		count=count+1;
		
	    }
	}


	

	System.out.println(count);
	    }
	}
