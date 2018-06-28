import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) {
        //test Mountain class
        Mountain mountain=new Mountain("HaungShan",12.099);
        System.out.println("test version control");
        System.out.println("name of mountain is: "+mountain.getName());
        System.out.println("height is: "+mountain.getLatitute());
        //
        String s="00:01";
        NextTime nextTime=new NextTime();
        String next=nextTime.getNext(s);
        System.out.println("next time is "+next);
        int[] flow=new int[]{2,4,1,5,6,7,8,3};
        int k=9;
        int day=getDay(flow,k);
        System.out.println("zai di "+day+" tian de dao jie guo");
        TreeMap<Integer,Integer> map=new TreeMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(8,8);
        map.put(4,4);
        int r=map.floorKey(4);
        System.out.println("bi shief afe: "+r);
        int v=map.ceilingKey(6);
        System.out.println("bi shief afe: "+v);
        System.out.println("测试连续开花的");
        System.out.println("开花天数是： "+getLastDayK(flow,2));
    }

    private static int getDay(int[] flowers, int k) {
        TreeSet<Integer> ts=new TreeSet<>();
        int n=flowers.length;
        ts.add(0);
        ts.add(n+1);
        for(int i=0;i<n;i++){
            int cur=flowers[i];
            int left=ts.floor(cur);
            int right=ts.ceiling(cur);
            if(left!=0){
                int leftDis=cur-left-1;
                if(leftDis==k) return i+1;
            }
            if(right!=n+1){
                int rightDis=right-cur-1;
                if(rightDis==k) return i+1;
            }



            ts.add(cur);
        }
        return -1;
    }
    static class Range{
        int left,right;
        public Range(int left,int right){
            this.left=left;
            this.right=right;
        }
    }
    static class UnionFind{

        Map<Integer,Integer> parent=new HashMap<>();
        public UnionFind(int n){
            for(int i=1;i<=n;i++){

                parent.put(i,i);
            }
        }
        int find(int a){
            int fa=parent.get(a);
            while(fa!=parent.get(fa)){
                fa=parent.get(fa);
            }
            return fa;
        }
        void union(int a, int b){
            int fa=parent.get(a);
            int fb=parent.get(b);
            if(fa!=fb){
                parent.put(fa,fb);
            }
        }
    }
    private static int getLastDayK(int[] flowers, int k){
        Map<Integer,Range> ranges=new HashMap<>();
        Set<Integer> valid=new HashSet<>();
        int n=flowers.length;
        for(int i=1;i<=n;i++){
            ranges.put(i,new Range(i,i));
        }
        UnionFind uf=new UnionFind(n);
        Set<Integer> taken=new HashSet<>();
        //List<Integer> res=new ArrayList<>();
        int res=-1;
        for(int i=0;i<n;i++){
            int cur=flowers[i];
            int leftParent=cur,rightParent=cur;
            if(taken.contains(cur-1)){
                 leftParent=uf.find(cur-1);
                uf.union(cur,leftParent);
            }
            if(taken.contains(cur+1)){
                rightParent=uf.find(cur+1);
                uf.union(cur,rightParent);
            }
            Range leftRange=ranges.get(leftParent);

            Range rightRange=ranges.get(rightParent);
            int left=leftRange.left,right=rightRange.right;
            if(valid.contains(leftParent)){
                valid.remove(leftParent);
            }
            if(valid.contains(rightParent)){
                valid.remove(rightParent);
            }
            uf.union(leftParent,rightParent);
            ranges.put(leftParent,new Range(left,right));
            ranges.put(rightParent,new Range(left,right));
            if(right-left+1==k){
                valid.add(uf.find(cur));
                //System.out.println("cur is : "+cur);
            }
            taken.add(cur);
            if(!valid.isEmpty()){
                res=i+1;
            }
        }

        return res;
    }
}
