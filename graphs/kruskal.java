import java.util.*;
import java.util.Collections;

public class kruskal {
    static class Edge implements Comparable<Edge>{
        int src;
        int dest;
        int wt;
        Edge(int s,int d,int w)
        {
            this.src=s;
            this.dest=d;
            this.wt=w;
        }


        @Override
        public int compareTo(Edge e2)
        {
            return this.wt-e2.wt;
        }



    }
    public static void creategraph(ArrayList<Edge> edges)
    {
        edges.add(new Edge(0,1,10));
        edges.add(new Edge(0,2,15));
        edges.add(new Edge(0,3,30));
        edges.add(new Edge(1,3,40));
        edges.add(new Edge(2,3,50));
    }

   static  int n=4;
    static int par[]=new int[n];
    static int rank[]=new int[n];

    public static void init()
    {
        for(int i=0;i<n;i++)
        {
            par[i]=i;
        }

    }

    public static int find(int x)
    {
        if(x==par[x])
        {
            return x;

        }
        return par[x]=find(par[x]);

    }

    public static void union(int a,int b)
    {
        int parA=find(a);
        int parB=find(b);

        if(rank[parA]==rank[parB])
        {
            par[parB]=parA;
            rank[parA]++;

        }
        else if(rank[parA]<rank[parB])
        {
            par[parA]=parB;

        }
        else
        {
            par[parB]=parA;
        }
    }

    public static int kruskalmst(ArrayList<Edge> edges,int n)
    {   init();
        Collections.sort(edges);//eloge
        int minocost=0;
        int count=0;
        for(int i=0;count<n-1;i++)//o(v)
        {
            Edge e=edges.get(i);
            //(src,dest,wt)
            int parA=find(e.src);//a--e.src
            int parB=find(e.dest);//b--e.dest
            if(parA!=parB)
            {
                union(e.src,e.dest);
                minocost+=e.wt;
                count++;
            }
        }
        return minocost;
    }
    public static void main(String args[])
    {
        ArrayList<Edge> edges=new ArrayList<>();
        creategraph(edges);
        System.out.println(kruskalmst(edges,n));

    }
}
