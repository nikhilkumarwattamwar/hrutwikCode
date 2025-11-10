package lambda;

import java.util.Arrays;
import java.util.TreeMap;

public class SortTreemap {
    public static void main(String[] args) {
        TreeMap<Integer,Character> map = new TreeMap<>((o1,o2)->-o1.compareTo(o2));
        map.put(1,'A');
        map.put(4,'D');
        map.put(2,'B');
        map.put(1,'A');
        map.put(3,'C');
        System.out.println(map);
    }
}
