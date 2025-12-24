import java.util.Scanner;

public class ie{

    public static void main(String[] args) {
	Scanner myScan = new Scanner(System.in);
	int nlocais = myScan.nextInt();
	int minLargura = myScan.nextInt();
	int maxLargura = myScan.nextInt();
	int minComprimento = myScan.nextInt();
	int maxComprimento = myScan.nextInt();
	int minAltura = myScan.nextInt();
	int origem = myScan.nextInt();
	int destino = myScan.nextInt();

       	int maxPath = 0;
	int matriz [][] = new int[2000][2000];

	int or,dest,mLarg,mComp,mAlt;
       	int conta=0;
	while(true){
	    
	    or = myScan.nextInt();
	    if(or == -1)
		break;
	    dest = myScan.nextInt();
	    mLarg = myScan.nextInt();
	    mComp = myScan.nextInt();
	    mAlt = myScan.nextInt();
	    if(mLarg >= minLargura && mComp >= minComprimento && mAlt >= minAltura){
		conta++;
		//   	matriz[or][dest]=mComp;
		//	matriz[dest][or]=mComp;
		//	maxPath = Math.max(maxPath, Math.max(or,dest));
	}
	}

	System.out.println(conta);

	
	/*        for (int k=1; k<maxPath;k++){
	for (int i=1; i<maxPath;i++){
	    for(int j=1;j<maxPath;j++){
		matriz[i][j]=Math.max(matriz[i][j], Math.min(matriz[i][k],matriz [k][j]));
		    }
	}
	}*/
}
}