package Collection.HashMap;

import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<>();
        String[] names={"Jivan","Alice","John"};
        Integer[] id={1,2,3};
        for (int i=0;i<id.length;i++){
            map.put(id[i],names[i]);
        }

        for (int i:map.keySet()){
            System.out.println("ID :"+i+" Name "+map.get(i));
        }
//        map.put(null,"john");
        map.put(1,"Ben");
        List<Integer> keysToRemove = new ArrayList<>();

        System.out.println();
        for (Map.Entry<Integer, String> entry:map.entrySet()){
            System.out.println("ID "+entry.getKey()+" Name :"+entry.getValue());
            if (entry.getKey()%2==0){
                keysToRemove.add((entry.getKey()));
            }
        }
        for(int i:keysToRemove){
            map.remove(i);
        }
        for (Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println("ID :"+entry.getKey()+" Names :"+entry.getValue());
        }
        System.out.println(map.containsKey(1));

    }
}
