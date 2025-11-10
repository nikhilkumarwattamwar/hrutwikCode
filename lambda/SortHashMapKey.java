package lambda;

import java.util.*;

public class SortHashMapKey {
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(2,"ram");
        map.put(3,"gia");
        map.put(5,"sam");
        map.put(4,"ana");
        map.put(1,"ria");

        TreeMap<Integer,String> t= new TreeMap<>((o1,o2)->o2-o1);
        t.putAll(map);
        System.out.println(t);

        ArrayList<Map.Entry<Integer,String>> al = new ArrayList<>(map.entrySet());
        Collections.sort(al,(o1,o2)->o2.getValue().compareTo(o1.getValue()));
        System.out.println(al);
    }
}
