import java.util.*;

class testProb09{
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        int tamanho = inp.nextInt();
        int nSeg = inp.nextInt();
        PriorityQueue<Segment> Q = new PriorityQueue<Segment>();
        boolean first = true;

        for(int i=0; i<nSeg;i++){
            int pIni = inp.nextInt();
            int pFin = inp.nextInt();

            if (pIni == 0 && pFin == 0)
                break;
                
            Q.offer(new Segment(Math.min(pIni, pFin), Math.max(pIni, pFin)));     
        }

        if (first)
                first = false;
            else
                System.out.println();
                        
            int left = 0;
            
            List<Segment> res = new ArrayList<Segment>();
            
            while (left < tamanho) {
                Segment sel = null;
                while (!Q.isEmpty() && Q.peek().pIni <= left) {
                    Segment cur = Q.poll();
                    if (sel == null || cur.pFin > sel.pFin)
                        sel = cur;
                }
                if (sel == null)
                    break;
                
                left = sel.pFin;
                res.add(sel);
            }
            
            if (left < tamanho)
                res.clear();
            System.out.println(res.size());
    }

}

class Segment implements Comparable<Segment> {
    int pIni, pFin;
    
    public Segment(int pIni, int pFin) {
        this.pIni = pIni;
        this.pFin = pFin;
    }

    public int compareTo(Segment s) {
        if (this.pIni != s.pIni)
            return this.pIni - s.pIni;
        return s.pFin - this.pFin;
    }
    
    public String toString() {
        return this.pIni + " " + this.pFin;
    }
}