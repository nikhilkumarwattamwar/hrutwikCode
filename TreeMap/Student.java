package Collection.TreeMap;

import java.util.Map;
import java.util.TreeMap;

public class Student implements Comparable<Student>{
    String name;
    int id;
    Student(String name,int id){
        this.id=id;
        this.name=name;
    }


    @Override
    public String toString() {
        return "Name :"+name+" Id: "+id;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }


}
 class ExeTreeMap{
     public static void main(String[] args) {
         Map<Student,String> map=new TreeMap<>();
         map.put(new Student("S003", 22), "Java");
         map.put(new Student("S001", 45), "Python");
         map.put(new Student("S002", 62), "C++");
         for (Map.Entry<Student,String> stu:map.entrySet()){
             System.out.println(stu.getKey()+" + "+stu.getValue());
         }
     }
 }