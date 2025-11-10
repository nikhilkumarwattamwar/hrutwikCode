package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortingArraylist {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList();
        al.addAll(Arrays.asList(10,40,30,55,15,78,67));
        Collections.sort(al,(o1,o2)-> -o1.compareTo(o2));
        System.out.println(al);

        Collections.sort(al,(o1,o2)->(o1>o2)?1:(o1<o2)?-1:0);
        System.out.println(al);
    }
}
