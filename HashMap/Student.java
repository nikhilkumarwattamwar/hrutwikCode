package Collection.HashMap;

import java.util.*;

public class Student {
    int rollNO;
    String name;

    Student(int rollNO,String name){
        this.rollNO=rollNO;
        this.name=name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj)return true;
        if (obj instanceof Student ==false)return false;
        Student s=(Student) obj;
        return this.rollNO==s.rollNO && this.name==s.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNO,name);
    }

    @Override
    public String toString() {
        return "RollNo :"+rollNO+" Name :"+name;
    }
}
class HashMapExecution{
    public static void main(String[] args) {
        Map<Student,Integer> map=new HashMap<>();
        map.put(new Student(1,"john"),69);
        map.put(new Student(2,"Alice"),99);
        map.put(new Student(1,"john"),68);

        for (Map.Entry<Student,Integer> entry:map.entrySet()){
            System.out.println("Id:"+entry.getKey().rollNO+" Name:"+entry.getKey().name+" Marks:"+entry.getValue());
        }
        Student s=new Student(4,"Roman");
        System.out.println(s);
        map.put(s,54);
        System.out.println(map);
    }


}
