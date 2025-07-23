package Collection.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HashMap1 {
        public static void main(String[] args) {
            String[] cities = {"mumbai", "pune"};
            String[] famous = {"financial", "cultural"};

            List list = new ArrayList<>();

            for (int i = 0; i < cities.length; i++) {

                java.util.HashMap<String, String> map = new java.util.HashMap<>();
                map.put("loc", cities[i]);
                map.put("fam", famous[i]);
                map.put("abc","jhsghjsa");
//                for (String key : map.keySet()) {
//                    System.out.print("\t"+key + ": " + map.get(key).toUpperCase());
//                }
                for (Map.Entry<String,String> entry: map.entrySet()) {
                    System.out.println(entry);
                }
//                System.out.println();
                list.add(map);
            }

            System.out.println(list);
        }


}
