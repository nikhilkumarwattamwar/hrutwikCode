package GitFolder.hrutwikCode.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CoustomCompartor {

    public static void main(String[] args) {
        Comparator<Student1> byId = new Comparator<>() {
            @Override
            public int compare(Student1 s1, Student1 s2) {
                return Integer.compare(s2.id, s1.id);
//                return s1.name.compareTo(s2.name);
            }

        };

        Map<Student1,String> map=new TreeMap<>(byId);
        map.put(new Student1("Anuj", 222), "Java");
        map.put(new Student1("S001", 45), "Python");
        map.put(new Student1("S002", 62), "C++");
        for (Map.Entry<Student1,String> m:map.entrySet()){
            System.out.println(m.getKey()+" : "+m.getValue());
        }
    }
//    Comparator<Student1> byIdComparator = new Comparator<>() {
//        @Override
//        public int compare(Student1 s1, Student1 s2) {
//            return Integer.compare(s1.id, s2.id);
//        }
//    };
//
//    Map<Student1, String> map = new TreeMap<>(byIdComparator);
//
//        map.put(new Student1("Alice", 3), "Java");
//        map.put(new Student1("Bob", 1), "Python");
//        map.put(new Student1("Charlie", 2), "C++");
//
//        for (Map.Entry<Student1, String> entry : map.entrySet()) {
//        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }


class Student1 {

    String name;
    int id;

    Student1(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id;
    }


}