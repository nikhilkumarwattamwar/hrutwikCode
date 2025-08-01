package GitFolder.hrutwikCode;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IdentityHashMapExaamples {
    public static void main(String[] args) {
        IdentityHashMap<String,Integer> identityHashMap=new IdentityHashMap<>();
        identityHashMap.put("Vipul",12);
        identityHashMap.put(new String("Vipul"),58);
        System.out.println(identityHashMap);

        String s1 = new String("key");
        String s2 = new String("key");

        identityHashMap.put(null, 25);
        identityHashMap.put(null, 255);
        identityHashMap.put("NullValue", null);
        identityHashMap.put("NullValue", null);

        identityHashMap.put(s1, 21);
        identityHashMap.put(s2, 6);  // treateD as different key because s1 != s2 (reference)
        identityHashMap.put(s1,786); //  overwrites the values associated to s1 object ;
        System.out.println(identityHashMap.size() +" "+identityHashMap); // 2
        System.out.println("========================================================================================");

        IdentityHashMap<Integer, String> map = new IdentityHashMap<>();
        Integer i1=1000;
        Integer i2=1000;

        System.out.println(System.identityHashCode(i2)+" : identityHashCode of i2");
        System.out.println(System.identityHashCode(i1)+" : identityHashCode of i1");
        String name="Hello";
        String name1="Hello";
        System.out.println(System.identityHashCode(name1)+" : identityHashCode of name1");
        System.out.println(System.identityHashCode(name)+" : identityHashCode of name");


        map.put(i1,"1000");
        map.put(i2,"1000");
        map.put(i2,"4000"); // overwrites previous values of i2 key
        System.out.println(map);

        Iterator<Map.Entry<String, Integer>> iterator=identityHashMap.entrySet().iterator();
        while(iterator.hasNext() ){
            System.out.println(iterator.next().getKey()+" : "+iterator.next().getValue());
        }

        Iterator<Map.Entry<Integer, String>> m1= map.entrySet().iterator();
        while (m1.hasNext()){
            System.out.println(m1.next().getKey()+" : "+m1.next().getValue());
        }
        for (Map.Entry<Integer, String> k: map.entrySet()){
            System.out.println(k.getKey() + " : "+k.getValue());
        }
       Set<Integer> m2 =map.keySet();
        for (Integer m:m2){
            System.out.println(m +" key"+ map.get(m)+" Values");
        }

    }
}
