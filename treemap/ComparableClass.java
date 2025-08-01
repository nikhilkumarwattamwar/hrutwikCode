package GitFolder.hrutwikCode.treemap;

import java.util.Map;
import java.util.TreeMap;

public class ComparableClass {
    public static void main(String[] args) {
        TreeMap<Person,Integer> map=new TreeMap<>();
        map.put(new Person("John", 22), 456);
        map.put(new Person("Alice", 45), 88);
        map.put(new Person("Kane", 62), 63);

        for (Map.Entry<Person, Integer> p:map.entrySet()){
            System.out.println("name :"+p.getKey().name+" Id:"+p.getKey().id+" marks:"+p.getValue());
        }
    }
}
class  Person implements Comparable<Person>{
    String name;
    int id;

    Person(String name,int id){
        this.id=id;
        this.name=name;
    }

    @Override
    public int compareTo(Person o) {
        return o.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "name :"+name+" ID:"+id;
    }
}
