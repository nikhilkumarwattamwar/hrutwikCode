package GitFolder.hrutwikCode.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomComparator {

    public static void main(String[] args) {
        Comparator<Employee> e1=new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.designation.compareTo(o2.designation);
            }
        };
        TreeMap<Employee,Integer> m=new TreeMap<>(e1);
        m.put(new Employee("Alice","Manager"),45);
        m.put(new Employee("Anna","Cook"),456);
        m.put(new Employee("Croco","Assistance"),455);
        m.put(new Employee("AA","Assistance"),4415);

        for (Map.Entry<Employee,Integer>map:m.entrySet()){
            System.out.println("NAme : "+map.getKey().name+" Job:"+map.getKey().designation+" Points:"+map.getValue());
        }
        System.out.println(  m.get(new Employee("AA","Assistance")));
    }
}

class Employee{
    String name;
    String designation;

    Employee(String name,String designation ){
        this.name =name;
        this.designation=designation;
    }

    @Override
    public String toString() {
        return name+" "+designation;
    }
}
