import java.util.*;

/**
 * Created by liuzhengping on 2018/4/21.
 */
public class NextTime {
    static String getNext(String s){
        List<Character> charList=new ArrayList<>();
        charList.add(s.charAt(0));
        charList.add(s.charAt(1));
        charList.add(s.charAt(3));
        charList.add(s.charAt(4));
        List<String> list=new ArrayList<>();
        boolean[] visit=new boolean[4];
        helper(list,charList,0,"",visit);
        Collections.sort(list);
        String test=s.substring(0,2)+s.substring(3);
        for (String cur:list){
            if(cur.compareTo(test)>0){
                return cur.substring(0,2)+":"+cur.substring(2);
            }
        }
        String res=list.get(0);

        return res.substring(0,2)+":"+res.substring(2);
    }

    static void helper(List<String> list,List<Character> set,int index,String s, boolean[] visit ){

        if(index==4){
            int hour=Integer.parseInt(s.substring(0,2));
            int min=Integer.parseInt(s.substring(2));
            if(hour<24 && min<60) list.add(s);
            return;
        }
        for(int i=0;i<set.size();i++){
            if(visit[i]) continue;
            char c=set.get(i);
            visit[i]=true;
            helper(list,set,index+1,s+c,visit);
            visit[i]=false;
        }
    }
    /*
    public static void main(String[] args) {
        String s="23:59";
        String next=getNext(s);
        System.out.println(next);
    }
    */
}
