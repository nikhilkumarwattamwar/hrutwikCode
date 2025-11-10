package lambda;

import java.util.TreeSet;

public class SortTreeset {
    public static void main(String[] args) {
        TreeSet<String> t= new TreeSet<>((o1,o2)->-o1.compareTo(o2));
        t.add("Cherry");
        t.add("Guava");
        t.add("Banana");
        t.add("Apple");
        System.out.println(t);
    }
}
