public class segmenttree
{
    static int tree[];
    public static void init(int n)
    {
        tree=new int[4*n];
    }

    public static void buildst(int arr[],int i,int start,int end)
    {
        if(start==end)
        {
            tree[i]=arr[start];
            return;
        }

        int mid=(start+end)/2;
        buildst(arr, 2*i+1, start, mid);
        buildst(arr,2*i+2,mid+1,end);

        tree[i]=tree[2*i+1]+tree[2*i+2];
    }
    public static int getsum(int arr[],int qi,int qj)
    {
        return getsumutil(0,0,arr.length-1,qi,qj);
    }

    public static int getsumutil(int i,int si,int sj,int qi,int qj)
    {
        if(qi>=sj || qj<=si)
        {
            return 0;

        }
        else if(si>=qi && sj<=qj)
        {
            return tree[i];
        }
        else
        {
            int mid=(si+sj)/2;
            int left=getsumutil(2*i+1,si,mid,qi,qj);
            int right=getsumutil(2*i+2,mid+1,sj,qi,qj);
            return left+right;
        }
    }
    public static void update(int arr[],int idx,int newval)
    {
        int diff=newval-arr[idx];
        arr[idx]=newval;
        int n=arr.length;

        updateutil(0,0,n-1,idx,diff);
    }
    public static void updateutil(int i,int si,int sj,int idx,int diff)
    {
        if(idx<si|| idx>sj)
        {
            return;

        }
        tree[i]+=diff;
        if(si!=sj)
        {
            int mid=(si+sj)/2;
            updateutil(2*i+1,si,mid,idx,diff);
            updateutil(2*i+2,mid+1,sj,idx,diff);

        }
    }
    public static void main(String args[])
    {
        int arr[]={1,2,3,4,5,6,7,8};
        int n=arr.length;
        init(n);
        buildst(arr,0,0,n-1);
        for(int i=0;i<4*n;i++)
        {
            System.out.print(tree[i]+" ");
        }

        System.out.println(getsum(arr,2,5));
        update(arr,2,2);
        System.out.println(getsum(arr,2,5));

    }
}