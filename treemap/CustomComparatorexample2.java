package GitFolder.hrutwikCode.treemap;

import java.util.*;

public class CustomComparatorexample2 {
    public static void main(String[] args) {
        Comparator<Emp2> comparator=new Comparator<Emp2>() {
            @Override
            public int compare(Emp2 o1, Emp2 o2) {
                return o1.job.compareTo(o2.job);
            }

        };
        TreeMap<Emp2,Integer> map=new TreeMap<>(comparator);
        map.put(new Emp2("Vipul","carpenter",1),45200);
        map.put(new Emp2("zail","Engineer",2),59200);
        map.put(new Emp2("Alice","Manager",3),485200);
        for (Map.Entry<Emp2,Integer>m:map.entrySet()){
            System.out.println("Name :"+m.getKey().name+" Id:"+m.getKey().id+" Job: "+m.getKey().job+" Salary:"+m.getValue() );
        }

    }
}

class Person2 implements Comparable<Person2>{
    String name;
    int id;
    String job;
    Person2(String name,String job,int id){
        this.id=id;
        this.name=name;
        this.job=job;
    }

    @Override
    public String toString() {
        return "name: "+name+" id:"+id+" Job:"+job;
    }

    @Override
    public int compareTo(Person2 o) {
        return o.job.compareTo(this.job);
    }
}
class Emp2{
    String name;
    int id;
    String job;
    Emp2(String name,String job,int id){
        this.id=id;
        this.name=name;
        this.job=job;
    }

    @Override
    public String toString() {
        return "name: "+name+" id:"+id+" Job:"+job;
    }


}