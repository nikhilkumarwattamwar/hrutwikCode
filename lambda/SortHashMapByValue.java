package lambda;

import java.util.*;

public class SortHashMapByValue {
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(30,"ram");
        map.put(90,"anna");
        map.put(76,"ria");
        map.put(56,"ria");
        map.put(45,"sam");

        ArrayList<Map.Entry<Integer,String>> al = new ArrayList<>(map.entrySet());
        Collections.sort(al,(o1,o2)->{
            int value=o2.getValue().compareTo(o1.getValue());
            if(value!=0){
                return value;
            }else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        System.out.println(al);
    }
}
