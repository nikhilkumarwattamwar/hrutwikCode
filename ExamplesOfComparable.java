package GitFolder.hrutwikCode;

import java.util.*;

public class ExamplesOfComparable {
    public static void main(String[] args) {
        List<Employee> empList=new ArrayList<>();
        empList.add( new Employee("Vinod",3));
        empList.add( new Employee("Vipul",4));
        empList.add( new Employee("Aikas",356));
        empList.add( new Employee("Z",1));

        Collections.sort(empList);
        System.out.println(empList);

        Set<Employee> employeeSet=new HashSet<>();
        employeeSet.add( new Employee("Vinod",4));
        employeeSet.add( new Employee("Vipul",2));
        employeeSet.add( new Employee("Vikas",9));
        employeeSet.add( new Employee("Z",1));

       /* Collections.sort(employeeSet); We cannot Because Collections.sort() only works on a List, not on a Set. */
        System.out.println(employeeSet);

        Map<Employee, Integer> empMap = new TreeMap<>();
        empMap.put( new Employee("Vinod", 4),101);
        empMap.put( new Employee("Vikas", 9),105);
        empMap.put( new Employee("Z", 1),102);
        empMap.put( new Employee("Aniket", 2),103);

        for (Map.Entry< Employee,Integer> entry : empMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

    }
}
class Employee implements Comparable<Employee>{


    @Override
    public int compareTo(Employee o) {
        return o.name.compareTo(this.name);
    }

    String name;
    int  id;

    Employee(String  name, int id){
        this.id=id;
        this.name=name;
    }
    @Override
    public String toString() {
        return "Name-"+name+ ":Id-"+id;
    }
}
