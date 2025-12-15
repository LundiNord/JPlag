import java.util.Scanner;
import java.util.Arrays;
public class prob21{
    static double INF=99999999.0;

    public static void main(String args[])
    {
	Scanner in=new Scanner(System.in);
  
	double p[][]=new double[101][101];
	double ax[]=new double[101];
	double ay[]=new double[101];
	double dis[]=new double[101];
	boolean used[]=new boolean[101];
	int n,i,j;
	n=in.nextInt();
	for(i=0;i< n;i++){
	    ax[i]=in.nextDouble();
	    ay[i]=in.nextDouble();
        }
	for(i=0;i<n;i++)
	    for(j=0;j<n;j++) 
		p[i][j]=INF;
	for(i=0;i< n;i++)
	    for(j=i+1;j< n;j++){
		double v=(ax[i]-ax[j])*(ax[i]-ax[j])+(ay[i]-ay[j])*(ay[i]-ay[j]);
		v=Math.sqrt(v);
		if(v< p[i][j])
		    p[i][j]=p[j][i]=v;
	    }
	for(i=0;i< n;i++)
	    dis[i]=p[0][i];
	Arrays.fill(used,false);
	dis[0]=0;
	used[0]=true;
	double ans=0;
	for(i=0;i<n-1;i++){
	    double min=INF;
	    int tag=-1;
	    for(j=1;j< n;j++){
		if(!used[j]&&dis[j]< min){
		    min=dis[j];
		    tag=j;
		}
	    }
	    used[tag]=true;
	    ans+=min;
	    for(j=1;j< n;j++){
		if(!used[j]&&dis[j]>p[tag][j])
		    dis[j]=p[tag][j];
	    }
	}
	System.out.printf("%.2f\n",ans);
    }
}
