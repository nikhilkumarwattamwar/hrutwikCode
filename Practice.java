package GitFolder.hrutwikCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Practice {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer,String> hashMap=new ConcurrentHashMap<>();
        hashMap.put(1,"hj");
        hashMap.put(2,"ho");
        hashMap.put(3,"hp");
        hashMap.put(4,"hk");
        for (Map.Entry<Integer,String> map:hashMap.entrySet()){
            System.out.println("ID  :"+map.getKey()+" Name : "+map.getValue());
            hashMap.put(5,"kjkl");
        }
        System.out.println(hashMap);
    }
}
