import java.io.*;
import java.util.*;

class Segmento implements Comparable<Segmento>{
    public int left,right,tamanho=0;

    Segmento(int l, int r){
	left=l;
	right=r;
	tamanho=right-left;
    }

    @Override
    public int compareTo(Segmento s){
        if (left < s.left) return -1;
	else if (left > s.left) return +1;
	else if(right < s.right) return -1;
	else if(right > s.right) return +1;
	return 0;
    }
}

class p009{
    public static void main(String args[]){

	Scanner in = new Scanner(System.in);

	int m = in.nextInt();
	int n = in.nextInt();
	Segmento s[] = new Segmento[n];
	for(int i=0;i<n;i++)
	    s[i] = new Segmento(in.nextInt(),in.nextInt());
	Arrays.sort(s);
	int end=0,cs=0,maior=0;
        for(int i=0;i<n;i++){
	    if(end>=m)
		break;
	    for(int j=i;j<n;j++){
		if(s[j].left<=end && s[j].right>maior)
		    maior=j;
	    }
	    end=s[maior].right;
	    cs++;
	}
	System.out.println(cs);
	    //System.out.println(s[i].left+" "+s[i].right);
    }
}
